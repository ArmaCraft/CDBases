package org.armacraft.bases;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.armacraft.bases.client.ClientDist;
import org.armacraft.bases.server.ServerDist;
import org.armacraft.bases.world.block.ModBlocks;
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

        this.dist = DistExecutor.safeRunForDist(() -> ClientDist::new, () -> ServerDist::new);
    }

    @SubscribeEvent
    public void onRightClick(PlayerInteractEvent event) {
        //Testing stuff
        if(event.getPlayer().isCrouching()) {
            ModStructureTemplates.DOOR.get().apply(event.getPos().above(), false).forEach(pos -> {
                Block block = new Block(Block.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noDrops().noOcclusion());
                event.getWorld().setBlock(pos, block.defaultBlockState(), 1);
                System.out.println(pos);
            });
        } else {
            Minecraft.getInstance().player.chat(event.getPos().toString());
        }
    }

    public static SurvivalBases instance() {
        return instance;
    }
}
