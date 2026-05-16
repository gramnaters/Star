package app.gramnaters.patches.hotstar.utils

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.string
import com.android.tools.smali.dexlib2.Opcode

object Fingerprints {
    val ApplicationOnCreate = object : Fingerprint(
        returnType = "V",
        name = "onCreate",
        filters = listOf(
            string("onCreate"),
        ),
    ) {}

    val UserTokenGetter = object : Fingerprint(
        returnType = "Ljava/lang/Object;",
        strings = listOf("getUserTokenValue"),
        filters = listOf(
            string("getUserTokenValue"),
        ),
    ) {}

    val MediaTokenGetter = object : Fingerprint(
        returnType = "Ljava/lang/Object;",
        strings = listOf("getMediaTokenValue"),
        filters = listOf(
            string("getMediaTokenValue"),
        ),
    ) {}
}
