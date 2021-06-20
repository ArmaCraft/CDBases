package org.armacraft.bases.world.tileentity;

import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.armacraft.bases.ArmaBases;
import org.armacraft.bases.world.block.ModBlocks;

public class ModTileEntities {
    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, ArmaBases.MODID);

    public static RegistryObject<TileEntityType<BaseCoreTileEntity>> BASE_CORE_TILE = TILE_ENTITY_TYPES.register("base_core",
            () -> TileEntityType.Builder.of(BaseCoreTileEntity::new, ModBlocks.BASE_CORE.get()).build(null));
}
