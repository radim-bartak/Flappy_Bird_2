package ui;

import core.GameState;
import core.ImageImport;

import java.awt.*;

public abstract class Button implements ImageImport{
    protected double x;
    protected double y;
    protected int width;
    protected int height;
    protected boolean mousePressed;
    protected boolean shop;
    protected Rectangle hitbox;

    public Button(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.shop = false;
    }

    @Override
    public abstract void importImg();
    @Override
    public abstract void render(Graphics g);

    public abstract void moveUp();
    public abstract void moveDown();

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

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public void setIndex(int index) {
    }

    public boolean isShop() {
        return shop;
    }

    public void setShop(boolean shop) {
        this.shop = shop;
    }
}
