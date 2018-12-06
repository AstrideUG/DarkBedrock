/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

subprojects {

    group = "de.astride.darkbedrock.apis.events"

    dependencies {
        if (this@subprojects != findProject(":APIs:Events:Events-Api"))
            compile(project(":APIs:Events:Events-Api"))
        compile("net.kyori:event-method-asm:3.0.0")
//        compile("com.google.guava", "guava", "27.0-jre")
//        compile("com.google.code.gson", "gson", "2.8.5")
//        compile("com.google.inject", "guice", extra["versions.guice"].toString())
    }


}
