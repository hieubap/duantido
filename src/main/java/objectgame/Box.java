package objectgame;

import animation.Animation;
import centre.Camera;
import centre.ImageManager;

import java.awt.image.BufferedImage;

public class Box extends Animation {
    public Box( double positionX, double positionY, int width, int height, int lengthIndex, Camera camera, int timeCountToChange) {
        super(ImageManager.boxImage, positionX, positionY, width, height, lengthIndex, camera, timeCountToChange);
    }
}
