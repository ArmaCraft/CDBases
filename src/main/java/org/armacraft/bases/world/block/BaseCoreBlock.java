package org.armacraft.bases.world.block;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import org.armacraft.bases.world.tileentity.BaseCoreBlockEntity;
import org.armacraft.bases.world.tileentity.ModTileEntities;
import org.jetbrains.annotations.Nullable;

public class BaseCoreBlock extends Block implements EntityBlock {

    public BaseCoreBlock() {
        super(Block.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noDrops().noOcclusion());
    }

    @Override
    public void setPlacedBy(Level worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        BaseCoreBlockEntity tile = (BaseCoreBlockEntity) worldIn.getBlockEntity(pos);
        if (placer instanceof Player) {
            tile.setOwner(placer.getUUID());
            tile.setLastTimeInteracted(System.currentTimeMillis());
            tile.setChanged();
        }
        super.setPlacedBy(worldIn, pos, state, placer, stack);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return ModTileEntities.BASE_CORE_BLOCK_ENTITY.get().create(pos, state);
    }
}
