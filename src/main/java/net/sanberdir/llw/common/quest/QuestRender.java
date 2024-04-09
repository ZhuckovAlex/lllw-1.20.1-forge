package net.sanberdir.llw.common.quest;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;
import net.sanberdir.llw.LLW;
import net.sanberdir.llw.common.gui.FolioElements;

public class QuestRender {
    public QuestRender(Quest quest, PoseStack poseStack, int scissorX, int scissorY, int scissorWidth, int scissorHeight, int screenCenterX, int screenCenterY, int textureX, int textureY) {
        this.itemRenderer = Minecraft.getInstance().getItemRenderer();
        screenCenterX = ((screenCenterX + FolioElements.WINDOW_WIDTH / 2) - 8);
        screenCenterY = ((screenCenterY + FolioElements.WINDOW_HEIGHT / 2) - 8);
        int positionX = screenCenterX + quest.getPosition().getX();
        int positionY = screenCenterY + quest.getPosition().getY();

        renderQuestType(poseStack, positionX, positionY, scissorX, scissorY, scissorWidth, scissorHeight, textureX, textureY);
        renderIcon(quest, positionX, positionY, scissorX, scissorY, scissorWidth, scissorHeight, textureX, textureY);
    }
    private final ResourceLocation QUEST_TYPE_TEXTURE = new ResourceLocation(LLW.MOD_ID, "textures/gui/quest_type_icons.png");
    private void renderQuestType(PoseStack poseStack, int screenCenterX, int screenCenterY, int scissorX, int scissorY, int scissorWidth, int scissorHeight, int textureX, int textureY)
    {
        RenderSystem.enableScissor(scissorX, scissorY, scissorWidth, scissorHeight);
        RenderSystem.setShaderTexture(0, QUEST_TYPE_TEXTURE);
        Screen.blit(poseStack, screenCenterX - 3 + textureX, screenCenterY - 2 + textureY, 0, 0, 22, 22, 86, 54);
        RenderSystem.disableScissor();
    }
    private final ItemRenderer itemRenderer;
    private void renderIcon(Quest quest, int x, int y, int scissorX, int scissorY, int scissorWidth, int scissorHeight, int textureX, int textureY)
    {
        String[] id = quest.getTexture().split(":");
        ResourceLocation itemId = new ResourceLocation(id[0], id[1]);

        Item item = ForgeRegistries.ITEMS.getValue(itemId);
        ItemStack itemStack = new ItemStack(item);

        RenderSystem.enableScissor(scissorX, scissorY, scissorWidth, scissorHeight);
        itemRenderer.renderGuiItem(itemStack, x + textureX, y + textureY);
        RenderSystem.disableScissor();
    }
}
