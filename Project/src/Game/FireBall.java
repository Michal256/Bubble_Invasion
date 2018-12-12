package Game;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

import static Game.ScreenScaler.scaleValueForScreen;

public class FireBall extends JPanel
{
    private BufferedImage fireBallTexture;
    private int width;
    private int height;

    private int xCord;
    private int yCord;

    private int xBeginSrc;
    private int yBeginSrc;
    private int xEndSrc;
    private int yEndSrc;

    private int flyingTime;//In Milliseconds

    private int ballTextureType;//7-Spell cast/6-3Flow/2-1-Hit
    private int actualTextureFrame;

    private boolean isDangerous;

    private int fireBallSpeed;

    public FireBall(BufferedImage fireBallTexture, int xCord, int yCord, int width, int height)
    {
        this.fireBallTexture=fireBallTexture;
        this.xCord=xCord;
        this.yCord=yCord;
        this.width=width;
        this.height=height;

        this.ballTextureType=7;
        this.actualTextureFrame=8;

        this.flyingTime=0;
        this.fireBallSpeed=2;
        this.isDangerous=true;

        setEssentialSrcCords();
    }

    private void setEssentialSrcCords() {
        xBeginSrc=2048-512;
        xEndSrc=2048-256;
        yBeginSrc=2100-300;
        yEndSrc=2100;
    }


    @Override
    public void paintComponent(Graphics g)
    {
        g.drawImage(fireBallTexture, scaleValueForScreen(xCord, 0), scaleValueForScreen(yCord, 1),scaleValueForScreen(xCord + width, 0), scaleValueForScreen(yCord + height, 1), xBeginSrc, yBeginSrc, xEndSrc, yEndSrc, null);
    }

    public int getyCord() {
        return yCord;
    }

    public void setyCord(int yCord) {
        this.yCord = yCord;
    }

    public int getxCord() {
        return xCord;
    }

    public int getFireBallSpeed() {
        return fireBallSpeed;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    public boolean isDangerous() {
        return isDangerous;
    }

    public void setDangerous(boolean dangerous) {
        isDangerous = dangerous;
    }

    public int getBallTextureType() {
        return ballTextureType;
    }

    public void setBallTextureType(int ballTextureType) {
        this.ballTextureType = ballTextureType;
    }

    public int getActualTextureFrame() {
        return actualTextureFrame;
    }

    public void setActualTextureFrame(int actualTextureFrame) {
        this.actualTextureFrame = actualTextureFrame;
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

    public int getFlyingTime() {
        return flyingTime;
    }

    public void setFlyingTime(int flyingTime) {
        this.flyingTime = flyingTime;
    }
}
