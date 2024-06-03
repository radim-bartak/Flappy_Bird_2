package ui;

import core.GameState;
import core.ImageImport;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class MenuBackground implements ImageImport {
    private double x;
    private double y;
    private int startY;
    private double moveSpeed;
    private int width;
    private int height;
    private BufferedImage img;
    private boolean shop;

    public MenuBackground(double x, double y, int startY, double moveSpeed, int width, int height, boolean shop) {
        this.x = x;
        this.y = y;
        this.startY = startY;
        this.moveSpeed = moveSpeed;
        this.width = width;
        this.height = height;
        this.shop = shop;

        importImg();
    }

    @Override
    public void importImg() {
        try {
            img = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/menu_background.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img,(int) x,(int) y, width, height,null);
    }

    //method that makes the menu or the shop visible
    public void moveUp(){
        if(y >= 120 && !shop){ //menu moves up from offscreen until it is stopped
            y -= moveSpeed;
        }else if(y >= -680 && shop){ //shop moves up from offscreen until it is stopped
            y -= moveSpeed;
        }
    }

    //method that makes the menu or the shop not visible
    public void moveDown(){
        if(y <= 1000){ //menu and shop move down offscreen
            y += moveSpeed;
        }
    }

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

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
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

    public boolean isShop() {
        return shop;
    }

    public void setShop(boolean shop) {
        this.shop = shop;
    }
}
