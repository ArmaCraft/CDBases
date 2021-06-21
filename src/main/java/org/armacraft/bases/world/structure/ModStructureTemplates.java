package org.armacraft.bases.world.structure;

import java.util.function.Supplier;

public class ModStructureTemplates {
    public static Supplier<StructureTemplate> SINGLE_BLOCK = () ->
            new StructureTemplate(new int[]{1});

    public static Supplier<StructureTemplate> WALL_2X3 = () ->
            new StructureTemplate(
                    new int[]{1, 1},
                    new int[]{1, 1},
                    new int[]{1, 1});

    public static Supplier<StructureTemplate> WALL_3X3 = () ->
            new StructureTemplate(
                    new int[]{1, 1, 1},
                    new int[]{1, 1, 1},
                    new int[]{1, 1, 1});

    public static Supplier<StructureTemplate> DOOR = () ->
            new StructureTemplate(
                    new int[]{1, 1, 1},
                    new int[]{0, 0, 1},
                    new int[]{1, 1, 1});
}
