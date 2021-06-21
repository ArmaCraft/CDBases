package org.armacraft.bases.world.structure;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

public class StructureTemplate {
    private int[][] template;

    public StructureTemplate(int[]... template) {
        this.template = template;
    }

    public Set<BlockPos> apply(BlockPos pos, Direction.Axis direction) {
        Set<BlockPos> affectedCoords = new HashSet<>();
        for(int i = 0; i < template.length; i++) {
            for(int j = 0; j < template[i].length; j++) {
                if(template[i][j] == 1) {
                    int relativePosY = pos.getY() + j;
                    affectedCoords.add(direction == Direction.Axis.X
                            ? new BlockPos(pos.getX() + i, relativePosY, pos.getZ())
                            : new BlockPos(pos.getX(), relativePosY, pos.getZ() + i));
                }
            }
        }
        return affectedCoords;
    }

    public void generateStructure(World world, BlockPos blockPos, Direction.Axis direction, Block material) {
        this.apply(blockPos, direction).forEach(pos -> world.setBlock(pos, material.defaultBlockState(), 1));
    }
}
