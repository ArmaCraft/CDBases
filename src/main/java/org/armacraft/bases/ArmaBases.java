package org.armacraft.bases;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.armacraft.bases.client.ClientDist;
import org.armacraft.bases.server.ServerDist;
import org.armacraft.bases.world.block.ModBlocks;
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

    public static ArmaBases instance() {
        return instance;
    }
}
