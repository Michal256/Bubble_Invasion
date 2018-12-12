package Game;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Game_ImageLoader
{
    public static BufferedImage loadImage(String nameOfFile)
    {
        BufferedImage img;
        try{
            img= ImageIO.read(new File(""+nameOfFile));
            return img;
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
        return null;
    }

}
