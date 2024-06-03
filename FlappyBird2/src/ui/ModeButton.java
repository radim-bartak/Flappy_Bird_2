package ui;

import core.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ModeButton extends Button{

    private int index;
    private int rowIndex;
    private double moveSpeed;
    private int lockY;
    private BufferedImage img;
    private BufferedImage[] btnImgs;
    private boolean shop;
    private boolean mousePressed;

    public ModeButton(double x, double y, int width, int height, int index, int lockY, int rowIndex, int moveSpeed) {
        super(x, y, width, height);
        this.index = index;
        this.lockY = lockY;
        this.rowIndex = rowIndex;
        this.moveSpeed = moveSpeed;

        hitbox = new Rectangle((int) x, (int) y,(int) (width/1.7),(int) (height/1.7));
        importImg();
    }

    //method for importing images of buttons from file
    @Override
    public void importImg() {
        btnImgs = new BufferedImage[2];

        try {
            img = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/mode_buttons.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(int i=0;i<btnImgs.length;i++){
            btnImgs[i] = img.getSubimage(i * width,rowIndex*height,width,height); //saves pressed and unpressed images of a button to a field
            //the type of the button is given by the row index of the object
        }
    }

    //method for rendering pressed or unpressed menu buttons according to index of the object
    @Override
    public void render(Graphics g) {
        g.drawImage(btnImgs[index],(int) x,(int) y, (int) (width/1.7),(int) (height/1.7),null);
    }

    //method that makes the button visible
    @Override
    public void moveUp(){
        if(y >= lockY && !shop){ //button moves up from offscreen with the menu until it is stopped by the lock
            y -= moveSpeed;
        }else if(y >= lockY - 800 && shop){
            y -= moveSpeed;
        }
        hitbox.y = (int) y;
    }

    //method that makes the button not visible
    @Override
    public void moveDown(){
        if(y <= 880 + lockY){ //button moves down offscreen
            y += moveSpeed;
        }
        hitbox.y = (int) y;
    }

    @Override
    public void setIndex(int index) {
        this.index = index;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public double getMoveSpeed() {
        return moveSpeed;
    }

    public void setMoveSpeed(double moveSpeed) {
        this.moveSpeed = moveSpeed;
    }

    public int getLockY() {
        return lockY;
    }

    public void setLockY(int lockY) {
        this.lockY = lockY;
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

    public boolean isShop() {
        return shop;
    }

    public void setShop(boolean shop) {
        this.shop = shop;
    }
}
