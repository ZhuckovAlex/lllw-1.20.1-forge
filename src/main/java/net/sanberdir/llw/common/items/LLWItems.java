package net.sanberdir.llw.common.items;

import net.minecraft.client.resources.model.Material;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.sanberdir.llw.LLW;
import net.sanberdir.llw.common.blocks.LLWBlocks;
import net.sanberdir.llw.common.items.custom.FolioOfTheElements;

public class LLWItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, LLW.MOD_ID);

    public static final RegistryObject<Item> FOCUSING_LENS = ITEMS.register("focusing_lens",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MOON_TEAR_ITEM = ITEMS.register("moon_tear_item",
            () -> new Item(new Item.Properties().rarity(Rarity.RARE)));
    public static final RegistryObject<Item> FOLIO_OF_THE_ELEMENTS = ITEMS.register("folio_of_the_elements",
            () -> new FolioOfTheElements(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> CRYSTAL_FORMATION = ITEMS.register("crystal_formation",
            () -> new ItemNameBlockItem(LLWBlocks.CRYSTAL_FORMATION.get(),(new Item.Properties())));
    public static final RegistryObject<Item> SALIS_MIRACULA = ITEMS.register("salis_miracula",
            () -> new Item(new Item.Properties().rarity(Rarity.UNCOMMON)));
    public static final RegistryObject<Item> INK_AND_PEN = ITEMS.register("ink_and_pen",
            () -> new Item(new Item.Properties()));
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
