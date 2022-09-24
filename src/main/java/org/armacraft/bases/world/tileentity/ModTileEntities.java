package org.armacraft.bases.world.tileentity;

import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.armacraft.bases.SurvivalBases;
import org.armacraft.bases.world.block.ModBlocks;

public class ModTileEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, SurvivalBases.MODID);

    public static RegistryObject<BlockEntityType<BaseCoreBlockEntity>> BASE_CORE_BLOCK_ENTITY = BLOCK_ENTITY_TYPES.register("base_core",
            () -> BlockEntityType.Builder.of(BaseCoreBlockEntity::new, ModBlocks.BASE_CORE.get()).build(null));
}
