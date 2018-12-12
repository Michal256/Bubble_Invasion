package Game;

import java.awt.*;
import static Game.ScreenScaler.scaleValueForScreen;
public class Box
{
    private int x,y;
    private int width,height,round;

    public Box(int x, int y,int width, int height,int round)
    {
        this.x=x;
        this.y=y;
        this.width=width;
        this.height=height;
        this.round=round;

    }

    public void fillRoundRect(Graphics g,Color boxColor)
    {
        g.setColor(boxColor);
        g.fillRoundRect(scaleValueForScreen(x,0),scaleValueForScreen(y,1),
                scaleValueForScreen(width,0),scaleValueForScreen(height,1),
                scaleValueForScreen(round,0),scaleValueForScreen(round,1));
    }

    public int getWidth() {
        return width;
    }

    public int getX() {
        return x;
    }


}
