package ui;

import core.GameState;
import core.ImageImport;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class MenuButton extends Button{

    private int index;
    private double x;
    private double y;
    private int rowIndex;
    private BufferedImage img;
    private BufferedImage[] buttonImgs;
    private double moveSpeed;
    private int lockY;
    private boolean mousePressed;


    public MenuButton(double x, double y,int width,int height, int lockY, double moveSpeed, int index, int rowIndex){
        super(x, y, width, height);
        this.x = x;
        this.y = y;
        this.lockY = lockY;
        this.moveSpeed = moveSpeed;
        this.index = index;
        this.rowIndex = rowIndex;

        importImg();
        hitbox = new Rectangle((int) x,(int) y,(int) (width/1.7),(int) (height/1.7));
    }

    @Override
    public void importImg() {
        buttonImgs = new BufferedImage[2];

        try {
            img = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/menu_buttons.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(int i=0;i<buttonImgs.length;i++){
            buttonImgs[i] = img.getSubimage(i * 394, rowIndex * 158,394,158);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(buttonImgs[index],(int) x,(int) y, (int) (394/1.7),(int) (158/1.7),null);
    }

    @Override
    public void moveUp(){
        if(y >= lockY){
            y -= moveSpeed;
        }else if(y >= lockY - 800 && shop){
            y -= moveSpeed;
        }
        hitbox.y = (int) y;
    }

    @Override
    public void moveDown(){
        if(y <= 880 + lockY){
            y += moveSpeed;
        }
        hitbox.y = (int) y;
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

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }

    public int getLockY() {
        return lockY;
    }

    public void setLockY(int lockY) {
        this.lockY = lockY;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public boolean isShop() {
        return shop;
    }

    public void setShop(boolean shop) {
        this.shop = shop;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }
}
