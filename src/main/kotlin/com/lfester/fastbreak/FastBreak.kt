package com.lfester.fastbreak

import cpw.mods.fml.common.FMLCommonHandler
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.fml.common.gameevent.TickEvent
import java.util.logging.Level
import java.util.logging.Logger

@Mod(
    modid = "gtnhfastbreak",
    name = "GTNH Fast Break",
    version = "1.0.0",
    acceptedMinecraftVersions = "[1.7.10]",
    acceptableRemoteVersions = "*"
)
class FastBreak {
    private val logger = Logger.getLogger("GTNH Fast Break")
    private var resetter: BlockDelayResetter? = null

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        if (!event.side.isClient) return
        try {
            resetter = BlockDelayResetter(
                Class.forName("net.minecraft.client.Minecraft"),
                Class.forName("net.minecraft.client.multiplayer.PlayerControllerMP")
            )
            FMLCommonHandler.instance().bus().register(this)
            logger.info("Enabled client-side block-break delay removal.")
        } catch (error: ReflectiveOperationException) {
            logger.log(Level.SEVERE, "Could not locate the mining delay; fast break is disabled.", error)
        }
    }

    @SubscribeEvent
    fun onClientTick(event: TickEvent.ClientTickEvent) {
        if (event.phase != TickEvent.Phase.END) return
        try {
            resetter?.clear()
        } catch (error: ReflectiveOperationException) {
            resetter = null
            logger.log(Level.SEVERE, "Could not reset the mining delay; fast break is disabled.", error)
        }
    }
}
