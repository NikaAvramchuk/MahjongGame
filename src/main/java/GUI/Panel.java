package GUI;

import GameBoard.Card;
import GameBoard.SizeOfTiles;
import GameBoard.Tiles;
import GameBoard.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.ObjectInputFilter;
import java.text.AttributedCharacterIterator;
import java.util.ArrayList;
import java.util.Collections;

public class Panel extends JPanel {
    JButton jButton;
    boolean clicked = true;




    public Panel () {
        setLayout(null);
        ImageIcon imageIcon = new ImageIcon("C:\\Users\\Marcin\\Desktop\\gb.jfif");
        try {
            final JButton test = new JButton(new ImageIcon(ResizeImages.resizeImage("C:\\Users\\Marcin\\Desktop\\tilesfinally.png", "C:\\Users\\Marcin\\Desktop\\Cmd\\tilesfixed2.png")));
            JButton test2 = new JButton(new ImageIcon(ResizeImages.resizeImage("C:\\Users\\Marcin\\Desktop\\tile1.png", "C:\\Users\\Marcin\\Desktop\\Cmd\\tile1fixed.png")));


        test2.setBounds(66, 10, SizeOfTiles.WIDTH.getValue(), SizeOfTiles.HEIGHT.getValue());
        test.setBounds(20, 10, SizeOfTiles.WIDTH.getValue(), SizeOfTiles.HEIGHT.getValue());
        test.setBorderPainted(false);
        test2.setBorderPainted(false);
        add(test);
        add(test2);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void paintComponent(Graphics g){
        Graphics2D g2 = (Graphics2D)g;

//        Polygon left = new Polygon();
//        left.addPoint(20, 10);
//        left.addPoint(10, 30);
//        left.addPoint(10, 90);
//        left.addPoint(20, 70);
//        g2.setPaint(Color.decode("#483D0F"));
//        g2.fillPolygon(left);
//
//        Polygon bottom = new Polygon();
//        bottom.addPoint(15, 80);
//        bottom.addPoint(10, 90);
//        bottom.addPoint(80, 90);
//        bottom.addPoint(85, 80);
//        g2.fillPolygon(bottom);


        //Middle-Left
        Polygon ml = new Polygon();
        ml.addPoint(20, 10);
        ml.addPoint(15, 20);
        ml.addPoint(15, 80);
        ml.addPoint(20, 70);
        g2.setPaint(Color.GRAY);
        g2.fillPolygon(ml);



        //Middle-bottom
        Polygon mb = new Polygon();
        mb.addPoint(20, 70);
        mb.addPoint(15, 80);
        mb.addPoint(61, 80);
        mb.addPoint(66, 70);
        g2.setPaint(Color.GRAY);

        g2.fillPolygon(mb);


        //top layer

        g2.setPaint(clicked ? Color.ORANGE : Color.MAGENTA);
        Rectangle2D top = new Rectangle2D.Double(20, 10, 46, 60);

        Polygon ml2 = new Polygon();
        ml2.addPoint(66, 10);
        ml2.addPoint(61, 20);
        ml2.addPoint(61, 80);
        ml2.addPoint(66, 70);
        g2.setPaint(Color.GRAY);
        g2.fillPolygon(ml2);


        //Middle-bottom
        Polygon mb2 = new Polygon();
        mb2.addPoint(66, 70);
        mb2.addPoint(61, 80);
        mb2.addPoint(107, 80);
        mb2.addPoint(113, 70);
        g2.setPaint(Color.GRAY);

        g2.fillPolygon(mb2);


        //top layer

        g2.setPaint(clicked ? Color.ORANGE : Color.MAGENTA);
        Rectangle2D top2 = new Rectangle2D.Double(66, 10, 46, 60);

        //new RoundRectangle2D.Double(x, y, rectwidth,rectheight,10, 10));
        //RoundRectangle2D top = new RoundRectangle2D.Double(20, 10, 70, 60,10,10);
        g2.fill(top);
    }




        public void createBoard () {
            Collections.shuffle(Card.cardArrayList);
            int i = 0;
            while (i < Card.cardArrayList.size()) {
                int s = 1;
                System.out.println(s);
                s++;
                for (int z = 0; z < Board.zCoord; z++) {
                    for (int y = 0; y < Board.yCoord; y++)
                        for (int x = 0; x < Board.zCoord; x++)
                            if (Board.boardNew[z][y][x] = true) {
                                Card.cardArrayList.get(i).setZ(z);
                                Card.cardArrayList.get(i).setY(y);
                                Card.cardArrayList.get(i).setX(x);
//                          Card.cardArrayList.get(i).setBounds(); // tutaj musimy wymyslic jak auomatycznie wyznaczac gdzie bedzie narysowany przycisk
                                add(Card.cardArrayList.get(i));
                            }
                }
                i++;
            }

        }

}
