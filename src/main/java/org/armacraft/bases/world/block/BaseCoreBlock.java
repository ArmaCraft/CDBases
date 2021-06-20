package org.armacraft.bases.world.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import org.armacraft.bases.world.tileentity.BaseCoreTileEntity;
import org.armacraft.bases.world.tileentity.ModTileEntities;

public class BaseCoreBlock extends Block {

    public BaseCoreBlock() {
        super(Block.Properties.of(Material.STONE).strength(-1.0F, 3600000.0F).noDrops().noOcclusion());
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return true;
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        return ModTileEntities.BASE_CORE_TILE.get().create();
    }

    @Override
    public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        BaseCoreTileEntity tile = (BaseCoreTileEntity) worldIn.getBlockEntity(pos);
        if (placer instanceof PlayerEntity) {
            tile.setOwner(placer.getUUID());
            tile.setLastTimeInteracted(System.currentTimeMillis());
            tile.setChanged();
        }
        super.setPlacedBy(worldIn, pos, state, placer, stack);
    }
}
