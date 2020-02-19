package GUI;

import GameBoard.SizeOfTiles;
import GameBoard.*;
import javafx.css.Size;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static GameBoard.Tile.allTiles;

public class Panel extends JLayeredPane {
    static ArrayList<Tile> allTilesinBoard = new ArrayList<Tile>();
    Tile[] remowedTiles = new Tile[2];
    ArrayList<Tile> compareTiles = new ArrayList<Tile>();
    JButton jButton1;
    JButton jButton2;
    JButton jButton3;
    JButton jButton4;

    int x = 20;
    int y = 10;
    Tile t;


    public Panel() {
        compareTiles = new ArrayList<Tile>();
        Border border = new TileBorder(3);
        setLayout(null);
        createBoard();
        setLocationOnBoard(allTilesinBoard);

        addActionListen(allTilesinBoard);
        jButton1 = new JButton();
        jButton1.setBounds(500, 20, SizeOfTiles.WIDTH.getValue(), SizeOfTiles.HEIGHT.getValue());
        jButton1.setBorder(border);
        add(jButton1);

        jButton2 = new JButton();
        jButton2.setBounds(552, 20, SizeOfTiles.WIDTH.getValue(), SizeOfTiles.HEIGHT.getValue());
        jButton2.setBorder(border);
        add(jButton2);

        for (final Tile t : allTilesinBoard) {
            t.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    System.out.println(t.getTileX() + " " + t.getTileY() + " " + t.getTileZ());
                    remove(t);
                    repaint();
                }
            });
        }


        setVisible(true);
    }


    public void createBoard() {

        int i=0;
        Random random = new Random();
        Tile.createAllTiles();


        for (int z = 0; z<Board.zCoord; z++) {
            for (int y = 0; y<Board.yCoord; y++)
                for (int x = Board.xCoord-1; x >=0; x--) {
                    if (Board.boardNew[z][y][x] == 1 && !(allTiles.isEmpty())) {
                        final Tile t = allTiles.get(random.nextInt(allTiles.size()));
                        t.setTileZ(z);
                        t.setTileY(y);
                        t.setTileX(x);
                        t.setLevel(i++);
                        if ((x == 1 && y == 0 && z == 0) || (x == 12 && y == 0 && z == 0))
                            t.tileSetEnable(true);
                        else if ((x == 3 && y == 1 && z == 0) || (x == 10 && y == 1 && z == 0))
                            t.tileSetEnable(true);
                        else if ((x == 2 && y == 2 && z == 0) || (x == 11 && y == 2 && z == 0))
                            t.tileSetEnable(true);
                        else if ((x == 1 && y == 3 && z == 0) || (x == 12 && y == 3 && z == 0))
                            t.tileSetEnable(true);
                        else if ((x == 0 && y == 4 && z == 0) || (x == 14 && y == 4 && z == 0))
                            t.tileSetEnable(true);
                        else if ((x == 2 && y == 5 && z == 0) || (x == 11 && y == 5 && z == 0))
                            t.tileSetEnable(true);
                        else if ((x == 3 && y == 6 && z == 0) || (x == 10 && y == 6 && z == 0))
                            t.tileSetEnable(true);
                        else if ((x == 1 && y == 7 && z == 0) || (x == 12 && y == 7 && z == 0))
                            t.tileSetEnable(true);
                        else if ((x == 4 && y == 1 && z == 1) || (x == 4 && y == 2 && z == 1) || (x == 4 && y == 3 && z == 1) || (x == 4 && y == 4 && z == 1) || (x == 4 && y == 5 && z == 1) || (x == 4 && y == 6 && z == 1))
                            t.tileSetEnable(true);
                        else if ((x == 9 && y == 1 && z == 1) || (x == 9 && y == 2 && z == 1) || (x == 9 && y == 3 && z == 1) || (x == 9 && y == 4 && z == 1) || (x == 9 && y == 5 && z == 1) || (x == 9 && y == 6 && z == 1))
                            t.tileSetEnable(true);
                        else if ((x == 5 && y == 2 && z == 2) || (x == 5 && y == 3 && z == 2) || (x == 5 && y == 4 && z == 2) || (x == 5 && y == 5 && z == 2))
                            t.tileSetEnable(true);
                        else if ((x == 8 && y == 2 && z == 2) || (x == 8 && y == 3 && z == 2) || (x == 8 && y == 4 && z == 2) || (x == 8 && y == 5 && z == 2))
                            t.tileSetEnable(true);
                        else if (x == 6 && y == 3 && z == 4)
                            t.tileSetEnable(true);
                        else
                            t.tileSetEnable(false);
                        allTilesinBoard.add(t);
                        allTiles.remove(t);
                        System.out.println(t.getTileZ() + " " + i);



                    }
                }
        }

        }


    public void setLocationOnBoard(ArrayList<Tile> allTilesinBoard){
        int x=0;
        int y=0;
        int z =0;

        int i =0;

            for (Tile tile : allTilesinBoard) {
                z= tile.getTileZ();
                    x = (SizeOfTiles.WIDTH.getValue() * tile.getTileX()) -SizeOfTiles.BOARD_lEFT.getValue()*tile.getTileX() + z*6;
                    y = (SizeOfTiles.HEIGHT.getValue() * tile.getTileY()) - SizeOfTiles.BOARD_DOWN.getValue()*tile.getTileY() -z*10;
                    tile.setBounds(x, y, SizeOfTiles.WIDTH.getValue(), SizeOfTiles.HEIGHT.getValue());
                    tile.setBorder(new TileBorder(4));
                    add(tile, new Integer (tile.getLevel()));
                System.out.println(allTilesinBoard.size());
            }
        }

        public static boolean isOccupied(int coordX, int coordY){
        for (Tile tile : allTilesinBoard){
            if (tile.contains(coordX, coordY)){
                return true;
            }
        }
        return false;
        }




    public void compareTilesMethod(ArrayList<Tile> compareTiles) {
        Tile t1 = compareTiles.get(0);
        Tile t2 = compareTiles.get(1);
        System.out.println(t1.getTileX()+ " " +  t1.getTileY() + " " + t1.getTileZ());
        System.out.println(t2.getTileX()+ " " +  t2.getTileY() + " " + t2.getTileZ());
        if (t1.getTileID() == t2.getTileID()) {
            remove(t1);
            remove(t2);
            allTilesinBoard.remove(t1);
            allTilesinBoard.remove(t2);
        }
        compareTiles.clear();
    }

    public void addActionListen (final ArrayList<Tile> allTilesinBoard) {
        for (final Tile tile : allTilesinBoard) {
            tile.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    compareTiles.add(tile);
                    if (compareTiles.size() == 2) {
                        Tile t1 = compareTiles.get(0);
                        Tile t2 = compareTiles.get(1);
                        System.out.println(t1.getTileX()+ " " +  t1.getTileY() + " " + t1.getTileZ());
                        System.out.println(t2.getTileX()+ " " +  t2.getTileY() + " " + t2.getTileZ());
                        if (t1.getTileID() == t2.getTileID()) {
                            remove(t1);
                            remove(t2);
                            allTilesinBoard.remove(t1);
                            allTilesinBoard.remove(t2);
                        }
                        compareTiles.clear();
                    }
                }
            });
        }
    }






        public void tileSetEnabled (ArrayList < Tile > allTilesinBoard) {
            for (Tile tile : allTilesinBoard)
                if (tile.tileIsEnable())
                    tile.tileSetEnable(true);
                else
                    tile.tileSetEnable(false);

        }


        public Tile findTile (ArrayList < Tile > allTilesinBoard,int z, int y, int x){
            Tile findTile = new Tile();
            for (Tile tile : allTilesinBoard) {
                if (tile.getTileX() == x && tile.getTileY() == y && tile.getTileZ() == z)
                    findTile = tile;
            }
            return findTile;

        }


        public void getBack () {
            add(remowedTiles[0]);
            add(remowedTiles[1]);
            allTilesinBoard.add(remowedTiles[0]);
            allTilesinBoard.add(remowedTiles[1]);
        }


            public int[] setBounds (Tile tile){
                int[] tablica = new int[2];
                int x = 0;
                int y = 0;
                if (tile.getTileZ() == 0) {
                    x = 100 + (50 * tile.getTileX());
                    y = 100 + (70 * tile.getTileY());
                } else if (tile.getTileZ() == 1) {
                    x = 260 + (40 * tile.getTileX());
                    y = 210 + (60 * tile.getTileY());
                } else if (tile.getTileZ() == 2) {
                    x = 300 + (40 * tile.getTileX());
                    y = 270 + (60 * tile.getTileY());
                } else if (tile.getTileZ() == 3) {
                    x = 340 + (40 * tile.getTileX());
                    y = 330 + (60 * tile.getTileY());
                } else if (tile.getTileZ() == 4) {
                    x = 340 + (40 * tile.getTileX());
                    y = 330 + (60 * tile.getTileY());
                }
                tablica[0] = x;
                tablica[1] = y;

                return tablica;
            }
}







