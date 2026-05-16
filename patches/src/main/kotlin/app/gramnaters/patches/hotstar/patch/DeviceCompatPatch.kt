package app.gramnaters.patches.hotstar.patch

import app.gramnaters.patches.hotstar.utils.Constants
import app.morphe.patcher.patch.resourcePatch
import org.w3c.dom.Element

val deviceCompatPatch = resourcePatch(
    name = "Device compatibility",
    description = "Fixes split APK attributes and native library extraction for non-split installs",
    default = true,
) {
    compatibleWith(Constants.COMPATIBILITY_HOTSTAR)
    execute {
        document("AndroidManifest.xml").use { document ->
            val manifest = document.getElementsByTagName("manifest").item(0) as Element

            val splitAttrs = listOf(
                "android:isSplitRequired",
                "android:requiredSplitTypes",
                "android:splitTypes",
                "android:intentMatchingFlags",
            )
            for (attr in splitAttrs) {
                if (manifest.hasAttribute(attr)) {
                    manifest.removeAttribute(attr)
                }
            }

            val metaDataNodes = manifest.getElementsByTagName("meta-data")
            for (i in 0 until metaDataNodes.length) {
                val meta = metaDataNodes.item(i) as Element
                if (meta.getAttribute("android:name") == "com.android.vending.splits") {
                    meta.parentNode?.removeChild(meta)
                }
            }

            val applicationNodes = document.getElementsByTagName("application")
            if (applicationNodes.length > 0) {
                val application = applicationNodes.item(0) as Element
                application.setAttribute("android:extractNativeLibs", "true")
            }

            val allElements = document.getElementsByTagName("*")
            for (i in 0 until allElements.length) {
                val el = allElements.item(i) as Element
                if (el.getAttribute("android:drawable") == "@null") {
                    el.setAttribute("android:drawable", "@android:color/transparent")
                }
            }
        }
    }
}
