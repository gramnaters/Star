package app.gramnaters.patches.hotstar

import app.gramnaters.patches.hotstar.patch.applicationHookPatch
import app.gramnaters.patches.hotstar.patch.deviceCompatPatch
import app.gramnaters.patches.hotstar.patch.identityRepositoryPatch
import app.gramnaters.patches.hotstar.patch.settingsPatch
import app.morphe.patcher.patch.bytecodePatch

val jioHotstarPatch = bytecodePatch(
    name = "JioHotstar Premium",
    description = "All-in-one patch for JioHotstar: device compatibility + cookie injection + identity repository patching",
    default = true,
) {
    dependsOn(
        settingsPatch,
        deviceCompatPatch,
        applicationHookPatch,
        identityRepositoryPatch,
    )
    execute {
        // All work done by dependency patches
    }
}
