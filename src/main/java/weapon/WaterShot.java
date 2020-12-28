package weapon;


import objectgame.ObjectGame;
import centre.EnvironmentVariable;
import centre.ImageManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WaterShot implements ObjectGame {
    private final int pixel = EnvironmentVariable.PIXEL;
    private double speed = EnvironmentVariable.SPEED_WATER_SHOT;

    private double positionX;
    private double positionY;
    private DirectionWeapon direction;
    private static final BufferedImage[] shotImage = new BufferedImage[4];

    static{
        shotImage[0] = ImageManager.waterShotImage;
        shotImage[1] = ImageManager.flipVertical(ImageManager.waterShotImage);
        shotImage[2] = ImageManager.rotation90(ImageManager.waterShotImage);
        shotImage[3] = ImageManager.flipHorizontal(shotImage[2]);
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
