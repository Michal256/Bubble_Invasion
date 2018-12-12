package Game;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import static Game.Board.*;
import static Game.GameResourcesKeeper.*;
import static Game.ScreenScaler.scaleValueForScreen;

public class BasicElementsDrawer
{
    private Graphics g;
    private BufferedImage backgroundTexture,northPanelTexture;

    public BasicElementsDrawer(Graphics g, BufferedImage[] textures)
    {
        this.g=g;
        this.backgroundTexture=textures[0];
        this.northPanelTexture=textures[1];

        drawBackground();
        drawPanel();
        drawButtons();
        drawBubblesWithWords();
        drawInfoBox();
        drawPanelText();

        if(isGameOver==true)
        {
            setTextInInfoBox(1);
        }
        else if(isDescriptionAvailable==false&&bringMeaNewWord==false)
        {
            setTextInInfoBox(0);
        }

        if(isDescriptionPanelOnTop==true&&isGameOver==false) {
            drawInfoBoxText(0);
        }
        else if(isGameOver==true)
        {
            //Necessary to be here
            //isDescriptionPanelOnTop=true;
            //drawInfoBox();
            drawInfoBoxText(1);
        }

    }

    private void drawBackground()
    {
        for(int x=0;x<(wWidth/300)+1;x++)
        {
            for(int y=0;y<(wHeight/300)+1;y++)
            {
                g.drawImage(backgroundTexture,x*300,y*300,x*300+300,y*300+300,0,0,300,300,null);
            }
        }
    }

    private void drawPanel()
    {
        for(int x=0;x<(wWidth/300)+1;x++)
        {
            g.drawImage(northPanelTexture,x*300,scaleValueForScreen(591,1),x*300+300, scaleValueForScreen(721,1),0,0,400,225,null);

        }
    }

    private void drawButtons()
    {
        //Speed up
        Board.arrayOfBoxes[0].fillRoundRect(g,new Color(81, 81, 214));

        //Exit
        Board.arrayOfBoxes[1].fillRoundRect(g,new Color(85, 85, 215));

        //Middle
        Board.arrayOfBoxes[2].fillRoundRect(g,new Color(66, 140, 62));

        //Description under word
        Board.arrayOfBoxes[3].fillRoundRect(g,new Color(0xA59B3E));

    }

    private void drawBubblesWithWords()
    {
        int howManyBubblesInLine=GameResourcesKeeper.gameHowManyBubblesWaveContainer.get(gameActualWave-1);

        g.setColor(Color.WHITE);
        for(int i = gameNumberOfBubblesToActualWave; i<gameNumberOfBubblesToActualWave+howManyBubblesInLine; i++)
        {
            arrayOfBubbles[i].paintComponent(g);

        }
    }

    private void drawInfoBox()
    {
        if(isDescriptionPanelOnTop==true) {
            //Information box
            Board.arrayOfInfoBoxes[0].paintComponent(g);
        }
    }

