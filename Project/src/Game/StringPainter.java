package Game;

import javax.swing.*;
import java.awt.*;

import static Game.ScreenScaler.scaleValueForScreen;
public class StringPainter extends JPanel {

    /**
     * text- text to be drawn
     * x,y- cords of where text should be drawn
     */
    private String text;
    private int x,y;
    private int fixedX,fixedY;

    /**
     * Constructor which is responsible for setting basic variables,
     * which will be used to draw a string on screen
     * @param text text which will be drawn on the screen
     * @param x where draw method at x axis should start drawing string
     * @param y where draw method at y axis should start drawing string
     */
    public StringPainter(String text,int x,int y)
    {
        this.text=text;
        this.x=x;
        this.y=y;
        this.fixedX=x;
        this.fixedY=y;

    }


    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    /**
     * Method which is responsible for drawing a string on the screen,
     * on specific place with specific text.
     * @param g actual screen graphics
     */
    @Override
    public void paintComponent(Graphics g)
    {
        //if those are equal it means, that it was not scaled yet.(Middle)
        if(this.x==this.fixedX&&this.y==this.fixedY) {
            g.drawString(this.text, scaleValueForScreen(this.fixedX, 0), scaleValueForScreen(this.fixedY, 1));
        }
        else
        {
            g.drawString(this.text,this.fixedX,scaleValueForScreen(this.fixedY,1));
        }
    }

    public int getAcutalX() {
        return x;
    }

    public int getAcutalY() {
        return y;
    }

    public int getFixedX() {
        return fixedX;
    }

    public void setFixedX(int fixedX) {
        this.fixedX = fixedX;
    }

    public int getFixedY() {
        return fixedY;
    }

    public void setFixedY(int fixedY) {
        this.fixedY= fixedY;
    }

}
