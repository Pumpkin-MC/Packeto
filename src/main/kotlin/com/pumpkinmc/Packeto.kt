package com.pumpkinmc

import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper
import net.fabricmc.fabric.api.client.rendering.v1.HudLayerRegistrationCallback
import net.fabricmc.fabric.api.client.rendering.v1.IdentifiedLayer
import net.minecraft.client.MinecraftClient
import net.minecraft.client.option.KeyBinding
import net.minecraft.client.util.InputUtil
import net.minecraft.text.Text
import net.minecraft.util.Colors
import net.minecraft.util.Identifier
import org.lwjgl.glfw.GLFW
import org.slf4j.LoggerFactory


object Packeto : ModInitializer {
    private val logger = LoggerFactory.getLogger("packeto")
    private lateinit var key_bind: KeyBinding
    var stopped = false


    override fun onInitialize() {
        HudLayerRegistrationCallback.EVENT.register { layer ->
            layer.addLayer(IdentifiedLayer.of(Identifier.of("packeto", "packets"), Screen))
        }
        key_bind = KeyBindingHelper.registerKeyBinding(
            KeyBinding(
                "key.packeto.stop",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_K,
                "category.packeto"
            )
        )
        ClientTickEvents.END_CLIENT_TICK.register(ClientTickEvents.EndTick { client: MinecraftClient ->
            while (key_bind.wasPressed()) {
                stopped = !stopped
                if (stopped) {
                    client.player!!.sendMessage(
                        Text.literal("Stopped listening for Packets").withColor(Colors.RED),
                        true
                    )
                } else {
                    client.player!!.sendMessage(
                        Text.literal("Started listening for Packets").withColor(Colors.GREEN),
                        true
                    )
                }
            }
        })
    }

}