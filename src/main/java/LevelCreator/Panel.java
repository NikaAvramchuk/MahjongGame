package LevelCreator;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;

public class Panel extends JPanel {
    int x = 20;
    int y = 10;


    public Panel() {
        setLayout(null);



    }

    public void drawTile(Graphics g){

        for (x = 20; x < 620; x += 46 ) {
            if (x == 618 && y == 490){
                PaneLevel1 paneLevel1 = new PaneLevel1();
                add(paneLevel1);
            }
            else if (x == 618){
                x = 20;
                y += 60;
            }
            Graphics2D g2d = (Graphics2D) g;


            g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
            g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
            g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);


            Polygon ml = new Polygon();
            ml.addPoint(x, y);
            ml.addPoint(x - 5, y + 10);
            ml.addPoint(x - 5, y + 70);
            ml.addPoint(x, y + 60);
            g2d.setPaint(Color.LIGHT_GRAY);
            g2d.fillPolygon(ml);
            g2d.setPaint(Color.GRAY);
            g2d.drawLine(x, y + 60, x - 5, y + 69);


            //Middle-bottom
            Polygon mb = new Polygon();
            mb.addPoint(x, y + 60);
            mb.addPoint(x - 5, y + 70);
            mb.addPoint(x + 41, y + 70);
            mb.addPoint(x + 46, y + 60);
            g2d.setPaint(Color.LIGHT_GRAY);
            g2d.fillPolygon(mb);

            JButton top = new JButton();
            top.setBounds(x, y, 46, 60);
            add(top);
        }

    }

    public void paintComponent(Graphics g){
        drawTile(g);



        //top layer
//        Rectangle2D top = new Rectangle2D.Double(x, y, 46, 60);



    }





}
