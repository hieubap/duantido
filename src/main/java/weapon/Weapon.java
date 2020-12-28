package weapon;

import enemy.Enemy;
import objectgame.ObjectGame;

import java.awt.*;

public interface Weapon extends ObjectGame {
    public void update();
    public void draw(Graphics g);
    public boolean isCollection(Enemy enemy);
}
