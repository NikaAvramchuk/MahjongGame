package GUI;

import GameBoard.SizeOfTiles;
import GameBoard.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.TimerTask;



public class Panel extends JLayeredPane {
    ArrayList<Tile> allTilesinBoard = new ArrayList<Tile>();
    ArrayList<Tile> allTilesinBoardCopy = new ArrayList<Tile>();
    Tile[] remowedTiles = new Tile[2];
    ArrayList<Tile> compareTiles = new ArrayList<Tile>();
    JButton start;
    JButton help;
    JButton shuffle;
    Bunny bunny3;
    Bunny bunny2;
    Bunny bunny;
    Tile [] helpPare;
    BufferedImage flames;
    int numberOfLives = 3;
    boolean isListenerToBeAdded = true;
    Timer timer = new Timer(400, null);
    public static Timer timerHard = new Timer(30000, null);
    Timer timeForMove = new Timer(1000, null);
    int secondsToMove = timerHard.getDelay() / 1000;
    boolean isListenerforHardTimerToBeAdded = true;
    JLabel timeToMove;
    Timer clueClicked = new Timer(600, null);
    int tooManyClues = 0;


    public Panel() {
        setLayout(null);
        Font font1 = new Font("base", Font.BOLD,14);
        try {
            flames = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameBoardImage").getFile() + "\\flames.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        Heart heart = new Heart();
        heart.setBounds(850, 20, 100, 100);
        add(heart);

        JLabel timeToAct = new JLabel("Time to act:");
        timeToAct.setBounds(860, 130, 250, 30);
        timeToAct.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
        timeToAct.setForeground(new Color(255, 102, 0));
        add(timeToAct);

        bunny = new Bunny();
        bunny.setBounds(1040, 20, 120, 120);
        add(bunny);

        bunny2 = new Bunny();
        bunny2.setBounds(980, 20, 120, 120);
        add(bunny2);

        bunny3 = new Bunny();
        bunny3.setBounds(920, 20, 120, 120);
        add(bunny3);


        help = new JButton("?");
        help.setFont(font1);
        help.setForeground(Color.WHITE);
        help.setBackground(Color.BLUE);
        help.setBounds(485,20,50,30);
        help.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tooManyClues++;
                helpPare = findPare();
                clueClicked.start();
                for (Tile t : helpPare) {
                    t.setIcon(new QualityIcon(replacePath(t.getIconPath())));
                }
                clueClicked.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        setNormalIconsInTable(helpPare);
                        clueClicked.stop();
                    }
                });
                helpPare[0].setBackground(Color.orange);
                helpPare[1].setBackground(Color.orange);
                repaint();
            }
        });
        add(help);

        if (GameMenu.isHardModeOn){
            timeToMove = new JLabel(String.valueOf(secondsToMove));
            timeToMove.setBounds(1060, 120, 50, 50);
            timeToMove.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
            timeToMove.setForeground(new Color(255, 102, 0));
            add(timeToMove);

            timeForMove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    secondsToMove--;
                    timeToMove.setText(String.valueOf(secondsToMove));
                    timeToMove.repaint();
                }
            });

        }

        shuffle = new JButton("S");
        shuffle.setFont(font1);
        shuffle.setForeground(Color.WHITE);
        shuffle.setBackground(Color.BLUE);
        shuffle.setBounds(545,20,60,30);
        shuffle.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                shuflleAllTilesOnBoard(allTilesinBoard);
                setLocationOnBoard(allTilesinBoard);
                for (Tile tile: allTilesinBoard) {
                    for(ActionListener actionListener: tile.getActionListeners())
                        tile.removeActionListener(actionListener);
                }
                addActionListen();
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
                repaint();
            }
        });
        add(start);

        createBoard();
        setLocationOnBoard(allTilesinBoard);
        addActionListen();
        setVisible(true);
    }


    public void createBoard() {
        for (int q=0; q<Board.zCoord; q++) {
            for (int w = 0; w<Board.yCoord; w++)
                System.arraycopy(Board.originalBoard[q][w], 0, Board.boardNewFirst[q][w], 0, Board.xCoord);
        }

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
            System.out.println(tile.getIconPath());
            z= tile.getTileZ();
            x = 180 + (SizeOfTiles.WIDTH.getValue() * tile.getTileX()) -SizeOfTiles.BOARD_lEFT.getValue()*tile.getTileX() + z*6;
            y = 100 + (SizeOfTiles.HEIGHT.getValue() * tile.getTileY()) - SizeOfTiles.BOARD_DOWN.getValue()*tile.getTileY() -z*10;
            tile.setBounds(x, y, SizeOfTiles.WIDTH.getValue(), SizeOfTiles.HEIGHT.getValue());
            tile.setBorder(new TileBorder(4));
            add(tile, new Integer (tile.getLevel()));
        }
        checkIfTileIsEnable(allTilesinBoard);
        tileSetEnableOnBoard(allTilesinBoard);
    }

    public String replacePath(String path){
        StringBuilder sb = new StringBuilder(path);
        sb.replace(path.length() - 4, path.length(), "L.PNG");
        String chosenPath = String.valueOf(sb);
        return chosenPath;
    }

    public void setNormalIcons(ArrayList<Tile> al){
        for (Tile t : al){
            t.setIcon(new QualityIcon(t.getIconPath()));
        }
    }

    public void setNormalIconsInTable(Tile table[]){
        for (Tile tile : table){
            tile.setIcon(new QualityIcon(tile.getIconPath()));
        }
    }
    ActionListener forHardMode = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e2) {
                    numberOfLives--;
                    timeForMove.restart();
                    secondsToMove = 30;
                    timeToMove.setText(String.valueOf(secondsToMove));
                    timeToMove.repaint();

                    if (numberOfLives == 2) {
                        bunny.setVisible(false);
                        repaint();
                    }
                    if (numberOfLives == 1) {
                        bunny2.setVisible(false);
                        repaint();
                    }
                    if (numberOfLives == 0) {
                        bunny3.setVisible(false);
                        repaint();

                    }
            }
        };


    public void addActionListen () {
        for (final Tile t: allTilesinBoard){
            t.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    timeForMove.start();
                    compareTiles.add(t);
                    t.setIcon(new QualityIcon(replacePath(t.getIconPath())));

                    if (compareTiles.size() == 2) {
                        timer.start();
                    }
                    ActionListener action = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e1) {
                            setNormalIcons(allTilesinBoard);
                            repaint();
                            timer.stop();
                        }
                    };
                    if (isListenerToBeAdded) {
                        timer.addActionListener(action);
                        isListenerToBeAdded = false;
                    }

                    if (GameMenu.isHardModeOn && isListenerforHardTimerToBeAdded) {
                        isListenerforHardTimerToBeAdded = false;
                        timerHard.addActionListener(forHardMode);
                        timerHard.start();
                    }


                    if (compareTiles.size() == 2) {
                        if (compareTiles.get(0).getTileID() == compareTiles.get(1).getTileID() && (compareTiles.get(0).getTileX() != compareTiles.get(1).getTileX() || compareTiles.get(0).getTileY() != compareTiles.get(1).getTileY() || compareTiles.get(0).getTileZ() != compareTiles.get(1).getTileZ())) {
                            secondsToMove = 30;
                            timeToMove.setText(String.valueOf(secondsToMove));
                            timeForMove.restart();
                            timeToMove.repaint();
                            timerHard.restart();
                            remove(compareTiles.get(0));
                            remove(compareTiles.get(1));
                            allTilesinBoard.remove(compareTiles.get(0));
                            allTilesinBoard.remove(compareTiles.get(1));
                            Board.boardNewFirst[compareTiles.get(0).getTileZ()][compareTiles.get(0).getTileY()][compareTiles.get(0).getTileX()] = 0;
                            Board.boardNewFirst[compareTiles.get(1).getTileZ()][compareTiles.get(1).getTileY()][compareTiles.get(1).getTileX()] = 0;
                            checkIfTileIsEnable(allTilesinBoard);
                            tileSetEnableOnBoard(allTilesinBoard);

                            repaint();
                            revalidate();
                        }
                        compareTiles.clear();




                        System.out.println(checkMovesNumber());
                        if (checkMovesNumber()==0) {
                            shuflleAllTilesOnBoard(allTilesinBoard);
                            setLocationOnBoard(allTilesinBoard);
                            for (Tile tile: allTilesinBoard) {
                                for(ActionListener actionListener: tile.getActionListeners())
                                    tile.removeActionListener(actionListener);
                            }
                            addActionListen();
                            repaint();
                        }

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
                tile.setDisabledIcon(tile.getIcon());
            }
            else
                tile.tileSetEnable(true);

    }

    public void deleteBoard () {
        for (Tile tile: allTilesinBoard)
            remove(tile);
    }

    public void shuflleAllTilesOnBoard (ArrayList<Tile> allTilesinBoard) {
        deleteBoard();
        allTilesinBoardCopy.clear();
        Collections.shuffle(allTilesinBoard);
        allTilesinBoardCopy.addAll(allTilesinBoard);
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
                        allTilesinBoardCopy.remove(t);
                    }
                }
        }

    }

    public int checkMovesNumber () {
        ArrayList<Tile> allAvailableTilesOnBoard = new ArrayList<Tile>();
        int identicalId = 1;
        int movesNumber = 0;
        int i;
        checkIfTileIsEnable(allTilesinBoard);

        for (Tile tile: allTilesinBoard)
            if(tile.tileIsEnable())
                allAvailableTilesOnBoard.add(tile);

        ArrayList<Tile> allAvailableTilesminusOne = new ArrayList<Tile>(allAvailableTilesOnBoard);

        for (Tile tile1: allAvailableTilesOnBoard) {
            i = tile1.getTileID();
            for (Tile tile2: allAvailableTilesminusOne) {
                if (tile2.getTileID() == i) {
                   identicalId++;
                }
            }
            movesNumber=movesNumber+(identicalId%2);
            identicalId=1;
        }
        return movesNumber;

    }

    public Tile[] findPare () {
        ArrayList<Tile> allAvailableTilesOnBoard = new ArrayList<Tile>();
        ArrayList<Tile> allAvailableTilesminusOne = new ArrayList<Tile>();
        int i=0;
        boolean found = true;

        Tile [] para = new Tile[2];

        for (Tile tile: allTilesinBoard)
            if(tile.tileIsEnable())
                allAvailableTilesOnBoard.add(tile);

        allAvailableTilesminusOne.addAll(allAvailableTilesOnBoard);

        for (Tile tile1: allAvailableTilesOnBoard) {
            i = tile1.getTileID();
            para[0] = tile1;
            allAvailableTilesminusOne.remove(tile1);
            for (Tile tile2: allAvailableTilesminusOne) {
                if (tile2.getTileID() == i) {
                    para[1] = tile2;
                    found = true;
                    break;
                }
                found=false;
            }
            if(found)
                break;
            }
        return para;
    }

    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.drawImage(flames, 0, 0, 1200, 680, null);
    }

    //-----------------------------------------------------------------------------------------
    class Heart extends JLabel implements ActionListener {
        BufferedImage heart;
        int numberOfImage = 1;

        @Override
        public void actionPerformed(ActionEvent e){
            try {
                numberOfImage++;
                heart = ImageIO.read(new File(Tile.class.getClassLoader().getResource("Heart").getFile() + "\\Heart" + numberOfImage + ".PNG"));
                if (numberOfImage == 12)
                    numberOfImage = 1;
                repaint();
            }
            catch (Exception ex){
                ex.printStackTrace();
            }

        }

        public Heart() {
            int delay = 100;


            try {
                Timer timer = new Timer(delay, this);
                timer.start();

                heart = ImageIO.read(new File(Tile.class.getClassLoader().getResource("Heart").getFile() + "\\Heart" + numberOfImage + ".PNG"));
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        public void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g;
            g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            g2d.drawImage(heart, 0, 0, 100, 100, null);

        }
    }
    //--------------------------------------------------------------------------------------------------
    class Bunny extends JLabel{

        BufferedImage bunny;

        public Bunny(){

            try {
                bunny = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameBoardImage").getFile() + "\\Bunny2.PNG"));
            }
            catch (Exception e){
                e.printStackTrace();
            }


        }

        public void paintComponent(Graphics g){
            Graphics2D g2d = (Graphics2D)g;
            g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
            g2d.drawImage(bunny, 0, 0, 120, 120, null);


        }

    }
}

