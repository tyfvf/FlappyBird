package src.handlers;

import java.awt.event.*;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException; 
import javax.sound.sampled.UnsupportedAudioFileException; 

import src.main.Game;

public class KeyHandler implements KeyListener {
    
    @Override
    public void keyTyped(KeyEvent e){

    }

    @Override
    public void keyPressed(KeyEvent e){
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            
            try {
                SoundHandler.playSound("sfx_wing.wav");
            } catch (LineUnavailableException | UnsupportedAudioFileException | IOException o) {
                
                o.printStackTrace();
            }
            Game.bird.setVelY(-5);
        }

    }

    @Override
    public void keyReleased(KeyEvent e){

    }

}
