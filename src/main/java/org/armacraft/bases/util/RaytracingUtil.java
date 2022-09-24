package org.armacraft.bases.util;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;

import java.util.Optional;

public class RaytracingUtil {
    public static Optional<HitResult> rayTraceBlocks(LivingEntity from, ClipContext.Fluid fluidMode, double distance, float partialTicks) {
        Vec3 start = from.getEyePosition(partialTicks);
        Vec3 look = from.getViewVector(partialTicks);

        Vec3 scaledLook = look.scale(distance);
        Vec3 end = start.add(scaledLook);
        return Optional.of(from.level.clip(new ClipContext(start, end,
                ClipContext.Block.COLLIDER, fluidMode, from)));
    }
}
