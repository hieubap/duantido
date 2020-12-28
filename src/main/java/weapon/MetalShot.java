package weapon;


import enemy.Enemy;
import centre.Camera;
import centre.EnvironmentVariable;
import centre.ImageManager;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MetalShot implements Weapon {
    private final int pixel = EnvironmentVariable.PIXEL;
    private double speed = EnvironmentVariable.SPEED_NORMAL_SHOT;

    private double positionX;
    private double positionY;
    private DirectionWeapon direction;
    private static final BufferedImage[] shotImage = new BufferedImage[4];
    public boolean isRemove = false;
    private Camera camera;

    static {
        shotImage[0] = ImageManager.metalShotImage;
        shotImage[1] = ImageManager.flipVertical(ImageManager.metalShotImage);
        shotImage[2] = ImageManager.rotation90(ImageManager.metalShotImage);
        shotImage[3] = ImageManager.flipHorizontal(shotImage[2]);
    }

    public MetalShot(double positionX, double positionY, DirectionWeapon direction,Camera camera) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.direction = direction;
        this.camera = camera;
    }

    @Override
    public void update() {
        if (positionX < 300 || positionX > 100* EnvironmentVariable.PIXEL || positionY < 100 || positionY > 100* EnvironmentVariable.PIXEL)
            isRemove = true;

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

    @Override
    public void draw(Graphics g) {
        switch (direction) {
            case UP -> {
                g.drawImage(shotImage[3], (int) (positionX- camera.positionX), (int) (positionY- camera.positionY), pixel, pixel, null);
                break;
            }
            case DOWN -> {
                g.drawImage(shotImage[2], (int) (positionX- camera.positionX), (int) (positionY- camera.positionY), pixel, pixel, null);
                break;
            }
            case LEFT -> {
                g.drawImage(shotImage[1], (int) (positionX- camera.positionX), (int) (positionY- camera.positionY), pixel, pixel, null);
                break;
            }
            case RIGHT -> {
                g.drawImage(shotImage[0], (int) (positionX- camera.positionX), (int) (positionY- camera.positionY), pixel, pixel, null);
                break;
            }
        }
    }

    @Override
    public boolean isCollection(Enemy enemy) {
        Rectangle rectangle = new Rectangle((int)positionX,(int)positionY, EnvironmentVariable.PIXEL, EnvironmentVariable.PIXEL);

        return rectangle.contains(enemy.positionX,enemy.positionY);
    }


}
