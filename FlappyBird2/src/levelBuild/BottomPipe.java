package levelBuild;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class BottomPipe extends Pipe{

    private BufferedImage img;
    Random rn = new Random();

    public BottomPipe(double x, double y, int startX, double moveSpeed, int width, int height) {
        super(x, y, startX, moveSpeed, width, height);
    }

    @Override
    public void importImg() {
        try {
            img = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/bottom_pipe.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(img, (int) x, (int) y, width, height,null);
    }

    //method for bottom pipe move
    @Override
    public void move() {
        x -= getMoveSpeed();
        getPipeHitbox().x = (int) x;
        getPipeHitbox().y = (int) y;

        if(x <= -100){ //if pipe leaves the screen, it travels back behind all other pipes
            x = 2400;
            setY(400 + (50 + rn.nextInt(100)));
        }
    }
}
