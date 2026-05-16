package app.gramnaters.patches.hotstar.utils

object Constants {
    const val PACKAGE_NAME = "in.startv.hotstar"
    const val APP_NAME = "JioHotstar"
    const val EXTENSION_DEX = "hotstar.mpe"

    val COMPATIBILITY_HOTSTAR = arrayOf(
        app.morphe.patcher.patch.CompatiblePackage(
            PACKAGE_NAME,
            targets = listOf(
                app.morphe.patcher.patch.PackageTarget(
                    versionRange = app.morphe.patcher.patch.VersionRange(
                        min = "11.0.0",
                        max = "99.99.99"
                    )
                )
            )
        )
    )
}
