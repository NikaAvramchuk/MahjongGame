package GUI;

import GameBoard.Tile;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static GUI.Window.*;

public class GameMenu extends JLayeredPane {
    BufferedImage bf;
    Random random = new Random();
    Timer timerWrongName = new Timer(1500, null);



    public GameMenu() {
        setLayout(null);
        setSize(1000, 700);
        try {
            bf = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameMenuImage").getFile() + "\\MahjongResize.PNG"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        NameField name = new NameField();
        name.setBounds(400, 100, 600, 350);
        add(name, 4);

        JTextField setName = new JTextField();
        if (playerName.isEmpty()){
            setName.setText("Type your name here...");
        }
        else {
            setName.setText(Window.playerName);

        }
        setName.setBounds(92, 28, 225, 48);
        setName.setHorizontalAlignment(SwingConstants.CENTER);
        setName.setOpaque(false);
        setName.setBorder(null);
        setName.setForeground(new Color(255, 102, 0));
        setName.setFont(new Font("Showcard Gothic", Font.PLAIN, 18));
        name.add(setName);

        setName.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (setName.getText().equalsIgnoreCase("Type your name here...")){
                    setName.setText("");
                    setName.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));
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


            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        setName.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

                if (setName.getText().length() > 8 && !(setName.getText().equalsIgnoreCase("Type your name here..."))){
                    if (e.getKeyCode() != KeyEvent.VK_BACK_SPACE) {
                        e.consume();
                    }
                }



            }

