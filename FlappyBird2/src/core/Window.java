package core;

import javax.swing.*;

public class Window extends JFrame {

    public Window(Panel gamePanel){
        this.setTitle("Flappy Bird");
        this.setResizable(false);
        this.add(gamePanel);
        this.pack();

        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
