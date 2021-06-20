package org.armacraft.bases.world.item;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import org.armacraft.bases.world.structure.StructureTemplate;

public class StructureItem extends Item {

    private final StructureTemplate structureTemplate;
    private final Block materialBlock;

    public StructureItem(Properties properties) {
        super(properties);
        this.structureTemplate = properties.template;
        this.materialBlock = properties.materialBlock;
    }

    public static class Properties extends Item.Properties {
        private StructureTemplate template;
        private Block materialBlock;

        public Properties setTemplate(StructureTemplate template) {
            this.template = template;
            return this;
        }

        public Properties setMaterialBlock(Block block) {
            this.materialBlock = block;
            return this;
        }
    }
}
