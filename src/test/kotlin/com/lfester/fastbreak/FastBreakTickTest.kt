package com.lfester.fastbreak

import cpw.mods.fml.common.gameevent.TickEvent
import kotlin.test.Test
import kotlin.test.assertEquals

class FastBreakTickTest {
    @Test
    fun `clears only at the end of the client tick`() {
        val game = BlockDelayResetterTest.SrgMinecraft()
        val controller = BlockDelayResetterTest.SrgController()
        game.field_71442_b = controller
        BlockDelayResetterTest.SrgMinecraft.instance = game

        val mod = FastBreak()
        FastBreak::class.java.getDeclaredField("resetter").apply {
            isAccessible = true
            set(mod, BlockDelayResetter(game.javaClass, controller.javaClass))
        }

        mod.onClientTick(TickEvent.ClientTickEvent(TickEvent.Phase.START))
        assertEquals(5, controller.field_78781_i)
        mod.onClientTick(TickEvent.ClientTickEvent(TickEvent.Phase.END))
        assertEquals(0, controller.field_78781_i)
    }
}
