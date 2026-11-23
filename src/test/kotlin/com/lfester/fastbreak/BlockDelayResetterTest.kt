package com.lfester.fastbreak

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class BlockDelayResetterTest {
    class SrgController(@JvmField var field_78781_i: Int = 5)

    class SrgMinecraft {
        @JvmField var field_71442_b: SrgController? = null

        companion object {
            @JvmField var instance: SrgMinecraft? = SrgMinecraft()
            @JvmStatic fun func_71410_x(): SrgMinecraft? = instance
        }
    }

    class DevController(@JvmField var blockHitDelay: Int = 5)

    class DevMinecraft {
        @JvmField var playerController: DevController? = DevController()

        companion object {
            @JvmField var instance = DevMinecraft()
            @JvmStatic fun getMinecraft(): DevMinecraft = instance
        }
    }

    @Test
    fun `clears runtime delay and handles menu and controller replacement`() {
        val resetter = BlockDelayResetter(SrgMinecraft::class.java, SrgController::class.java)
        SrgMinecraft.instance = null
        resetter.clear()
        SrgMinecraft.instance = SrgMinecraft()
        resetter.clear()
        val first = SrgController()
        SrgMinecraft.instance!!.field_71442_b = first
        resetter.clear()
        assertEquals(0, first.field_78781_i)
        val replacement = SrgController()
        SrgMinecraft.instance!!.field_71442_b = replacement
        resetter.clear()
        assertEquals(0, replacement.field_78781_i)
    }

    @Test
    fun `supports development mappings`() {
        val resetter = BlockDelayResetter(DevMinecraft::class.java, DevController::class.java)
        resetter.clear()
        assertEquals(0, DevMinecraft.instance.playerController!!.blockHitDelay)
    }

    @Test
    fun `reports unsupported mappings`() {
        assertFailsWith<NoSuchMethodException> {
            BlockDelayResetter(Any::class.java, DevController::class.java)
        }
    }
}
