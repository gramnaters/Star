group = "gramnaters"

patches {
    about {
        name = "Star"
        description = "Morphe patches for JioHotstar (Premium cookie injection)"
        source = "git@github.com:gramnaters/Star.git"
        author = "gramnaters"
        contact = "https://github.com/gramnaters/Star"
        website = "https://github.com/gramnaters/Star"
        license = "GNU General Public License v3.0"
    }
}

dependencies {
    implementation(libs.gson)
    implementation(libs.morphe.patches.library)
}

tasks {
    register<JavaExec>("generatePatchesList") {
        description = "Build patch with patch list"

        dependsOn(build, "buildAndroid")

        classpath = sourceSets["main"].runtimeClasspath
        mainClass.set("app.morphe.util.PatchListGeneratorKt")
    }
    publish {
        dependsOn("generatePatchesList")
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs = listOf("-Xcontext-parameters")
    }
}
