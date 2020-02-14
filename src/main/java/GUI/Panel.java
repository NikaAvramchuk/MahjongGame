package GUI;

import GameBoard.SizeOfTiles;
import GameBoard.*;
import javafx.scene.layout.Pane;

import javax.sound.sampled.Line;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Panel extends JPanel {
    Board board = new Board();
    ArrayList<Tile> allTilesinBoard = new ArrayList<Tile>();
    Tile [] remowedTiles = new Tile[2];


    public Panel() {
        setLayout(null);
        Tile.createAllTiles();
        createBoard();


        try {
            final JButton test = new JButton(new ImageIcon(ResizeImages.resizeImage("C:\\Users\\Marcin\\Desktop\\tilesfinally.png", "C:\\Users\\Marcin\\Desktop\\Cmd\\tilesfixed2.png")));
            JButton test2 = new JButton(new ImageIcon(ResizeImages.resizeImage("C:\\Users\\Marcin\\Desktop\\tile1.png", "C:\\Users\\Marcin\\Desktop\\Cmd\\tile1fixed.png")));
            final JButton test3 = new JButton(new ImageIcon(ResizeImages.resizeImage("C:\\Users\\Marcin\\Desktop\\tilesfinally.png", "C:\\Users\\Marcin\\Desktop\\Cmd\\tilesfixed2.png")));
            JButton test4 = new JButton(new ImageIcon(ResizeImages.resizeImage("C:\\Users\\Marcin\\Desktop\\tile1.png", "C:\\Users\\Marcin\\Desktop\\Cmd\\tile1fixed.png")));
            final JButton test5 = new JButton(new ImageIcon(ResizeImages.resizeImage("C:\\Users\\Marcin\\Desktop\\tilesfinally.png", "C:\\Users\\Marcin\\Desktop\\Cmd\\tilesfixed2.png")));
            JButton test6 = new JButton(new ImageIcon(ResizeImages.resizeImage("C:\\Users\\Marcin\\Desktop\\tile1.png", "C:\\Users\\Marcin\\Desktop\\Cmd\\tile1fixed.png")));
            final JButton test7 = new JButton(new ImageIcon(ResizeImages.resizeImage("C:\\Users\\Marcin\\Desktop\\tilesfinally.png", "C:\\Users\\Marcin\\Desktop\\Cmd\\tilesfixed2.png")));

            test7.setBounds(296, 10, SizeOfTiles.WIDTH.getValue(), SizeOfTiles.HEIGHT.getValue());
            test6.setBounds(250, 10, SizeOfTiles.WIDTH.getValue(), SizeOfTiles.HEIGHT.getValue());
            test5.setBounds(204, 10, SizeOfTiles.WIDTH.getValue(), SizeOfTiles.HEIGHT.getValue());
            test4.setBounds(158, 10, SizeOfTiles.WIDTH.getValue(), SizeOfTiles.HEIGHT.getValue());
            test3.setBounds(112, 10, SizeOfTiles.WIDTH.getValue(), SizeOfTiles.HEIGHT.getValue());
            test2.setBounds(66, 10, SizeOfTiles.WIDTH.getValue(), SizeOfTiles.HEIGHT.getValue());
            test.setBounds(20, 10, SizeOfTiles.WIDTH.getValue(), SizeOfTiles.HEIGHT.getValue());
            test.setBorderPainted(false);
            test2.setBorderPainted(false);
            test3.setBorderPainted(false);
            test4.setBorderPainted(false);
            test5.setBorderPainted(false);
            test6.setBorderPainted(false);
            test7.setBorderPainted(false);
            add(test);
            add(test2);
            add(test3);
            add(test4);
            add(test5);
            add(test6);
            add(test7);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }



    public void createBoard() {
        Random random = new Random();

        for (int z = 0; z < Board.zCoord; z++) {
            for (int y = 0; y < Board.yCoord; y++)
                for (int x = 0; x < Board.xCoord; x++) {
                    if (Board.boardNew[z][y][x] && !(Tile.allTiles.isEmpty())) {
                        Tile t = Tile.allTiles.get(random.nextInt(Tile.allTiles.size()));
                        t.setCoords(z,y,x);

                        if ((x==1 && y==0 && z==0) || (x==12 && y==0 && z==0))
                            t.setEnable(true);
                        else if ((x==3 && y==1 && z==0) || (x==10 && y==1 && z==0))
                            t.setEnable(true);
                        else if ((x==2 && y==2 && z==0) || (x==11 && y==2 && z==0))
                            t.setEnable(true);
                        else if ((x==1 && y==3 && z==0) || (x==12 && y==3 && z==0))
                            t.setEnable(true);
                        else if ((x==0 && y==4 && z==0) || (x==14 && y==4 && z==0))
                            t.setEnable(true);
                        else if ((x==2 && y==5 && z==0) || (x==11 && y==5 && z==0))
                            t.setEnable(true);
                        else if ((x==3 && y==6 && z==0) || (x==10 && y==6 && z==0))
                            t.setEnable(true);
                        else if ((x==1 && y==7 && z==0)||(x==12 && y==7 && z==0))
                            t.setEnable(true);
                        else if ((x==4 && y==1 && z==1) || (x==4 && y==2 && z==1) || (x==4 && y==3 && z==1) || (x==4 && y==4 && z==1) || (x==4 && y==5 && z==1) || (x==4 && y==6 && z==1))
                            t.setEnable(true);
                        else if ((x==9 && y==1 && z==1) || (x==9 && y==2 && z==1) || (x==9 && y==3 && z==1) || (x==9 && y==4 && z==1) || (x==9 && y==5 && z==1) || (x==9 && y==6 && z==1))
                            t.setEnable(true);
                        else if ((x==5 && y==2 && z==2) || (x==5 && y==3 && z==2) || (x==5 && y==4 && z==2) || (x==5 && y==5 && z==2))
                            t.setEnable(true);
                        else if ((x==8 && y==2 && z==2) || (x==8 && y==3 && z==2) || (x==8 && y==4 && z==2) || (x==8 && y==5 && z==2))
                            t.setEnable(true);
                        else if (x==6 && y==3 && z==4)
                            t.setEnable(true);
                        else
                            t.setEnable(false);
                        t.setBounds(setCoords(t)[0],setCoords(t)[1],40,60);
                        add(t);
                        t.setVisible(true);
                        t.repaint();
                        t.revalidate();
                        Tile.allTiles.remove(t);
                        repaint();
                        revalidate();
                    }
                }
        }
    }

    public void removeTiles(Tile firstSelected, Tile secondSelected) {
        if (firstSelected.getId() == secondSelected.getId()) {
            remowedTiles[0]= firstSelected;
            remowedTiles[1] = secondSelected;
            remove(firstSelected);
            remove(secondSelected);
            allTilesinBoard.remove(firstSelected);
            allTilesinBoard.remove(secondSelected);
            Board.boardNew[firstSelected.getZ()][firstSelected.getY()][firstSelected.getX()]=false;
            Board.boardNew[secondSelected.getZ()][secondSelected.getY()][secondSelected.getX()]=false;
            if(Board.boardNew[firstSelected.getZ()][firstSelected.getY()][firstSelected.getX() + 1])
                findTile(allTilesinBoard,firstSelected.getZ(),firstSelected.getY(),(firstSelected.getX()+1)).setEnable(true);
            else
                findTile(allTilesinBoard,firstSelected.getZ(),firstSelected.getY(),(firstSelected.getX()-1)).setEnable(true);
        }
        setEnabled(allTilesinBoard);
    }

    public void paintComponent(Graphics g){

        int y = 10;
        for (int x = 20; x < 322; x += 46) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            //left
            Polygon ml = new Polygon();
            ml.addPoint(x, y);
            ml.addPoint(x - 5, y + 10);
            ml.addPoint(x - 5, y + 70);
            ml.addPoint(x, y + 60);
            g2d.setPaint(Color.LIGHT_GRAY);
            g2d.fillPolygon(ml);
            g2d.setPaint(Color.GRAY);
            g2d.drawLine(x, y + 60, x - 5, y + 69);


            //Middle-bottom
            Polygon mb = new Polygon();
            mb.addPoint(x, y + 60);
            mb.addPoint(x - 5, y + 70);
            mb.addPoint(x + 41, y + 70);
            mb.addPoint(x + 46, y + 60);
            g2d.setPaint(Color.LIGHT_GRAY);
            g2d.fillPolygon(mb);


            //top layer
            Rectangle2D top = new Rectangle2D.Double(x, y, 46, 60);
        }
//        Polygon ml2 = new Polygon();
//        ml2.addPoint(66, 10);
//        ml2.addPoint(61, 20);
//        ml2.addPoint(61, 80);
//        ml2.addPoint(66, 70);
//        g2.setPaint(Color.LIGHT_GRAY);
//        g2.fillPolygon(ml2);
//
//
//        //Middle-bottom
//        Polygon mb2 = new Polygon();
//        mb2.addPoint(66, 70);
//        mb2.addPoint(61, 80);
//        mb2.addPoint(107, 80);
//        mb2.addPoint(112, 70);
//        g2.setPaint(Color.LIGHT_GRAY);
//
//        g2.fillPolygon(mb2);
//
//        g2.setColor(Color.MAGENTA);
//        g2.drawLine(150, 140, 120, 80);
//
//
//        //top layer
//        Rectangle2D top2 = new Rectangle2D.Double(66, 10, 46, 60);
//        g2.fill(top);
    }

    public void setEnabled (ArrayList<Tile> allTilesinBoard) {
        for (Tile tile: allTilesinBoard)
            if (tile.isEnable())
                tile.setEnabled(true);
            else
                tile.setEnabled(false);
    }

    public Tile findTile (ArrayList<Tile> allTilesinBoard, int z, int y, int x){
        Tile findTile = new Tile();
        for (Tile tile: allTilesinBoard) {
            if (tile.getX() == x && tile.getY() == y && tile.getZ() == z)
                findTile = tile;
        }
        return findTile;

    }

    public void getBack (){
        add(remowedTiles[0]);
        add(remowedTiles[1]);
        allTilesinBoard.add(remowedTiles[0]);
        allTilesinBoard.add(remowedTiles[1]);
    }

    public int[] setCoords (Tile tile) {
        int [] tablica = new int[2];
        int x=0;
        int y=0;
        if (tile.getZ()==0) {
            x = 100 + (40 * tile.getX());
            y = 150 + (60 * tile.getY());
        }
        else if (tile.getZ()==1) {
            x = 260 + (40 * tile.getX());
            y = 210 + (60 * tile.getY());
        }
        else if (tile.getZ()==2) {
            x = 300 + (40 * tile.getX());
            y = 270 + (60 * tile.getY());
        }
        else if (tile.getZ()==3) {
            x = 340 + (40 * tile.getX());
            y = 330 + (60 * tile.getY());
        }
        else if (tile.getZ()==4) {
            x = 340 + (40 * tile.getX());
            y = 330 + (60 * tile.getY());
        }
        tablica[0] = x;
        tablica[1]= y;

        return tablica;

    }








}
