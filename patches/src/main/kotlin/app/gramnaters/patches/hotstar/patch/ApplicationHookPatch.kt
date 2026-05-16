package app.gramnaters.patches.hotstar.patch

import app.gramnaters.patches.hotstar.utils.Constants
import app.gramnaters.patches.hotstar.utils.Fingerprints
import app.morphe.patcher.extensions.InstructionExtensions.addInstruction
import app.morphe.patcher.patch.bytecodePatch

val applicationHookPatch = bytecodePatch(
    name = "Cookie seeding",
    description = "Hooks Application.onCreate to read cookies from assets and cache them for token injection",
    default = true,
) {
    compatibleWith(Constants.COMPATIBILITY_HOTSTAR)
    dependsOn(settingsPatch)
    execute {
        Fingerprints.ApplicationOnCreate.method.addInstruction(
            0,
            "invoke-static {p0}, Lapp/gramnaters/hotstar/CookieSeeder;->seedIfNeeded(Landroid/content/Context;)V",
        )
    }
}