            @Override
            public void keyPressed(KeyEvent e) {
                if (setName.getText().equalsIgnoreCase("Type your name here...")){
                    setName.setText("");
                    setName.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));
                }
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    Window.playerName = setName.getText();
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        JLabel quit = new JLabel("Quit");
        quit.setBounds(770, 580, 250, 40);
        quit.setForeground(new Color(255, 102, 0));
        quit.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));
        add(quit);

        quit.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Music.playSound("Click");
                window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                quit.setFont(new Font("Showcard Gothic", Font.PLAIN, 45));
                Music.playSound("Tick");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                quit.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));

            }
        });




        JLabel musicOn = new JLabel();
        if (musicNumber == 1){
            musicOn.setText("Music: Off");
        }
        else {
            musicOn.setText("Music: On");
        }
        musicOn.setBounds(770, 530, 250, 40);
        musicOn.setForeground(new Color(255, 102, 0));
        musicOn.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));
        add(musicOn);


        musicOn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Music.playSound("Click");
                if (musicNumber == 0) {
                    try {
                        clip.stop();
                        clip.close();
                        musicNumber = 1;
                        musicOn.setText("Music: Off");
                        musicOn.repaint();
                        clip.addLineListener(new LineListener() {
                            @Override
                            public void update(LineEvent event) {
                            }
                        });
                    }
                    catch (Exception ex1){
                        ex1.printStackTrace();
                    }

                }
                else if (musicNumber == 1){
                    try {
                        musicNumber = 0;
                        musicOn.setText("Music: On");
                        musicOn.repaint();
                        input = AudioSystem.getAudioInputStream(new File(Music.musicPaths.get(random.nextInt(4))));
                        clip.open(input);
                        clip.start();
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

        JLabel wrongName = new JLabel("Type correct name");
        wrongName.setBounds(430, 200, 350, 48);
        wrongName.setForeground(new Color(85, 85, 85));
        wrongName.setFont(new Font("Showcard Gothic", Font.PLAIN, 30));
        wrongName.setVisible(false);
        add(wrongName);

        final JLabel newGame = new JLabel("New Game");
        newGame.setForeground(new Color(255, 102, 0));
        newGame.setBounds(770, 380, 260, 40);
        newGame.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));
        add(newGame);

        newGame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (!setName.getText().equalsIgnoreCase("Type your name here...") && !setName.getText().contains(" ")) {
                    Music.playSound("Click");
                    setNameForWinner(setName.getText());
                    window.getContentPane().removeAll();
                    repaint();
                    if (Tile.allTiles.isEmpty()) {
                        Tile.allTiles = new ArrayList<Tile>(Tile.createAllTilesInBoard());
                    }
                    window.getContentPane().add(new Panel());
                    window.getContentPane().validate();
                    repaint();
                }
                else {
                    timerWrongName.start();
                    wrongName.setVisible(true);
                    timerWrongName.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            wrongName.setVisible(false);
                            timerWrongName.stop();
                        }
                    });




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
                newGame.setFont(new Font("Showcard Gothic", Font.PLAIN, 45));
                Music.playSound("Tick");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                newGame.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));

            }
        });



        final JLabel tilesChooser = new JLabel("Tiles Chooser");
        tilesChooser.setForeground(new Color(255, 102, 0));
        tilesChooser.setBounds(770, 430, 350, 40);
        tilesChooser.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));
        add(tilesChooser);

        tilesChooser.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Music.playSound("Click");
                window.getContentPane().removeAll();
                repaint();
                window.getContentPane().add(new TilesChooseMenu());
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
                tilesChooser.setFont(new Font("Showcard Gothic", Font.PLAIN, 45));
                Music.playSound("Tick");

            }

            @Override
            public void mouseExited(MouseEvent e) {
                tilesChooser.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));

            }
        });

        JLabel fire = new JLabel("Fire Mahjong");
        fire.setForeground(new Color(255, 102, 0));
        fire.setBounds(350, 30, 550, 70);
        fire.setFont(new Font("Showcard Gothic", Font.PLAIN, 70));
        add(fire);

        JLabel hardModeChooser = new JLabel();
        if (challangeNumber == 1){
            hardModeChooser.setText("Challenge: Off");
        }
        else {
            hardModeChooser.setText("Challenge: On");
        }
        hardModeChooser.setBounds(770, 480, 370, 40);
        hardModeChooser.setForeground(new Color(255, 102, 0));
        hardModeChooser.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));
        add(hardModeChooser);

        hardModeChooser.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Music.playSound("Click");
                if (challangeNumber == 0) {
                    hardModeChooser.setText("Challenge: Off");
                    hardModeChooser.repaint();
                    isHardModeOn = false;
                    challangeNumber = 1;
                }
                else if (challangeNumber == 1){
                    hardModeChooser.setText("Challenge: On");
                    hardModeChooser.repaint();
                    isHardModeOn = true;
                    challangeNumber = 0;
                }
                hardModeChooser.repaint();

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                hardModeChooser.setFont(new Font("Showcard Gothic", Font.PLAIN, 45));
                Music.playSound("Tick");
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hardModeChooser.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));


            }
        });


        BestScores bestScores = new BestScores();
        bestScores.setBounds(30, 90, 300, 350);
        add(bestScores);

        JLabel info = new JLabel();
        info.setHorizontalAlignment(SwingConstants.LEFT);
        info.setVerticalAlignment(SwingConstants.CENTER);
        info.setBounds(30, 50, 240, 40);
        bestScores.add(info);

        JLabel infoRank = new JLabel("Rank:");
        infoRank.setVerticalAlignment(SwingConstants.CENTER);
        infoRank.setHorizontalAlignment(SwingConstants.CENTER);
        infoRank.setBounds(2, 0, 50, 40);
        infoRank.setForeground(new Color(255, 102, 0));
        infoRank.setFont(new Font("Showcard Gothic", Font.PLAIN, 16));
        info.add(infoRank);

        JLabel infoPoints = new JLabel("Time:");
        infoPoints.setVerticalAlignment(SwingConstants.CENTER);
        infoPoints.setHorizontalAlignment(SwingConstants.CENTER);
        infoPoints.setBounds(155, 0, 80, 40);
        infoPoints.setForeground(new Color(255, 102, 0));
        infoPoints.setFont(new Font("Showcard Gothic", Font.PLAIN, 16));
        info.add(infoPoints);

        JLabel infoName = new JLabel("Name:");
        infoName.setVerticalAlignment(SwingConstants.CENTER);
        infoName.setHorizontalAlignment(SwingConstants.CENTER);
        infoName.setBounds(35, 0, 135, 40);
        infoName.setForeground(new Color(255, 102, 0));
        infoName.setFont(new Font("Showcard Gothic", Font.PLAIN, 16));
        info.add(infoName);


        String scoreInfo = null;
        String [] scoreTable;
        ArrayList<Integer> time = new ArrayList<>();
        ArrayList<String> names = new ArrayList<>();

        try {
            Scanner sc = new Scanner(Panel.file);
            while (sc.hasNextLine()) {
                scoreInfo = sc.nextLine();
                scoreTable = scoreInfo.split(" ");
                System.out.println(scoreTable.length);
                names.add(scoreTable[0]);
                time.add(Integer.parseInt(scoreTable[1]));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        JLabel first = new JLabel();
        first.setBounds(30, 92, 240, 40);
        bestScores.add(first);

        JLabel firstName = new JLabel(names.get(0));
        firstName.setVerticalAlignment(SwingConstants.CENTER);
        firstName.setHorizontalAlignment(SwingConstants.CENTER);
        firstName.setBounds(35, 0, 135, 40);
        firstName.setForeground(new Color(255, 102, 0));
        firstName.setFont(new Font("Showcard Gothic", Font.PLAIN, 16));
        first.add(firstName);

        JLabel firstPoints = new JLabel(String.valueOf(time.get(0)));
        firstPoints.setVerticalAlignment(SwingConstants.CENTER);
        firstPoints.setHorizontalAlignment(SwingConstants.CENTER);
        firstPoints.setBounds(160, 0, 75, 40);
        firstPoints.setForeground(new Color(255, 102, 0));
        firstPoints.setFont(new Font("Showcard Gothic", Font.PLAIN, 16));
        first.add(firstPoints);

        Golden golden = new Golden();
        golden.setBounds(0, 2, 40, 40);
        first.add(golden);

        JLabel second = new JLabel();
        second.setBounds(30, 134, 240, 40);
        bestScores.add(second);

        JLabel secondName = new JLabel(names.get(1));
        secondName.setVerticalAlignment(SwingConstants.CENTER);
        secondName.setHorizontalAlignment(SwingConstants.CENTER);
        secondName.setBounds(35, 0, 135, 40);
        secondName.setForeground(new Color(255, 102, 0));
        secondName.setFont(new Font("Showcard Gothic", Font.PLAIN, 16));
        second.add(secondName);

        JLabel secondPoints = new JLabel(String.valueOf(time.get(1)));
        secondPoints.setVerticalAlignment(SwingConstants.CENTER);
        secondPoints.setHorizontalAlignment(SwingConstants.CENTER);
        secondPoints.setBounds(160, 0, 75, 40);
        secondPoints.setForeground(new Color(255, 102, 0));
        secondPoints.setFont(new Font("Showcard Gothic", Font.PLAIN, 16));
        second.add(secondPoints);

        Silver silver = new Silver();
        silver.setBounds(0, 2, 35, 35);
        second.add(silver);

        JLabel third = new JLabel();
        third.setBounds(30, 176, 240, 40);
        bestScores.add(third);

        JLabel thirdName = new JLabel(names.get(2));
        thirdName.setVerticalAlignment(SwingConstants.CENTER);
        thirdName.setHorizontalAlignment(SwingConstants.CENTER);
        thirdName.setBounds(35, 0, 135, 40);
        thirdName.setForeground(new Color(255, 102, 0));
        thirdName.setFont(new Font("Showcard Gothic", Font.PLAIN, 16));
        third.add(thirdName);

        JLabel thirdPoints = new JLabel(String.valueOf(time.get(2)));
        thirdPoints.setVerticalAlignment(SwingConstants.CENTER);
        thirdPoints.setHorizontalAlignment(SwingConstants.CENTER);
        thirdPoints.setBounds(160, 0, 75, 40);
        thirdPoints.setForeground(new Color(255, 102, 0));
        thirdPoints.setFont(new Font("Showcard Gothic", Font.PLAIN, 16));
        third.add(thirdPoints);

        Bronze bronze = new Bronze();
        bronze.setBounds(3, 4, 30, 30);
        third.add(bronze);

        JLabel forth = new JLabel("  4");
        forth.setBounds(30, 218, 240, 40);
        forth.setForeground(new Color(255, 102, 0));
        forth.setFont(new Font("Showcard Gothic", Font.PLAIN, 22));
        bestScores.add(forth);

        JLabel forthPoints = new JLabel(String.valueOf(time.get(3)));
        forthPoints.setVerticalAlignment(SwingConstants.CENTER);
        forthPoints.setHorizontalAlignment(SwingConstants.CENTER);
        forthPoints.setBounds(160, 0, 75, 40);
        forthPoints.setForeground(new Color(255, 102, 0));
        forthPoints.setFont(new Font("Showcard Gothic", Font.PLAIN, 16));
        forth.add(forthPoints);

        JLabel forthName = new JLabel(names.get(3));
        forthName.setBackground(Color.LIGHT_GRAY);
        forthName.setVerticalAlignment(SwingConstants.CENTER);
        forthName.setHorizontalAlignment(SwingConstants.CENTER);
        forthName.setBounds(35, 0, 135, 40);
        forthName.setForeground(new Color(255, 102, 0));
        forthName.setFont(new Font("Showcard Gothic", Font.PLAIN, 16));
        forth.add(forthName);

        JLabel fifth = new JLabel("  5");
        fifth.setBounds(30, 260, 240, 40);
        fifth.setForeground(new Color(255, 102, 0));
        fifth.setFont(new Font("Showcard Gothic", Font.PLAIN, 22));
        bestScores.add(fifth);

        JLabel fifthPoints = new JLabel(String.valueOf(time.get(4)));
        fifthPoints.setVerticalAlignment(SwingConstants.CENTER);
        fifthPoints.setHorizontalAlignment(SwingConstants.CENTER);
        fifthPoints.setBounds(160, 0, 75, 40);
        fifthPoints.setForeground(new Color(255, 102, 0));
        fifthPoints.setFont(new Font("Showcard Gothic", Font.PLAIN, 16));
        fifth.add(fifthPoints);

        JLabel fifthName = new JLabel(names.get(4));
        fifthName.setBackground(Color.LIGHT_GRAY);
        fifthName.setVerticalAlignment(SwingConstants.CENTER);
        fifthName.setHorizontalAlignment(SwingConstants.CENTER);
        fifthName.setBounds(35, 0, 135, 40);
        fifthName.setForeground(new Color(255, 102, 0));
        fifthName.setFont(new Font("Showcard Gothic", Font.PLAIN, 16));
        fifth.add(fifthName);

        JSeparator sep1 = new JSeparator();
        sep1.setBackground(Color.WHITE);

        sep1.setBounds(30, 91, 235, 10);
        bestScores.add(sep1);

        JSeparator sep2 = new JSeparator();
        sep2.setBounds(30, 133, 235, 10);
        bestScores.add(sep2);

        JSeparator sep3 = new JSeparator();
        sep3.setBounds(30, 175, 235, 10);
        bestScores.add(sep3);

        JSeparator sep4 = new JSeparator();
        sep4.setBounds(30, 217, 235, 10);
        bestScores.add(sep4);

        JSeparator sep5 = new JSeparator();
        sep5.setBounds(30, 259, 235, 10);
        bestScores.add(sep5);

        JSeparator sep6 = new JSeparator();
        sep6.setBounds(30, 301, 235, 10);
        bestScores.add(sep6);



    }

    public static void setNameForWinner(String nameForWinner){
        Window.playerName = nameForWinner;
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
        g2d.drawImage(bf, 0, 0, 1200, 680, null);
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


    class NameField extends JLabel{

        BufferedImage nameField;

        public NameField(){

            try {
                nameField = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameMenuImage").getFile() + "\\TextName.PNG"));
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
            g2d.drawImage(nameField, 0, 0, 395, 100, null);
        }
    }

    class BestScores extends JLabel{

        BufferedImage bestScores;

        public BestScores(){

            try {
                bestScores = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameBoardImage").getFile() + "\\Top5Best2.PNG"));
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
            g2d.drawImage(bestScores, 0, 0, 300, 350, null);


        }

    }

    class Golden extends JLabel{

        BufferedImage golden;

        public Golden(){

            try {
                golden = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameBoardImage").getFile() + "\\Gold.PNG"));
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
            g2d.drawImage(golden, 0, 0, 40, 40, null);


        }

    }

    class Silver extends JLabel{

        BufferedImage silver;

        public Silver(){

            try {
                silver = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameBoardImage").getFile() + "\\Silver.PNG"));
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
            g2d.drawImage(silver, 0, 0, 35, 35, null);


        }

    }

    class Bronze extends JLabel{

        BufferedImage bronze;

        public Bronze(){

            try {
                bronze = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameBoardImage").getFile() + "\\Bronze.PNG"));
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
            g2d.drawImage(bronze, 0, 0, 30, 30, null);


        }

    }



}


//--------------------------------------------------------------------------------------








