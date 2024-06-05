package de.madone.nightboxes.utils;

import net.minecraft.resources.ResourceLocation;

import java.io.Serializable;

public class BoxType implements Serializable {
    public String Name;
    public ResourceLocation Tagname;
    public ResourceLocation MaterialTagname;
    public int ItemSlots;
    public int FluidSlots;
    public int ItemSlotsPerRow = 9;
    public int FluidSlotsPerRow = 9;
    public boolean SlotsLockable = true;
    public boolean SlotsFilterable = true;

    public BoxType(String name, ResourceLocation tagname, ResourceLocation materialTagname, int itemSlots, int fluidSlots, int itemSlotsPerRow, int fluidSlotsPerRow, boolean slotsLockable, boolean slotsFilterable) {
        Name = name;
        Tagname = tagname;
        MaterialTagname = materialTagname;
        ItemSlots = itemSlots;
        FluidSlots = fluidSlots;
        ItemSlotsPerRow = itemSlotsPerRow;
        FluidSlotsPerRow = fluidSlotsPerRow;
        SlotsLockable = slotsLockable;
        SlotsFilterable = slotsFilterable;
    }

    public BoxType(String name, ResourceLocation tagname, ResourceLocation materialTagname, int itemSlots, int fluidSlots) {
        Name = name;
        Tagname = tagname;
        MaterialTagname = materialTagname;
        ItemSlots = itemSlots;
        FluidSlots = fluidSlots;
    }

}
