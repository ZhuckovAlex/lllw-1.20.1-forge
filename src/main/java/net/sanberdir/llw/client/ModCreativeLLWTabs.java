package net.sanberdir.llw.client;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.sanberdir.llw.LLW;
import net.sanberdir.llw.common.blocks.LLWBlocks;
import net.sanberdir.llw.common.items.LLWItems;

public class ModCreativeLLWTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LLW.MOD_ID);

    public static final RegistryObject<CreativeModeTab> LLW_TAB = CREATIVE_MODE_TABS.register("llw_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(LLWItems.INK_AND_PEN.get()))
                    .title(Component.translatable("llw.tutorllw_tabial_tab"))
                    .displayItems((pParameters, pOutput) -> {
                        pOutput.accept(LLWItems.FOCUSING_LENS.get());
                        pOutput.accept(LLWItems.INK_AND_PEN.get());
                        pOutput.accept(LLWItems.FOLIO_OF_THE_ELEMENTS.get());
                        pOutput.accept(LLWItems.SALIS_MIRACULA.get());
                        pOutput.accept(LLWItems.MOON_TEAR_ITEM.get());
                        pOutput.accept(LLWItems.CRYSTAL_FORMATION.get());
                        pOutput.accept(LLWBlocks.CRYSTAL_CAVE_STONE.get());
                    })
                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}
