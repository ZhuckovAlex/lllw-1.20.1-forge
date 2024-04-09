package net.sanberdir.llw.common.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.*;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

import net.sanberdir.llw.LLW;
import net.sanberdir.llw.common.quest.Quest;
import net.sanberdir.llw.common.quest.QuestLoader;
import net.sanberdir.llw.common.quest.QuestRender;

import java.util.ArrayList;
import java.util.List;

public class FolioElements extends Screen {
    // Textures
    private static final ResourceLocation WINDOW_TEXTURE = new ResourceLocation(LLW.MOD_ID, "textures/gui/gui_research.png");
    private static final ResourceLocation INSIDE_TEXTURE = new ResourceLocation(LLW.MOD_ID, "textures/gui/gui_research_back_1.png");
    // Window
    public static final int WINDOW_WIDTH = 256;
    public static final int WINDOW_HEIGHT = 230;
    private int screenCenterX, screenCenterY;
    private int textureX, textureY;
    private double scale;
    // Quests
    public static List<Quest> quests = new ArrayList<>();
    public FolioElements(Component component) {
        super(component);
    }
    @Override
    public void onClose() {
        quests.clear();
        super.onClose();
    }
    @Override
    protected void init() {
        screenCenterX = (this.width - WINDOW_WIDTH) / 2;
        screenCenterY = (this.height - WINDOW_HEIGHT) / 2;

        scale = minecraft.getWindow().getGuiScale();

        QuestLoader.loadQuests();
        super.init();
    }
    @Override
    public void render(PoseStack poseStack, int mouseX, int mouseY, float delta) {
        super.render(poseStack, mouseX, mouseY, delta);
        this.renderBackground(poseStack);

        // Renders
        renderInside(poseStack);

        for (Quest quest: quests){
            if (quest.getParentId() != null) {
                Quest parentQuest = findQuestById(quest.getParentId());
                drawThickLine(poseStack, parentQuest.getPosition().getX() + ((screenCenterX + (float) FolioElements.WINDOW_WIDTH / 2)) + textureX, parentQuest.getPosition().getY() + ((screenCenterY + (float) FolioElements.WINDOW_HEIGHT / 2)) + textureY,
                        quest.getPosition().getX() + ((screenCenterX + (float) FolioElements.WINDOW_WIDTH / 2)) + textureX, quest.getPosition().getY() + ((screenCenterY + (float) FolioElements.WINDOW_HEIGHT / 2)) + textureY, 1.5f, 0.8f,0.8f,0.8f,1.0f);
            }
            new QuestRender(quest, poseStack, getScissorX(224), getScissorY(194), getScissorWidth(224), getScissorHeight(194), screenCenterX, screenCenterY, textureX, textureY);
        }

        renderWindow(poseStack);
    }

    public void drawThickLine(PoseStack matrixStack, float startX, float startY, float endX, float endY, float thickness, float r, float g, float b, float alpha) {
        RenderSystem.enableBlend();
        RenderSystem.disableTexture();
        RenderSystem.defaultBlendFunc();
        RenderSystem.enableScissor(getScissorX(WINDOW_WIDTH), getScissorY(WINDOW_HEIGHT), getScissorWidth(WINDOW_WIDTH), getScissorHeight(WINDOW_HEIGHT));

        RenderSystem.setShader(GameRenderer::getPositionColorShader);

        float dx = endX - startX;
        float dy = endY - startY;
        float perpX = -dy;
        float perpY = dx;
        float length = (float) Math.sqrt(perpX * perpX + perpY * perpY);
        perpX /= length;
        perpY /= length;
        perpX *= thickness / 2;
        perpY *= thickness / 2;

        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferBuilder = tesselator.getBuilder();
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_COLOR);
        bufferBuilder.vertex(matrixStack.last().pose(), startX - perpX, startY - perpY, 0.0F).color(r, g, b, alpha).endVertex();
        bufferBuilder.vertex(matrixStack.last().pose(), startX + perpX, startY + perpY, 0.0F).color(r, g, b, alpha).endVertex();
        bufferBuilder.vertex(matrixStack.last().pose(), endX + perpX, endY + perpY, 0.0F).color(r, g, b, alpha).endVertex();
        bufferBuilder.vertex(matrixStack.last().pose(), endX - perpX, endY - perpY, 0.0F).color(r, g, b, alpha).endVertex();
        tesselator.end();

        RenderSystem.enableTexture();
        RenderSystem.disableBlend();
        RenderSystem.disableScissor();
    }


    private void renderWindow(PoseStack poseStack)
    {
        RenderSystem.enableBlend();
        RenderSystem.setShaderTexture(0, WINDOW_TEXTURE);
        blit(poseStack, screenCenterX, screenCenterY, 0,0, WINDOW_WIDTH, WINDOW_HEIGHT);
        RenderSystem.disableBlend();
    }
    private void renderInside(PoseStack poseStack)
    {
        RenderSystem.enableScissor(getScissorX(WINDOW_WIDTH), getScissorY(WINDOW_HEIGHT), getScissorWidth(WINDOW_WIDTH), getScissorHeight(WINDOW_HEIGHT));
        RenderSystem.setShaderTexture(0, INSIDE_TEXTURE);
        blit(poseStack, (screenCenterX - WINDOW_WIDTH) + textureX, (screenCenterY - WINDOW_HEIGHT) + textureY, WINDOW_WIDTH, WINDOW_HEIGHT, WINDOW_WIDTH * 3, WINDOW_HEIGHT * 3);
        RenderSystem.disableScissor();
    }
    @Override
    public boolean mouseDragged(double mouseX, double mouseY, int button, double deltaX, double deltaY) {
        textureX += (int) deltaX;
        textureY += (int) deltaY;

        int maxOffsetX = 256;
        int maxOffsetY = 230;

        textureX = Math.max(-maxOffsetX, Math.min(maxOffsetX, textureX));
        textureY = Math.max(-maxOffsetY, Math.min(maxOffsetY, textureY));

        return super.mouseDragged(mouseX, mouseY, button, deltaX, deltaY);
    }

    private int getScissorX(int WINDOW_WIDTH)
    {
        return (int) (((this.width - WINDOW_WIDTH) / 2) * scale);
    }

    private int getScissorY(int WINDOW_HEIGHT)
    {
        return (int) (minecraft.getWindow().getScreenHeight() - (((this.height - WINDOW_HEIGHT) / 2) + WINDOW_HEIGHT) * scale);
    }

    private int getScissorWidth(int WINDOW_WIDTH)
    {
        return (int) (WINDOW_WIDTH * scale);
    }

    private int getScissorHeight(int WINDOW_HEIGHT)
    {
        return (int) (WINDOW_HEIGHT * scale);
    }
    private Quest findQuestById(String questId) {
        for (Quest quest : FolioElements.quests) {
            if (quest.getId().equals(questId)) {
                return quest;
            }
        }
        return null;
    }
}
