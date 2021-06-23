package org.armacraft.bases.client;

import com.mojang.blaze3d.platform.GlStateManager;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.RenderSkybox;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.renderer.vertex.VertexFormat;
import net.minecraft.client.renderer.vertex.VertexFormatElement;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import net.minecraftforge.client.event.DrawHighlightEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.armacraft.bases.IModDist;
import org.armacraft.bases.util.RaytracingUtil;
import org.armacraft.bases.world.item.StructureItem;

import java.nio.ByteBuffer;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.lwjgl.opengl.GL11.*;

public class ClientDist implements IModDist {

    public ClientDist() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void handleRender(TickEvent.ClientTickEvent event) {
        /*PlayerEntity player = Minecraft.getInstance().player;
        if (player != null && player.isAddedToWorld()) {
            if (player.getItemInHand(Hand.MAIN_HAND).getItem() instanceof StructureItem) {
                StructureItem item = (StructureItem) player.getItemInHand(Hand.MAIN_HAND).getItem();
                RaytracingUtil.rayTraceBlocks(player, RayTraceContext.FluidMode.NONE, 5D, System.currentTimeMillis()).ifPresent(result -> {
                    renderStructure = true;
                    item.getStructureTemplate().apply(result.getBlockPos(), item.getDirection()).forEach(pos -> {

                    });
                });
            }
        }*/
    }

    @SubscribeEvent
    public void handleWorldRender(RenderWorldLastEvent event) {
        PlayerEntity player = Minecraft.getInstance().player;
        if (player != null && player.isAddedToWorld()) {
            if (player.getItemInHand(Hand.MAIN_HAND).getItem() instanceof StructureItem) {
                StructureItem item = (StructureItem) player.getItemInHand(Hand.MAIN_HAND).getItem();
                RaytracingUtil.rayTraceBlocks(player, RayTraceContext.FluidMode.NONE, 5D, System.currentTimeMillis()).ifPresent(result -> {
                    item.getStructureTemplate().apply(result.getBlockPos(), item.getDirection()).forEach(pos -> {
                        glPushMatrix();
                        glTranslated(-pos.getX(), -pos.getY(), -pos.getZ());
                        Tessellator tessellator = Tessellator.getInstance();
                        BufferBuilder buffer = tessellator.getBuilder();
                        buffer.begin(GL_LINES, DefaultVertexFormats.POSITION);
                        AxisAlignedBB bb = new AxisAlignedBB(0, 0, 0, 1, 1, 1);
                        buffer.vertex(bb.minX, bb.minY, bb.minZ).color(255, 255, 0, 10).endVertex();
                        buffer.vertex(bb.maxX, bb.minY, bb.minZ).color(255, 255, 0, 10).endVertex();
                        buffer.vertex(bb.maxX, bb.minY, bb.maxZ).color(255, 255, 0, 10).endVertex();
                        buffer.vertex(bb.minX, bb.minY, bb.maxZ).color(255, 255, 0, 10).endVertex();
                        buffer.vertex(bb.minX, bb.minY, bb.minZ).color(255, 255, 0, 10).endVertex();

                        buffer.vertex(bb.minX, bb.maxY, bb.minZ).color(255, 255, 0, 10).endVertex();
                        buffer.vertex(bb.maxX, bb.maxY, bb.minZ).color(255, 255, 0, 10).endVertex();
                        buffer.vertex(bb.maxX, bb.maxY, bb.maxZ).color(255, 255, 0, 10).endVertex();
                        buffer.vertex(bb.minX, bb.maxY, bb.maxZ).color(255, 255, 0, 10).endVertex();
                        buffer.vertex(bb.minX, bb.maxY, bb.minZ).color(255, 255, 0, 10).endVertex();

                        buffer.vertex(bb.minX, bb.minY, bb.minZ).color(255, 255, 0, 10).endVertex();
                        buffer.vertex(bb.minX, bb.maxY, bb.minZ).color(255, 255, 0, 10).endVertex();
                        buffer.vertex(bb.maxX, bb.minY, bb.minZ).color(255, 255, 0, 10).endVertex();
                        buffer.vertex(bb.maxX, bb.maxY, bb.minZ).color(255, 255, 0, 10).endVertex();
                        buffer.vertex(bb.maxX, bb.minY, bb.maxZ).color(255, 255, 0, 10).endVertex();
                        buffer.vertex(bb.maxX, bb.maxY, bb.maxZ).color(255, 255, 0, 10).endVertex();
                        buffer.vertex(bb.minX, bb.minY, bb.maxZ).color(255, 255, 0, 10).endVertex();
                        buffer.vertex(bb.minX, bb.maxY, bb.maxZ).color(255, 255, 0, 10).endVertex();
                        tessellator.end();
                        glPopMatrix();
                    });
                });
            }
        }
    }


}
