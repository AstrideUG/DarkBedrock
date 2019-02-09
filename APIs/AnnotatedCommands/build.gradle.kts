/*
 * © Copyright - Lars Artmann aka. LartyHD 2018.
 */

subprojects {

    group = "${group}.annotatedcommands"

    dependencies {
        if (this@subprojects != findProject(":APIs:AnnotatedCommands:AnnotatedCommands-Api"))
            compile(project(":APIs:AnnotatedCommands:AnnotatedCommands-Api"))
        compile("com.google.inject", "guice", extra["versions.guice"].toString())
    }

    tasks.withType<Jar> {
        //        baseName = "AnnotatedCommands-${this@subprojects.name.toUpperCase()}"
    }

}