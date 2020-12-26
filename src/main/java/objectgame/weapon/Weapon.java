package objectgame.weapon;

import java.awt.*;

public interface Weapon {
    public double positionX = 0,getPositionY = 0;

    // cập nhật tọa độ đạn
    public void update();

    // vẽ hình đạn
    public void draw(Graphics g);
}
