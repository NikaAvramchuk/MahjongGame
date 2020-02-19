package GUI;

import GameBoard.Tile;

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

        System.out.println();
        Graphics2D g2d = (Graphics2D)g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.translate(x, y);

        // Highlight border edge

        g2d.setColor(Color.LIGHT_GRAY);

//52, 70
        Polygon ml = new Polygon();
        ml.addPoint(x + 7, y);
        ml.addPoint(x, y + 15);
        ml.addPoint(x, y + 70);
        ml.addPoint(x + 45, y + 70);
        ml.addPoint(x + 52, y + 60);
        ml.addPoint(x + 7, y + 60);
        ml.addPoint(x + 7, y);
        g2d.fillPolygon(ml);

        g2d.setColor(Color.GRAY);
        g2d.drawLine(x + 7, y, x, y + 15);
        g2d.drawLine(x + 7, y + 60, x, y + 70);
        g2d.drawLine(x + 52, y, x + 52, y + 65);
        g2d.drawLine(x + 7, y, x + 52, y);
        g2d.drawLine(x + 45, y + 70, x + 52, y + 60);
        g2d.drawLine(x, y + 15, x, y + 70);
        g2d.drawLine(x, y + 70, x + 45, y + 70);
        g2d.drawLine(x + 7, y, x + 7, y + 60);
        g2d.drawLine(x + 7, y + 60, x + 52, y + 60);



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