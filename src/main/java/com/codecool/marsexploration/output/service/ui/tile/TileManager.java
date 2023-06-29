package com.codecool.marsexploration.output.service.ui.tile;

import com.codecool.marsexploration.output.service.ui.GamePanel;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int[][] mapTileNum;

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[5];
        mapTileNum = new int[50][50];
        getTileImage();
        loadMap(0);
    }

    public void loadMap(int mapNum) {
        try{
            InputStream is = getClass().getResourceAsStream("/maps/exploration-"+mapNum+".map");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            int col =0;
            int row = 0;
            while (col < gp.side && row < gp.side) {
                String line = br.readLine();
                while(col < gp.side){
                    String character = String.valueOf(line.charAt(col));
                    int num;
                    switch (character){
                        case "%":
                            num = 1;
                            break;
                        case "#":
                            num = 2;
                            break;
                        case "&":
                            num = 3;
                            break;
                        case "*":
                            num = 4;
                            break;
                        default:
                            num = 0;
                    }
                    mapTileNum[col][row]=num;
                    col++;
                }
                if(col == gp.side){
                    col = 0;
                    row++;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/empty.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/mineral.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/mountain.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/pit.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void draw(Graphics2D g2) {
        int col = 0;
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gp.side && row < gp.side) {
            int tileNum = mapTileNum[col][row];
            g2.drawImage(tile[tileNum].image, x, y, gp.tileSize, gp.tileSize, null);
            col++;
            x += gp.tileSize;
            if(col == gp.side){
                col = 0;
                x = 0;
                row++;
                y += gp.tileSize;
            }
        }
    }
}
