package org.armacraft.bases;

import io.netty.buffer.Unpooled;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraftforge.client.event.InputUpdateEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.network.PacketDistributor;
import org.armacraft.bases.client.ClientDist;
import org.armacraft.bases.server.ServerDist;
import org.armacraft.bases.world.block.ModBlocks;
import org.armacraft.bases.world.item.ModItems;
import org.armacraft.bases.world.item.StructureItem;
import org.armacraft.bases.world.structure.ModStructureTemplates;
import org.armacraft.bases.world.tileentity.ModTileEntities;

@Mod(SurvivalBases.MODID)
public class SurvivalBases {
    public static final String MODID = "survivalbases";

    private static SurvivalBases instance;

    private IModDist dist;

    public SurvivalBases() {
        instance = this;
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        MinecraftForge.EVENT_BUS.register(this);

        ModTileEntities.TILE_ENTITY_TYPES.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);

        this.dist = DistExecutor.safeRunForDist(() -> ClientDist::new, () -> ServerDist::new);
    }

    @SubscribeEvent
    public void handleRightClick(PlayerInteractEvent.RightClickBlock event) {
        PlayerEntity player = (PlayerEntity) event.getEntity();
        if(player.getItemInHand(Hand.MAIN_HAND).getItem() instanceof StructureItem) {
            StructureItem item = (StructureItem) player.getItemInHand(Hand.MAIN_HAND).getItem();
            item.setPos(event.getPos().above());
            item.getStructureTemplate().generateStructure(player.getCommandSenderWorld(),
                    item.getPos(), item.getRelative(), item.getMaterialBlock());
            if(!player.isCreative()) {
                player.setItemInHand(Hand.MAIN_HAND, ItemStack.EMPTY);
            }
        }
    }

    @SubscribeEvent
    public void handleLivingUpdate(LivingEvent.LivingUpdateEvent event) {
        if(event.getEntity() instanceof PlayerEntity) {
            PlayerEntity player = (PlayerEntity) event.getEntity();
            if(player.getItemInHand(Hand.MAIN_HAND).getItem() instanceof StructureItem) {
                StructureItem item = (StructureItem) player.getItemInHand(Hand.MAIN_HAND).getItem();
                if(player.isCrouching()) {
                    item.setDirection(Direction.Axis.Z);
                } else {
                    item.setDirection(Direction.Axis.X);
                }
            }
        }
    }

    public static SurvivalBases instance() {
        return instance;
    }
}
