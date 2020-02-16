package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Rectangle2D;

public class Animation extends JPanel implements ActionListener {

    Timer time = new Timer(5, this);
    int x = 20;
    int y = 10;
    int velX = 2;

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;

        g.setColor(Color.MAGENTA);
//        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
//        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
//        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
//        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
//        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
//        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
//        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
//        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
//        //left
//        Polygon ml = new Polygon();
//        ml.addPoint(x, y);
//        ml.addPoint(x - 5, y + 10);
//        ml.addPoint(x - 5, y + 70);
//        ml.addPoint(x, y + 60);
//        g2d.setPaint(Color.LIGHT_GRAY);
//        g2d.fillPolygon(ml);
//        g2d.setPaint(Color.GRAY);
//        g2d.drawLine(x, y + 60, x - 5, y + 69);
//
//
//        //Middle-bottom
//        Polygon mb = new Polygon();
//        mb.addPoint(x, y + 60);
//        mb.addPoint(x - 5, y + 70);
//        mb.addPoint(x + 41, y + 70);
//        mb.addPoint(x + 46, y + 60);
//        g2d.setPaint(Color.LIGHT_GRAY);
//        g2d.fillPolygon(mb);
//
//
//        //top layer
//        Rectangle2D top = new Rectangle2D.Double(x, y, 46, 60);
        g.fillRect(600, 200, 60, 40);
//        time.start();
    }

    public void actionPerformed(ActionEvent e) {


    }
}
