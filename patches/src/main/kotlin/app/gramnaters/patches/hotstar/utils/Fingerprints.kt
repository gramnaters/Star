package app.gramnaters.patches.hotstar.utils

import app.morphe.patcher.Fingerprint
import app.morphe.patcher.Opcode

object Fingerprints {
    val ApplicationOnCreate = Fingerprint(
        returnType = "V",
        opcodes = listOf(
            Opcode.INVOKE_SUPER,
            Opcode.NEW_INSTANCE,
        ),
        methodName = "onCreate",
        parameters = listOf(),
    )

    val UserTokenGetter = Fingerprint(
        returnType = "Ljava/lang/Object;",
        opcodes = listOf(
            Opcode.IGET_OBJECT,
            Opcode.IF_NEZ,
        ),
        strings = listOf("getUserTokenValue"),
    )

    val MediaTokenGetter = Fingerprint(
        returnType = "Ljava/lang/Object;",
        opcodes = listOf(
            Opcode.IGET_OBJECT,
            Opcode.IF_NEZ,
        ),
        strings = listOf("getMediaTokenValue"),
    )
}
