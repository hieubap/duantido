package Primary;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Camera implements KeyListener {
    public int positionX, positionY,speed = 10;
    public int width,height;


    public Camera(int positionX, int posisionY, int width, int height) {
        this.positionX = positionX;
        this.positionY = posisionY;
        this.width = width;
        this.height = height;
    }

    public Camera(int positionX, int positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.width = EnvironmentConfig.WIDTH;
        this.height = EnvironmentConfig.HEIGHT;
    }

    public boolean canDraw(int x,int y){
        Rectangle rectangle = new Rectangle(positionX,positionY,EnvironmentConfig.WIDTH,EnvironmentConfig.HEIGHT);
        return rectangle.contains(x,y);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int k = e.getKeyCode();
        switch (k){
            case KeyEvent.VK_UP:{
                this.positionY-=speed;
                break;
            }
            case KeyEvent.VK_DOWN:{
                this.positionY+=speed;
                break;
            }
            case KeyEvent.VK_RIGHT:{
                this.positionX+=speed;
                break;
            }
            case KeyEvent.VK_LEFT:{
                this.positionX-=speed;
                break;
            }

        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
