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
        description = "Generate patches-list.json"
        classpath = sourceSets.main.get().runtimeClasspath
        mainClass = "app.gramnaters.patches.hotstar.utils.PatchListGenerator"
        workingDir = rootProject.projectDir
    }
}

kotlin {
    compilerOptions {
        freeCompilerArgs = listOf("-Xcontext-parameters")
    }
}
