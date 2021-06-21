package org.armacraft.bases.client;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.armacraft.bases.IModDist;
import org.armacraft.bases.util.RaytracingUtil;
import org.armacraft.bases.world.item.StructureItem;

public class ClientDist implements IModDist {

    public ClientDist() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void handleTick(TickEvent.ClientTickEvent event) {
        PlayerEntity player = Minecraft.getInstance().player;
        if(player != null && player.isAddedToWorld()) {
            if(player.getItemInHand(Hand.MAIN_HAND).getItem() instanceof StructureItem) {
                StructureItem item = (StructureItem) player.getItemInHand(Hand.MAIN_HAND).getItem();
                RaytracingUtil.rayTraceBlocks(player, RayTraceContext.FluidMode.NONE, 5D, System.currentTimeMillis()).ifPresent(result -> {
                    //TODO RENDER STRUCTURE PREVIEW WITH OPENGL
                });
            }
        }
    }


}
