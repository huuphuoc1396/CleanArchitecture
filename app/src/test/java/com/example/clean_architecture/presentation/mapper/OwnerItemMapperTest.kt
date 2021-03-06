package com.example.clean_architecture.presentation.mapper

import com.example.clean_architecture.domain.model.Owner
import com.example.clean_architecture.presentation.feature.main.mapper.OwnerItemMapper
import com.example.clean_architecture.presentation.feature.main.model.OwnerItem
import com.tngtech.java.junit.dataprovider.DataProvider
import com.tngtech.java.junit.dataprovider.DataProviderRunner
import com.tngtech.java.junit.dataprovider.UseDataProvider
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(DataProviderRunner::class)
class OwnerItemMapperTest {

    private val ownerItemMapper =
        OwnerItemMapper()

    @Test
    @UseDataProvider("dataProvider")
    fun map(owner: Owner, expected: OwnerItem) {
        val actual = ownerItemMapper.map(owner)
        Assert.assertEquals(expected, actual)
    }

    companion object {
        @JvmStatic
        @DataProvider
        fun dataProvider() = listOf(
            listOf(
                Owner(
                    id = 82128465,
                    avatarUrl = "https://avatars2.githubusercontent.com/u/23095877?v=4",
                    login = "open-android"
                ),
                OwnerItem(
                    id = 82128465,
                    avatarUrl = "https://avatars2.githubusercontent.com/u/23095877?v=4",
                    login = "open-android"
                )
            )
        )
    }
}