package objectgame;

import animation.Animation;
import centre.Camera;
import centre.ImageManager;

public class Chest extends Animation {
    public Chest( double positionX, double positionY, int width, int height, int lengthIndex, Camera camera, int timeCountToChange) {
        super(ImageManager.chestImage, positionX, positionY, width, height, lengthIndex, camera, timeCountToChange);
    }
}
