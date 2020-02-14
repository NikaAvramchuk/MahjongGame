package GUI;

import GameBoard.SizeOfTiles;
import GameBoard.*;

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
    ArrayList<Tile> compareTiles = new ArrayList<Tile>();
    JButton jButton1;
    JButton jButton2;
    JButton jButton3;
    JButton jButton4;




    public Panel() {
        setLayout(null);
        createBoard();
<<<<<<< HEAD
        setVisible(true);

=======
        try {
            final JButton test = new JButton(new ImageIcon(ResizeImages.resizeImage("C:\\Users\\Marcin\\Desktop\\tilesfinally.png", "C:\\Users\\Marcin\\Desktop\\Cmd\\tilesfixed2.png")));
            JButton test2 = new JButton(new ImageIcon(ResizeImages.resizeImage("C:\\Users\\Marcin\\Desktop\\tile1.png", "C:\\Users\\Marcin\\Desktop\\Cmd\\tile1fixed.png")));


            test2.setBounds(66, 10, SizeOfTiles.WIDTH.getValue(), SizeOfTiles.HEIGHT.getValue());
            test.setBounds(20, 10, SizeOfTiles.WIDTH.getValue(), SizeOfTiles.HEIGHT.getValue());
            test.setBorderPainted(false);
            test2.setBorderPainted(false);
            add(test);
            add(test2);
        }
        catch (Exception e){
            e.printStackTrace();
        }
>>>>>>> d39d6f5f493a4d38e1029a42c972ebb76c3dd026
    }

    

    public void createBoard() {
        Random random = new Random();
        Tile.createAllTiles();

        for (int z = 0; z < Board.zCoord; z++) {
            for (int y = 0; y < Board.yCoord; y++)
                for (int x = 0; x < Board.xCoord; x++) {
                    if (Board.boardNew[z][y][x]==1 && !(Tile.allTiles.isEmpty())) {
                        final Tile t = Tile.allTiles.get(random.nextInt(Tile.allTiles.size()));
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
                        t.setBounds(setBounds(t)[0],setBounds(t)[1],50,70);
                        t.addActionListener(new ActionListener() {
                            public void actionPerformed(ActionEvent e) {
                                    boolean result = false;
                                    compareTiles.add(t);
                                    if (compareTiles.size() == 2) {
                                        result = compareTilesMethod(compareTiles.get(0), compareTiles.get(1));
                                        compareTiles.remove(1);
                                        compareTiles.remove(0);
                                    }
                                }
                        });
                        add(t);
                        allTilesinBoard.add(t);
                        Tile.allTiles.remove(t);
                    }
                }
//            setEnableds(allTilesinBoard);
        }
    }

    public boolean compareTilesMethod(Tile firstSelected, Tile secondSelected) {
        if (firstSelected.getId() == secondSelected.getId()) {
            remowedTiles[0]= firstSelected;
            remowedTiles[1] = secondSelected;
            remove(firstSelected);
            remove(secondSelected);
            allTilesinBoard.remove(firstSelected);
            allTilesinBoard.remove(secondSelected);
            Board.boardNew[firstSelected.getZ()][firstSelected.getY()][firstSelected.getX()]=0;
            Board.boardNew[secondSelected.getZ()][secondSelected.getY()][secondSelected.getX()]=0;
            if(Board.boardNew[firstSelected.getZ()][firstSelected.getY()][firstSelected.getX() + 1]==0)
                findTile(allTilesinBoard,firstSelected.getZ(),firstSelected.getY(),(firstSelected.getX()+1)).setEnable(true);
            else if (Board.boardNew[firstSelected.getZ()][firstSelected.getY()][firstSelected.getX() -1]==0)
                findTile(allTilesinBoard,firstSelected.getZ(),firstSelected.getY(),(firstSelected.getX()-1)).setEnable(true);
            repaint();
            revalidate();
            return true;
        }

<<<<<<< HEAD
        return false;
=======
    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;
        //left
        Polygon ml = new Polygon();
        ml.addPoint(20, 10);
        ml.addPoint(15, 20);
        ml.addPoint(15, 80);
        ml.addPoint(20, 70);
        g2.setPaint(Color.GRAY);
        g2.fillPolygon(ml);



        //Middle-bottom
        Polygon mb = new Polygon();
        mb.addPoint(20, 70);
        mb.addPoint(15, 80);
        mb.addPoint(61, 80);
        mb.addPoint(66, 70);
        g2.setPaint(Color.GRAY);

        g2.fillPolygon(mb);


        //top layer
        Rectangle2D top = new Rectangle2D.Double(20, 10, 46, 60);

        Polygon ml2 = new Polygon();
        ml2.addPoint(66, 10);
        ml2.addPoint(61, 20);
        ml2.addPoint(61, 80);
        ml2.addPoint(66, 70);
        g2.setPaint(Color.GRAY);
        g2.fillPolygon(ml2);


        //Middle-bottom
        Polygon mb2 = new Polygon();
        mb2.addPoint(66, 70);
        mb2.addPoint(61, 80);
        mb2.addPoint(107, 80);
        mb2.addPoint(113, 70);
        g2.setPaint(Color.GRAY);

        g2.fillPolygon(mb2);


        //top layer
        Rectangle2D top2 = new Rectangle2D.Double(66, 10, 46, 60);
        g2.fill(top);
    }

    public void setEnabled (ArrayList<Tile> allTilesinBoard) {
        for (Tile tile: allTilesinBoard)
            if (tile.isEnable())
                tile.setEnabled(true);
            else
                tile.setEnabled(false);
>>>>>>> d39d6f5f493a4d38e1029a42c972ebb76c3dd026
    }

//    public void setEnableds (ArrayList<Tile> allTilesinBoard) {
//        for (Tile tile: allTilesinBoard)
//            if (tile.isEnable())
//                tile.setEnabled(true);
//            else
//                tile.setEnabled(false);
//    }

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

    public int[] setBounds (Tile tile) {
        int [] tablica = new int[2];
        int x=0;
        int y=0;
        if (tile.getZ()==0) {
            x = 100 + (50 * tile.getX());
            y = 100 + (70 * tile.getY());
        }
//        else if (tile.getZ()==1) {
//            x = 260 + (40 * tile.getX());
//            y = 210 + (60 * tile.getY());
//        }
//        else if (tile.getZ()==2) {
//            x = 300 + (40 * tile.getX());
//            y = 270 + (60 * tile.getY());
//        }
//        else if (tile.getZ()==3) {
//            x = 340 + (40 * tile.getX());
//            y = 330 + (60 * tile.getY());
//        }
//        else if (tile.getZ()==4) {
//            x = 340 + (40 * tile.getX());
//            y = 330 + (60 * tile.getY());
//        }
        tablica[0] = x;
        tablica[1]= y;

        return tablica;

    }








}
