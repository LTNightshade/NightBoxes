package de.madone.nightboxes.gui;

import com.example.examplemod.NightBoxes;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.phys.Vec2;

public class BoxContainerScreen extends AbstractContainerScreen {

    public BoxMenuExtraData DATA;
    private float rows = 0;
    private float cols = 0;
    private Vec2 SlotSize = new Vec2(18,18);
    private Vec2 PlayerInvSize = new Vec2( 14  + 9 * SlotSize.x, 4 * SlotSize.y + 7 + 4 + 12);
    private Vec2 InventorySize = new Vec2(0,0);

    public static final ResourceLocation IMAGE = new ResourceLocation(NightBoxes.MODID, "textures/gui/universal_chest.png");

    public BoxContainerScreen(AbstractContainerMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        rows = Math.ceilDiv( DATA.item_slots , DATA.item_slotsPerRow);
        cols = DATA.item_slotsPerRow;
        InventorySize = new Vec2( rows * SlotSize.x,cols * SlotSize.y);
        imageWidth = (int) (Math.max(InventorySize.x, 9*24) + 14);
        imageHeight = (int) (InventorySize.y + 14f + PlayerInvSize.y);
    }

    @Override
    protected void renderBg(GuiGraphics G, float pPartialTick, int pMouseX, int pMouseY) {
        G.blit(IMAGE,0,0,0,0,24,24);
        G.blit(IMAGE,24,0,24,0,24,24,24,imageWidth - 48);
        G.blit(IMAGE,imageWidth - 24 , 0,48,0,24,24);

    }
}
