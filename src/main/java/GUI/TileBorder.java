package GUI;

import GameBoard.Tile;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.border.*;


public class TileBorder extends AbstractBorder
{
    protected int thickness;
    protected Color shadowInner;
    protected Color shadowOuter;



    public TileBorder(int thickness) {
        this.thickness = thickness;
    }





    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
    {
        int h = height;
        int w = width;
        int t = thickness;
        int m = thickness / 2;


        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.translate(x, y);

        if (Window.theme == 1) {
            g2d.setColor(new Color(0x989898));
        }
        else {
            g2d.setColor(new Color(0x4E4E4E));
        }
        Polygon ml = new Polygon();
        ml.addPoint(x + 6, y + 2);
        ml.addPoint(x, y + 13);
        ml.addPoint(x, y + 70);
        ml.addPoint(x + 45, y + 70);
        ml.addPoint(x + 52, y + 61);
        ml.addPoint(x + 6, y + 61);
        ml.addPoint(x + 6, y + 2);
        g2d.fillPolygon(ml);

        if (Window.theme == 1) {
            g2d.setColor(new Color(0xFAFBFF));
        }
        else {
            g2d.setColor(new Color(0x000000));
        }

        g2d.drawLine(x + 6, y + 2, x, y + 13);
        g2d.drawLine(x + 6, y + 61, x, y + 70);
        g2d.drawLine(x + 52, y + 2, x + 52, y + 61);
        g2d.drawLine(x + 6, y + 2, x + 52, y + 2);
        g2d.drawLine(x + 45, y + 70, x + 52, y + 61);
        g2d.drawLine(x, y + 13, x, y + 70);
        g2d.drawLine(x, y + 70, x + 45, y + 70);
        g2d.drawLine(x + 6, y + 2, x + 6, y + 61);
        g2d.drawLine(x + 6, y + 61, x + 52, y + 61);



        //  Cleanup

        g2d.dispose();
    }


    public Insets getBorderInsets(Component c, Insets insets)
    {
        insets.set(0, thickness, thickness, 0);
        return insets;
    }


    public Color getShadowInnerColor(Component c)
    {
        Color shadow = getShadowInnerColor();
        return shadow != null ? shadow : c.getBackground().darker();
    }


    public Color getShadowOuterColor(Component c)
    {
        Color shadow = getShadowOuterColor();
        return shadow != null ? shadow : c.getBackground().darker().darker();
    }


    public Color getShadowInnerColor()
    {
        return shadowInner;
    }


    public Color getShadowOuterColor()
    {
        return shadowOuter;
    }


    public boolean isBorderOpaque()
    {
        return true;
    }



}