package player;

public class Direction {

    public boolean up, down, left, right;

    public Direction() {
        up = false;
        down = false;
        left = false;
        right = false;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public boolean isLeft() {
        return left;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public boolean isRight() {
        return right;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setAllFalse() {
        up = false;
        down = false;
        left = false;
        right = false;
    }
}
