package Things;

import Primary.Camera;
import Primary.EnvironmentConfig;
import Primary.Picture;

import java.awt.*;

public class Ball implements Thing {
    public float positionX, positionY, radius, speed = 10;
    public Color color = new Color((int) (Math.random() * 0xFFFFFF));
    private Camera camera;

    public Ball(float positionX, float positionY, float radius) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.radius = radius;
    }

    public Ball(float positionX, float positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
        radius = 100;
    }

    public Ball(float positionX, float positionY, Camera picture) {
        this(positionX, positionY);
        this.camera = picture;
    }

    public void update() {
        positionY += speed;

        if (positionY >= EnvironmentConfig.HEIGHT) {
            positionY = -camera.positionY;
        }
    }

    public void draw(Graphics g) {
        if (camera.canDraw((int)positionX, (int)positionY)) {
            g.drawImage(Picture.ballImage, (int) (positionX - camera.positionX), (int) (positionY - camera.positionY), 100, 100, null);
        }
    }
}
