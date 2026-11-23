package com.lfester.fastbreak

import java.lang.reflect.Field
import java.lang.reflect.Method

/**
 * Resolve both Forge's runtime SRG names and development names. Minecraft
 * assigns the delay after breaking a non-instant block, so clearing at the end
 * of every client tick handles that case as well as instant mining.
 */
internal class BlockDelayResetter(minecraft: Class<*>, controller: Class<*>) {
    private val getMinecraft: Method =
        findMethod(minecraft, "func_71410_x", "getMinecraft")
    private val playerController: Field =
        findField(minecraft, "field_71442_b", "playerController")
    private val blockHitDelay: Field =
        findField(controller, "field_78781_i", "blockHitDelay")

    fun clear() {
        val game = getMinecraft.invoke(null) ?: return
        val controller = playerController.get(game) ?: return
        blockHitDelay.setInt(controller, 0)
    }

    private fun findMethod(type: Class<*>, vararg names: String): Method {
        for (name in names) {
            try {
                return type.getDeclaredMethod(name).apply { isAccessible = true }
            } catch (_: NoSuchMethodException) {
                // Try the next mapping.
            }
        }
        throw NoSuchMethodException("${type.name}: ${names.joinToString()}")
    }

    private fun findField(type: Class<*>, vararg names: String): Field {
        for (name in names) {
            try {
                return type.getDeclaredField(name).apply { isAccessible = true }
            } catch (_: NoSuchFieldException) {
                // Try the next mapping.
            }
        }
        throw NoSuchFieldException("${type.name}: ${names.joinToString()}")
    }
}
