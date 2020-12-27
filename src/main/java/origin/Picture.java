package origin;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Picture {
    public static String resourcePath = EnvironmentConfig.PATHRESOUREASSET;

    public static BufferedImage earthShotImage;
    public static BufferedImage fireShotImage;
    public static BufferedImage metalShotImage;
    public static BufferedImage normalShotImage;
    public static BufferedImage woodShotImage;
    public static BufferedImage waterShotImage;

    static {
        normalShotImage = Picture.loadImage("/ball.png");

        earthShotImage = Picture.loadImage("/earthball.png");
        fireShotImage = Picture.loadImage("/fireball.jpg");
        metalShotImage = Picture.loadImage("/metalball.png");
        waterShotImage = Picture.loadImage("/waterball.jpg");
        woodShotImage = Picture.loadImage("/woodball.png");

    }

    public static BufferedImage loadImage(String path) {

        try {
            BufferedImage loadedImage = ImageIO.read(new File(resourcePath+path));
            BufferedImage formatImage = new BufferedImage(loadedImage.getWidth(),
                    loadedImage.getHeight(),BufferedImage.TYPE_INT_RGB);

            formatImage.getGraphics().drawImage(loadedImage,0,0,null);

            return formatImage;

        }catch(IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    public static BufferedImage flipVertical(BufferedImage image) {
        BufferedImage buffer = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = buffer.createGraphics();

        AffineTransform af = AffineTransform.getScaleInstance(-1, 1);
        af.translate(-image.getWidth(),0);

        g2d.transform(af);
        g2d.drawImage(image, 0, 0, null);

        return buffer;
    }
    public static BufferedImage flipHorizontal(BufferedImage image) {
        BufferedImage buffer = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = buffer.createGraphics();

        AffineTransform af = AffineTransform.getScaleInstance(1, -1);
        af.translate(0,-image.getHeight());

        g2d.transform(af);
        g2d.drawImage(image, 0, 0, null);

        return buffer;
    }
    public static BufferedImage rotation90(BufferedImage image) {
        BufferedImage buffer = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = buffer.createGraphics();

        AffineTransform af = AffineTransform.getRotateInstance(Math.PI/2);
        af.translate(0,-image.getWidth());
//        af.setToScale(1,1);

        g2d.transform(af);
        g2d.drawImage(image, 0, 0,image.getHeight(),image.getWidth(), null);

        return buffer;
    }
}
