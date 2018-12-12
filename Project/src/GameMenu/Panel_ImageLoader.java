package GameMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Panel_ImageLoader extends JPanel {
    private BufferedImage image;

    public Panel_ImageLoader(String nameOfFile)
    {

        try{
            image= ImageIO.read(new File(""+nameOfFile));
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }

    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawImage(image,0,0,this);
    }
}
