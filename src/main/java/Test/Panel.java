package Test;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    static Panel panel = new Panel();
    int x;
    int y;
    public void paintComponent(Graphics g){

        Graphics2D g2d = (Graphics2D) g;

        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);

        x = 100;
        y = 100;


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
        panel.add(top);

        Polygon ml2 = new Polygon();
        ml2.addPoint(x + 51, y - 10);
        ml2.addPoint(x + 46, y);
        ml2.addPoint(x + 46, y + 70);
        ml2.addPoint(x + 51, y + 50);
        g2d.setPaint(Color.LIGHT_GRAY);
        g2d.fillPolygon(ml2);
        g2d.setPaint(Color.GRAY);
        g2d.drawLine(x + 51, y + 50, x + 46, y + 59);

        //Middle-bottom
        Polygon mb2 = new Polygon();
        mb2.addPoint(x + 51, y + 50);
        mb2.addPoint(x + 46, y + 60);
        mb2.addPoint(x + 92, y + 60);
        mb2.addPoint(x + 97, y + 50);
        g2d.setPaint(Color.LIGHT_GRAY);
        g2d.fillPolygon(mb2);

        JButton top2 = new JButton();
        top2.setBounds(x + 51, y - 10, 46, 60);
        panel.add(top2);

        Polygon mb4 = new Polygon();
        mb4.addPoint(x + 46, y + 60);
        mb4.addPoint(x + 41, y + 70);
        mb4.addPoint(x + 87, y + 70);
        mb4.addPoint(x + 92, y + 60);
        g2d.setPaint(Color.LIGHT_GRAY);
        g2d.fillPolygon(mb4);

        x = x + 102;
        y = y - 20;

        Polygon ml3 = new Polygon();
        ml3.addPoint(x, y);
        ml3.addPoint(x - 5, y + 10);
        ml3.addPoint(x - 5, y + 70);
        ml3.addPoint(x, y + 60);
        g2d.setPaint(Color.LIGHT_GRAY);
        g2d.fillPolygon(ml3);
        g2d.setPaint(Color.GRAY);
        g2d.drawLine(x, y + 60, x - 5, y + 69);

        //Middle-bottom
        Polygon mb3 = new Polygon();
        mb3.addPoint(x, y + 60);
        mb3.addPoint(x - 5, y + 70);
        mb3.addPoint(x + 41, y + 70);
        mb3.addPoint(x + 46, y + 60);
        g2d.setPaint(Color.LIGHT_GRAY);
        g2d.fillPolygon(mb3);

        JButton top3 = new JButton();
        top3.setBounds(x, y, 46, 60);
        panel.add(top3);

        Polygon mb6 = new Polygon();
        mb6.addPoint(x - 5, y + 70);
        mb6.addPoint(x - 10, y + 80);
        mb6.addPoint(x + 36, y + 80);
        mb6.addPoint(x + 41, y + 70);
        g2d.setPaint(Color.LIGHT_GRAY);
        g2d.fillPolygon(mb6);

        Polygon mb7 = new Polygon();
        mb7.addPoint(x - 10, y + 80);
        mb7.addPoint(x - 15, y + 90);
        mb7.addPoint(x + 31, y + 90);
        mb7.addPoint(x + 36, y + 80);
        g2d.setPaint(Color.LIGHT_GRAY);
        g2d.fillPolygon(mb7);




    }
}
