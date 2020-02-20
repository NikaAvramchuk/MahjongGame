package LevelCreator;

import javax.swing.*;
import java.awt.*;

public class CustomComponent extends JButton {

    public CustomComponent(){
        setContentAreaFilled(false);
    }
    public void paintBorder(Graphics g){
        int x = 0;
        int y = 0;
        Graphics2D g2d = (Graphics2D)g;
        Polygon ml = new Polygon();
        ml.addPoint(x, y);
        ml.addPoint(x + 5, y + 10);
        ml.addPoint(x + 5, y + 70);
        ml.addPoint(x, y + 60);
        g2d.setPaint(Color.MAGENTA);
        g2d.fillPolygon(ml);
        g2d.setPaint(Color.GRAY);
        g2d.drawLine(x, y + 60, x - 5, y + 69);

        //Middle-bottom
        Polygon mb = new Polygon();
        mb.addPoint(x, y + 60);
        mb.addPoint(x - 5, y + 70);
        mb.addPoint(x + 41, y + 70);
        mb.addPoint(x + 46, y + 60);
        g2d.setPaint(Color.MAGENTA);
        g2d.fillPolygon(mb);
    }
}
