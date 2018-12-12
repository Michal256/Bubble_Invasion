package Game;

import javax.swing.*;
import java.awt.*;

import static Game.Board.arrayOfBubbles;
import static Game.GameResourcesKeeper.*;

public class BubbleController implements Runnable
{
    private JFrame Screen;
    private Graphics g;


    public BubbleController()
    {
        this.Screen= Main.Game.Screen;
    }

    public void run()
    {
        try
        {
            g=Screen.getGraphics();
            if(gameStarted==true) {

                if (bringMeaNewWord == true && gameActualWave < numberOfWavesCreated) {
                    //Game start on level 1 and bringMeaNewWord==true. When we destroy first bubble, waves will not come. That's why I need to check once if gameScore==1
                    if (gameActualWave > 1 || GameResourcesKeeper.gameScore == 1) {
                        gameActualWave++;
                    }
                    int sumOfBubblesPerLineToActual = 0;
                    for (int x = 0; x < gameActualWave - 1; x++) {
                        sumOfBubblesPerLineToActual += GameResourcesKeeper.gameHowManyBubblesWaveContainer.get(x);
                    }

                    gameNumberOfBubblesToActualWave = sumOfBubblesPerLineToActual;
                    String text = changeFirstLetterToUpperCase(gameLevelWordsContainerOrdered.get(idOfDisplayedWordContainer[gameActualWave - 1] * 4));
                    Board.listOfPanelStrings.get(4).setText(text);

                    setDefaultListOfInfoBoxStrings();
                    isDescriptionAvailable = false;
                    bringMeaNewWord = false;
                }

                moveBubbles();
                Screen.repaint();
            }

            Thread.sleep(40);
        }
        catch(InterruptedException e)
        {
            System.out.println(e);
        }

    }

    private void moveBubbles()
    {
        int howManyBubblesInLine=GameResourcesKeeper.gameHowManyBubblesWaveContainer.get(gameActualWave-1);

        for(int i = gameNumberOfBubblesToActualWave; i<gameNumberOfBubblesToActualWave+howManyBubblesInLine; i++)
        {
            Bubble tempBubble=arrayOfBubbles[i];

            if(tempBubble.isVisible()==true) {
                int bubbleYCord = tempBubble.getY();
                int bubbleHeight = tempBubble.getHeight();
                if (bubbleYCord + bubbleHeight < 591) {
                    if(getGameSpeedMultiplier()==1)
                    {
                        tempBubble.setY(bubbleYCord + (tempBubble.getBubbleFallingSpeed()-1) * 1);
                    }
                    else {
                        tempBubble.setY(bubbleYCord + tempBubble.getBubbleFallingSpeed() * (getGameSpeedMultiplier() / 2));
                    }
                } else {
                    tempBubble.setVisible(false);

                    if (tempBubble.getWordIndex() == (idOfDisplayedWordContainer[gameActualWave - 1] * 4 + 1)) {
                        isGameOver=true;
                    }
                }
            }
        }
    }

    private String changeFirstLetterToUpperCase(String newText)
    {
        String bigLetter=newText.substring(0, 1).toUpperCase();
        newText=bigLetter+newText.substring(1);
        return newText;
    }

    private void setDefaultListOfInfoBoxStrings()
    {
        for(int i=0;i<Board.listOfInfoBoxStrings.size();i++)
        {
            Board.listOfInfoBoxStrings.get(i).setText("");
        }
    }

}
