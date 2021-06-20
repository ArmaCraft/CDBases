package org.armacraft.bases.world.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import java.util.Date;
import java.util.UUID;

public class BaseCoreTileEntity extends TileEntity {
    private UUID owner;
    private long lastTimeInteracted;

    public BaseCoreTileEntity() {
        super(ModTileEntities.BASE_CORE_TILE.get());
    }

    @Override
    public SUpdateTileEntityPacket getUpdatePacket() {
        CompoundNBT nbtTag = new CompoundNBT();
        nbtTag = this.save(nbtTag);
        return new SUpdateTileEntityPacket(this.getBlockPos(), -1, nbtTag);
    }

    @Override
    public void onDataPacket(NetworkManager net, SUpdateTileEntityPacket pkt){}

    public void setOwner(UUID owner) {
        this.owner = owner;
        this.setChanged();
    }

    public void setLastTimeInteracted(long lastTimeInteracted) {
        this.lastTimeInteracted = lastTimeInteracted;
        this.setChanged();
    }

    public UUID getOwner() {
        return owner;
    }

    public long getLastTimeInteracted() {
        return lastTimeInteracted;
    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        compound.putBoolean("isGenerator", true);
        if(this.owner != null) {
            compound.putString("owner", owner.toString());
            compound.putLong("lastTimeInteracted", lastTimeInteracted);
        }
        return super.save(compound);
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        if(compound.contains("owner")) {
            this.owner = UUID.fromString(compound.getString("owner"));
            this.lastTimeInteracted = compound.getLong("lastTimeInteracted");
        }
        super.load(state, compound);
    }
}
