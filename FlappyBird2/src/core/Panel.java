package core;

import inputs.KeyInputs;
import inputs.MouseInputs;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Panel extends JPanel {

    private Game game;
    private KeyInputs keyInputs;
    private MouseInputs mouseInputs;

    private final int windowWidth = 1280;
    private final int windowHeight = 800;

    private BufferedImage img;

    public Panel(Game game){
        this.game = game;

        mouseInputs = new MouseInputs(this);
        keyInputs = new KeyInputs(this);

        Dimension size = new Dimension(windowWidth,windowHeight);
        setPreferredSize(size);

        addKeyListener(keyInputs);
        addMouseListener(mouseInputs);
        addMouseMotionListener(mouseInputs);
    }

    public Game getGame() {
        return game;
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        g.drawImage(img,0,0,1280,800,null);

        try {
            game.render(g);
        } catch (IOException | FontFormatException e) {
            throw new RuntimeException(e);
        }
    }

    public int getWindowWidth() {
        return windowWidth;
    }

    public int getWindowHeight() {
        return windowHeight;
    }
}
