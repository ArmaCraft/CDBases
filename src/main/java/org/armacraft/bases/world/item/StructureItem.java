package org.armacraft.bases.world.item;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.item.ItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import static net.minecraft.util.Direction.Axis;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.TextComponentUtils;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import org.armacraft.bases.world.structure.StructureTemplate;

import javax.annotation.Nullable;
import java.util.List;

public class StructureItem extends Item {

    private long lastTimeClicked;
    private final long DELAY = 5000;
    private final StructureTemplate structureTemplate;
    private final Block materialBlock;
    private BlockPos pos;
    private Axis direction = Axis.X;

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

    public void setDirection(Axis axis) {
        this.direction = axis;
    }

    public Axis getDirection() {
        return direction;
    }

    @Override
    public void appendHoverText(ItemStack p_77624_1_, @Nullable World p_77624_2_, List<ITextComponent> p_77624_3_, ITooltipFlag p_77624_4_) {
        super.appendHoverText(p_77624_1_, p_77624_2_, p_77624_3_, p_77624_4_);
        p_77624_3_.add(new StringTextComponent(""));
        p_77624_3_.add(new TranslationTextComponent("lore.survivalbases.blueprint.1").withStyle(TextFormatting.GRAY));
        p_77624_3_.add(new TranslationTextComponent("lore.survivalbases.blueprint.2").withStyle(TextFormatting.GRAY));
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
