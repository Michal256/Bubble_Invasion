package Game;

public class ScreenScaler
{
    private static double xScale,yScale;

    public static int scaleValueForScreen(int value,int axis)
    {
        getScaleValueForScreen();
        if(axis==0)
        {
            value=(int)(value*xScale);
        }
        else if(axis==1)
        {
            value=(int)(value*yScale);
        }
        return value;
    }

    private static void getScaleValueForScreen()
    {
        xScale=(Board.wWidth/1006.0);
        yScale=(Board.wHeight/721.0);
    }

    public static double getxScale() {
        return xScale;
    }
}
