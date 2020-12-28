package animation;

import objectgame.ObjectGame;
import centre.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation implements ObjectGame {
    public int SPEED_CHANGE_IMAGE = 2;

    public int lengthIndex = 0;
    public int timeCountToChange = -1, index = 0, denTa = 1;
    public double positionX, positionY;
    public int width, height;
    private Image[] listSprite;
    private Camera camera;


    public Animation(BufferedImage bufferedImage, double positionX, double positionY, int width, int height, int lengthIndex, Camera camera) {
        index = (int) (Math.random() * (lengthIndex - 2)) + 1;

        this.camera = camera;
        this.positionX = positionX;
        this.positionY = positionY;
        this.lengthIndex = lengthIndex;
        this.width = width;
        this.height = height;

        listSprite = new Image[lengthIndex];
        for (int i = 0; i < lengthIndex; i++)
            listSprite[i] = bufferedImage.getSubimage(i * width, 0, width, height);
    }

    public Animation(BufferedImage bufferedImage, double positionX, double positionY, int width, int height, int lengthIndex, Camera camera,int timeCountToChange) {
        this(bufferedImage, positionX, positionY, width, height, lengthIndex, camera);
        this.SPEED_CHANGE_IMAGE = timeCountToChange;
    }

    public void setSpeedChangeImage(int time) {
        SPEED_CHANGE_IMAGE = time;
    }


    public Image getSprite(int index) {
        return listSprite[index];
    }

    @Override
    public void update() {
        if (timeCountToChange-- < 0) {
            timeCountToChange = SPEED_CHANGE_IMAGE;
            index += denTa;
            if (index >= lengthIndex - 1 || index <= 0) {
                denTa = -denTa;
            }
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getSprite(index), (int) (positionX - camera.positionX), (int) (positionY - camera.positionY), null);
    }
}
