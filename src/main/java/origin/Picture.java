package origin;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Picture {
    public static String resourcePath = "D:/WorkspaceEclipse/TANK/src/main/java/asset";
    public static Image ballImage;

    static {
        ballImage = loadImage("/ball.png");
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
}
