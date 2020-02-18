package GUI;

import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import javax.swing.border.*;

/**
 * A class which implements a simple tile border.
 */
public class TileBorder extends AbstractBorder
{
    protected int thickness;
    protected Color shadowInner;
    protected Color shadowOuter;
    int right;

    /**
     * Creates a tile border with the specified type and whose
     * colors will be derived from the background color of the
     * component passed into the paintBorder method.
     * @param thickness the type of tile for the border
     */
    public TileBorder(int thickness) {
        this.thickness = thickness;
    }

    /**
     * Creates a tile border with the specified type, highlight and
     * shadow colors.
     * @param thickness the type of tile for the border
     * @param shadow the color to use for the tile shadow
     */
    public TileBorder(int thickness, Color shadow)
    {
        this(thickness, shadow, shadow.brighter());
    }

    /**
     * Creates a tile border with the specified type, highlight and
     * shadow colors.
     *
     * @param thickness the type of tile for the border
     * @param shadowOuterColor the color to use for the tile outer shadow
     * @param shadowInnerColor the color to use for the tile inner shadow
     */
    public TileBorder(int thickness, Color shadowOuterColor, Color shadowInnerColor)
    {
        this(thickness);
        this.shadowOuter = shadowOuterColor;
        this.shadowInner = shadowInnerColor;
    }

    /**
     * Paints the border for the specified component with the specified
     * position and size.
     * @param c the component for which this border is being painted
     * @param g the paint graphics
     * @param x the x position of the painted border
     * @param y the y position of the painted border
     * @param width the width of the painted border
     * @param height the height of the painted border
     */
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
    {
        int h = height;
        int w = width;
        int t = thickness;
        int m = thickness / 2;

        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.translate(x, y);

        //  Paint border on right/bottom of component

        Shape outer = new Rectangle(0, 0, w - 1, h - 1);
        Shape inner = new Rectangle(7, -8, w - t, h - t);

        Area border = new Area(outer);
        border.subtract( new Area(inner) );

        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fill( border );

        // Highlight border edge

        g2d.setColor(Color.LIGHT_GRAY);

        g2d.draw( border );




        g2d.setColor( c.getParent().getBackground() );

        Polygon ml = new Polygon();
        ml.addPoint(x - 1, y);
        ml.addPoint(x + 7, y);
        ml.addPoint(x - 1, y + 15);
        g2d.fillPolygon(ml);
        g2d.setColor(Color.GRAY);
        g2d.drawLine(x + 7, y, x, y + 15);
        g2d.drawLine(x + 7, y + 68, x, y + 79);
        g2d.drawLine(x + 61, y, x + 61, y + 68);
        g2d.drawLine(x + 7, y, x + 60, y);
        g2d.drawLine(x + 53, y + 79, x + 61, y + 68);
        g2d.drawLine(x, y + 14, x, y + 100);
        g2d.drawLine(x, y + 79, x + 60, y + 79);
        g2d.drawLine(x + 7, y, x + 7, y + 68);
        g2d.drawLine(x + 7, y + 68, x + 100, y + 68);


        if (c.getX() < 130) {
            g2d.setColor(Color.GRAY);
            g2d.drawLine(x + 90, y + 79, x + 50, y + 79 );
            right = 1000;
            g2d.setColor(Color.LIGHT_GRAY);
        }
        else {
            g2d.setColor(c.getParent().getBackground());
            right = 1;
        }

        Polygon mb = new Polygon();
        mb.addPoint(x + 54, y + 79 + right);
        mb.addPoint(x + 62, y + 79 + right);
        mb.addPoint(x + 63, y + 67);
        g2d.fillPolygon(mb);


        //  Cleanup

        g2d.dispose();
    }

    /**
     * Reinitialize the insets parameter with this MyBorder's current Insets.
     * @param c the component for which this border insets value applies
     * @param insets the object to be reinitialized
     */
    public Insets getBorderInsets(Component c, Insets insets)
    {
        insets.set(0, thickness, thickness, 0);
        return insets;
    }

    /**
     * Returns the inner shadow color of the tile border
     * when rendered on the specified component.  If no shadow
     * color was specified at instantiation, the shadow color
     * is derived from the specified component's background color.
     * @param c the component for which the shadow may be derived
     */
    public Color getShadowInnerColor(Component c)
    {
        Color shadow = getShadowInnerColor();
        return shadow != null ? shadow : c.getBackground().darker();
    }

    /**
     * Returns the outer shadow color of the tile border
     * when rendered on the specified component.  If no shadow
     * color was specified at instantiation, the shadow color
     * is derived from the specified component's background color.
     * @param c the component for which the shadow may be derived
     */
    public Color getShadowOuterColor(Component c)
    {
        Color shadow = getShadowOuterColor();
        return shadow != null ? shadow : c.getBackground().darker().darker();
    }

    /**
     * Returns the inner shadow color of the tile border.
     * Will return null if no shadow color was specified
     * at instantiation.
     */
    public Color getShadowInnerColor()
    {
        return shadowInner;
    }

    /**
     * Returns the outer shadow color of the tile border.
     * Will return null if no shadow color was specified
     * at instantiation.
     */
    public Color getShadowOuterColor()
    {
        return shadowOuter;
    }

    /**
     * Returns whether or not the border is opaque.
     */
    public boolean isBorderOpaque()
    {
        return true;
    }



}