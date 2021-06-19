package org.armacraft.bases;

import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import org.armacraft.bases.client.ClientDist;
import org.armacraft.bases.server.ServerDist;

@Mod(ArmaBases.MODID)
public class ArmaBases {
    public static final String MODID = "armabases";

    private IModDist dist;

    public ArmaBases() {
        this.dist = DistExecutor.safeRunForDist(() -> ClientDist::new, () -> ServerDist::new);
    }
}
