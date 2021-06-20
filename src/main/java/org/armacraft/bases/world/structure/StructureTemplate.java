package org.armacraft.bases.world.structure;

import jdk.nashorn.internal.ir.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

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
}
