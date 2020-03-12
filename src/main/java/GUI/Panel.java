package GUI;

import GameBoard.SizeOfTiles;
import GameBoard.*;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.swing.*;
import javax.swing.Timer;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

import static GUI.Window.*;

//396 line cheat
public class Panel extends JLayeredPane {
    ArrayList<Tile> allTilesinBoard = new ArrayList<Tile>();
    ArrayList<Tile> allTilesinBoardCopy = new ArrayList<Tile>();
    static ArrayList<Integer> allScores;
    static ArrayList<String> allinfo;
    static ArrayList<String> afterSorting;
    ArrayList<String> information;
    Tile[] remowedTiles = new Tile[2];
    ArrayList<Tile> compareTiles = new ArrayList<Tile>();
    ArrayList<Tile> removedTiles = new ArrayList<>();
    public static ArrayList<String> findarray;
    Bunny bunny3;
    Bunny bunny2;
    Bunny bunny;
    Tile [] helpPare;
    BufferedImage flames;
    Restart restart;
    static JLabel movesNumber;

    Timer timer = new Timer(400, null);
    public static Timer timerHard = new Timer(3000, null);
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
    int automaticShuffles = 0;
    JLabel startGame;
    JLabel winTrophy;
    JLabel youRWinner;
    JLabel youWin;

    int worstresult = findWorstResult();
    int endGameTime;
    public static final String bestScoresPath = Tile.class.getClassLoader().getResource("BestScores").getFile() + "\\TopScores.txt";
    static File file = new File(bestScoresPath);


    public Panel() {
        setLayout(null);
        Font font1 = new Font("base", Font.BOLD,14);
        try {
            flames = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameBoardImage").getFile() + "\\flames.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        SoundOn soundOn = new SoundOn();
        SoundOff soundOff = new SoundOff();
        soundOn.setBounds(30, 25, 60, 60);
        soundOff.setBounds(30, 25, 60, 60);
        add(soundOn);
        add(soundOff);
        soundOn.setVisible(false);
        soundOff.setVisible(false);
        if (!Window.clip.isOpen()){
            soundOff.setVisible(true);
            soundOn.setVisible(false);
        }
        else {
            soundOn.setVisible(true);
            soundOff.setVisible(false);
        }
        repaint();
        JLabel infoMusicOn = new JLabel("Turn music off");
        infoMusicOn.setHorizontalAlignment(SwingConstants.CENTER);
        infoMusicOn.setBounds(110, 40, 250, 30);
        infoMusicOn.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
        infoMusicOn.setForeground(new Color(255, 102, 0));
        add(infoMusicOn);
        infoMusicOn.setVisible(false);

        JLabel infoMusicOff = new JLabel("Turn music on");
        infoMusicOff.setHorizontalAlignment(SwingConstants.CENTER);
        infoMusicOff.setBounds(110, 40, 250, 30);
        infoMusicOff.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
        infoMusicOff.setForeground(new Color(255, 102, 0));
        add(infoMusicOff);
        infoMusicOff.setVisible(false);

        soundOn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Music.playSound("Click");
                if (Window.clip.isRunning()) {
                    try {
                        Window.clip.stop();
                        Window.clip.close();
                        soundOff.setVisible(true);
                        soundOn.setVisible(false);
                    }
                    catch (Exception ex1){
                        ex1.printStackTrace();
                    }

                }
                else if (!Window.clip.isRunning()){
                    try {
                        soundOff.setVisible(false);
                        soundOn.setVisible(true);
                        Window.input = AudioSystem.getAudioInputStream(new File(Music.musicPaths.get((new Random()).nextInt(4))));

                        Window.clip.open(Window.input);
                        Window.clip.start();
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
                Music.playSound("Tick");
                infoMusicOn.setVisible(true);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                infoMusicOn.setVisible(false);
            }
        });

        soundOff.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Music.playSound("Click");
                if (Window.clip.isRunning()) {
                    try {
                        Window.clip.stop();
                        Window.clip.close();
                        soundOff.setVisible(true);
                        soundOn.setVisible(false);
                    }
                    catch (Exception ex1){
                        ex1.printStackTrace();
                    }

                }
                else if (!Window.clip.isRunning()){
                    try {
                        soundOff.setVisible(false);
                        soundOn.setVisible(true);
                        Window.input = AudioSystem.getAudioInputStream(new File(Music.musicPaths.get((new Random()).nextInt(4))));

                        Window.clip.open(Window.input);
                        Window.clip.start();
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
                Music.playSound("Tick");
                infoMusicOff.setVisible(true);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                infoMusicOff.setVisible(false);
            }
        });

