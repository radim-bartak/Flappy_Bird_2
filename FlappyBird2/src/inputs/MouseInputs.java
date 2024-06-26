package inputs;

import core.Panel;
import core.GameState;
import ui.*;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseInputs implements MouseListener, MouseMotionListener {

    private Panel gamePanel;

    public MouseInputs(Panel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    //if the user presses a button, its index changes to 0, which makes it appear as pressed
    @Override
    public void mousePressed(MouseEvent e) {
        switch (gamePanel.getGame().getGameState()){
            case START:
                if(isInToggleMenuBtn(e,toggleMenuBtn())){ //goes to menu game state if the button toggling the menu is pressed
                    toggleMenuBtn().setIndex(0);
                    toggleMenuBtn().setMousePressed(true);
                    gamePanel.getGame().setGameState(GameState.MENU);
                }else{
                    gamePanel.getGame().setGameState(GameState.PLAYING); //game starts
                }
                break;
            case PLAYING:
                gamePanel.getGame().getPlayer().setJumping(true); //player jumps
                break;
            case GAME_OVER:
                if(isInToggleMenuBtn(e,toggleMenuBtn())){ //goes to menu game state if the button toggling the menu is pressed
                    toggleMenuBtn().setMousePressed(true);
                    gamePanel.getGame().setGameState(GameState.MENU);
                }else {
                    gamePanel.getGame().setGameState(GameState.START); //game back to start
                }
                break;
            case MENU:
                if(isInToggleMenuBtn(e,toggleMenuBtn())){ //goes back to starting state if the button toggling the menu is pressed in the menu
                    toggleMenuBtn().setIndex(0);
                    toggleMenuBtn().setMousePressed(true);
                    gamePanel.getGame().setGameState(GameState.START);
                }
                for(int i = 0; i < btns().length; i++){
                    if(isIn(e,btns()[i])){
                        btns()[i].setIndex(0);
                        btns()[i].setMousePressed(true);
                    }
                }
                break;
        }
    }

    //if the user releases a button, its use is applied and its index changes back to 1
    @Override
    public void mouseReleased(MouseEvent e) {
        if(toggleMenuBtn().isMousePressed()){
            toggleMenuBtn().setIndex(1);
            toggleMenuBtn().setMousePressed(false);
            gamePanel.getGame().getMenu_bg().setShop(false); //menu and shop closes
            for(int i = 0; i < btns().length; i++){
                btns()[i].setShop(false);
            }
        }
        if(gamePanel.getGame().getGameState() == GameState.MENU){
            for(int i = 0; i < btns().length; i++){
                if(btns()[i].isMousePressed()) {
                    if (btns()[0].isMousePressed()) { //shop button
                        gamePanel.getGame().getMenu_bg().setShop(true); //opens shop
                        for (int j = 0; j < btns().length; j++) {
                            btns()[j].setShop(true);
                        }
                    } else if (btns()[1].isMousePressed()) { //exit button
                        System.exit(0); //exits the game
                    }else if(btns()[2].isMousePressed()){  //day mode button
                        gamePanel.getGame().getBg().setY(0);
                        gamePanel.getGame().getFl().setY(0);
                    }else if(btns()[3].isMousePressed()){ //night mode button
                        gamePanel.getGame().getBg().setY(-800);
                        gamePanel.getGame().getFl().setY(-800);
                    }else if(btns()[4].isMousePressed()){ //default skin button
                        gamePanel.getGame().getPlayer().setSkin(0);
                    }else if(btns()[5].isMousePressed()){ //button for buying the first skin
                        if(gamePanel.getGame().getCoins() >= 10){ //if the player has enough coins, the skin equips
                            gamePanel.getGame().getPlayer().setSkin(1);
                            gamePanel.getGame().setCoins(gamePanel.getGame().getCoins()-10);
                        }
                    }else if(btns()[6].isMousePressed()){ //button for buying the second skin
                        if(gamePanel.getGame().getCoins() >= 25){
                            gamePanel.getGame().getPlayer().setSkin(2);
                            gamePanel.getGame().setCoins(gamePanel.getGame().getCoins()-25);
                        }
                    }else if(btns()[7].isMousePressed()){ //button for buying the third skin
                        if(gamePanel.getGame().getCoins() >= 50){
                            gamePanel.getGame().getPlayer().setSkin(3);
                            gamePanel.getGame().setCoins(gamePanel.getGame().getCoins()-50);
                        }
                    }
                }
                btns()[i].setIndex(1);
                btns()[i].setMousePressed(false);
            }
        }
    }


    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    public boolean isIn(MouseEvent e, Button btn) {
        return btn.getHitbox().contains(e.getX(), e.getY());
    }

    public boolean isInToggleMenuBtn(MouseEvent e, ToggleMenuButton btn) {
        return btn.getHitbox().contains(e.getX(), e.getY());
    }

    public Button[] btns(){
        return gamePanel.getGame().getBtns();
    }

    public ToggleMenuButton toggleMenuBtn(){
        return gamePanel.getGame().getToggleMenuBtn();
    }
}
