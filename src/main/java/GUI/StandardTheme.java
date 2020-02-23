package GUI;

import GameBoard.Tile;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;

import static javax.swing.border.BevelBorder.LOWERED;

public class StandardTheme extends JLabel {
    BufferedImage standardTheme;
    private int widthStdImg;
    private int heightStdImg;

    public int getWidthStdImg() {
        return widthStdImg;
    }

    public void setWidthStdImg(int widthStdImg) {
        this.widthStdImg = widthStdImg;
    }

    public int getHeightStdImg() {
        return heightStdImg;
    }

    public void setHeightStdImg(int heightStdImg) {
        this.heightStdImg = heightStdImg;
    }




    public StandardTheme() {
        try {
            standardTheme = ImageIO.read(new File(Tile.class.getClassLoader().getResource("GameBoardImage").getFile() + "\\StandardBoard.PNG"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Border bevel1 = BorderFactory.createBevelBorder(LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY);
        Border bevel2 = BorderFactory.createBevelBorder(LOWERED, Color.LIGHT_GRAY, Color.LIGHT_GRAY);
        Border border = BorderFactory.createCompoundBorder(bevel1, bevel2);
        setBorder(border);
        setWidthStdImg(160);
        setHeightStdImg(160);

    }

    public void paintComponent(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        g2d.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        g2d.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g2d.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
        g2d.drawImage(standardTheme, 0, 0, widthStdImg, heightStdImg, null);
    }

}
