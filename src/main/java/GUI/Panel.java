package GUI;

import GameBoard.SizeOfTiles;
import GameBoard.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
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
    Timer pointsTimer = new Timer(1000, null);
    int tenTimesSecond = 0;
    int secondsPassedFromStart = 0;
    int minutes = 0;
    int tenTimesMinutes = 0;
    int automaticShuffles = 0;
    JLabel startGame;
    JLabel winTrophy;
    JLabel youRWinner;
    JLabel youWin;


    public Panel() {
        setLayout(null);
        Font font1 = new Font("base", Font.BOLD,14);
        try {
            flames = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameBoardImage").getFile() + "\\flames.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        JLabel musicOn = new JLabel(GameMenu.musicOn.getText());
        musicOn.setBounds(30, 50, 250, 40);
        musicOn.setForeground(new Color(255, 102, 0));
        musicOn.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));
        add(musicOn);


        musicOn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Music.playSound("Click");
                if (GameMenu.clip.isRunning()) {
                    try {
                        GameMenu.clip.stop();
                        GameMenu.clip.close();
                        musicOn.setText("Music: Off");
                        musicOn.repaint();
                    }
                    catch (Exception ex1){
                        ex1.printStackTrace();
                    }

                }
                else if (!GameMenu.clip.isRunning()){
                    try {
                        musicOn.setText("Music: On");
                        musicOn.repaint();
                        if ((int) (Math.random() * 100) > 14) {
                            GameMenu.input = AudioSystem.getAudioInputStream(new File(Music.musicPaths.get((int) (Math.random() * 3))));
                        }
                        else {
                            GameMenu.input = AudioSystem.getAudioInputStream(new File(Music.musicPaths.get(0)));
                        }
                        GameMenu.clip.open(GameMenu.input);
                        GameMenu.clip.start();
                    }
                    catch (Exception ex){
                        ex.printStackTrace();
                    }
                }


            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                musicOn.setFont(new Font("Showcard Gothic", Font.PLAIN, 45));
                Music.playSound("Tick");

            }

            @Override
            public void mouseExited(MouseEvent e) {
                musicOn.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));

            }
        });

        youWin = new JLabel("Congratulations, you win!!!");
        youWin.setBounds(380, 140, 500, 100);
        youWin.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
        youWin.setForeground(new Color(255, 102, 0));
        add(youWin);
        youWin.setVisible(false);

        winTrophy = new Trophy();
        winTrophy.setBounds(450, 150, 300, 400 );
        add(winTrophy);
        winTrophy.setVisible(false);

        youRWinner = new JLabel(GameMenu.name);
        youRWinner.setBounds(79, 311, 100, 30);
        youRWinner.setFont(new Font("Showcard Gothic", Font.PLAIN, 18));
        youRWinner.setForeground(new Color(255, 102, 0));
        youRWinner.setVerticalAlignment(SwingConstants.CENTER);
        youRWinner.setHorizontalAlignment(SwingConstants.CENTER);
        winTrophy.add(youRWinner);
        youRWinner.setVisible(false);

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


        Clue clue = new Clue();
        clue.setBounds(485,20,60,60);
        clue.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Music.playSound("Click");
                tooManyClues++;
                if (secondsPassedFromStart >= 30) {
                    secondsPassedFromStart -= 30;
                    minutes++;
                    startGame.repaint();
                }
                else {
                    secondsPassedFromStart += 30;
                    startGame.repaint();
                }
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

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Music.playSound("Tick");

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(clue);

        startGame = new JLabel(String.valueOf(minutes) + " : " + String.valueOf(tenTimesSecond) + String.valueOf(secondsPassedFromStart));
        startGame.setBounds(650, 40, 200, 30);
        startGame.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
        startGame.setForeground(new Color(255, 102, 0));
        add(startGame);

        pointsTimer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                secondsPassedFromStart++;
                    if (secondsPassedFromStart < 10) {
                        startGame.setText(String.valueOf(minutes) + " : " + String.valueOf(tenTimesSecond) + String.valueOf(secondsPassedFromStart));
                        startGame.repaint();
                    }
                    else {
                        startGame.setText(String.valueOf(minutes) + " : " + String.valueOf(secondsPassedFromStart));
                        if (secondsPassedFromStart > 59){
                            secondsPassedFromStart = 0;
                            minutes++;
                            startGame.repaint();
                        }
                    }
                }
        });

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
                    if (tooManyClues == 3){
                        numberOfLives--;
                        tooManyClues = 0;
                    }
                }
            });

        }
        else {
            Infinity infinity = new Infinity();
            infinity.setBounds(1060, 110, 70, 60);
            add(infinity);
        }

        Shuffle shuffle = new Shuffle();
        shuffle.setBounds(545,20,60,60);
        shuffle.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Music.playSound("Click");
                shuflleAllTilesOnBoard(allTilesinBoard);
                setLocationOnBoard(allTilesinBoard);
                for (Tile tile: allTilesinBoard) {
                    for(ActionListener actionListener: tile.getActionListeners())
                        tile.removeActionListener(actionListener);
                }
                addActionListen();
                repaint();
            }


            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Music.playSound("Tick");

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(shuffle);

        Restart restart = new Restart();
        restart.setBounds(400,20,60,60);
        restart.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println(GameMenu.name);
                timeForMove.restart();
                timerHard.restart();
                pointsTimer.restart();
                secondsPassedFromStart = 0;
                minutes = 0;
                numberOfLives = 3;
                secondsToMove = 30;
                winTrophy.setVisible(false);
                youRWinner.setVisible(false);
                youWin.setVisible(false);
                repaint();



                if (!bunny.isVisible()){
                    bunny.setVisible(true);
                }
                else if (!bunny2.isVisible()){
                    bunny2.setVisible(true);
                }
                else if (!bunny3.isVisible()){
                    bunny3.setVisible(true);
                }
                Music.playSound("Click");
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

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                Music.playSound("Tick");

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        add(restart);

        //78, 310
        ////450, 150



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


                    pointsTimer.start();
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
                            setNormalIcons(compareTiles);
                            remove(compareTiles.get(0));
                            remove(compareTiles.get(1));
                            allTilesinBoard.remove(compareTiles.get(0));
                            allTilesinBoard.remove(compareTiles.get(1));
                            Board.boardNewFirst[compareTiles.get(0).getTileZ()][compareTiles.get(0).getTileY()][compareTiles.get(0).getTileX()] = 0;
                            Board.boardNewFirst[compareTiles.get(1).getTileZ()][compareTiles.get(1).getTileY()][compareTiles.get(1).getTileX()] = 0;
                            checkIfTileIsEnable(allTilesinBoard);
                            tileSetEnableOnBoard(allTilesinBoard);
                            if (allTilesinBoard.size() == 0){
                                showWinningMassage();
                                timeForMove.stop();
                            }
                            repaint();
                            revalidate();
                        }
                        compareTiles.clear();




                        System.out.println(checkMovesNumber());
                        automaticShuffles = 0;
                        while (checkMovesNumber()==0) {
                            automaticShuffles++;
                            shuflleAllTilesOnBoard(allTilesinBoard);
                            setLocationOnBoard(allTilesinBoard);
                            for (Tile tile: allTilesinBoard) {
                                for(ActionListener actionListener: tile.getActionListeners())
                                    tile.removeActionListener(actionListener);
                            }
                            addActionListen();
                            repaint();
                            if (automaticShuffles == 20){
                                showWinningMassage();
                                timeForMove.stop();
                                break;
                            }
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

    public void showWinningMassage() {
        youWin.setVisible(true);
        youRWinner.setVisible(true);
        winTrophy.setVisible(true);

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
    class Infinity extends JLabel {

        BufferedImage infinity;

        public Infinity() {

            try {
                infinity = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameBoardImage").getFile() + "\\infinity.PNG"));
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
            g2d.drawImage(infinity, 0, 0, 70, 60, null);
        }
    }

    class Trophy extends JLabel{

        BufferedImage trophy;

        public Trophy(){

            try {
                trophy = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameBoardImage").getFile() + "\\Trophy.PNG"));
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
            g2d.drawImage(trophy, 0, 0, 300, 400, null);
        }

    }

    class Restart extends JLabel {

        BufferedImage restart;

        public Restart() {

            try {
                restart = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameBoardImage").getFile() + "\\Restart.PNG"));
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
            g2d.drawImage(restart, 0, 0, 60, 60, null);
        }
    }

    class Clue extends JLabel {

        BufferedImage clue;

        public Clue() {

            try {
                clue = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameBoardImage").getFile() + "\\Clue.PNG"));
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
            g2d.drawImage(clue, 0, 0, 60, 60, null);
        }
    }

    class Shuffle extends JLabel {

        BufferedImage shuffle;

        public Shuffle() {

            try {
                shuffle = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameBoardImage").getFile() + "\\Shuffle.PNG"));
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
            g2d.drawImage(shuffle, 0, 0, 60, 60, null);
        }
    }

    class SoundOn extends JLabel {

        BufferedImage soundOn;

        public SoundOn() {

            try {
                soundOn = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameBoardImage").getFile() + "\\SoundOn.PNG"));
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
            g2d.drawImage(soundOn, 0, 0, 60, 60, null);
        }
    }

    class SoundOff extends JLabel {

        BufferedImage soundOff;

        public SoundOff() {

            try {
                soundOff = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameBoardImage").getFile() + "\\SoundOff.PNG"));
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
            g2d.drawImage(soundOff, 0, 0, 60, 60, null);
        }
    }





}

