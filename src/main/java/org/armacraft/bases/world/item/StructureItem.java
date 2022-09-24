package org.armacraft.bases.world.item;

import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.armacraft.bases.world.structure.StructureTemplate;

import javax.annotation.Nullable;
import java.util.List;

public class StructureItem extends Item {

    private long lastTimeClicked;
    private final long DELAY = 5000;
    private final StructureTemplate structureTemplate;
    private final Block materialBlock;
    private BlockPos pos;
    private Direction.Axis direction = Direction.Axis.X;

    public StructureItem(Properties properties) {
        super(properties);
        this.structureTemplate = properties.template;
        this.materialBlock = properties.materialBlock;
    }

    public BlockPos getPos() {
        return pos;
    }

    public void setPos(BlockPos pos) {
        this.pos = pos;
    }

    public void setDirection(Direction.Axis axis) {
        this.direction = axis;
    }

    public Direction.Axis getDirection() {
        return direction;
    }

    @Override
    public void appendHoverText(ItemStack item, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
        super.appendHoverText(item, world, tooltip, flag);
        tooltip.add(new TextComponent(""));
        tooltip.add(new TranslatableComponent("lore.survivalbases.blueprint.1").withStyle(ChatFormatting.GRAY));
        tooltip.add(new TranslatableComponent("lore.survivalbases.blueprint.2").withStyle(ChatFormatting.GRAY));
    }

    public StructureTemplate getStructureTemplate() {
        return structureTemplate;
    }

    public Block getMaterialBlock() {
        return materialBlock;
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
