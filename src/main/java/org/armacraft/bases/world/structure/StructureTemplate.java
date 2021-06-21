package org.armacraft.bases.world.structure;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashSet;
import java.util.Set;

public class StructureTemplate {
    private int[][] template;

    public StructureTemplate(int[]... template) {
        this.template = template;
    }

    public Set<BlockPos> apply(BlockPos pos, boolean relativeToX) {
        Set<BlockPos> affectedCoords = new HashSet<>();
        for(int i = 0; i < template.length; i++) {
            for(int j = 0; j < template[i].length; j++) {
                if(template[i][j] == 1) {
                    int relativePosY = pos.getY() + j;
                    affectedCoords.add(relativeToX
                            ? new BlockPos(pos.getX() + i, relativePosY, pos.getZ())
                            : new BlockPos(pos.getX(), relativePosY, pos.getZ() + i));
                }
            }
        }
        return affectedCoords;
    }

    public void generateStructure(World world, BlockPos blockPos, boolean relativeToX, Block material) {
        this.apply(blockPos, relativeToX).forEach(pos -> {
            world.setBlock(pos, material.defaultBlockState(), 1);
            System.out.println(pos);
        });
    }
}
