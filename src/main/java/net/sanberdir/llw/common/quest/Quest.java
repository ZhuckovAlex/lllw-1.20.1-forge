package net.sanberdir.llw.common.quest;
public class Quest {
    private String id;
    public String getId() {
        return id;
    }
    private String texture;
    public String getTexture() {
        return texture;
    }
    private String parentId;
    public String getParentId() {
        return parentId;
    }
    private String name;
    public String getName() {
        return name;
    }
    private String description;
    public String getDescription() {
        return description;
    }
    private Position position;
    public Position getPosition() {
        return position;
    }
    public static class Position {
        private int x;
        public int getX() {
            return x;
        }
        private int y;
        public int getY() {
            return y;
        }
    }
}
