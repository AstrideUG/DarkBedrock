/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

subprojects {

    group = "de.astride.darkbedrock.apis.modules"

    dependencies {
        if (this@subprojects != findProject(":APIs:Modules:Modules-Api"))
            compile(project(":APIs:Modules:Modules-Api"))
        compile("com.google.guava", "guava", "27.0-jre")
        compile("com.google.code.gson", "gson", "2.8.5")
        compile("com.google.inject", "guice", extra["versions.guice"].toString())
    }


}
