package app.gramnaters.patches.hotstar.patch

import app.gramnaters.patches.hotstar.utils.Constants
import app.morphe.patcher.patch.resourcePatch

val deviceCompatPatch = resourcePatch(
    name = "Device compatibility",
    description = "Fixes split APK attributes and native library extraction for non-split installs",
    default = true,
) {
    compatibleWith(Constants.COMPATIBILITY_HOTSTAR)
    execute { document ->
        val manifest = document.manifest

        // Remove split-related attributes that prevent installation on some devices
        val splitAttrs = listOf(
            "android:isSplitRequired",
            "android:requiredSplitTypes",
            "android:splitTypes",
            "android:intentMatchingFlags",
        )
        for (attr in splitAttrs) {
            val nodes = document.allNodes { it.hasAttr(attr) }
            for (node in nodes) {
                node.removeAttr(attr)
            }
        }

        // Remove splits meta-data entry
        val metaNodes = document.allNodes {
            it.tagName == "meta-data" &&
            it.attr("android:name") == "com.android.vending.splits"
        }
        for (node in metaNodes) {
            node.remove()
        }

        // Set extractNativeLibs = true (required for merged split APKs)
        val applicationNode = document.allNodes { it.tagName == "application" }.firstOrNull()
        if (applicationNode != null) {
            applicationNode.attr("android:extractNativeLibs", "true")
        }

        // Fix @null drawables
        val nullDrawables = document.allNodes {
            it.attr("android:drawable") == "@null"
        }
        for (node in nullDrawables) {
            node.attr("android:drawable", "@android:color/transparent")
        }
    }
}
