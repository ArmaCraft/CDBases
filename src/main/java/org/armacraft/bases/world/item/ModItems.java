package org.armacraft.bases.world.item;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.armacraft.bases.SurvivalBases;
import org.armacraft.bases.world.block.ModBlocks;
import org.armacraft.bases.world.structure.ModStructureTemplates;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, SurvivalBases.MODID);

    public static final RegistryObject<StructureItem> CONCRETE_WALL_STRUCTURE =
            ITEMS.register("concrete_wall",
                    () -> new StructureItem((StructureItem.Properties) new StructureItem.Properties()
                            .setMaterialBlock(ModBlocks.CONCRETE_BLOCK.get())
                            .setTemplate(ModStructureTemplates.WALL_2X3.get())
                            .stacksTo(1)
                            .tab(ItemGroup.TAB_MISC)));
}
