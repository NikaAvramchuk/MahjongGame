package Test;

import javax.swing.*;
import java.awt.*;

public class TestPoly extends JLayeredPane {
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        int xpoints[] = {25, 145, 25, 145, 25};
        int ypoints[] = {25, 25, 145, 145, 25};
        int npoints = 5;

        g.drawPolygon(xpoints, ypoints, npoints);




    }

}
