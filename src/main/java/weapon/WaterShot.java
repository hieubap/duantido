package weapon;


import objectgame.ObjectGame;
import origin.EnvironmentConfig;
import origin.Picture;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WaterShot implements ObjectGame {
    private final int pixel = EnvironmentConfig.PIXEL;
    private double speed = EnvironmentConfig.SPEED_WATER_SHOT;

    private double positionX;
    private double positionY;
    private DirectionWeapon direction;
    private static final BufferedImage[] shotImage = new BufferedImage[4];

    @Override
    public boolean isRemove() {
        return false;
    }

    static{
        shotImage[0] = Picture.waterShotImage;
        shotImage[1] = Picture.flipVertical(Picture.waterShotImage);
        shotImage[2] = Picture.rotation90(Picture.waterShotImage);
        shotImage[3] = Picture.flipHorizontal(shotImage[2]);
    }
    public WaterShot(double positionX, double positionY, DirectionWeapon direction) {
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

        switch (direction) {
            case UP -> {
                g.drawImage(shotImage[2], (int) positionX, (int) positionY, pixel, pixel, null);
                break;
            }
            case DOWN -> {
                g.drawImage(shotImage[3], (int) positionX, (int) positionY, pixel, pixel, null);
                break;
            }
            case LEFT -> {
                g.drawImage(shotImage[0], (int) positionX, (int) positionY, pixel, pixel, null);
                break;
            }
            case RIGHT -> {
                g.drawImage(shotImage[1], (int) positionX, (int) positionY, pixel, pixel, null);
                break;
            }
        }
    }
}
