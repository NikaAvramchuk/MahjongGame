package GUI;

import GameBoard.Tile;
import javafx.scene.layout.Background;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.File;

public class GameMenu extends JLayeredPane {
    BufferedImage bf;
    public GameMenu() {
        setLayout(null);
        setSize(1000, 700);
        try {
            bf = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameMenuImage").getFile() + "\\MahjongResize.PNG"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
        JLabel newGame = new JLabel("New Game");
        newGame.setForeground(new Color(255, 102, 0));
        newGame.setBounds(700, 460, 300, 60);
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

        JLabel fire = new JLabel("Fire Mahjong");
        fire.setForeground(new Color(255, 102, 0));
        fire.setBounds(350, 30, 550, 70);
        fire.setFont(new Font("Showcard Gothic", Font.PLAIN, 70));
        add(fire);



    }
    public void paintComponent(Graphics g){
        Graphics2D g2d = (Graphics2D)g;
        g2d.drawImage(bf, 0, 0, 1200, 680, null);
    }

}