        youWin = new JLabel("Congratulations, you win!!!");
        youWin.setBounds(350, 130, 500, 100);
        youWin.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
        youWin.setForeground(new Color(255, 102, 0));
        add(youWin);
        youWin.setVisible(false);

        winTrophy = new Trophy();
        winTrophy.setBounds(450, 150, 300, 400 );
        add(winTrophy);
        winTrophy.setVisible(false);

        youRWinner = new JLabel(Window.playerName);
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

        JLabel infoClue = new JLabel("Get a clue (+30sec)");
        infoClue.setHorizontalAlignment(SwingConstants.CENTER);
        infoClue.setBounds(110, 40, 250, 30);
        infoClue.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
        infoClue.setForeground(new Color(255, 102, 0));
        add(infoClue);
        infoClue.setVisible(false);

        clue.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (numberOfLives != 0 && !winTrophy.isVisible()) {
                    Music.playSound("Click");
                    tooManyClues++;
                    if (secondsPassedFromStart >= 30) {
                        secondsPassedFromStart -= 30;
                        minutes++;
                        startGame.repaint();
                    } else {
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
                infoClue.setVisible(true);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                infoClue.setVisible(false);
            }
        });
        add(clue);

        startGame = new JLabel(String.valueOf(minutes) + " : " + String.valueOf(tenTimesSecond) + String.valueOf(secondsPassedFromStart));
        startGame.setBounds(700, 40, 200, 30);
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

        if (Window.isHardModeOn){
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
                    if (numberOfLives == 0){
                        showLosingMassage();
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

        JLabel infoShuffle = new JLabel("Shuffle the board");
        infoShuffle.setHorizontalAlignment(SwingConstants.CENTER);
        infoShuffle.setBounds(110, 40, 250, 30);
        infoShuffle.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
        infoShuffle.setForeground(new Color(255, 102, 0));
        add(infoShuffle);
        infoShuffle.setVisible(false);

        shuffle.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                showWinningMassage();
                if (numberOfLives != 0 && !winTrophy.isVisible()) {
                    Music.playSound("Click");
                    shuflleAllTilesOnBoard(allTilesinBoard);
                    movesNumber.setText("(" + checkMovesNumber() + ")");
                    setLocationOnBoard(allTilesinBoard);
                    for (Tile tile : allTilesinBoard) {
                        for (ActionListener actionListener : tile.getActionListeners())
                            tile.removeActionListener(actionListener);
                    }
                    addActionListen();

                    repaint();
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
                Music.playSound("Tick");
                infoShuffle.setVisible(true);

            }

            @Override
            public void mouseExited(MouseEvent e) {
                infoShuffle.setVisible(false);

            }
        });
        add(shuffle);

        if (numberOfLives == 0){

        }

        restart = new Restart();
        restart.setBounds(400,20,60,60);


        JLabel infoRestart = new JLabel("Restart the game");
        infoRestart.setHorizontalAlignment(SwingConstants.CENTER);
        infoRestart.setBounds(110, 40, 250, 30);
        infoRestart.setFont(new Font("Showcard Gothic", Font.PLAIN, 20));
        infoRestart.setForeground(new Color(255, 102, 0));
        add(infoRestart);
        infoRestart.setVisible(false);

        JLabel cautionRestart = new JLabel("Use with caution!");
        cautionRestart.setBounds(110, 70, 250, 20);
        cautionRestart.setHorizontalAlignment(SwingConstants.CENTER);
        cautionRestart.setFont(new Font("Showcard Gothic", Font.PLAIN, 15));
        cautionRestart.setForeground(new Color(255, 102, 0));
        add(cautionRestart);
        cautionRestart.setVisible(false);

        restart.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (numberOfLives != 0 && !winTrophy.isVisible()) {
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
                    movesNumber.setText("");
                    repaint();
                    if (!bunny.isVisible()) {
                        bunny.setVisible(true);
                    }
                    if (!bunny2.isVisible()) {
                        bunny2.setVisible(true);
                    }
                    if (!bunny3.isVisible()) {
                        bunny3.setVisible(true);
                    }
                    Music.playSound("Click");
                    deleteBoard();
                    allTilesinBoard.clear();
                    createBoard();
                    setLocationOnBoard(allTilesinBoard);
                    for (Tile tile : allTilesinBoard) {
                        for (ActionListener actionListener : tile.getActionListeners())
                            tile.removeActionListener(actionListener);
                    }
                    addActionListen();
                    repaint();
                }
            }

                @Override
                public void mousePressed (MouseEvent e){

                }

                @Override
                public void mouseReleased (MouseEvent e){


                }

                @Override
                public void mouseEntered (MouseEvent e){
                    Music.playSound("Tick");
                    infoRestart.setVisible(true);
                    cautionRestart.setVisible(true);

                }

                @Override
                public void mouseExited (MouseEvent e){
                infoRestart.setVisible(false);
                cautionRestart.setVisible(false);


            }
        });
        add(restart);

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
        movesNumber = new JLabel();
        movesNumber.setText("(" + checkMovesNumber() + ")");
        movesNumber.setHorizontalAlignment(SwingConstants.CENTER);
        movesNumber.setBounds(600, 40, 100, 30);
        movesNumber.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
        movesNumber.setForeground(new Color(255, 102, 0));
        add(movesNumber);

    }

    public void setLocationOnBoard(ArrayList<Tile> allTilesinBoard){
        int x=0;
        int y=0;
        int z =0;

        for (Tile tile : allTilesinBoard) {
            z= tile.getTileZ();
            x = 180 + (SizeOfTiles.WIDTH.getValue() * tile.getTileX()) -SizeOfTiles.BOARD_lEFT.getValue()*tile.getTileX() + z*6;
            y = 100 + (SizeOfTiles.HEIGHT.getValue() * tile.getTileY()) - SizeOfTiles.BOARD_DOWN.getValue()*tile.getTileY() -z*10;
            tile.setBounds(x, y, SizeOfTiles.WIDTH.getValue(), SizeOfTiles.HEIGHT.getValue());
            if (tile.getTileZ()==4) {
                tile.setBounds(490, 260, 54, 70);
            }
            else {
                z = tile.getTileZ();
                x = 180 + (SizeOfTiles.WIDTH.getValue() * tile.getTileX()) - SizeOfTiles.BOARD_lEFT.getValue() * tile.getTileX() + z * 6;
                y = 100 + (SizeOfTiles.HEIGHT.getValue() * tile.getTileY()) - SizeOfTiles.BOARD_DOWN.getValue() * tile.getTileY() - z * 10;
                tile.setBounds(x, y, SizeOfTiles.WIDTH.getValue(), SizeOfTiles.HEIGHT.getValue());
            }
            tile.setBorder(new TileBorder(4));
            add(tile, Integer.valueOf(tile.getLevel()));
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
            if (isHardModeOn) {
                numberOfLives--;
                timeForMove.restart();
                secondsToMove = timerHard.getDelay() / 1000;
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
                    removedTiles.add(t);

                    if (compareTiles.size() == 2) {
                        timer.start();
                    }
                    ActionListener action = new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e1) {
                            setNormalIcons(removedTiles);
                            try {
                                removedTiles.get(0).repaint();
                                removedTiles.get(1).repaint();
                                removedTiles.clear();
                            }
                            catch (Exception e2){
                            }
                            timer.stop();
                            isListenerToBeAdded = 0;
                            timer.removeActionListener(this);

                        }
                    };
                    if (compareTiles.size() == 1) {
                        timer.addActionListener(action);
                    }


                    if (Window.isHardModeOn && isListenerforHardTimerToBeAdded) {
                        isListenerforHardTimerToBeAdded = false;
                        timerHard.addActionListener(forHardMode);
                        timerHard.start();
                    }


                    if (compareTiles.size() == 2) {
                        if (compareTiles.get(0).getTileID() == compareTiles.get(1).getTileID() && (compareTiles.get(0).getTileX() != compareTiles.get(1).getTileX() || compareTiles.get(0).getTileY() != compareTiles.get(1).getTileY() || compareTiles.get(0).getTileZ() != compareTiles.get(1).getTileZ())) {
                            if (Window.challangeNumber == 0) {
                                secondsToMove = 30;
                                timeToMove.setText(String.valueOf(secondsToMove));
                                timeForMove.restart();
                                timeToMove.repaint();
                                timerHard.restart();
                            }
                            setNormalIcons(compareTiles);
                            remove(compareTiles.get(0));
                            remove(compareTiles.get(1));
                            allTilesinBoard.remove(compareTiles.get(0));
                            allTilesinBoard.remove(compareTiles.get(1));
                            movesNumber.setText("(" + checkMovesNumber() + ")");
                            movesNumber.repaint();
                            Board.boardNewFirst[compareTiles.get(0).getTileZ()][compareTiles.get(0).getTileY()][compareTiles.get(0).getTileX()] = 0;
                            Board.boardNewFirst[compareTiles.get(1).getTileZ()][compareTiles.get(1).getTileY()][compareTiles.get(1).getTileX()] = 0;
                            checkIfTileIsEnable(allTilesinBoard);
                            tileSetEnableOnBoard(allTilesinBoard);
                            if (allTilesinBoard.size() == 0){
                                showWinningMassage();
                            }
                            repaint();
                            revalidate();
                        }
                        compareTiles.clear();




                        automaticShuffles = 0;
                        while (checkMovesNumber()==0) {
                            automaticShuffles++;
                            shuflleAllTilesOnBoard(allTilesinBoard);
                            movesNumber.setText("(" + checkMovesNumber() + ")");
                            setLocationOnBoard(allTilesinBoard);
                            for (Tile tile: allTilesinBoard) {
                                for(ActionListener actionListener: tile.getActionListeners())
                                    tile.removeActionListener(actionListener);
                            }
                            addActionListen();
                            repaint();
                            if (automaticShuffles == 50){
                                showWinningMassage();
                                break;
                            }
                        }

                    }


                }
            });
        }
    }


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
        return movesNumber/2;

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
        endGameTime = getTimeOnFinish();
        if(endGameTime<worstresult)
            printToPlik(Window.playerName);
        oneTime = 0;
        System.out.println(information);
        youWin.setVisible(true);
        youRWinner.setVisible(true);
        winTrophy.setVisible(true);
        timerHard.stop();
        timer.stop();
        timeForMove.stop();
        pointsTimer.stop();
        if (allTilesinBoard.size() > 0){
            for (Tile t : allTilesinBoard){
                t.setVisible(false);
            }
        }
        JLabel tryAgainText = new JLabel("Try again?");
        tryAgainText.setBounds(650, 220, 300, 50);
        tryAgainText.setHorizontalAlignment(SwingConstants.CENTER);
        tryAgainText.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
        tryAgainText.setForeground(new Color(255, 102, 0));
        add(tryAgainText);

        JLabel exitText = new JLabel("Exit?");
        exitText.setBounds(220, 220, 300, 50);
        exitText.setHorizontalAlignment(SwingConstants.CENTER);
        exitText.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
        exitText.setForeground(new Color(255, 102, 0));
        add(exitText);

        Exit exit = new Exit();
        exit.setBounds(320, 288, 100, 100);
        add(exit);
        exit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                resetAll();
                Tile.imagesPaths.clear();
                clearAllArrays();
                Window.window.getContentPane().removeAll();
                Window.window.getContentPane().add(new GameMenu());
                Window.window.getContentPane().validate();
                repaint();
                Tile.allTiles.clear();
                timeForMove.restart();
                timeForMove.stop();
                timerHard.restart();
                timerHard.stop();
                pointsTimer.restart();
                pointsTimer.stop();
                secondsPassedFromStart = 0;
                minutes = 0;
                numberOfLives = 3;
                secondsToMove = timerHard.getDelay()/1000;
                winTrophy.setVisible(false);
                youRWinner.setVisible(false);
                youWin.setVisible(false);
                bunny.setVisible(true);
                bunny2.setVisible(true);
                bunny3.setVisible(true);
                setNormalIcons(allTilesinBoard);
                timerHard.removeActionListener(forHardMode);
                if (timer.getActionListeners().length > 0) {
                    timer.removeActionListener(timer.getActionListeners()[0]);
                }
                Music.playSound("Click");
                for (Tile t : allTilesinBoard){
                    t.setVisible(true);
                }
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


        TryAgain tryAgain = new TryAgain();
        tryAgain.setBounds(750, 280, 100, 100);
        add(tryAgain);
        tryAgain.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Music.playSound("Click");
                resetAll();
                clearAllArrays();
                Tile.imagesPaths.clear();
                Panel.findarray.clear();
                window.getContentPane().removeAll();
                repaint();
                window.getContentPane().add(new Panel());
                window.getContentPane().validate();
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
        for (Tile t : allTilesinBoard){
            t.setVisible(false);
        }
        repaint();
    }

    public void showLosingMassage(){
        JLabel gameover = new JLabel("Game Over");
        gameover.setBounds(450, 260, 500, 60);
        gameover.setFont(new Font("Showcard Gothic", Font.PLAIN, 60));
        gameover.setForeground(new Color(255, 102, 0));
        add(gameover);
        setNormalIcons(allTilesinBoard);
        compareTiles.clear();

        pointsTimer.stop();
        timeForMove.stop();
        timerHard.stop();
        timer.stop();

        JLabel tryAgainText = new JLabel("Try again?");
        tryAgainText.setBounds(580, 340, 300, 50);
        tryAgainText.setHorizontalAlignment(SwingConstants.CENTER);
        tryAgainText.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
        tryAgainText.setForeground(new Color(255, 102, 0));
        add(tryAgainText);

        JLabel exitText = new JLabel("Exit?");
        exitText.setBounds(350, 340, 300, 50);
        exitText.setHorizontalAlignment(SwingConstants.CENTER);
        exitText.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
        exitText.setForeground(new Color(255, 102, 0));
        add(exitText);

        Exit exit = new Exit();
        exit.setBounds(450, 408, 100, 100);
        add(exit);
        exit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                allTilesinBoard.clear();
                allTilesinBoardCopy.clear();
                Tile.allTiles.clear();
                resetAll();
                clearAllArrays();
                Window.window.getContentPane().removeAll();
                Window.window.getContentPane().add(new GameMenu());
                Window.window.getContentPane().validate();
                repaint();
                timeForMove.restart();
                timeForMove.stop();
                timerHard.restart();
                timerHard.stop();
                pointsTimer.restart();
                pointsTimer.stop();
                secondsPassedFromStart = 0;
                movesNumber.setText("");
                minutes = 0;
                numberOfLives = 3;
                secondsToMove = timerHard.getDelay()/1000;
                winTrophy.setVisible(false);
                youRWinner.setVisible(false);
                youWin.setVisible(false);
                bunny.setVisible(true);
                bunny2.setVisible(true);
                bunny3.setVisible(true);
                setNormalIcons(allTilesinBoard);
                Music.playSound("Click");
                for (Tile t : allTilesinBoard){
                    t.setVisible(true);
                }
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


        TryAgain tryAgain = new TryAgain();
        tryAgain.setBounds(680, 400, 100, 100);
        add(tryAgain);
        tryAgain.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Music.playSound("Click");
                resetAll();
                clearAllArrays();
                window.getContentPane().removeAll();
                repaint();
                window.getContentPane().add(new Panel());
                window.getContentPane().validate();
                repaint();
                for (Tile tile : allTilesinBoard){
                    tile.setVisible(true);
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
                Music.playSound("Tick");

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        for (Tile t : allTilesinBoard){
            t.setVisible(false);
        }
        repaint();
    }

    public static void resetAll(){
        numberOfLives = 3;
        if (timerHard.getActionListeners().length > 0) {
            timerHard.removeActionListener(timerHard.getActionListeners()[0]);
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

    class TryAgain extends JLabel {

        BufferedImage tryAgain;

        public TryAgain() {

            try {
                tryAgain = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameBoardImage").getFile() + "\\Restart.PNG"));
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
            g2d.drawImage(tryAgain, 0, 0, 100, 100, null);
        }
    }

    class Exit extends JLabel {

        BufferedImage exit;

        public Exit() {

            try {
                exit = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameBoardImage").getFile() + "\\Exit.PNG"));
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
            g2d.drawImage(exit, 0, 0, 87, 92, null);
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

    public void clearAllArrays (){
        if (allScores.size() > 0) {
            allScores.clear();
        }
        if (allinfo.size() > 0) {
            allinfo.clear();
        }
        if (afterSorting.size() > 0) {
            afterSorting.clear();
        }
        if (information.size() > 0) {
            information.clear();
        }
        if (findarray.size() > 0) {
            findarray.clear();
        }
    }

    public static ArrayList<String> scoreSort (File file, String name, int time) {
        String scoreInfo;
        String [] scoreTable;
        allScores = new ArrayList<>();
        allinfo = new ArrayList<>();
        afterSorting = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine() && allScores.size()<5){
                    scoreInfo = scanner.nextLine();
                    allinfo.add(scoreInfo);
                    scoreTable = scoreInfo.split(" ");
                    allScores.add(Integer.parseInt(scoreTable[1]));
            }
            allScores.add(time);
            allinfo.add(name + " " + time);
            scanner.close();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        Collections.sort(allScores);

        for (Integer integer: allScores)
            for (String s: allinfo) {
                scoreTable = s.split(" ");
                if (Integer.parseInt(scoreTable[1]) == integer && !afterSorting.contains(scoreTable[0] + " " + integer)) {
                    afterSorting.add(scoreTable[0] + " " + integer);
                }
            }

        return afterSorting;

    }

    public int getTimeOnFinish () {
        String nowy = startGame.getText().substring(0,startGame.getText().indexOf(':')).trim() + startGame.getText().substring(startGame.getText().indexOf(':')+1).trim();
        return Integer.parseInt(nowy);
    }

    public void printToPlik (String name) {
        information = scoreSort(file, name, endGameTime);
        PrintWriter pw = null;
        int counter = 1;
        try {
            pw = new PrintWriter(file);
            pw.print("");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try {
            for (String top5 : information) {
                if (counter<=5) {
                    pw.println(top5);
                    pw.flush();
                }
                counter++;

            }
            pw.close();

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public int findWorstResult () {
        findarray = new ArrayList<>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine())
                findarray.add(scanner.nextLine());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return Integer.parseInt(findarray.get(findarray.size()-1).split(" ")[1]);
    }

}