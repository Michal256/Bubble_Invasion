package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static Game.ScreenScaler.scaleValueForScreen;

public class InfoBox extends JPanel {
    private BufferedImage boxTexture;
    private int xCord, yCord;
    private int width, height, round;

    public InfoBox(BufferedImage boxTexture, int xCord, int yCord, int width, int height, int round) {
        this.boxTexture = boxTexture;
        this.xCord = xCord;
        this.yCord = yCord;
        this.width = width;
        this.height = height;
        this.round = round;

    }

    @Override
    public void paintComponent(Graphics g) {

        for (int y=0; y<height / 130; y++) {
            for(int x=0;x<width/300;x++) {
                g.drawImage(boxTexture, scaleValueForScreen(xCord+x*300, 0), scaleValueForScreen(yCord+y*130, 1), scaleValueForScreen(xCord+x*300+300, 0), scaleValueForScreen(yCord+y*130+130, 1), 0,0,400,225, null);
            }
        }


    }

    public int getxCord() {
        return xCord;
    }

    public int getyCord() {
        return yCord;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }


}
