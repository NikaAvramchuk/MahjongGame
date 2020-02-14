package GUI;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ResizeImages {
    public static String resizeImage(String inputImagePath, String outputImagePath) throws IOException {
        BufferedImage inputImage = ImageIO.read(new File(inputImagePath));
        BufferedImage outputImage = new BufferedImage(46, 60, inputImage.getType());


        Graphics2D g2d = outputImage.createGraphics();
        g2d.drawImage(inputImage, 0, 0, 46, 60, null);
        g2d.dispose();

        String formatName = outputImagePath.substring(outputImagePath
                .lastIndexOf(".") + 1);

        ImageIO.write(outputImage, formatName, new File(outputImagePath));

        return outputImagePath;




    }

}
