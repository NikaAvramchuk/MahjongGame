package GUI;

import GameBoard.Tile;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

public class GameMenu extends JLayeredPane {
    BufferedImage bf;
    static boolean isHardModeOn = true;
    int challangeNumber = 0;
    public static AudioInputStream input;
    public static Clip clip;
    int moreMusic;
    Random random = new Random();
    public static JLabel musicOn;
    public static String name;



    public GameMenu() {
        setLayout(null);
        setSize(1000, 700);
        try {
            bf = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameMenuImage").getFile() + "\\MahjongResize.PNG"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Music.moveMusic();
            clip = AudioSystem.getClip();
            if (!clip.isRunning() && !clip.isOpen() && !clip.isActive()) {
                input = AudioSystem.getAudioInputStream(new File(Music.musicPaths.get(random.nextInt(4))));

                clip.open(input);
                clip.loop(2);
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        NameField name = new NameField();
        name.setBounds(400, 100, 600, 350);
        add(name, 4);

        JTextField setName = new JTextField("Type your name here...");
        setName.setBounds(92, 28, 225, 48);
        setName.setOpaque(false);
        setName.setBorder(null);
        setName.setForeground(new Color(255, 102, 0));
        setName.setFont(new Font("Showcard Gothic", Font.PLAIN, 18));
        name.add(setName);
        int test = 1;

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
                    GameMenu.name = setName.getText();
                }

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        musicOn = new JLabel("Music: On");
        musicOn.setBounds(700, 580, 250, 40);
        musicOn.setForeground(new Color(255, 102, 0));
        musicOn.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));
        add(musicOn);


        musicOn.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Music.playSound("Click");
                System.out.println(Music.musicPaths);
                if (clip.isRunning()) {
                    try {
                        clip.stop();
                        clip.close();

                        musicOn.setText("Music: Off");
                        musicOn.repaint();
                        clip.addLineListener(new LineListener() {
                            @Override
                            public void update(LineEvent event) {
                                System.out.println(clip.getMicrosecondPosition());
                            }
                        });
                    }
                    catch (Exception ex1){
                        ex1.printStackTrace();
                    }

                }
                else if (!clip.isRunning()){
                    try {
                        musicOn.setText("Music: On");
                        musicOn.repaint();
                        input = AudioSystem.getAudioInputStream(new File(Music.musicPaths.get(random.nextInt(4))));
                        System.out.println(random.nextInt(4));

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

        final JLabel newGame = new JLabel("New Game");
        newGame.setForeground(new Color(255, 102, 0));
        newGame.setBounds(700, 430, 260, 40);
        newGame.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));
        add(newGame);

        newGame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Music.playSound("Click");
                setNameForWinner(setName.getText());
                Window.window.getContentPane().removeAll();
                repaint();
                Window.window.getContentPane().add(new Panel());
                Window.window.getContentPane().validate();
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
        tilesChooser.setBounds(700, 480, 350, 40);
        tilesChooser.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));
        add(tilesChooser);

        tilesChooser.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Music.playSound("Click");
                Window.window.getContentPane().removeAll();
                repaint();
                Window.window.getContentPane().add(new TilesChooseMenu());
                Window.window.getContentPane().validate();
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

//        JTextField testfield = new JTextField();
//        testfield.setBounds(128, 270, 100, 30);
//        add(testfield);
        JLabel trophyName = new JLabel("Weronika");
        trophyName.setVerticalAlignment(SwingConstants.CENTER);
        trophyName.setHorizontalAlignment(SwingConstants.CENTER);
        trophyName.setForeground(new Color(255, 102, 0));
        trophyName.setFont(new Font("Showcard Gothic", Font.PLAIN, 18));
        trophyName.setBounds(128, 270, 100, 30);
        add(trophyName);

        JLabel fire = new JLabel("Fire Mahjong");
        fire.setForeground(new Color(255, 102, 0));
        fire.setBounds(350, 30, 550, 70);
        fire.setFont(new Font("Showcard Gothic", Font.PLAIN, 70));
        add(fire);

        JLabel hardModeChooser = new JLabel("Challange: ON");
        hardModeChooser.setBounds(700, 530, 370, 40);
        hardModeChooser.setForeground(new Color(255, 102, 0));
        hardModeChooser.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));
        add(hardModeChooser);

        hardModeChooser.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Music.playSound("Click");
                if (challangeNumber == 0) {
                    hardModeChooser.setText("Challange: OFF");
                    isHardModeOn = false;
                    challangeNumber = 1;
                }
                else if (challangeNumber == 1){
                    hardModeChooser.setText("Challange: ON");
                    isHardModeOn = true;
                    challangeNumber = 0;
                }
                hardModeChooser.repaint();
                System.out.println(isHardModeOn);

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
        Trophy trophy = new Trophy();
        trophy.setBounds(50, -40, 300, 400);
        add(trophy);
    }
    public static void setNameForWinner(String nameForWinner){
        name = nameForWinner;
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
            g2d.drawImage(nameField, 0, 0, 400, 100, null);


        }
    }


}


//--------------------------------------------------------------------------------------








