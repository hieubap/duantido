package weapon;


import objectgame.ObjectGame;
import origin.EnvironmentConfig;
import origin.Picture;

import java.awt.*;

public class WoodShot implements ObjectGame {
    private final int pixel = EnvironmentConfig.PIXEL;
    private double speed = EnvironmentConfig.SPEED_WOOD_SHOT;

    private double positionX;
    private double positionY;
    private DirectionWeapon direction;

    public WoodShot(double positionX, double positionY, DirectionWeapon direction) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.direction = direction;
    }

    @Override
    public void update() {

        if (positionX > 200) {
            switch (direction) {
                case UP -> {
                    positionY -= speed;
                    break;
                }
                case DOWN -> {
                    positionY += speed;
                    break;
                }
                case LEFT -> {
                    positionX -= speed;
                    break;
                }
                case RIGHT -> {
                    positionX += speed;
                    break;
                }
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(Picture.woodShotImage, (int) positionX, (int) positionY, pixel, pixel, null);
    }

    @Override
    public boolean isRemove() {
        return false;
    }
}
