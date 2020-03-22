package GUI;

import GameBoard.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static javax.swing.border.BevelBorder.LOWERED;
import static javax.swing.border.BevelBorder.RAISED;

public class BlackTheme extends JLabel {
    BufferedImage blackTheme;
    private int widthImg;
    private int heighImg;
    private Color color = Color.LIGHT_GRAY;

    public void setColor(Color color) {
        this.color = color;
        repaint();
    }

    public int getWidthImg() {
        return widthImg;
    }

    public void setWidthImg(int widthImg) {
        this.widthImg = widthImg;
    }

    public int getHeighImg() {
        return heighImg;
    }

    public void setHeighImg(int heighImg) {
        this.heighImg = heighImg;
    }

    public BlackTheme(){
        try {
            blackTheme = ImageIO.read(GameMenu.class.getClassLoader().getResource("GameBoardImage/BlackBoard.PNG"));
        }
        catch (Exception e){
            e.printStackTrace();
        }

        Border bevel1 = BorderFactory.createBevelBorder(LOWERED, color, color);
        Border bevel2 = BorderFactory.createBevelBorder(LOWERED, color, color);
        Border border = BorderFactory.createCompoundBorder(bevel1, bevel2);
        setBorder(border);
        setWidthImg(160);
        setHeighImg(160);
    }

    public void paintComponent(Graphics g){

        Graphics2D g2d = (Graphics2D)g;
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.drawImage(blackTheme, 0, 0, widthImg, heighImg, null);

    }



}
