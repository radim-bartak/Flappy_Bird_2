package levelBuild;

import core.ImageImport;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Cannon implements ImageImport {
    private double x;
    private double y;
    private int startX;
    private int startY;
    private int width;
    private int height;
    private double moveSpeedX;
    private double moveSpeedY;
    private boolean falling;
    private BufferedImage img;

    public Cannon(double x, double y, int startX, int startY, int width, int height, double moveSpeedX, double moveSpeedY, boolean falling) {
        this.x = x;
        this.y = y;
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
        this.moveSpeedX = moveSpeedX;
        this.moveSpeedY = moveSpeedY;
        this.falling = falling;

        importImg();
    }

    @Override
    public void importImg() {
        try {
            img = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/cannon.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img,(int) x,(int) y, width, height,null);
    }

    public void move(){
        if(x > 1120){
            x -= moveSpeedX;
        }else{
            if(falling){
                y += moveSpeedY;
                if(y >= 570){
                    falling = false;
                }
            }else {
                y -= moveSpeedY;
                if (y <= 50) {
                    falling = true;
                }
            }
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

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
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

    public double getMoveSpeedX() {
        return moveSpeedX;
    }

    public void setMoveSpeedX(double moveSpeedX) {
        this.moveSpeedX = moveSpeedX;
    }

    public double getMoveSpeedY() {
        return moveSpeedY;
    }

    public void setMoveSpeedY(double moveSpeedY) {
        this.moveSpeedY = moveSpeedY;
    }

    public boolean isFalling() {
        return falling;
    }

    public void setFalling(boolean falling) {
        this.falling = falling;
    }
}