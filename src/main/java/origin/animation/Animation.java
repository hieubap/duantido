package origin.animation;

import objectgame.ObjectGame;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Animation implements ObjectGame {
    public final int SPEED_CHANGE_IMAGE = 10;

    public int lengthIndex = 0;
    public int timeCountToChange = -1,index=0;
    public double positionX,positionY;
    public Image[] listSprite;

    public Animation(BufferedImage bufferedImage,double positionX, double positionY) {
        this.positionX = positionX;
        this.positionY = positionY;

    }

    public Image getSprite(int index){
        return listSprite[index];
    }

    @Override
    public void update() {
        if (timeCountToChange-- < 0){
            timeCountToChange = SPEED_CHANGE_IMAGE;
            if (index++>lengthIndex)
                index = 0;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(getSprite(index),(int)positionX,(int)positionY,null);
    }
}
