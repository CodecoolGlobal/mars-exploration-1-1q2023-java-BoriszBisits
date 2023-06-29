package com.codecool.marsexploration.output.service.ui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {
    public boolean spacePressed;
    public int mapNum=-1;
    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_D){
            if(mapNum<4) {
                mapNum++;
            }else{
                mapNum=4;
            }
            spacePressed = true;
        }
        if(code == KeyEvent.VK_A){
            if(mapNum>0) {
                mapNum--;
            }else{
                mapNum=0;
            }
            spacePressed = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == KeyEvent.VK_SPACE){
            spacePressed = false;
        }
    }
}
