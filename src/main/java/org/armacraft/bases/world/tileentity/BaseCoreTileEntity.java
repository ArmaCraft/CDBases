package org.armacraft.bases.world.tileentity;

import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.nbt.ListNBT;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SUpdateTileEntityPacket;
import net.minecraft.tileentity.TileEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BaseCoreTileEntity extends TileEntity {
    private UUID owner;
    private List<UUID> trustedPlayers = new ArrayList<>();
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

    public void removeTrustedPlayer(UUID uuid) {

    }

    @Override
    public CompoundNBT save(CompoundNBT compound) {
        if(this.owner != null) {
            compound.putString("owner", owner.toString());
            compound.putLong("lastTimeInteracted", lastTimeInteracted);

            ListNBT trustedMembersNBT = new ListNBT();
            for(int i = 0; i<trustedPlayers.size(); i++) {
                CompoundNBT nbt = new CompoundNBT();
                nbt.putUUID("uuid", trustedPlayers.get(i));
                trustedMembersNBT.add(i, nbt);
            }

            compound.put("trustedPlayers", trustedMembersNBT);
        }
        return super.save(compound);
    }

    @Override
    public void load(BlockState state, CompoundNBT compound) {
        if(compound.contains("owner")) {
            this.owner = compound.getUUID("owner");
            this.lastTimeInteracted = compound.getLong("lastTimeInteracted");

            trustedPlayers.clear();

            ListNBT trustedMembersNBT = (ListNBT) compound.get("trustedPlayers");
            for(int i = 0; i < trustedMembersNBT.size(); i++) {
                trustedPlayers.add(trustedMembersNBT.getCompound(i).getUUID("uuid"));
            }
        }
        super.load(state, compound);
    }
}
