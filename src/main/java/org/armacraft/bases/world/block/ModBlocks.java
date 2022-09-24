package org.armacraft.bases.world.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import org.armacraft.bases.SurvivalBases;

public class ModBlocks {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITIES, SurvivalBases.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SurvivalBases.MODID);

    public static RegistryObject<BaseCoreBlock> BASE_CORE = BLOCKS.register("base_core", BaseCoreBlock::new);

    public static RegistryObject<Block> CONCRETE_BLOCK = BLOCKS.register("concrete",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.STONE)));
}
