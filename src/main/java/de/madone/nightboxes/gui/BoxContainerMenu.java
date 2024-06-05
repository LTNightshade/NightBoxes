package de.madone.nightboxes.gui;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.common.extensions.IFriendlyByteBufExtension;
import net.neoforged.neoforge.items.SlotItemHandler;

public class BoxContainerMenu extends AbstractContainerMenu {

    public BoxMenuExtraData ExtraData;
    public ContainerLevelAccess containerLevelAccess;
    private int BoxSlotOffset = 0;
    private int DataSlotOffset = 0;

    public BoxContainerMenu(int pContainerId, Inventory playerInventory, FriendlyByteBuf buf) {
        this(pContainerId, playerInventory, new SimpleContainerData(buf.getInt(1)),ContainerLevelAccess.NULL, buf);

    }

    public BoxContainerMenu(int pContainerId, Inventory playerInventory,ContainerData containerData, ContainerLevelAccess containerLevelAccess, FriendlyByteBuf buf) {
        super(Guis.BOX_MENU.get(), pContainerId);
        this.containerLevelAccess = containerLevelAccess;
        this.ExtraData = BoxMenuExtraData.create(buf);
        checkContainerDataCount(containerData, ExtraData.item_slots);
        addPlayerSlots(playerInventory);
        BoxSlotOffset = playerInventory.getContainerSize();
        addDataSlots(containerData);
    }

    private void addPlayerSlots(Inventory playerInventory) {
        for (int i = 0; i < playerInventory.getContainerSize(); i++) {
            addSlot(new Slot(playerInventory, i ,i % ExtraData.item_slotsPerRow,i / ExtraData.item_slotsPerRow));
        }
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return super.stillValid(this.containerLevelAccess, pPlayer, NIGHTBOX.get());
    }

    @Override
    public ItemStack quickMoveStack(Player player, int quickMovedSlotIndex) {
        // The quick moved slot stack
        ItemStack quickMovedStack = ItemStack.EMPTY;
        // The quick moved slot
        Slot quickMovedSlot = this.slots.get(quickMovedSlotIndex);

        // If the slot is in the valid range and the slot is not empty
        if (quickMovedSlot != null && quickMovedSlot.hasItem()) {
            // Get the raw stack to move
            ItemStack rawStack = quickMovedSlot.getItem();
            // Set the slot stack to a copy of the raw stack
            quickMovedStack = rawStack.copy();

    /*
    The following quick move logic can be simplified to if in data inventory,
    try to move to player inventory/hotbar and vice versa for containers
    that cannot transform data (e.g. chests).
    -/

    // If the quick move was performed on the data inventory result slot
    if (quickMovedSlotIndex == 0) {
      // Try to move the result slot into the player inventory/hotbar
      if (!this.moveItemStackTo(rawStack, 5, 41, true)) {
        // If cannot move, no longer quick move
        return ItemStack.EMPTY;
      }

      // Perform logic on result slot quick move
      slot.onQuickCraft(rawStack, quickMovedStack);
    }
    // Else if the quick move was performed on the player inventory or hotbar slot
    else if (quickMovedSlotIndex >= 5 && quickMovedSlotIndex < 41) {
      // Try to move the inventory/hotbar slot into the data inventory input slots
      if (!this.moveItemStackTo(rawStack, 1, 5, false)) {
        // If cannot move and in player inventory slot, try to move to hotbar
        if (quickMovedSlotIndex < 32) {
          if (!this.moveItemStackTo(rawStack, 32, 41, false)) {
            // If cannot move, no longer quick move
            return ItemStack.EMPTY;
          }
        }
        // Else try to move hotbar into player inventory slot
        else if (!this.moveItemStackTo(rawStack, 5, 32, false)) {
          // If cannot move, no longer quick move
          return ItemStack.EMPTY;
        }
      }
    }
    // Else if the quick move was performed on the data inventory input slots, try to move to player inventory/hotbar
    else if (!this.moveItemStackTo(rawStack, 5, 41, false)) {
      // If cannot move, no longer quick move
      return ItemStack.EMPTY;
    }

    if (rawStack.isEmpty()) {
      // If the raw stack has completely moved out of the slot, set the slot to the empty stack
      quickMovedSlot.set(ItemStack.EMPTY);
    } else {
      // Otherwise, notify the slot that that the stack count has changed
      quickMovedSlot.setChanged();
    }

    /*
    The following if statement and Slot#onTake call can be removed if the
    menu does not represent a container that can transform stacks (e.g.
    chests).
    -/
    if (rawStack.getCount() == quickMovedStack.getCount()) {
      // If the raw stack was not able to be moved to another slot, no longer quick move
      return ItemStack.EMPTY;
    }
    // Execute logic on what to do post move with the remaining stack
    quickMovedSlot.onTake(player, rawStack);
  }

  return quickMovedStack; // Return the slot stack
}
}