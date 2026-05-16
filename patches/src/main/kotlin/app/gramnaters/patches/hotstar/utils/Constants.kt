package app.gramnaters.patches.hotstar.utils

import app.morphe.patcher.patch.ApkFileType
import app.morphe.patcher.patch.AppTarget
import app.morphe.patcher.patch.Compatibility

object Constants {
    const val PACKAGE_NAME = "in.startv.hotstar"
    const val APP_NAME = "JioHotstar"
    const val EXTENSION_DEX = "hotstar.mpe"

    val COMPATIBILITY_HOTSTAR =
        Compatibility(
            name = APP_NAME,
            packageName = PACKAGE_NAME,
            apkFileType = ApkFileType.APKM,
            appIconColor = 0xE53935,
            signatures = null,
            targets = listOf(
                AppTarget(version = "24.04.27.10"),
                AppTarget(version = "24.03.28.10"),
                AppTarget(version = "24.02.26.10"),
                AppTarget(version = "24.01.25.10"),
            ),
        )
}
