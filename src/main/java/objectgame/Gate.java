package objectgame;

import animation.Animation;
import centre.Camera;
import centre.ImageManager;

import java.awt.image.BufferedImage;

public class Gate extends Animation{

    public Gate(double positionX, double positionY, int width, int height, int lengthIndex, Camera camera, int timeCountToChange) {
        super(ImageManager.gateImage, positionX, positionY, width, height, lengthIndex, camera, timeCountToChange);
    }
}
