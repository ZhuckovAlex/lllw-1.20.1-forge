package net.sanberdir.llw.common.items.custom;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.sanberdir.llw.common.gui.FolioElements;

public class FolioOfTheElements extends Item {
    public FolioOfTheElements(Properties properties) {
        super(properties);
    }
    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand interactionHand) {
        if (Minecraft.getInstance().screen == null) {
            Minecraft.getInstance().setScreen(new FolioElements(Component.literal("Achievements")));
            // Воспроизведение звука листания книги
            level.playSound(player, player.blockPosition(), SoundEvents.BOOK_PAGE_TURN, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
        return super.use(level, player, interactionHand);
    }
}