package app.gramnaters.patches.hotstar.patch

import app.gramnaters.patches.hotstar.utils.Constants
import app.morphe.patcher.patch.bytecodePatch

val settingsPatch = bytecodePatch(
    name = "Settings",
    description = "Adds settings to control patch preferences",
    default = true,
) {
    compatibleWith(Constants.COMPATIBILITY_HOTSTAR)
    execute {
        // Settings are managed via SharedPreferences injected at runtime
    }
}
