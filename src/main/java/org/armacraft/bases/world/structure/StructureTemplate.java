package org.armacraft.bases.world.structure;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

public class StructureTemplate {
    private int[][] template;

    public StructureTemplate(int[]... template) {
        this.template = template;
    }

    public Vector3d[][] apply(BlockPos pos, boolean relativeToX) {
        Vector3d[][] relativeCoords = new Vector3d[template.length][template[0].length];
        for(int i = 0; i < template.length; i++) {
            for(int j = 0; j < template[i].length; j++) {
                if(template[i][j] == 1) {
                    int relativePosY = pos.getY() + j;
                    relativeCoords[i][j] = relativeToX
                            ? new Vector3d(pos.getX() + i, relativePosY, pos.getZ())
                            : new Vector3d(pos.getX(), relativePosY, pos.getZ() + i);
                }
            }
        }
        return relativeCoords;
    }
}
