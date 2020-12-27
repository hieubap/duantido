package weapon;



import image.EarthShotImage;
import image.WaterShotImage;
import primary.EnvironmentConfig;
import image.NormalShotImage;

import java.awt.*;

public class EarthShot implements Weapon {
    private double positionX;
    private double positionY;
    private final int pixel = EnvironmentConfig.PIXEL;
    private double speed = 10;
    private Direction direction;

    public EarthShot(double positionX, double positionY, Direction direction) {
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
        g.drawImage(EarthShotImage.ballImage, (int) positionX, (int) positionY, pixel, pixel, null);
    }
}
