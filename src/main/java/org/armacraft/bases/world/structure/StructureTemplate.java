package org.armacraft.bases.world.structure;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;

public class StructureTemplate {
    private int[][] template;
    private Vector3d[][] relativeCoords;

    public StructureTemplate(int[]... template) {
        this.template = template;
    }

    public Vector3d[][] apply(BlockPos pos, boolean relativeToX) {
        Vector3d[][] relativeCoords = new Vector3d[template.length][template[0].length];
        for(int i = 0; i < template.length; i++) {
            for(int j = 0; j < template[i].length; j++) {
                if(template[i][j] == 1) {
                    if(relativeToX) {
                        int relativePosX = pos.getX() + i;
                        int relativePosY = pos.getY() + j;
                        relativeCoords[i][j] = new Vector3d(relativePosX, relativePosY, pos.getZ());
                    } else {
                        int relativePosZ = pos.getZ() + i;
                        int relativePosY = pos.getY() + j;
                        relativeCoords[i][j] = new Vector3d(pos.getX(), relativePosY, relativePosZ);
                    }
                }
            }
        }
        return relativeCoords;
    }
}
