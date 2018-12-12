package Game;

import javax.swing.*;
import java.awt.*;

import static Game.GameResourcesKeeper.*;
import static Game.GameResourcesKeeper.bringMeaNewWord;

public class FireBallController implements Runnable
{
    private JFrame Screen;
    private Graphics g;



    public FireBallController()
    {
        this.Screen= Main.Game.Screen;
    }
    public void run()
    {
        try
        {
            g=Screen.getGraphics();
            if(GameResourcesKeeper.getGameSpeedMultiplier()>1&&GameResourcesKeeper.gameStarted==true) {
                moveFireBalls();

                Screen.repaint();
            }
            Thread.sleep(10);
        }
        catch(InterruptedException e)
        {
            System.out.println(e);
        }

    }

    private void moveFireBalls()
    {
        for(int i=0;i<GameResourcesKeeper.gameActualNumberOfFireBalls;i++)
        {
            FireBall tempFireBall=Board.arrayOfFireBalls[i];
            int rangeOfFireball=10;
            if(tempFireBall.getyCord()>=rangeOfFireball) {
                new FireBallFlyingAnimation();
                tempFireBall.setFlyingTime(tempFireBall.getFlyingTime()+10);
                tempFireBall.setyCord(tempFireBall.getyCord() - tempFireBall.getFireBallSpeed() * getGameSpeedMultiplier());

                if(tempFireBall.isDangerous()==true)
                {
                    //It's necessary to check if any bubble is touching fireball
                    int howManyBubblesInLine=GameResourcesKeeper.gameHowManyBubblesWaveContainer.get(gameActualWave-1);
                    for(int x = gameNumberOfBubblesToActualWave; x<gameNumberOfBubblesToActualWave+howManyBubblesInLine; x++)
                    {
                        Bubble tempBubble=Board.arrayOfBubbles[x];
                        if(tempBubble.isVisible()==true) {
                            int xCordHitPrecision=60;
                            if (((tempFireBall.getxCord() + tempFireBall.getWidth()-xCordHitPrecision) >= tempBubble.getX()) && (tempFireBall.getxCord()+xCordHitPrecision <= (tempBubble.getX() + tempBubble.getWidth()))
                                    && ((tempFireBall.getyCord() <= (tempBubble.getY() + tempBubble.getHeight())) && ((tempFireBall.getyCord() + tempFireBall.getHeight()) >= tempBubble.getY()))) {

                                //Set proper textures on hit event
                                tempFireBall.setDangerous(false);
                                tempFireBall.setBallTextureType(2);
                                tempFireBall.setActualTextureFrame(8);


                                //Good
                                if (tempBubble.getWordIndex() == idOfDisplayedWordContainer[gameActualWave - 1] * 4 + 1) {
                                    gameScore++;
                                    for(int z=gameNumberOfBubblesToActualWave;z<gameNumberOfBubblesToActualWave+howManyBubblesInLine;z++)
                                    {
                                        Board.arrayOfBubbles[z].setVisible(false);
                                    }
                                    if(gameActualWave < numberOfWavesCreated)
                                    {
                                    bringMeaNewWord=true;
                                    }
                                    else
                                    {
                                        Board.arrayOfSounds[2].playSound();
                                        isGameOver = true;
                                        isDescriptionPanelOnTop=true;
                                    }
                                    break;
                                    //
                                }
                                //Bad
                                else {
                                    gameBadAnswerScore++;
                                    tempBubble.setVisible(false);
                                }

                            }
                        }
                    }
                }
            }
            else
            {
                //If out screen of range
                if(tempFireBall.isDangerous()==true) {
                    tempFireBall.setBallTextureType(2);
                    tempFireBall.setActualTextureFrame(8);
                    tempFireBall.setDangerous(false);
                }
                if(tempFireBall.getBallTextureType()==1||tempFireBall.getBallTextureType()==2) {
                    new FireBallFlyingAnimation();
                }
            }
        }
    }
}
