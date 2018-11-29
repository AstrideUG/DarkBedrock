subprojects {

    group = "de.astride.darkbedrock.apis.annotatedcommands"

    dependencies {
        if (this@subprojects != findProject(":APIs:AnnotatedCommands:annotatedcommands-api"))
            compile(project(":APIs:AnnotatedCommands:annotatedcommands-api"))
        compile("com.google.inject", "guice", extra["versions.guice"].toString())
    }

    tasks.withType<Jar> {
        //        baseName = "AnnotatedCommands-${this@subprojects.name.toUpperCase()}"
    }

}
