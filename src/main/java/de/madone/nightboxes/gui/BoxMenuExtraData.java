package de.madone.nightboxes.gui;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.inventory.ContainerData;
import net.neoforged.neoforge.common.util.FriendlyByteBufUtil;

public class BoxMenuExtraData {
    public boolean lockable;
    public boolean filterable;

    public int item_slots;
    public int item_slotsPerRow;
    public int item_lockedSlots;

    public int fluid_slots;
    public int fluid_slotsPerRow;
    public int fluid_lockedSlots;

    public BoxMenuExtraData(boolean lockable, boolean filterable, int item_slots, int item_slotsPerRow, int item_lockedSlots, int fluid_slots, int fluid_slotsPerRow, int fluid_lockedSlots) {
        this.lockable = lockable;
        this.filterable = filterable;
        this.item_slots = item_slots;
        this.item_slotsPerRow = item_slotsPerRow;
        this.item_lockedSlots = item_lockedSlots;
        this.fluid_slots = fluid_slots;
        this.fluid_slotsPerRow = fluid_slotsPerRow;
        this.fluid_lockedSlots = fluid_lockedSlots;
    }

    public static BoxMenuExtraData create(ContainerData data) {
        boolean lock = (data.get(0) & 0x01) == 0x01;
        boolean filter = (data.get(0) & 0x02) == 0x02;
        int islots = data.get(1);
        int ispr = data.get(2);
        int ils = data.get(3);
        int fslots = data.get(4);
        int fspr = data.get(5);
        int fls = data.get(6);
        return new BoxMenuExtraData(lock, filter, islots, ispr, ils, fslots, fspr, fls);
    }

    public static BoxMenuExtraData create(FriendlyByteBuf buf) {
        boolean lock = (buf.getInt(0) & 0x01) == 0x01;
        boolean filter = (buf.getInt(0) & 0x02) == 0x02;
        int islots = buf.getInt(1);
        int ispr = buf.getInt(2);
        int ils = buf.getInt(3);
        int fslots = buf.getInt(4);
        int fspr = buf.getInt(5);
        int fls = buf.getInt(6);
        return new BoxMenuExtraData(lock, filter, islots, ispr, ils, fslots, fspr, fls);
    }

    public FriendlyByteBuf ToByteBuf(FriendlyByteBuf buf) {
        buf.setInt(0, this.filterable?2:0 + (this.lockable?1:0) );
        buf.setInt(1, this.item_slots);
        buf.setInt(2,this.item_slotsPerRow);
        buf.setInt(3,this.item_lockedSlots);
        buf.setInt(4,this.fluid_slots);
        buf.setInt(5,this.fluid_slotsPerRow);
        buf.setInt(6,this.fluid_lockedSlots);
        return buf;
    }

}
