package GUI;

import GameBoard.SizeOfTiles;
import GameBoard.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Panel extends JLayeredPane {
    ArrayList<Tile> allTilesinBoard = new ArrayList<Tile>();
    ArrayList<Tile> allTilesinBoardCopy = new ArrayList<Tile>();
    Tile[] remowedTiles = new Tile[2];
    ArrayList<Tile> compareTiles = new ArrayList<Tile>();
    JButton start;
    JButton help;
    JButton shuffle;
    Tile [] helpPare;

    public Panel() {
        setLayout(null);
        Font font1 = new Font("base", Font.BOLD,14);

        JSeparator separator = new JSeparator();
        separator.setBounds(0, 65, 1000, 30);
        separator.setOrientation(SwingConstants.HORIZONTAL);
        separator.setBackground(Color.BLUE);
        add(separator);


        help = new JButton("?");
        help.setFont(font1);
        help.setForeground(Color.WHITE);
        help.setBackground(Color.BLUE);
        help.setBounds(485,20,50,30);
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                helpPare = findPare();
                helpPare[0].setBackground(Color.red);
                helpPare[1].setBackground(Color.red);
                helpPare[0].setForeground(Color.pink);
                helpPare[1].setForeground(Color.pink);
            }
        });
        add(help);

        shuffle = new JButton("S");;
        shuffle.setFont(font1);
        shuffle.setForeground(Color.WHITE);
        shuffle.setBackground(Color.BLUE);
        shuffle.setBounds(545,20,60,30);
        shuffle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shuflleAllTilesOnBoard();
                repaint();
            }
        });
        add(shuffle);

        start = new JButton("Start");
        start.setFont(font1);
        start.setForeground(Color.WHITE);
        start.setBackground(Color.BLUE);
        start.setBounds(400,20,75,30);
        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteBoard();
                allTilesinBoard.clear();
                createBoard();
                setLocationOnBoard(allTilesinBoard);
                for (Tile tile: allTilesinBoard) {
                    for(ActionListener actionListener: tile.getActionListeners())
                        tile.removeActionListener(actionListener);
                }
                addActionListen();
            }
        });
        add(start);

        createBoard();
        setLocationOnBoard(allTilesinBoard);
        addActionListen();
        setVisible(true);
    }


    public void createBoard() {
        allTilesinBoardCopy.addAll(Tile.allTiles);
        int i=0;
        Random random = new Random();
        for (int z = 0; z<Board.zCoord; z++) {
            for (int y = 0; y<Board.yCoord; y++)
                for (int x = Board.xCoord-1; x >=0; x--) {
                    if (Board.boardNewFirst[z][y][x] == 1 && !(allTilesinBoardCopy.isEmpty())) {
                        Tile t = allTilesinBoardCopy.get(random.nextInt(allTilesinBoardCopy.size()));
                        t.setTileZ(z);
                        t.setTileY(y);
                        t.setTileX(x);
                        t.setLevel(i++);
                        allTilesinBoard.add(t);
                        allTilesinBoardCopy.remove(t);
                    }
                }
        }


    }

    public void setLocationOnBoard(ArrayList<Tile> allTilesinBoard){
        int x=0;
        int y=0;
        int z =0;

            for (Tile tile : allTilesinBoard) {
                z= tile.getTileZ();
                x = 180+ (SizeOfTiles.WIDTH.getValue() * tile.getTileX()) -SizeOfTiles.BOARD_lEFT.getValue()*tile.getTileX() + z*6;
                y = 100 + (SizeOfTiles.HEIGHT.getValue() * tile.getTileY()) - SizeOfTiles.BOARD_DOWN.getValue()*tile.getTileY() -z*10;
                tile.setBounds(x, y, SizeOfTiles.WIDTH.getValue(), SizeOfTiles.HEIGHT.getValue());
                tile.setBorder(new TileBorder(4));
                add(tile, new Integer (tile.getLevel()));
            }
            checkIfTileIsEnable(allTilesinBoard);
            tileSetEnableOnBoard(allTilesinBoard);
        }


    public void addActionListen () {
        for (final Tile t: allTilesinBoard){
            t.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    compareTiles.add(t);
                    if (compareTiles.size()==2) {
                        if (compareTiles.get(0).getTileID()==compareTiles.get(1).getTileID() && (compareTiles.get(0).getTileX() != compareTiles.get(1).getTileX() || compareTiles.get(0).getTileY()!=compareTiles.get(1).getTileY() || compareTiles.get(0).getTileZ() != compareTiles.get(1).getTileZ())) {
                            remove(compareTiles.get(0));
                            remove(compareTiles.get(1));
                            allTilesinBoard.remove(compareTiles.get(0));
                            allTilesinBoard.remove(compareTiles.get(1));
                            Board.boardNewSecond[compareTiles.get(0).getTileZ()][compareTiles.get(0).getTileY()][compareTiles.get(0).getTileX()] = 0;
                            Board.boardNewSecond[compareTiles.get(1).getTileZ()][compareTiles.get(1).getTileY()][compareTiles.get(1).getTileX()] = 0;
                            checkIfTileIsEnable(allTilesinBoard);
                            tileSetEnableOnBoard(allTilesinBoard);
                            repaint();
                            revalidate();
                        }
                        compareTiles.clear();
                        if (checkMovesNumber()==0)
                            shuflleAllTilesOnBoard();
                    }
                }
            });
        }
    }


