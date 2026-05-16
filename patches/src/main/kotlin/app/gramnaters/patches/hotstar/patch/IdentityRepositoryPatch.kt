package app.gramnaters.patches.hotstar.patch

import app.gramnaters.patches.hotstar.utils.Constants
import app.gramnaters.patches.hotstar.utils.Fingerprints
import app.morphe.patcher.extensions.InstructionExtensions.addInstructions
import app.morphe.patcher.patch.bytecodePatch

val identityRepositoryPatch = bytecodePatch(
    name = "Identity repository injection",
    description = "Patches IdentityRepository token getters to return injected cookies when cache is empty",
    default = true,
) {
    compatibleWith(Constants.COMPATIBILITY_HOTSTAR)
    dependsOn(settingsPatch)
    execute {
        // Patch user token getter (LFf/d.c())
        Fingerprints.UserTokenGetter.method.apply {
            addInstructions(
                index = 0,
                instructions = """
                    iget-object v0, p0, LFf/d;->a:Ljava/lang/String;
                    if-nez v0, :patch_orig_c

                    invoke-static {}, Lapp/gramnaters/hotstar/CookieSeeder;->getInjectedUserToken()Ljava/lang/String;
                    move-result-object v1
                    if-nez v1, :patch_orig_c

                    iput-object v1, p0, LFf/d;->a:Ljava/lang/String;
                    return-object v1

                    :patch_orig_c
                """.trimIndent(),
            )
        }

        // Patch media token getter (LFf/d.i())
        Fingerprints.MediaTokenGetter.method.apply {
            addInstructions(
                index = 0,
                instructions = """
                    iget-object v0, p0, LFf/d;->g:Ljava/lang/String;
                    if-nez v0, :patch_orig_i

                    invoke-static {}, Lapp/gramnaters/hotstar/CookieSeeder;->getInjectedMediaToken()Ljava/lang/String;
                    move-result-object v1
                    if-nez v1, :patch_orig_i

                    iput-object v1, p0, LFf/d;->g:Ljava/lang/String;
                    return-object v1

                    :patch_orig_i
                """.trimIndent(),
            )
        }
    }
}
