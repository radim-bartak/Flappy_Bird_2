package ui;

import core.GameState;
import core.ImageImport;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class ToggleMenuButton implements ImageImport{

    private int x;
    private int y;
    private int width;
    private int height;
    private int index;
    private GameState gameState;
    private BufferedImage[] buttonImgs;
    private BufferedImage img;
    private boolean mousePressed;
    private Rectangle hitbox;

    public ToggleMenuButton(int x,int y, int width, int height, int index, GameState gameState) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.index = index;
        this.gameState = gameState;

        importImg();
        hitbox = new Rectangle(x,y,width,height);
    }

    //method for importing images of buttons from file
    @Override
    public void importImg() {
        buttonImgs = new BufferedImage[2];

        try {
            img = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/toggle_menu_button.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        for(int i=0;i<buttonImgs.length;i++){
            buttonImgs[i] = img.getSubimage(i * width, 0, width, height); //saves pressed and unpressed images of a button to a field
        }
    }

    //method for rendering pressed or unpressed menu buttons according to index of the object
    @Override
    public void render(Graphics g) {
        g.drawImage(buttonImgs[index], x, y, width, height,null);
    }

    //method for updating the visibility of the button
    public void updateVisibility(){
        if(gameState == GameState.PLAYING){ //button becomes not visible in the playing game state
            y = - 100;
        }else{
            y = 20;
        }
        hitbox.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
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
