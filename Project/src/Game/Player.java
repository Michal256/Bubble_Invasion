package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static Game.ScreenScaler.scaleValueForScreen;

public class Player extends JPanel
{
    private BufferedImage playerTexture;
    private int width;
    private int height;

    private int xCord;
    private int yCord;

    private int xBeginSrc;
    private int yBeginSrc;
    private int xEndSrc;
    private int yEndSrc;

    private int destinationXCord;

    private int textureDirection;//0-top 1-left 2-bottom 3-right
    private int actualTextureFrame;

    private int playerWalkSpeed;

    private int playerSpellCastCooldown;
    private int playerCooldownCounter;

    private boolean isCastingSpell;

    public Player(BufferedImage playerTexture,int xCord,int yCord,int width,int height)
    {
        this.playerTexture=playerTexture;
        this.xCord=xCord;
        this.yCord=yCord;
        this.width=width;
        this.height=height;
        this.isCastingSpell=false;
        this.textureDirection=0;
        this.playerWalkSpeed=2;
        this.playerSpellCastCooldown=200/GameResourcesKeeper.getGameSpeedMultiplier();
        this.playerCooldownCounter=200/GameResourcesKeeper.getGameSpeedMultiplier();
        setEssentialSrcCords();
    }

    private void setEssentialSrcCords()
    {
        actualTextureFrame=1;
        //Top
        if(textureDirection==0)
        {
            xBeginSrc=0;
            xEndSrc=64;
            yBeginSrc=0;
            yEndSrc=64;
        }
        //Left
        else if(textureDirection==1)
        {
            xBeginSrc=0;
            xEndSrc=64;
            yBeginSrc=64;
            yEndSrc=128;
        }
        //Bottom
        else if(textureDirection==2)
        {
            xBeginSrc=0;
            xEndSrc=64;
            yBeginSrc=128;
            yEndSrc=192;
        }
        //Right
        else if(textureDirection==3)
        {
            xBeginSrc=0;
            xEndSrc=64;
            yBeginSrc=192;
            yEndSrc=256;
        }

    }

    public void setProperPlayerDirection()
    {
        //Left
        if(xCord>destinationXCord-width/2)
        {
           textureDirection=1;
        }
        //Right
        else if(xCord<destinationXCord+width/2)
        {
            textureDirection=3;
        }
        else
        {
            textureDirection=0;
        }
        setEssentialSrcCords();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        g.drawImage(playerTexture, scaleValueForScreen(xCord, 0), scaleValueForScreen(yCord, 1),scaleValueForScreen(xCord + width, 0), scaleValueForScreen(yCord + height, 1), xBeginSrc, yBeginSrc, xEndSrc, yEndSrc, null);
    }

    public int getxCord() {
        return xCord;
    }

    public void setxCord(int xCord) {
        this.xCord = xCord;
    }

    public int getyCord() {
        return yCord;
    }

    public int getxBeginSrc() {
        return xBeginSrc;
    }

    public void setxBeginSrc(int xBeginSrc) {
        this.xBeginSrc = xBeginSrc;
    }

    public int getyBeginSrc() {
        return yBeginSrc;
    }

    public void setyBeginSrc(int yBeginSrc) {
        this.yBeginSrc = yBeginSrc;
    }

    public int getxEndSrc() {
        return xEndSrc;
    }

    public void setxEndSrc(int xEndSrc) {
        this.xEndSrc = xEndSrc;
    }

    public int getyEndSrc() {
        return yEndSrc;
    }

    public void setyEndSrc(int yEndSrc) {
        this.yEndSrc = yEndSrc;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public int getDestinationXCord() {
        return destinationXCord;
    }

    public int getPlayerWalkSpeed() {
        return playerWalkSpeed;
    }

    public void setDestinationXCord(int destinationXCord) {
        this.destinationXCord = destinationXCord;
    }

    public int getTextureDirection() {
        return textureDirection;
    }

    public void setTextureDirection(int textureDirection) {
        this.textureDirection = textureDirection;
        setEssentialSrcCords();
    }

    public int getActualTextureFrame() {
        return actualTextureFrame;
    }

    public void setActualTextureFrame(int actualTextureFrame) {
        this.actualTextureFrame = actualTextureFrame;
    }

    public boolean isCastingSpell() {
        return isCastingSpell;
    }

    public void setCastingSpell(boolean castingSpell) {
        isCastingSpell = castingSpell;
    }

    public int getPlayerSpellCastCooldown() {
        return playerSpellCastCooldown;
    }

    public int getPlayerCooldownCounter() {
        return playerCooldownCounter;
    }

    public void setPlayerCooldownCounter(int playerCooldownCounter) {
        this.playerCooldownCounter = playerCooldownCounter;
    }
}
