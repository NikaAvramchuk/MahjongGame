package GameBoard;

import GUI.QualityIcon;
import GUI.ResizeImages;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Tile extends JButton {
    private int x;
    private int y;
    private int z;

    public static ArrayList<String> imagesPaths = new ArrayList<String>();

    private int level;

    public static ArrayList<Tile> allTiles = new ArrayList<Tile>(Tile.createAllTilesInBoard((new ImageIcon("C:\\Users\\Marcin\\Desktop\\Cmd\\tile2.png")), new ImageIcon("C:\\Users\\Marcin\\Desktop\\Cmd\\tilesnew.png")));

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private int IDTile;

    private boolean tileIsEnable;

    public boolean tileIsEnable() {
        return tileIsEnable;
    }

    public void tileSetEnable(boolean enable) {
        tileIsEnable = enable;
    }

    public int getTileX() {
        return x;
    }

    public void setTileX(int x) {
        this.x = x;
    }

    public int getTileY() {
        return y;
    }

    public void setTileY(int y) {
        this.y = y;
    }

    public int getTileZ() {
        return z;
    }

    public void setTileZ(int z) {
        this.z = z;
    }

    public int getTileID() {
        return IDTile;
    }

    public void setTileId(int id) {
        IDTile = id;
    }





    public static ArrayList<Tile> createAllTilesInBoard(ImageIcon imageIcon, ImageIcon imageIcon1) {

        try {
            File dir = new File("C:\\Users\\Marcin\\Desktop\\TilesOrigin");
            File[] listOfFiles = dir.listFiles();
            for (File file : listOfFiles) {
                ResizeImages.resizeImage(file.getAbsolutePath(), "C:\\Users\\Marcin\\IdeaProjects\\Mahjong\\src\\main\\resources\\" + file.getName());
                Tile.imagesPaths.add("C:\\Users\\Marcin\\IdeaProjects\\Mahjong\\src\\main\\resources\\" + file.getName());
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

            ArrayList<Tile> allTiles = new ArrayList<Tile>();
            for (int i = 0; i < 144; i++) {
                Tile t = new Tile();
                t.setContentAreaFilled(false);
                allTiles.add(t);
                if (i % 9 == 0) {
                    t.setTileId(0);
                    System.out.println(imagesPaths.size());
                    t.setIcon(new ImageIcon(imagesPaths.get(0)));
                }
                else if (i % 9 == 1) {
                    t.setTileId(1);
                    t.setIcon(new ImageIcon(imagesPaths.get(1)));
                }
                else if (i % 9 == 2){
                    t.setTileId(2);
                    t.setIcon(new ImageIcon(imagesPaths.get(2)));
                }
                else if (i % 9 == 3){
                    t.setTileId(3);
                    t.setIcon(new ImageIcon(imagesPaths.get(3)));
                }
                else if (i % 9 == 4){
                    t.setTileId(4);
                    t.setIcon(new ImageIcon(imagesPaths.get(4)));
                }
                else if (i % 9 == 5){
                    t.setTileId(5);
                    t.setIcon(new ImageIcon(imagesPaths.get(5)));
                }
                else {
                    t.setTileId(6);
                    t.setIcon(new ImageIcon(imagesPaths.get(6)));
                }
            }

            return allTiles;

    }
}



