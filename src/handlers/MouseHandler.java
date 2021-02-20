package src.handlers;

import java.awt.event.*;

import src.main.Game;
import src.supers.Button;

public class MouseHandler implements MouseListener {

    @Override
    public void mouseClicked(MouseEvent e) {
        

    }

    @Override
    public void mousePressed(MouseEvent e) {
        
        if(Button.checkCollision(e.getX(), e.getY(), Game.startButton)){
            if(Game.gameover){
                Game.startButton.pressed = true;
                ObjectHandler.list.clear();
                ObjectHandler.addObject(Game.bird);
                Game.gameover = false;
                Game.score = 0;
                Game.startButton.pressed = false;
            }
        }

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
       

    }

    @Override
    public void mouseExited(MouseEvent e) {
        

    }
    
}
