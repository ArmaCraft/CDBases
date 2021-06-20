package org.armacraft.bases.world.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.armacraft.bases.SurvivalBases;

public class ModBlocks {
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, SurvivalBases.MODID);

    public static RegistryObject<BaseCoreBlock> BASE_CORE = BLOCKS.register("base_core", BaseCoreBlock::new);

    public static RegistryObject<Block> CONCRETE_BLOCK = BLOCKS.register("concrete",
            () -> new Block(AbstractBlock.Properties.copy(Blocks.STONE)));
}
