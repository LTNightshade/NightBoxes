package de.madone.nightboxes.gui;

import com.example.examplemod.NightBoxes;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class Guis {
    public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(Registries.MENU, NightBoxes.MODID);

    public static final DeferredHolder<MenuType<?>,MenuType<?>> BOX_MENU = MENUS.register("box_menu", () -> IMenuTypeExtension.create(BoxContainerMenu::new));


}