//        public Tile findTile (ArrayList < Tile > allTilesinBoard,int z, int y, int x){
//            Tile findTile = new Tile();
//            for (Tile tile : allTilesinBoard) {
//                if (tile.getTileX() == x && tile.getTileY() == y && tile.getTileZ() == z)
//                    findTile = tile;
//            }
//            return findTile;
//
//        }

        public boolean isSuchTileOnBoard (ArrayList < Tile > allTilesinBoard,int z, int y, int x){
            for (Tile tile : allTilesinBoard) {
                if (tile.getTileX() == x && tile.getTileY() == y && tile.getTileZ() == z)
                    return true;
                }
            return false;
    }


        public void getBack () {
            add(remowedTiles[0]);
            add(remowedTiles[1]);
            allTilesinBoard.add(remowedTiles[0]);
            allTilesinBoard.add(remowedTiles[1]);
        }

        public boolean tileHasTwoNeighbors (Tile tile, ArrayList<Tile> allTilesinBoard) {
            int x = tile.getTileX();
            int y= tile.getTileY();
            int z = tile.getTileZ();

            return isSuchTileOnBoard(allTilesinBoard, z, y, x + 1) && isSuchTileOnBoard(allTilesinBoard, z, y, x - 1);

        }

    public boolean tileHasAnotherTileOnIt (Tile tile, ArrayList<Tile> allTilesinBoard) {
        int x = tile.getTileX();
        int y= tile.getTileY();
        int z = tile.getTileZ();

        return isSuchTileOnBoard(allTilesinBoard, z+1, y, x);

    }

    public void tileSetEnableOnBoard (ArrayList < Tile > allTilesinBoard) {
        for (Tile tile: allTilesinBoard)
            if(!(tile.tileIsEnable()))
                tile.setEnabled(false);
            else
                tile.setEnabled(true);

    }

    public void checkIfTileIsEnable (ArrayList < Tile > allTilesinBoard) {
        for (Tile tile: allTilesinBoard)
            if (tileHasTwoNeighbors(tile, allTilesinBoard) || tileHasAnotherTileOnIt(tile, allTilesinBoard)) {
                tile.tileSetEnable(false);
//                tile.setDisabledIcon(new ImageIcon("C:\\Users\\Nika\\Downloads\\button.png"));
            }
            else
                tile.tileSetEnable(true);

    }

    public void deleteBoard () {
        for (Tile tile: allTilesinBoard)
            remove(tile);
    }

    public void shuflleAllTilesOnBoard () {
        deleteBoard();
        Collections.shuffle(allTilesinBoard);
        allTilesinBoardCopy.addAll(allTilesinBoard);
        System.out.println(allTilesinBoard.size());
        int i=0;
        Random random = new Random();
        for (int z = 0; z<Board.zCoord; z++) {
            for (int y = 0; y<Board.yCoord; y++)
                for (int x = Board.xCoord-1; x >=0; x--) {
                    if (Board.boardNewSecond[z][y][x] == 1 && !(allTilesinBoardCopy.isEmpty())) {
                        Tile t = allTilesinBoardCopy.get(random.nextInt(allTilesinBoardCopy.size()));
                        t.setTileZ(z);
                        t.setTileY(y);
                        t.setTileX(x);
                        t.setLevel(i++);
                        allTilesinBoardCopy.remove(t);
                    }
                }
        }

        setLocationOnBoard(allTilesinBoard);
        for (Tile tile: allTilesinBoard) {
            for(ActionListener actionListener: tile.getActionListeners())
                tile.removeActionListener(actionListener);
        }
        addActionListen();

    }

    public int checkMovesNumber () {
        ArrayList<Tile> allAvailableTilesOnBoard = new ArrayList<Tile>();
        int numberOfId1=0;
        int numberOfId2=0;
        checkIfTileIsEnable(allTilesinBoard);
        for (Tile tile: allTilesinBoard)
            if(tile.tileIsEnable())
                allAvailableTilesOnBoard.add(tile);
        for (Tile tile: allAvailableTilesOnBoard) {
            if(tile.getTileID()==1)
                numberOfId1++;
            else
                numberOfId2++;
        }

        System.out.println(numberOfId1/2 + ""+ numberOfId2/2);

       return numberOfId1/2 + numberOfId2/2;

    }

    public Tile[] findPare () {
        ArrayList<Tile> allAvailableTilesOnBoard = new ArrayList<Tile>();
        int numberOfId1=1;
        int numberOfId2=1;

        Tile [] para = new Tile[2];

        for (Tile tile: allTilesinBoard)
            if(tile.tileIsEnable())
                allAvailableTilesOnBoard.add(tile);

        for (Tile tile: allAvailableTilesOnBoard)
            if (tile.getTileID()==1) {
                if(numberOfId1==1)
                    para[0] =tile;
                else if (numberOfId1==2) {
                    para[1] = tile;
                    break;
                }
                numberOfId1++;
            }
            else {
                if (numberOfId2 == 1)
                    para[0] = tile;
                else if (numberOfId2 == 2) {
                    para[1] = tile;
                    break;
                }
                numberOfId2++;
            }
            return para;


    }


}







