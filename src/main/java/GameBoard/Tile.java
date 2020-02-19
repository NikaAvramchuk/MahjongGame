package GameBoard;

import GUI.ResizeImages;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class Tile extends JButton {
    private int x;
    private int y;
    private int z;

    private int level;

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

    public void setCoords(int z, int y, int x) {
        this.z = z;
        this.y = y;
        this.x = x;
        setVisible(true);
    }



    public static ArrayList<Tile> allTiles = new ArrayList<Tile>();


    public static void createAllTiles() {
        for (int i = 0; i < 144; i++) {
            Tile t = new Tile();
            t.setContentAreaFilled(false);
            t.setIcon(new ImageIcon("C:\\Users\\Marcin\\Desktop\\Cmd\\tilesnew.png"));
            allTiles.add(t);
            if (i % 2 == 0)
                t.setTileId(0);
            else
                t.setTileId(1);
        }
    }

    }





