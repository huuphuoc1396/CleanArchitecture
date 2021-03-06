// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        jcenter()
        maven {
            url = uri(Url.jitPack)
        }
    }
    dependencies {
        classpath(BuildPlugins.android)
        classpath(BuildPlugins.kotlin)
        classpath(BuildPlugins.navigation)
        classpath(BuildPlugins.googleServices)
        classpath(BuildPlugins.firebaseCrashlytics)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

apply {
    from("jacoco.gradle")
    from("dependency_graph.gradle")
}

allprojects {
    repositories {
        google()
        jcenter()
        maven {
            url = uri(Url.jitPack)
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