    private void drawPanelText()
    {
        g.setColor(new Color(0x07B42C));
        g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, scaleValueForScreen(16, 1)));

        Board.listOfPanelStrings.get(0).setText("G: "+GameResourcesKeeper.gameScore);
        Board.listOfPanelStrings.get(0).paintComponent(g);

        g.setColor(new Color(0xAF1405));
        Board.listOfPanelStrings.get(5).setText("B: "+GameResourcesKeeper.gameBadAnswerScore);
        Board.listOfPanelStrings.get(5).paintComponent(g);

        g.setColor(Color.WHITE);

        g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, scaleValueForScreen(14, 1)));

        Board.listOfPanelStrings.get(1).setText("Time: "+getGameDisplayTime());
        Board.listOfPanelStrings.get(1).paintComponent(g);


        //Speed up Button On the middle so i need to scale it
        g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, scaleValueForScreen(16, 1)));
        fixToTheMiddleTextPosition(Board.listOfPanelStrings.get(2),Board.arrayOfBoxes[0]);
        Board.listOfPanelStrings.get(2).paintComponent(g);

        g.setColor(new Color(0xB3C803));
        g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, scaleValueForScreen(14, 1)));
        if(GameResourcesKeeper.getGameSpeedMultiplier()==1) {
            Board.listOfPanelStrings.get(6).setText("Multiplier: 0x");
        }
        else
        {
            Board.listOfPanelStrings.get(6).setText("Multiplier: " + (GameResourcesKeeper.getGameSpeedMultiplier() / 2) + "x");
        }
        fixToTheMiddleTextPosition(Board.listOfPanelStrings.get(6),Board.arrayOfBoxes[0]);
        Board.listOfPanelStrings.get(6).paintComponent(g);



        g.setColor(Color.WHITE);
        //Exit
        g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, scaleValueForScreen(16, 1)));

        fixToTheMiddleTextPosition(Board.listOfPanelStrings.get(3),Board.arrayOfBoxes[1]);
        Board.listOfPanelStrings.get(3).paintComponent(g);

        //Middle
        g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, scaleValueForScreen(18, 1)));


        if(gameStarted==true&&isGamePaused==false) {
            int wordLength = g.getFontMetrics().stringWidth(Board.listOfPanelStrings.get(4).getText());

            if (wordLength > scaleValueForScreen(364, 0)) {
                Board.listOfPanelStrings.get(4).setText(Board.listOfPanelStrings.get(4).getText().substring(0, scaleValueForScreen(30, 1)) + "...");
            }
        }
        else if(isGamePaused==true)
        {
            Board.listOfPanelStrings.get(4).setText("Click here to play again!");
        }
        else
        {
            Board.listOfPanelStrings.get(4).setText("(Click or press any key to start)");

        }


        fixToTheMiddleTextPosition(Board.listOfPanelStrings.get(4),Board.arrayOfBoxes[2]);
        Board.listOfPanelStrings.get(4).paintComponent(g);

        g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, scaleValueForScreen(10, 1)));
        fixToTheMiddleTextPosition(Board.listOfPanelStrings.get(7),Board.arrayOfBoxes[3]);
        Board.listOfPanelStrings.get(7).paintComponent(g);


    }

    /**
     * Set proper text for info box
     * @param gameState
     */
    private void setTextInInfoBox(int gameState)
    {
        if(gameState==0) {
            g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, scaleValueForScreen(18, 1)));
            String text = changeFirstLetterToUpperCase(gameLevelWordsContainerOrdered.get(idOfDisplayedWordContainer[gameActualWave - 1] * 4));
            Board.listOfInfoBoxStrings.get(0).setText(text);

            fixToTheMiddleTextPosition(Board.listOfInfoBoxStrings.get(0), Board.arrayOfInfoBoxes[0]);


            g.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, scaleValueForScreen(14, 1)));

            String description = changeFirstLetterToUpperCase(gameLevelWordsContainerOrdered.get(idOfDisplayedWordContainer[gameActualWave - 1] * 4 + 2));
            Board.listOfInfoBoxStrings.get(1).setText("Description: ");

            int boxActualWidth = scaleValueForScreen(arrayOfInfoBoxes[0].getWidth(), 0);
            int maxLineWidth = boxActualWidth - scaleValueForScreen(50, 0);

            description+=".";
            ArrayList<String> descriptionStringGroups = groupDescriptionString(description);
            int descriptionLine = 1;
            int widthOfNextDescriptionLine;
            boolean nextWordIsTooWide = false;

            for (int numberOfGroup = 0; numberOfGroup < descriptionStringGroups.size(); numberOfGroup++) {
                if (descriptionLine <= Board.listOfInfoBoxStrings.size() - 1) {
                    if (nextWordIsTooWide == true) {
                        nextWordIsTooWide = false;
                        if(descriptionLine<=11) {
                            descriptionLine++;
                        }
                    }
                    String actualText = Board.listOfInfoBoxStrings.get(descriptionLine).getText();
                    String additionalText = descriptionStringGroups.get(numberOfGroup);

                    Board.listOfInfoBoxStrings.get(descriptionLine).setText(actualText + " " + additionalText);


                    if (numberOfGroup + 1 < descriptionStringGroups.size()) {
                        actualText = Board.listOfInfoBoxStrings.get(descriptionLine).getText();
                        additionalText = descriptionStringGroups.get(numberOfGroup + 1);
                        widthOfNextDescriptionLine = g.getFontMetrics().stringWidth(actualText + additionalText);
                        if (descriptionLine == 1) {
                            widthOfNextDescriptionLine += 30;
                        }

                        if (widthOfNextDescriptionLine >= maxLineWidth) {
                            nextWordIsTooWide = true;
                        }
                    }
                } else {
                    break;
                }
            }

            isDescriptionAvailable = true;
            //104
        }
        else if(gameState==1)
        {
            setDefaultListOfInfoBoxStrings();
            g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, scaleValueForScreen(18, 1)));

            if(gameActualWave<numberOfWavesCreated)
            {
                Board.listOfInfoBoxStrings.get(0).setText("Defeat!");
            }
            else
            {
                Board.listOfInfoBoxStrings.get(0).setText("Victory!");
            }
            fixToTheMiddleTextPosition(Board.listOfInfoBoxStrings.get(0), Board.arrayOfInfoBoxes[0]);

            g.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, scaleValueForScreen(16, 1)));
            Board.listOfInfoBoxStrings.get(1).setText("Score:");

            g.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, scaleValueForScreen(14, 1)));
            Board.listOfInfoBoxStrings.get(3).setText("Good answers: "+GameResourcesKeeper.gameScore);


            Board.listOfInfoBoxStrings.get(4).setText("Bad answers: "+GameResourcesKeeper.gameBadAnswerScore);


            g.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, scaleValueForScreen(16, 1)));
            Board.listOfInfoBoxStrings.get(6).setText("Time:");


            g.setFont(new Font("Lucida Sans Typewriter", Font.PLAIN, scaleValueForScreen(14, 1)));
            Board.listOfInfoBoxStrings.get(7).setText(getGameDisplayTime());


        }


    }

    private void setDefaultListOfInfoBoxStrings()
    {
        for(int i=0;i<Board.listOfInfoBoxStrings.size();i++)
        {
            Board.listOfInfoBoxStrings.get(i).setText("");
        }
    }

    /**
     * Method is responsible for drawing objects.
     * @param gameState 0-Still Playing 1-End
     */
    private void drawInfoBoxText(int gameState)
    {
        if(gameState==0)
         {
            g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, scaleValueForScreen(18, 1)));

            g.setColor(new Color(0x07B42C));
            Board.listOfInfoBoxStrings.get(0).paintComponent(g);
            g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, scaleValueForScreen(14, 1)));
            g.setColor(Color.WHITE);


            Board.listOfInfoBoxStrings.get(1).paintComponent(g);
            Board.listOfInfoBoxStrings.get(2).paintComponent(g);
            Board.listOfInfoBoxStrings.get(3).paintComponent(g);
            Board.listOfInfoBoxStrings.get(4).paintComponent(g);
            Board.listOfInfoBoxStrings.get(5).paintComponent(g);
            Board.listOfInfoBoxStrings.get(6).paintComponent(g);
            Board.listOfInfoBoxStrings.get(7).paintComponent(g);
            Board.listOfInfoBoxStrings.get(8).paintComponent(g);
            Board.listOfInfoBoxStrings.get(9).paintComponent(g);
            Board.listOfInfoBoxStrings.get(10).paintComponent(g);
            Board.listOfInfoBoxStrings.get(11).paintComponent(g);
         }
        else if(gameState==1) {
            g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, scaleValueForScreen(18, 1)));
            if(gameActualWave<numberOfWavesCreated)
            {
                g.setColor(new Color(0xAF1405));
            }
            else {
                g.setColor(new Color(0x07B42C));
            }
            Board.listOfInfoBoxStrings.get(0).paintComponent(g);
            g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, scaleValueForScreen(14, 1)));
            g.setColor(new Color(0xB3C803));
            Board.listOfInfoBoxStrings.get(1).paintComponent(g);

            g.setColor(Color.WHITE);
            Board.listOfInfoBoxStrings.get(2).paintComponent(g);
            Board.listOfInfoBoxStrings.get(3).paintComponent(g);
            Board.listOfInfoBoxStrings.get(4).paintComponent(g);
            Board.listOfInfoBoxStrings.get(5).paintComponent(g);

            g.setColor(new Color(81, 81, 214));
            Board.listOfInfoBoxStrings.get(6).paintComponent(g);
            g.setColor(Color.WHITE);
            Board.listOfInfoBoxStrings.get(7).paintComponent(g);
            Board.listOfInfoBoxStrings.get(8).paintComponent(g);
            Board.listOfInfoBoxStrings.get(9).paintComponent(g);
            Board.listOfInfoBoxStrings.get(10).paintComponent(g);
            Board.listOfInfoBoxStrings.get(11).paintComponent(g);
        }

    }

    private String changeFirstLetterToUpperCase(String newText)
    {
        String bigLetter=newText.substring(0, 1).toUpperCase();
        newText=bigLetter+newText.substring(1);
        return newText;
    }

    private ArrayList<String> groupDescriptionString(String description)
    {
        int groupIndex=0;
        ArrayList<String> descriptionStringGroups=new ArrayList<>();
        int spaceIndex;
        do {
            spaceIndex=description.indexOf(" ",groupIndex);
            if(spaceIndex!=-1) {
                descriptionStringGroups.add(description.substring(groupIndex, spaceIndex));
            }
            else
            {
                descriptionStringGroups.add(description.substring(groupIndex));
            }
            groupIndex=spaceIndex+1;
        }while(spaceIndex>0);
        return descriptionStringGroups;
    }

    /**
     * Method called for positioning object, to the middle
     * @param tempStr indexes of texts: 2,3
     * @param tempBox indexes of box'es in which those texts are(in same order): 0,1,2
     */
    private void fixToTheMiddleTextPosition(StringPainter tempStr, Box tempBox)
    {
        tempStr.setFixedX(scaleValueForScreen(tempBox.getX()+((tempBox.getWidth()/2)),0)-(g.getFontMetrics().stringWidth(tempStr.getText())/2));
    }
    private void fixToTheMiddleTextPosition(StringPainter tempStr, InfoBox tempBox)
    {
        tempStr.setFixedX(scaleValueForScreen(tempBox.getxCord()+((tempBox.getWidth()/2)),0)-(g.getFontMetrics().stringWidth(tempStr.getText())/2));
    }

    /**
     * Method which calculate seconds on more common format and pack it to one String.
     * @return String with calculated min/sec of actual time.
     */
    private String getGameDisplayTime()
    {
        String gameDisplayTime="";
        GameResourcesKeeper.gameMinuteDisplay=GameResourcesKeeper.gameTime/60;
        int gameMinuteDisplayInSeconds=GameResourcesKeeper.gameMinuteDisplay*60;
        GameResourcesKeeper.gameSecondsDisplay=GameResourcesKeeper.gameTime-gameMinuteDisplayInSeconds;

        if(GameResourcesKeeper.gameMinuteDisplay<10)
        {
            gameDisplayTime+="0";
        }

        gameDisplayTime+=GameResourcesKeeper.gameMinuteDisplay+":";

        if(GameResourcesKeeper.gameSecondsDisplay<10)
        {
            gameDisplayTime+="0";
        }

        gameDisplayTime+=GameResourcesKeeper.gameSecondsDisplay;
        return gameDisplayTime;
    }


}
