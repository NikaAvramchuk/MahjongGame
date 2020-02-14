package GameBoard;

import javafx.css.Size;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;

public class Tile extends JButton {
    private int x;
    private int y;
    private int z;
    private int Id;
    private boolean isEnable;

//    public void paintComponent(Graphics g) {
//        Graphics2D g2 = (Graphics2D) g;
//
//        //left
//        Polygon ml = new Polygon();
//        ml.addPoint(x, y);
//        ml.addPoint(x - 5, y + 10);
//        ml.addPoint(x - 5, y + 60);
//        ml.addPoint(x, y + 60);
//        g2.setPaint(Color.LIGHT_GRAY);
//        g2.fillPolygon(ml);
//
////20, 10, 46, 60
//
//        //Middle-bottom
//        Polygon mb = new Polygon();
//        mb.addPoint(x, y + 60);
//        mb.addPoint(x - 5, y + 70);
//        mb.addPoint(x + 41, y + 70);
//        mb.addPoint(x + 46, y + 60);
//        g2.setPaint(Color.LIGHT_GRAY);
//        g2.fillPolygon(mb);
//
//
//        //top layer
//        Rectangle2D top = new Rectangle2D.Double(x, y, SizeOfTiles.WIDTH.getValue(), SizeOfTiles.HEIGHT.getValue());
//
//    }
    public Tile() {

    }



    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public void setCoords(int z, int y, int x) {
        this.z = z;
        this.y = y;
        this.x = x;
    }


    public static ArrayList<Tile> allTiles = new ArrayList<Tile>();

    public static void createAllTiles() {
        for (int i = 0; i < 144; i++) {
            Tile t = new Tile();
            allTiles.add(t);
        }
    }

    public static ArrayList<Tile> createAllTilesinBoard() {
        ArrayList<Tile> tiles = new ArrayList<Tile>();
        for (int i = 0; i < 144; i++) {
            Tile t = new Tile();
            tiles.add(t);

        }

        return tiles;
    }


}