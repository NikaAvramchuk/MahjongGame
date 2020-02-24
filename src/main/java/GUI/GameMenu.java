package GUI;

import GameBoard.Tile;
import javafx.scene.layout.Background;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;

public class GameMenu extends JLayeredPane {
    static Heart heart;
    BufferedImage bf;

    public GameMenu() {
        setLayout(null);
        setSize(1000, 700);
        try {
            bf = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameMenuImage").getFile() + "\\MahjongResize.PNG"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        JLabel newGame = new JLabel("New Game");
        newGame.setForeground(new Color(255, 102, 0));
        newGame.setBounds(700, 460, 260, 40);
        newGame.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));
        add(newGame);

        newGame.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
            }

            @Override
            public void mouseExited(MouseEvent e) {
                newGame.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));

            }
        });

        JLabel tilesChooser = new JLabel("Tiles Chooser");
        tilesChooser.setForeground(new Color(255, 102, 0));
        tilesChooser.setBounds(700, 510, 350, 40);
        tilesChooser.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));
        add(tilesChooser);

        tilesChooser.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
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

        JLabel timeLabel = new JLabel();
        timeLabel.setBounds(900, 40, 180, 40);
//        timeLabel.setText(String.valueOf(timer.getInitialDelay()));
        timeLabel.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));
        timeLabel.setForeground(new Color(255, 102, 0));
        add(timeLabel);


        int delay = 100;
        long counter;

        heart = new Heart();
        heart.setBounds(150, 20, 100, 100);
        add(heart);

        ActionListener taskPerformer = new ActionListener() {
            int i = 1;
            long milliseconds = 0;
            long seconds = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                milliseconds += 4;

                timeLabel.setText(seconds + " : " + milliseconds);
                if (milliseconds >= 99) {
                    seconds++;
                    milliseconds = 0;
                }
                repaint();
            }
        };
        Timer timer = new Timer(delay, taskPerformer);
        timer.start();

//        JLabel forVer = new JLabel("Wyłącz timer <3");
//        forVer.setBounds(900, 80, 100, 100);
//        forVer.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));
//        add(forVer);


    }

    public void animationHelper() {

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
}


