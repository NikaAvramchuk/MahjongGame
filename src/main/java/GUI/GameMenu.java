package GUI;

import GameBoard.Tile;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;

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
import java.io.InputStream;

public class GameMenu extends JLayeredPane {
    BufferedImage bf;
    static boolean isHardModeOn;
    int challangeNumber = 0;



    public GameMenu() {
        setLayout(null);
        setSize(1000, 700);
        try {
            bf = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameMenuImage").getFile() + "\\MahjongResize.PNG"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        final JLabel newGame = new JLabel("New Game");
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

        final JLabel tilesChooser = new JLabel("Tiles Chooser");
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

        JLabel hardModeChooser = new JLabel("Challange: ON");
        hardModeChooser.setBounds(700, 560, 370, 40);
        hardModeChooser.setForeground(new Color(255, 102, 0));
        hardModeChooser.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));
        add(hardModeChooser);

        hardModeChooser.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
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
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hardModeChooser.setFont(new Font("Showcard Gothic", Font.PLAIN, 40));


            }
        });




    }

//    public void playSound() {
//        try
//        {
//            // get the sound file as a resource out of my jar file;
//            // the sound file must be in the same directory as this class file.
//            // the input stream portion of this recipe comes from a javaworld.com article.
//            InputStream inputStream = getClass().getResourceAsStream(SOUND_FILENAME);
//            AudioStream audioStream = new AudioStream(inputStream);
//            AudioPlayer.player.start(audioStream);
//        }
//        catch (Exception e)
//        {
//            // a special way i'm handling logging in this application
//            if (debugFileWriter!=null) e.printStackTrace(debugFileWriter);
//        }
//
//    }


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
}


    //--------------------------------------------------------------------------------------


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
        g2d.drawImage(infinity, 0, 0, 100, 100, null);


    }
}





