package org.armacraft.bases.util;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.vector.Vector3d;

import java.util.Optional;

public class RaytracingUtil {
    public static Optional<BlockRayTraceResult> rayTraceBlocks(LivingEntity from, RayTraceContext.FluidMode fluidMode, double distance, float partialTicks) {
        Vector3d start = from.getEyePosition(partialTicks);
        Vector3d look = from.getViewVector(partialTicks);
        Vector3d scaledLook = look.scale(distance);
        Vector3d end = start.add(scaledLook);
        return Optional.ofNullable(from.level.clip(new RayTraceContext(start, end,
                RayTraceContext.BlockMode.COLLIDER, fluidMode, from)));
    }
}
