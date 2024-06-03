package ui;

import core.GameState;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ShopButton extends Button{
    private int index;
    private int rowIndex;
    private double moveSpeed;
    private int lockY;
    private BufferedImage img;
    private BufferedImage[] btnImgs;
    private boolean shop;
    private boolean mousePressed;

    public ShopButton(double x, double y, int width, int height,int index, int rowIndex, double moveSpeed, int lockY) {
        super(x, y, width, height);
        this.index = index;
        this.lockY = lockY;
        this.rowIndex = rowIndex;
        this.moveSpeed = moveSpeed;

        importImg();
        hitbox = new Rectangle((int) x, (int) y,(int) (width/2.5),(int) (height/2.5));
    }

    @Override
    public void importImg() {
        btnImgs = new BufferedImage[2];

        try {
            img = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/shop_buttons.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(int i=0;i<btnImgs.length;i++){
            btnImgs[i] = img.getSubimage(i * width,rowIndex*height,width,height);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(btnImgs[index],(int) x,(int) y, (int) (width/2.5),(int) (height/2.5),null);
    }

    @Override
    public void moveUp(){
        if(y >= lockY && shop){
            y -= moveSpeed;
        }else if(y >= lockY + 800 && !shop){
            y -= moveSpeed;
        }
        hitbox.y = (int) y;
    }

    @Override
    public void moveDown(){
        if(y <= 880 + lockY + 800){
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

    public boolean isShop() {
        return shop;
    }

    public void setShop(boolean shop) {
        this.shop = shop;
    }
}
