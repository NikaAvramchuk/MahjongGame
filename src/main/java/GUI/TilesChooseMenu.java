package GUI;

import GameBoard.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;

import static GameBoard.Tile.putImagesOnTiles;

public class TilesChooseMenu extends JLayeredPane {
    BufferedImage bf;
    public static int theme = 1;



    public TilesChooseMenu(){

        setLayout(null);
        setSize(1000, 700);
        try {
            bf = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameMenuImage").getFile() + "\\MahjongResize.PNG"));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        Arrow arrow = new Arrow();
        arrow.setBounds(20, 500, 260, 100);
        add(arrow);

        JLabel back = new JLabel("Back");
        back.setForeground(new Color(255, 102, 0));
        back.setBounds(20, 500, 160, 240);
        back.setFont(new Font("Showcard Gothic", Font.PLAIN, 50));
        add(back);
        back.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                Window.window.getContentPane().removeAll();
                repaint();
                Window.window.getContentPane().add(new GameMenu());
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
                back.setFont(new Font("Showcard Gothic", Font.PLAIN, 60));
                arrow.setWidthImg(640);
                arrow.setHeightImg(250);
                arrow.setxOfImg(-250);
                arrow.setyOfImg(-80);
                repaint();


            }

            @Override
            public void mouseExited(MouseEvent e) {
                back.setFont(new Font("Showcard Gothic", Font.PLAIN, 50));
                arrow.setWidthImg(560);
                arrow.setHeightImg(200);
                arrow.setxOfImg(-215);
                arrow.setyOfImg(-45);
                repaint();


            }
        });

        JLabel chooseTheme = new JLabel("Choose your board");
        chooseTheme.setForeground(new Color(255, 102, 0));
        chooseTheme.setBounds(250, 30, 700, 60);
        chooseTheme.setFont(new Font("Showcard Gothic", Font.PLAIN, 60));
        add(chooseTheme);

        JSeparator horizUp = new JSeparator();
        horizUp.setOrientation(SwingConstants.HORIZONTAL);
        horizUp.setBounds(160, 100, 790, 20 );
        add(horizUp);

        JSeparator horizDown = new JSeparator();
        horizDown.setOrientation(SwingConstants.HORIZONTAL);
        horizDown.setBounds(160, 550, 790, 20);
        add(horizDown);

        JSeparator vertLeft = new JSeparator();
        vertLeft.setOrientation(SwingConstants.VERTICAL);
        vertLeft.setBounds(160, 100, 20, 450);
        add(vertLeft);

        JSeparator vertRight = new JSeparator();
        vertRight.setOrientation(SwingConstants.VERTICAL);
        vertRight.setBounds(950, 100, 20, 450);
        add(vertRight);

        JSeparator vertCenter = new JSeparator();
        vertCenter.setOrientation(SwingConstants.VERTICAL);
        vertCenter.setBounds(550, 100, 20, 450 );
        add(vertCenter);

        BlackTheme blackTheme = new BlackTheme();
        blackTheme.setBounds(650, 115, 160, 160);
        add(blackTheme);
        blackTheme.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                theme = 1;


            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {


            }

            @Override
            public void mouseEntered(MouseEvent e) {
                blackTheme.setWidthImg(250);
                blackTheme.setHeighImg(250);
                blackTheme.setBounds(650, 115, blackTheme.getWidthImg(), blackTheme.getHeighImg());
                repaint();

            }

            @Override
            public void mouseExited(MouseEvent e) {
                blackTheme.setWidthImg(160);
                blackTheme.setHeighImg(160);
                blackTheme.setBounds(650, 115, blackTheme.getWidthImg(), blackTheme.getHeighImg());
                repaint();
            }
        });

        StandardTheme standardTheme = new StandardTheme();
        standardTheme.setBounds(280, 115, 160, 160);
        add(standardTheme);
        standardTheme.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                theme = 2;



            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                standardTheme.setWidthStdImg(250);
                standardTheme.setHeightStdImg(250);
                standardTheme.setBounds(280, 115, standardTheme.getWidthStdImg(), standardTheme.getHeightStdImg());
                repaint();

            }

            @Override
            public void mouseExited(MouseEvent e) {
                standardTheme.setWidthStdImg(160);
                standardTheme.setHeightStdImg(160);
                standardTheme.setBounds(280, 115, standardTheme.getWidthStdImg(), standardTheme.getHeightStdImg());
                repaint();

            }
        });

        paintStandardText();
        paintBlackText();


    }

    public void paintStandardText(){
        char std[] = new char[]{'S', 'T', 'A', 'N', 'D', 'A', 'R', 'D'};
        for (int i = 0; i < 8; i++) {
            JLabel text = new JLabel(String.valueOf(std[i]));
            text.setForeground(new Color(255, 102, 0));
            text.setBounds(220, 115 + i * 25, 25, 25);
            text.setFont(new Font("Showcard Gothic", Font.PLAIN, 25));
            add(text);
        }
    }

    public void paintBlackText() {
        char black[] = new char[]{'B', 'L', 'A', 'C', 'K'};
        for (int i = 0; i < black.length; i++) {
            JLabel text = new JLabel(String.valueOf(black[i]));
            text.setForeground(Color.BLACK);
            text.setBounds(600, 115 + i * 25, 25, 25);
            text.setFont(new Font("Showcard Gothic", Font.PLAIN, 25));
            add(text);
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
        g2d.drawImage(bf, 0, 0, 1200, 680, null);
    }



//-------------------------------------------------------------------------------------------------------


    class Arrow extends JLabel {

        private int xOfImg = -215;
        private int yOfImg = -45;
        private int widthImg = 560;
        private int heightImg = 200;

        public int getxOfImg() {
            return xOfImg;
        }

        public void setxOfImg(int xOfImg) {
            this.xOfImg = xOfImg;
        }

        public int getyOfImg() {
            return yOfImg;
        }

        public void setyOfImg(int yOfImg) {
            this.yOfImg = yOfImg;
        }

        public int getWidthImg() {
            return widthImg;
        }

        public void setWidthImg(int widthImg) {
            this.widthImg = widthImg;
        }

        public int getHeightImg() {
            return heightImg;
        }

        public void setHeightImg(int heightImg) {
            this.heightImg = heightImg;
        }

        BufferedImage arrow;
        public Arrow(){
            try {
                arrow = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameBoardImage").getFile() + "\\Arrow.PNG")); }
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
            g2d.drawImage(arrow, xOfImg, yOfImg, widthImg, heightImg, null);

        }


    }

}


