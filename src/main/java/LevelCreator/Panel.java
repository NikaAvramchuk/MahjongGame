package LevelCreator;

import javafx.scene.layout.Pane;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Panel extends JPanel {
    static Panel panel = new Panel();

    ArrayList<JButton> collect = new ArrayList<JButton>();


    public Panel() {
        setLayout(null);
        setSize(1000, 700);

    }

    public void drawLevel1(Graphics g) {
        int x = 20;
        int y = 10;
        for (x = 20; x < 670; x += 46) {
            if (x > 650 && y > 480) {
                break;
            } else if (x > 650 && y < 480) {
                x = 20;
                y += 60;
                System.out.println("Wszedłem na tego ifa");
            }
        }
    }


        public void drawLevel2(Graphics g){
            int x = 43;
            int y = 40;
            for (x = 43; x < 670; x += 46 ) {
                if (x > 650 && y > 480) {
                    break;
                } else if (x > 650 && y < 480) {
                    x = 20;
                    y += 60;
                    System.out.println("Wszedłem na tego ifa");
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
                    panel.add(top);
                    collect.add(top);
                    System.out.println(collect.size());
                }
            }




    public void paintComponent(Graphics g){
        if (collect.size() < 126) {
            drawLevel1(g);
        }


//        drawLevel2(g);



        //top layer
//        Rectangle2D top = new Rectangle2D.Double(x, y, 46, 60);



    }





}
