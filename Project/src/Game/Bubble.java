package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static Game.ScreenScaler.scaleValueForScreen;

public class Bubble extends JPanel
{
    private int x,y;
    private int width,height;
    private BufferedImage bubbleTexture;
    private int additionalWidth;//For better look

    private int wordIndex;
    private String word;
    private int wordFontSize;
    private int wordWidth;

    private boolean bubbleWidthIsSet;
    private boolean isVisible;
    private int bubbleFallingSpeed;

    public Bubble(BufferedImage bubbleTexture,int x, int y,int additionalWidth,int height,int wordIndex)
    {
        this.bubbleTexture=bubbleTexture;
        this.x=x;
        this.y=y;
        this.additionalWidth=additionalWidth;
        this.bubbleWidthIsSet=false;
        this.height=height;
        this.wordIndex=wordIndex;
        this.wordFontSize=16;
        this.isVisible=true;
        this.bubbleFallingSpeed=1;

        setBubbleText();
    }

    public Bubble()
    {
        this.bubbleTexture=null;
        this.x=0;
        this.y=0;
        this.width=0;
        this.height=0;

        this.word="";
        this.wordIndex=0;
        this.wordFontSize=0;
        this.wordWidth=0;
        this.bubbleWidthIsSet=true;
        this.isVisible=false;
        this.bubbleFallingSpeed=0;
    }

    private void setBubbleText()
    {
        word=GameResourcesKeeper.gameLevelWordsContainerOrdered.get(wordIndex);
    }
    @Override
    public void paintComponent(Graphics g)
    {
        if(isVisible==true) {
            Font oldFont = g.getFont();
            g.setFont(new Font("Lucida Sans Typewriter", Font.BOLD, scaleValueForScreen(wordFontSize, 1)));
            wordWidth = g.getFontMetrics().stringWidth(word);

            if (bubbleWidthIsSet == false) {
                width = wordWidth + additionalWidth;
                bubbleWidthIsSet = true;
            }
            g.drawString(word, scaleValueForScreen((x + (width / 2)), 0) - wordWidth / 2, scaleValueForScreen(y + (height / 2) + wordFontSize / 2, 1));
            g.setFont(oldFont);


            g.drawImage(bubbleTexture, scaleValueForScreen(x, 0), scaleValueForScreen(y, 1), scaleValueForScreen(x + width, 0), scaleValueForScreen(y + height, 1), 0, 0, 322, 322, null);
        }
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    @Override
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public boolean isVisible() {
        return isVisible;
    }

    @Override
    public void setVisible(boolean visible) {
        isVisible = visible;
        if(visible==false) {
            Board.arrayOfSounds[1].playSound();
        }
    }

    public int getBubbleFallingSpeed() {
        return bubbleFallingSpeed;
    }

    public int getWordIndex() {
        return wordIndex;
    }
}
