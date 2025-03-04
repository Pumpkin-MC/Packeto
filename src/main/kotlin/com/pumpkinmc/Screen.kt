package com.pumpkinmc

import io.netty.buffer.ByteBuf
import net.minecraft.client.MinecraftClient
import net.minecraft.client.gui.DrawContext
import net.minecraft.client.gui.LayeredDrawer
import net.minecraft.client.render.RenderTickCounter
import net.minecraft.network.NetworkPhase
import net.minecraft.network.NetworkState
import net.minecraft.network.listener.PacketListener
import net.minecraft.network.packet.Packet
import net.minecraft.network.state.*
import net.minecraft.util.Colors
import java.util.Collections
import java.util.concurrent.CopyOnWriteArrayList
import java.util.function.Function
import java.util.stream.Collectors
import java.util.stream.Stream

object Screen : LayeredDrawer.Layer {
    var sent_packets: MutableList<Packet<*>> = CopyOnWriteArrayList();
    var received_packets: MutableList<Packet<*>> = CopyOnWriteArrayList();

    override fun render(context: DrawContext, tickCounter: RenderTickCounter) {
        context.drawText(
            MinecraftClient.getInstance().textRenderer,
            "Sent Packets",
            0,
            0,
            Colors.RED,
            false
        );
        if (sent_packets.size >= 20) sent_packets.removeFirst();
        var i = 6;
        for (packet in sent_packets) {
            i += 10;
            context.drawText(
                MinecraftClient.getInstance().textRenderer,
                packet.packetType.id.path,
                0,
                i,
                Colors.WHITE,
                false
            );
        }
        context.drawText(
            MinecraftClient.getInstance().textRenderer,
            "Received Packets",
            context.scaledWindowWidth - 110,
            0,
            Colors.RED,
            false
        );
        if (received_packets.size >= 20) received_packets.removeFirst();
        var x = 6;
        for (packet in received_packets) {
            x += 10;
            context.drawText(
                MinecraftClient.getInstance().textRenderer,
                packet.packetType.id.path,
                context.scaledWindowWidth - 110,
                x,
                Colors.WHITE,
                false
            );
        }
    }
}