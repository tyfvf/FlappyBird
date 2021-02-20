package src.gameobjects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

import src.enums.TubeType;
import src.handlers.ObjectHandler;
import src.handlers.SoundHandler;
import src.loaders.GraphicsLoader;
import src.main.Game;
import src.supers.GameObject;

public class Tube extends GameObject {

    TubeType type;

    BufferedImage tubeBlock;
    BufferedImage tube;

    public Tube(int x, int y, int width, int height, TubeType type) {
        super(x, y, width, height);

        this.type = type;
        this.velX = 3;

        tube = GraphicsLoader.loadGraphics("tube.png");

        if (type == TubeType.BOTTOM) {
            tubeBlock = GraphicsLoader.loadGraphics("tubebottomdown.png");
        } else if (type == TubeType.TOP) {
            tubeBlock = GraphicsLoader.loadGraphics("tubebottomtop.png");
        }

    }

    @Override
    public void tick() {
        x -= velX;

        if(x + width < 0){
            ObjectHandler.removeObject(this);
            
            if (type == TubeType.TOP){
                try {
                    SoundHandler.playSound("point.wav");
                } catch (LineUnavailableException | UnsupportedAudioFileException | IOException e) {
                    
                    e.printStackTrace();
                }
                Game.score += 1;
            }
        }
    }

    @Override
    public void render(Graphics g) {
        if(type == TubeType.BOTTOM){
            g.drawImage(tube, x, y, 52, height, null);
            g.drawImage(tubeBlock, x , y , null);
        } else if(type == TubeType.TOP){
            g.drawImage(tube, x, y, 52, height, null);
            g.drawImage(tubeBlock, x , y + height - 23  , null);
        }
    }
    
}
