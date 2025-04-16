package com.pumpkinmc

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer
import net.minecraft.util.Identifier
import org.slf4j.LoggerFactory

object Packeto : ModInitializer {
    private val logger = LoggerFactory.getLogger("packeto")

    override fun onInitialize() {
        HudLayerRegistrationCallback.EVENT.register { layer ->
            layer.addLayer(IdentifiedLayer.of(Identifier.of("packeto", "packets"), Screen))
        }
    }
}