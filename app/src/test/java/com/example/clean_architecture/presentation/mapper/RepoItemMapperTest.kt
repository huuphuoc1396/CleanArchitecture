package com.example.clean_architecture.presentation.mapper

import com.example.clean_architecture.presentation.model.OwnerItem
import com.example.clean_architecture.presentation.model.RepoItem
import com.example.common_unit_test.makeRandomInstance
import com.example.domain.model.Owner
import com.example.domain.model.Repo
import com.tngtech.java.junit.dataprovider.DataProvider
import com.tngtech.java.junit.dataprovider.DataProviderRunner
import com.tngtech.java.junit.dataprovider.UseDataProvider
import io.mockk.every
import io.mockk.mockk
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(DataProviderRunner::class)
class RepoItemMapperTest {

    private val ownerItemMapper: OwnerItemMapper = mockk()

    private val repoItemMapper = RepoItemMapper(
        ownerItemMapper = ownerItemMapper
    )

    @Test
    @UseDataProvider("dataProvider")
    fun map(repo: Repo, expected: RepoItem) {
        every {
            ownerItemMapper.map(repo.owner)
        } returns ownerItem
        val actual = repoItemMapper.map(repo)
        Assert.assertEquals(expected, actual)
    }

    companion object {
        private val owner: Owner = makeRandomInstance()
        private val ownerItem: OwnerItem = makeRandomInstance()

        @JvmStatic
        @DataProvider
        fun dataProvider() = listOf(
            listOf(
                Repo(
                    id = 82128465,
                    name = "Android",
                    description = "Android App Example",
                    owner = owner
                ),
                RepoItem(
                    id = 82128465,
                    name = "Android",
                    description = "Android App Example",
                    owner = ownerItem
                )
            )
        )
    }
}