package levelBuild;

import core.ImageImport;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Floor extends Background implements ImageImport {

    BufferedImage img;
    Rectangle flHitbox;

    public Floor(double x, int y, double moveSpeed) {
        super(x, y, moveSpeed);
        flHitbox = new Rectangle((int) x,684,2560,116); //creates hitbox for collision

    }

    @Override
    public void importImg() {
        try {
            img = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/floor.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img,(int) x,y,null);
    }

    public void move(){ //Method for moving background
        x -= getMoveSpeed();

        if(x < -1280){ //if half of the floor picture is outside the window, the background resets
            x = 0;
        }
    }

    public Rectangle getFlHitbox() {
        return flHitbox;
    }

    public void setFlHitbox(Rectangle flHitbox) {
        this.flHitbox = flHitbox;
    }
}
