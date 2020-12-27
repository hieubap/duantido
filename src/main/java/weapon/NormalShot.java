package weapon;

import objectgame.ObjectGame;
import origin.Camera;
import origin.EnvironmentConfig;
import origin.Picture;

import java.awt.*;
import java.awt.image.BufferedImage;

public class NormalShot implements ObjectGame {
    private final int pixel = EnvironmentConfig.PIXEL;
    private double speed = EnvironmentConfig.SPEED_NORMAL_SHOT;

    private double positionX;
    private double positionY;
    private DirectionWeapon direction;
    private static final BufferedImage[] shotImage = new BufferedImage[4];
    private boolean isRemove = false;
    private Camera camera;

    static {
        shotImage[0] = Picture.normalShotImage;
        shotImage[1] = Picture.flipVertical(Picture.normalShotImage);
        shotImage[2] = Picture.rotation90(Picture.normalShotImage);
        shotImage[3] = Picture.flipHorizontal(shotImage[2]);
    }

    public NormalShot(double positionX, double positionY, DirectionWeapon direction,Camera camera) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.direction = direction;
        this.camera = camera;
    }

    @Override
    public void update() {
        if (positionX < -200 || positionX > 100*EnvironmentConfig.PIXEL || positionY < -200 || positionY > 100*EnvironmentConfig.PIXEL)
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
//        g.drawImage(shotImage[0], 0,0, shotImage[0].getWidth(), shotImage[0].getHeight(), null);
//        g.drawImage(shotImage[1], 100,0, pixel, pixel, null);
//        g.drawImage(shotImage[2], 200,0, pixel, pixel, null);
//        g.drawImage(shotImage[3], 300,100, shotImage[3].getWidth(), shotImage[3].getHeight(), null);


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
    public boolean isRemove() {
        return isRemove;
    }
}
