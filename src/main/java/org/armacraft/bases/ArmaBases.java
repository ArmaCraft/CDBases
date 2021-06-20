package org.armacraft.bases;

import net.minecraft.client.Minecraft;
import net.minecraft.util.math.vector.Vector3d;
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

@Mod(ArmaBases.MODID)
public class ArmaBases {
    public static final String MODID = "armabases";

    private static ArmaBases instance;

    private IModDist dist;

    public ArmaBases() {
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
            Vector3d[][] structure = ModStructureTemplates.WALL_2X3.get().apply(event.getPos().above(), false);
            for(int i = 0; i<structure.length; i++) {
                for(int j = 0; j<structure[i].length; j++) {
                    System.out.println(structure[i][j].toString());
                }
            }
        } else {
            Minecraft.getInstance().player.chat(event.getPos().toString());
        }
    }

    public static ArmaBases instance() {
        return instance;
    }
}
