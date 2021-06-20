package org.armacraft.bases.world.block;

import net.minecraft.block.Block;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.armacraft.bases.ArmaBases;

public class ModBlocks {
    public static DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, ArmaBases.MODID);

    public static RegistryObject<BaseCoreBlock> BASE_CORE = BLOCKS.register("base_core", BaseCoreBlock::new);
}
