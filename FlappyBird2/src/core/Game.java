package core;

import levelBuild.Cannon;
import levelBuild.*;
import entities.Player;
import ui.*;
import ui.Button;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Game implements Runnable{

    Random rn = new Random();
    private Window gameWindow;
    private Panel gamePanel;
    private Thread gameLoop;
    GameState gameState = GameState.START;

    private final int FPS = 60;
    private final int TPS = 200;
    private BufferedImage start;
    private BufferedImage gameOver;
    private BufferedImage hud;
    private Font font;

    private Player player;
    private Background bg;
    private Floor fl;
    private Pipe topPipes[];
    private Pipe botPipes[];
    private Cannon cannon;
    private Cannonball cannonball;
    private Coin coin;

    private ToggleMenuButton toggleMenuBtn;
    private MenuBackground menu_bg;
    private MenuButton menuBtnShop;
    private MenuButton menuBtnExit;
    private ModeButton modeBtnDay;
    private ModeButton modeBtnNight;
    private ShopButton shopBtn1;
    private ShopButton shopBtn2;
    private ShopButton shopBtn3;
    private ShopButton shopBtn4;
    private Button[] btns;

    private int currentScore;
    private int highScore;
    private int coins;
    private int coinsY = 900;

    //Initialization of all objects
    public Game(){
        bg = new Background(0,0,0.5); //Creates background object
        topPipes = new Pipe[5]; //New array of top pipes objects
        botPipes = new Pipe[5]; //New array of bottom pipes objects

        for(int i = 0; i < 5; i++){ //Adding pipe objects to arrays
            topPipes[i] = new TopPipe(1300+(i*500),0,1300+(i*500),1,100,400);
            botPipes[i] = new BottomPipe(1300+(i*500),0,1300+(i*500),1,100,400);
        }

        fl = new Floor(0, 0,1); //Creates floor object
        player = new Player(300,300,300,444,551,0.5,false,4.5,0); //Creates player object
        cannon = new Cannon(2500,300,2500,300, 114,102,0.7,1, true); //Creates canon object
        cannonball = new Cannonball(2510,310,2510,310,40,40,0.7,1.2,true); //Creates canonball object
        coin = new Coin(1550,300,1550,300,35,35,1,0.5,true); //Creates coin object

        menu_bg = new MenuBackground(640 - 200,1000,1000,10,423,1305,false);

        //Initialization of all buttons
        toggleMenuBtn = new ToggleMenuButton(1200,20,56,57,1,getGameState()); //button of toggling the menu
        menuBtnShop = new MenuButton(640-105,880+280,394,158,280,10,1,0); //button for toggling the shop in the menu
        menuBtnExit = new MenuButton(640-105,880+480,394,158,480,10,1,1); //button for exiting the game
        modeBtnDay = new ModeButton(640 - 105, 880+385,134,137,1,385,0,10); //button for day mode
        modeBtnNight = new ModeButton(690, 880+385,134,137,1,385,1,10); //button for night mode
        shopBtn1 = new ShopButton(640-115,880+290+800,252,255,1,0,10,290); //buttons for buying skins in the shop
        shopBtn2 = new ShopButton(677,880+290+800,252,255,1,1,10,290);
        shopBtn3 = new ShopButton(640-115,880+430+800,252,255,1,2,10,430);
        shopBtn4 = new ShopButton(677,880+430+800,252,255,1,3,10,430);

        btns = new Button[]{menuBtnShop, menuBtnExit, modeBtnDay, modeBtnNight, shopBtn1, shopBtn2, shopBtn3, shopBtn4}; //field of all menu buttons

        gamePanel = new Panel(this);
        gameWindow = new Window(gamePanel);
        gamePanel.requestFocus();

        startGameLoop(); //game loop starts
        try {
            start = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/start_screen.png")));
            gameOver = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/gameover_screen.png")));
            hud = ImageIO.read(Objects.requireNonNull(this.getClass().getResource("/hud.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void startGameLoop(){
        gameLoop = new Thread(this);
        gameLoop.start();
    }

    //method for game loop thread
    @Override
    public void run() {
        double timePerFrame = 1000000000.0 / FPS;
        double timePerTick = 1000000000.0 / TPS;

        long previousTime = System.nanoTime();

        int frames = 0;
        int ticks = 0;

        double tickDiff = 0;
        double frameDiff = 0;

        while(true){
            long currentTime = System.nanoTime();

            tickDiff += (currentTime - previousTime) / timePerTick;
            frameDiff += (currentTime - previousTime) / timePerFrame;
            previousTime = currentTime;

            if(tickDiff >= 1){ //TPS
                tick();
                ticks++;
                tickDiff--;
            }

            if(frameDiff >= 1){ //FPS
                gamePanel.repaint();
                frames++;
                frameDiff--;
            }
        }
    }

    //method for one tick of the game
    public void tick(){
        switch (gameState){
            case START: //tick while start screen
                bg.move(); //background moving
                fl.move(); //floor moving
                menu_bg.moveDown(); //menu and menu buttons move under the screen
                for (Button btn : btns) {
                    btn.moveDown();
                }
                if(coinsY <= 900){
                    coinsY += 10;
                }
                break;
            case PLAYING: //tick while playing
                player.fall(); //player falls and jumps
                bg.move();
                fl.move();
                coin.move(); //game objects starts moving
                cannon.move();
                cannonball.move();

                toggleMenuBtn.setGameState(GameState.PLAYING);
                toggleMenuBtn.updateVisibility(); //updates visibility of button for toggling menu

                for (Pipe topPipe : topPipes) {
                    topPipe.move(); //each top pipe moves
                    if(topPipe.getPipeHitbox().intersects(player.getHitbox())){ //if player collides with pipe then game over
                        gameState = GameState.GAME_OVER;
                    }
                    if(topPipe.getX() == 200){  //if player gets through a pipe, player gets a point
                        currentScore += 1;
                        if(currentScore > highScore){
                            highScore = currentScore;
                        }
                    }
                }
                for (Pipe botPipe : botPipes) { //each bottom pipe moves
                    botPipe.move();
                    if(botPipe.getPipeHitbox().intersects(player.getHitbox())){ //if player collides with pipe then game over
                        gameState = GameState.GAME_OVER;
                    }
                }
                if(player.getHitbox().intersects(fl.getFlHitbox())){ //if player collides with floor then game over
                    gameState = GameState.GAME_OVER;
                }
                if(cannonball.getX() <= -400){ //cannonball returns to cannon
                    cannonball.setX(cannon.getX() + 10);
                    cannonball.setY(cannon.getY() + 10);
                }
                if(player.getHitbox().intersects(cannonball.getHitbox())){ //if player collides with a cannonball then game over
                    gameState = GameState.GAME_OVER;
                }
                if(player.getHitbox().intersects(coin.getHitbox())){ //if player collects a coin, the coin will add up to the number of collected coins
                    coins++;
                    coin.setX(coin.getX()+coin.getStartX()); //after the coin is collected, its object will move to another location
                }
                break;
            case GAME_OVER:
                toggleMenuBtn.setGameState(GameState.GAME_OVER);
                toggleMenuBtn.updateVisibility(); //updates visibility of button for toggling menu
                break;
            case MENU: //tick while in menu
                menu_bg.moveUp(); //menu with its buttons moves up so the user can see it
                for (Button btn : btns) {
                    btn.moveUp();
                }
                if(menu_bg.isShop()){
                    if(coinsY >= 100){
                        coinsY -= 10;
                    }
                }
                break;
        }
    }

    //method for rendering objects of the game
    public void render(Graphics g) throws IOException, FontFormatException {

        bg.render(g); //rendering background
        player.render(g); //rendering player
        for (Pipe topPipe : topPipes) { //rendering top pipes from array
            topPipe.render(g);
        }
        for (Pipe botPipe : botPipes) { //rendering bottom pipes from array
            botPipe.render(g);
        }
        coin.render(g); //rendering coin
        cannonball.render(g); //rendering cannonball
        fl.render(g); //rendering floor
        cannon.render(g); //rendering canon
        toggleMenuBtn.render(g); //rendering button for toggling the menu
        switch (gameState) {
            case PLAYING: //render when playing
                g.drawImage(hud,0,0,null);

                font = new Font("Agency FB",Font.BOLD,40);
                g.setFont(font);
                g.setColor(Color.white);
                g.drawString(String.valueOf(currentScore), 180, 65); //shows current score
                g.drawString(String.valueOf(coins), 180, 130); //shows number of collected coins
                break;
            case GAME_OVER: //render game over screen
                g.drawImage(gameOver,0,0,null); //render game over screen

                g.setFont(font);
                g.setColor(Color.white);
                g.drawString("HIGHSCORE: " + String.valueOf(highScore), 540, 300); //render high score title

                currentScore = 0; //current score resets back to zero
                break;
            case START: //render start screen
                g.drawImage(start,0,0,null); //render start screen

                player.setY(player.getStartY()); //resets player back to starting position
                for (Pipe topPipe : topPipes) { //resets top pipes back to starting position
                    topPipe.setX(topPipe.getStartX());
                    topPipe.setY(-(120 + rn.nextInt(80))); //random gap between pipes
                }
                for (Pipe botPipe : botPipes) { //resets bottom pipes back to starting position
                    botPipe.setX(botPipe.getStartX());
                    botPipe.setY(400+(120 + rn.nextInt(80))); //random gap between pipes
                }
                //other objects move to their start position
                coin.setX(coin.getStartX());
                cannon.setX(cannon.getStartX());
                cannon.setY(cannon.getStartY());
                cannonball.setX(cannonball.getStartX());
                cannonball.setY(cannonball.getStartY());

                menu_bg.render(g);
                for (Button btn : btns) {
                    btn.render(g);
                }
                break;
            case MENU: //render menu
                menu_bg.render(g);
                for (Button btn : btns) {
                    btn.render(g);
                }
                for (int i = 0; i < 2; i++){
                toggleMenuBtn.setGameState(GameState.MENU);
                toggleMenuBtn.setY(20);
                }
                //draws the number of coins the player has available to spend in the shop
                font = new Font("Agency FB",Font.BOLD,45);
                g.setFont(font);
                g.setColor(Color.white);
                g.drawString(String.valueOf(coins), 715, coinsY);
                break;
            }
        }

    public Player getPlayer() {
        return player;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void setGameState(GameState gameState) {
        this.gameState = gameState;
    }

    public Background getBg() {
        return bg;
    }

    public Floor getFl() {
        return fl;
    }

    public MenuBackground getMenu_bg() {
        return menu_bg;
    }

    public Button[] getBtns() {
        return btns;
    }

    public ToggleMenuButton getToggleMenuBtn() {
        return toggleMenuBtn;
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }
}