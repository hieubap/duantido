package centre;

import java.awt.*;

public class EnvironmentVariable {
    // environment
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 800;
    public static final int FPS = 30;
    public static final int DELAYPROCESS = 1000/FPS;
    public static final String PATHRESOUREASSET = "D:/WorkspaceEclipse/TANK/src/main/java/asset";
    public static final Color COLOR_BACKGROUND_LIGHT = new Color(0x91b09a);
    public static final Color COLOR_BACKGROUND_BLACK = new Color(0x2f283a);

    // map
    public static final int PIXEL = 64;
    public static final int PIXEL_DESIGN = 20;
    public static final int NUMBER_SPRITE_WIDTH = 24;
    public static final int NUMBER_SPRITE_HEIGHT = 10;

    // speed
    public static final int SPEED_PLAYER = 5;
    public static final int SPEED_CAMERA = 5;
    public static final int SPEED_ENEMY = 5;
    public static final int SPEED_SHOT = 11;
    public static final int SPEED_NORMAL_SHOT = SPEED_SHOT;
    public static final int SPEED_FIRE_SHOT = SPEED_SHOT;
    public static final int SPEED_WATER_SHOT = SPEED_SHOT;
    public static final int SPEED_EARTH_SHOT = SPEED_SHOT;
    public static final int SPEED_METAL_SHOT = SPEED_SHOT;
    public static final int SPEED_WOOD_SHOT = SPEED_SHOT;

    // distance
    public static final int RANGE_TO_CAMERA_MOVE = 200;
}
