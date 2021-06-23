package org.armacraft.bases.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.player.ClientPlayerEntity;
import net.minecraft.client.renderer.BlockRendererDispatcher;
import net.minecraft.client.renderer.RenderSkybox;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexBuffer;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.AxisAlignedBB;
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

import static org.lwjgl.opengl.GL11.*;

public class ClientDist implements IModDist {

    public ClientDist() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void handleRender(TickEvent.ClientTickEvent event) {
        PlayerEntity player = Minecraft.getInstance().player;
        if (player != null && player.isAddedToWorld()) {
            if (player.getItemInHand(Hand.MAIN_HAND).getItem() instanceof StructureItem) {
                StructureItem item = (StructureItem) player.getItemInHand(Hand.MAIN_HAND).getItem();
                RaytracingUtil.rayTraceBlocks(player, RayTraceContext.FluidMode.NONE, 5D, System.currentTimeMillis()).ifPresent(result -> {
                    glPushMatrix();
                    glEnable(GL_BLEND);
                    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
                    glEnable(GL_LINE_SMOOTH);
                    glLineWidth(2);
                    glDisable(GL_TEXTURE_2D);
                    glEnable(GL_CULL_FACE);
                    glDisable(GL_DEPTH_TEST);
                    item.getStructureTemplate().apply(result.getBlockPos(), item.getDirection()).forEach(pos -> {
                        Vector3d projectedView = Minecraft.getInstance().gameRenderer.getMainCamera().getPosition();
                        double renderPosX = projectedView.x;
                        double renderPosY = projectedView.y;
                        double renderPosZ = projectedView.z;

                        glTranslated(-renderPosX, -renderPosY, -renderPosZ);
                        glTranslated(pos.getX(), pos.getY(), pos.getZ());

                        glColor4f(132, 255, 0, 1.0F);
                        defaultOutlineGL(new AxisAlignedBB(0, 0, 0, 1, 1, 1));

                        glColor4f(1, 1, 1, 1);

                        glEnable(GL_DEPTH_TEST);
                        glEnable(GL_TEXTURE_2D);
                        glDisable(GL_BLEND);
                        glDisable(GL_LINE_SMOOTH);
                        glPopMatrix();
                    });
                });
            }
        }
    }

    public static void defaultOutlineGL(AxisAlignedBB bb) {

        glBegin(GL_LINES);
        glVertex3d(bb.minX, bb.minY, bb.minZ);
        glVertex3d(bb.maxX, bb.minY, bb.minZ);

        glVertex3d(bb.maxX, bb.minY, bb.minZ);
        glVertex3d(bb.maxX, bb.minY, bb.maxZ);

        glVertex3d(bb.maxX, bb.minY, bb.maxZ);
        glVertex3d(bb.minX, bb.minY, bb.maxZ);

        glVertex3d(bb.minX, bb.minY, bb.maxZ);
        glVertex3d(bb.minX, bb.minY, bb.minZ);

        glVertex3d(bb.minX, bb.minY, bb.minZ);
        glVertex3d(bb.minX, bb.maxY, bb.minZ);

        glVertex3d(bb.maxX, bb.minY, bb.minZ);
        glVertex3d(bb.maxX, bb.maxY, bb.minZ);

        glVertex3d(bb.maxX, bb.minY, bb.maxZ);
        glVertex3d(bb.maxX, bb.maxY, bb.maxZ);

        glVertex3d(bb.minX, bb.minY, bb.maxZ);
        glVertex3d(bb.minX, bb.maxY, bb.maxZ);

        glVertex3d(bb.minX, bb.maxY, bb.minZ);
        glVertex3d(bb.maxX, bb.maxY, bb.minZ);

        glVertex3d(bb.maxX, bb.maxY, bb.minZ);
        glVertex3d(bb.maxX, bb.maxY, bb.maxZ);

        glVertex3d(bb.maxX, bb.maxY, bb.maxZ);
        glVertex3d(bb.minX, bb.maxY, bb.maxZ);

        glVertex3d(bb.minX, bb.maxY, bb.maxZ);
        glVertex3d(bb.minX, bb.maxY, bb.minZ);
        glEnd();
    }


}
