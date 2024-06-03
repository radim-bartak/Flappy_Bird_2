package levelBuild;

import core.ImageImport;

import java.awt.*;


public abstract class Pipe implements ImageImport {

    protected double x;
    protected double y;
    protected int startX;
    protected double moveSpeed;
    protected int width;
    protected int height;
    protected Rectangle pipeHitbox;


    public Pipe(double x, double y, int startX, double moveSpeed, int width, int height) {
        this.x = x;
        this.y = y;
        this.startX = startX;
        this.moveSpeed = moveSpeed;
        this.width = width;
        this.height = height;
        this.pipeHitbox = new Rectangle((int) x,(int) y,width,height); //creates hitbox for collision
        importImg();
    }

    @Override
    public abstract void importImg();
    @Override
    public abstract void render(Graphics g);

    public abstract void move();

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(double moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public Rectangle getPipeHitbox() {
        return pipeHitbox;
    }

    public void setPipeHitbox(Rectangle pipeHitbox) {
        this.pipeHitbox = pipeHitbox;
    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }
}

