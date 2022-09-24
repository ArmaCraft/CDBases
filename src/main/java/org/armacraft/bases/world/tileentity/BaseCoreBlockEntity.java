package org.armacraft.bases.world.tileentity;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BaseCoreBlockEntity extends BlockEntity implements BlockEntityType.BlockEntitySupplier<BaseCoreBlockEntity> {
    private UUID owner;
    private List<UUID> trustedPlayers = new ArrayList<>();
    private long lastTimeInteracted;

    public BaseCoreBlockEntity(BlockPos pos, BlockState state) {
        super(ModTileEntities.BASE_CORE_BLOCK_ENTITY.get(), pos, state);
    }

    @Override
    public @NotNull CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        this.saveAdditional(tag);
        return tag;
    }

    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

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
    protected void saveAdditional(CompoundTag compound) {
        if(this.owner != null) {
            compound.putString("owner", owner.toString());
            compound.putLong("lastTimeInteracted", lastTimeInteracted);

            ListTag trustedMembersNBT = new ListTag();
            for(int i = 0; i<trustedPlayers.size(); i++) {
                CompoundTag nbt = new CompoundTag();
                nbt.putUUID("uuid", trustedPlayers.get(i));
                trustedMembersNBT.add(i, nbt);
            }

            compound.put("trustedPlayers", trustedMembersNBT);
        }
    }

    @Override
    public void load(CompoundTag compound) {
        if(compound.contains("owner")) {
            this.owner = compound.getUUID("owner");
            this.lastTimeInteracted = compound.getLong("lastTimeInteracted");

            trustedPlayers.clear();

            ListTag trustedMembersNBT = (ListTag) compound.get("trustedPlayers");
            for(int i = 0; i < trustedMembersNBT.size(); i++) {
                trustedPlayers.add(trustedMembersNBT.getCompound(i).getUUID("uuid"));
            }
        }
        super.load(compound);
    }

    @Override
    public BaseCoreBlockEntity create(BlockPos pos, BlockState state) {
        return ModTileEntities.BASE_CORE_BLOCK_ENTITY.get().create(pos, state);
    }
}
