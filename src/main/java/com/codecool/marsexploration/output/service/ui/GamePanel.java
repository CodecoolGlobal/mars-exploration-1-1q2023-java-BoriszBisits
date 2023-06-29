package com.codecool.marsexploration.output.service.ui;

import com.codecool.marsexploration.output.service.ui.tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    Thread gameThread;
    KeyHandler keyH = new KeyHandler();
    TileManager tileM = new TileManager(this);

    public int tileSize = 24;
    public int panelSize;
    public int side;

    public GamePanel(int side) {
        this.side = side;
        this.panelSize = tileSize * side;
        this.setPreferredSize(new Dimension(panelSize, panelSize));
        this.setBackground(Color.BLACK);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            update();
            repaint();
        }
    }

    public void update() {
        if (keyH.spacePressed) {
            tileM.loadMap(keyH.mapNum);
            keyH.spacePressed = false;
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        g2.dispose();
    }
}
