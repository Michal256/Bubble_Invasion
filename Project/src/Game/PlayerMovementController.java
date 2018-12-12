package Game;

import javax.swing.*;
import java.awt.*;
import static Game.GameResourcesKeeper.getGameSpeedMultiplier;

public class PlayerMovementController implements Runnable
{
    private JFrame Screen;
    private Graphics g;

    public PlayerMovementController()
    {
        this.Screen= Main.Game.Screen;

    }

    public void run()
    {
        try
        {
            g=Screen.getGraphics();
            if(GameResourcesKeeper.getGameSpeedMultiplier()>1&&GameResourcesKeeper.gameStarted==true&&GameResourcesKeeper.isGamePaused==false) {
                movePlayer();

                Screen.repaint();
            }

            Thread.sleep(10);
        }
        catch(InterruptedException e)
        {
            System.out.println(e);
        }

    }



    private void movePlayer()
    {
        Player tempPlayer=Board.arrayOfPlayers[0];
        int textureDirection=tempPlayer.getTextureDirection();
        int destinationXCord=tempPlayer.getDestinationXCord();
        int playerWidth=tempPlayer.getWidth();

        if(textureDirection==4)
        {
            new PlayerWalkingAnimator();
        }
        //Left
        else if(textureDirection==1&&tempPlayer.getxCord()>destinationXCord-playerWidth/2)
        {
            new PlayerWalkingAnimator();
            tempPlayer.setxCord(tempPlayer.getxCord()-tempPlayer.getPlayerWalkSpeed()*(getGameSpeedMultiplier()/2));
        }
        //Right
        else if(textureDirection==3&&tempPlayer.getxCord()<destinationXCord-playerWidth/2)
        {
            new PlayerWalkingAnimator();
            tempPlayer.setxCord(tempPlayer.getxCord()+tempPlayer.getPlayerWalkSpeed()*(getGameSpeedMultiplier()/2));
        }
        else
        {
            //When he will stop
            tempPlayer.setTextureDirection(0);
        }
    }

}
