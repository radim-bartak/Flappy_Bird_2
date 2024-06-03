package ui;

import core.GameState;
import core.ImageImport;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ToggleMenuButton implements ImageImport{

    private double x;
    private double y;
    private int width;
    private int height;
    private int index;
    private GameState gameState;
    private BufferedImage[] buttonImgs;
    private BufferedImage img;
    private boolean mousePressed;
    private Rectangle hitbox;

    public ToggleMenuButton(double x,double y, int width, int height, int index, GameState gameState) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.index = index;
        this.gameState = gameState;

        importImg();
        hitbox = new Rectangle((int) x,(int) y,width,height);
    }


    @Override
    public void importImg() {
        buttonImgs = new BufferedImage[2];

        try {
            img = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/toggle_menu_button.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(int i=0;i<buttonImgs.length;i++){
            buttonImgs[i] = img.getSubimage(i * width, 0, width, height);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(buttonImgs[index],(int) x,(int) y, width, height,null);
    }

    public void update(){
        if(gameState == GameState.START || gameState == GameState.GAME_OVER || gameState == GameState.MENU){
            if(index == 1){
                if(mousePressed){
                    y = -100;
                }
            }
        }else{
            y = -100;
        }

        hitbox.x = (int) x;
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

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public boolean isMousePressed() {
        return mousePressed;
    }

    public void setMousePressed(boolean mousePressed) {
        this.mousePressed = mousePressed;
    }

    public Rectangle getHitbox() {
        return hitbox;
    }

    public void setHitbox(Rectangle hitbox) {
        this.hitbox = hitbox;
    }
}
