package weapon;

import image.FireShotImage;

import primary.EnvironmentConfig;

import java.awt.*;

public class FireShot implements Weapon{
    private double positionX;
    private double positionY;
    private final int pixel = EnvironmentConfig.PIXEL;
    private double speed = 10;
    private Direction direction;

    public FireShot(double positionX, double positionY, Direction direction) {
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
        g.drawImage(FireShotImage.ballImage, (int) positionX, (int) positionY, pixel*4, pixel*3, null);
    }
}
