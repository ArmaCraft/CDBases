package org.armacraft.bases.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import com.mojang.math.Matrix4f;
import com.mojang.math.Vector3f;
import com.mojang.math.Vector4f;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.ShaderInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.client.event.RenderLevelLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.armacraft.bases.IModDist;
import org.armacraft.bases.util.RaytracingUtil;
import org.armacraft.bases.world.item.StructureItem;
import org.lwjgl.opengl.GL11;

public class ClientDist implements IModDist {

    public ClientDist() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void handleWorldRender(RenderLevelLastEvent event) {
        LocalPlayer player = Minecraft.getInstance().player;
        if (player != null && player.isAddedToWorld()) {
            if (player.getItemInHand(InteractionHand.MAIN_HAND).getItem() instanceof StructureItem) {
                StructureItem item = (StructureItem) player.getItemInHand(InteractionHand.MAIN_HAND).getItem();
                RaytracingUtil.rayTraceBlocks(player, ClipContext.Fluid.NONE, 5D, System.currentTimeMillis()).ifPresent(result -> {
                    Vector3f color = item.getStructureTemplate().available(new BlockPos(result.getLocation()), item.getDirection(), player.getLevel())
                            ? new Vector3f(1, 1, 1)
                            : new Vector3f(1, 0, 0);
                    item.getStructureTemplate().apply(new BlockPos(result.getLocation()), item.getDirection()).forEach(pos -> {
                        drawBox(event.getPoseStack(), event.getProjectionMatrix(), pos, event.getPartialTick(), color.x(), color.y(), color.z(), 1);
                    });
                });
            }
        }
    }

    public static void drawBox(PoseStack matrix, Matrix4f projectionMatrix, BlockPos where, float partialTicks, float r, float g, float b, float a) {
        Vec3 view = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
        LocalPlayer player = Minecraft.getInstance().player;

        RenderSystem.depthMask(false);
        RenderSystem.disableCull();
        RenderSystem.enableBlend();
        RenderSystem.defaultBlendFunc();
        RenderSystem.disableTexture();
        RenderSystem.lineWidth(5f);
        GL11.glEnable(GL11.GL_LINE_SMOOTH);

        double beginX = where.getX(), beginY = where.getY(), beginZ = where.getZ();

        GL11.glDisable(GL11.GL_DEPTH_TEST);

        BufferBuilder buffer = Tesselator.getInstance().getBuilder();
        VertexBuffer vertexBuffer = new VertexBuffer();

        buffer.begin(VertexFormat.Mode.DEBUG_LINES, DefaultVertexFormat.POSITION_COLOR);
        buffer.vertex(beginX, beginY, beginZ).color(r, g, b, a).endVertex();
        buffer.vertex(beginX+1, beginY, beginZ).color(r, g, b, a).endVertex();

        buffer.vertex(beginX+1, beginY+1, beginZ).color(r, g, b, a).endVertex();
        buffer.vertex(beginX, beginY+1, beginZ).color(r, g, b, a).endVertex();

        buffer.vertex(beginX, beginY, beginZ).color(r, g, b, a).endVertex();
        buffer.vertex(beginX, beginY+1, beginZ).color(r, g, b, a).endVertex();

        buffer.vertex(beginX+1, beginY+1, beginZ).color(r, g, b, a).endVertex();
        buffer.vertex(beginX+1, beginY, beginZ).color(r, g, b, a).endVertex();

        buffer.vertex(beginX, beginY, beginZ+1).color(r, g, b, a).endVertex();
        buffer.vertex(beginX+1, beginY, beginZ+1).color(r, g, b, a).endVertex();

        buffer.vertex(beginX+1, beginY+1, beginZ+1).color(r, g, b, a).endVertex();
        buffer.vertex(beginX, beginY+1, beginZ+1).color(r, g, b, a).endVertex();

        buffer.vertex(beginX, beginY, beginZ+1).color(r, g, b, a).endVertex();
        buffer.vertex(beginX, beginY+1, beginZ+1).color(r, g, b, a).endVertex();

        buffer.vertex(beginX+1, beginY+1, beginZ+1).color(r, g, b, a).endVertex();
        buffer.vertex(beginX+1, beginY, beginZ+1).color(r, g, b, a).endVertex();

        buffer.vertex(beginX, beginY, beginZ).color(r, g, b, a).endVertex();
        buffer.vertex(beginX, beginY, beginZ+1).color(r, g, b, a).endVertex();

        buffer.vertex(beginX+1, beginY, beginZ).color(r, g, b, a).endVertex();
        buffer.vertex(beginX+1, beginY, beginZ+1).color(r, g, b, a).endVertex();

        buffer.vertex(beginX, beginY+1, beginZ+1).color(r, g, b, a).endVertex();
        buffer.vertex(beginX, beginY+1, beginZ).color(r, g, b, a).endVertex();

        buffer.vertex(beginX+1, beginY+1, beginZ).color(r, g, b, a).endVertex();
        buffer.vertex(beginX+1, beginY+1, beginZ+1).color(r, g, b, a).endVertex();

        buffer.end();
        vertexBuffer.bind();
        vertexBuffer.upload(buffer);

        matrix.pushPose();
        matrix.translate(-view.x, -view.y, -view.z);
        ShaderInstance shader = GameRenderer.getPositionColorShader();
        vertexBuffer.drawWithShader(matrix.last().pose(), projectionMatrix.copy(), shader);
        matrix.popPose();

        VertexBuffer.unbind();
    }


}
