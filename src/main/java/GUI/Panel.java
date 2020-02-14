package GUI;

import GameBoard.SizeOfTiles;
import GameBoard.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
                        t.setBounds(setBounds(t)[0],setBounds(t)[1],40,60);
                        add(t);
                        Tile.allTiles.remove(t);
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

    public int[] setBounds (Tile tile) {
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
