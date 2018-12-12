package Game;

public class FireBallFlyingAnimation
{
    public FireBallFlyingAnimation()
    {
            changeFireBallTexture();
    }

    private void changeFireBallTexture()
    {
        for(int i=0;i<GameResourcesKeeper.gameActualNumberOfFireBalls;i++)
        {
            FireBall tempBall=Board.arrayOfFireBalls[i];
            int ballTextureType =tempBall.getBallTextureType();//0-Spell cast/1-Flow/2-Hit
            int actualTextureFrame=tempBall.getActualTextureFrame();

            //Hit
            if(tempBall.isDangerous()==false&&ballTextureType==2&&actualTextureFrame<=0)
            {
                ballTextureType--;
                tempBall.setBallTextureType(ballTextureType);

                actualTextureFrame = 1;
                tempBall.setActualTextureFrame(1);
            }

            //Flying
            if(tempBall.isDangerous()==true) {
                // 1/10 second cast
                if (tempBall.getFlyingTime() <= 200 && actualTextureFrame <= 0) {
                    ballTextureType = 6;
                    tempBall.setBallTextureType(ballTextureType);

                    actualTextureFrame = 8;
                    tempBall.setActualTextureFrame(8);
                } else if (ballTextureType <= 2) {
                    tempBall.setBallTextureType(5);
                    ballTextureType = 4;

                    actualTextureFrame = 8;
                    tempBall.setActualTextureFrame(actualTextureFrame);
                } else if (actualTextureFrame <= 0) {
                    ballTextureType--;
                    tempBall.setBallTextureType(ballTextureType);

                    actualTextureFrame = 8;
                    tempBall.setActualTextureFrame(actualTextureFrame);

                }

                //Cast Spell
                if (ballTextureType == 7 || ballTextureType == 6) {
                    tempBall.setxBeginSrc(256 * (actualTextureFrame - 1));
                    tempBall.setxEndSrc(256 * actualTextureFrame);

                    tempBall.setyBeginSrc(300 * (ballTextureType - 1));
                    tempBall.setyEndSrc(300 * (ballTextureType));

                    tempBall.setActualTextureFrame(actualTextureFrame - 1);
                }
                //After cast
                else if (ballTextureType <= 5 && ballTextureType >= 3) {
                    tempBall.setxBeginSrc(256 * (actualTextureFrame - 1));
                    tempBall.setxEndSrc(256 * actualTextureFrame);

                    tempBall.setyBeginSrc(300 * (ballTextureType - 1));
                    tempBall.setyEndSrc(300 * (ballTextureType));

                    tempBall.setActualTextureFrame(actualTextureFrame - 1);

                }
            }
            //Hit 1
            else if(tempBall.isDangerous()==false&&ballTextureType>1&&actualTextureFrame>0)
            {

                tempBall.setxBeginSrc(256 * (actualTextureFrame - 1));
                tempBall.setxEndSrc(256 * actualTextureFrame);

                tempBall.setyBeginSrc(300 * (ballTextureType - 1));
                tempBall.setyEndSrc(300 * (ballTextureType));

                tempBall.setActualTextureFrame(actualTextureFrame - 1);
            }
            //Hit 0
            else if(ballTextureType==1&&actualTextureFrame>=1)
            {
                tempBall.setxBeginSrc(256 * (actualTextureFrame - 1));
                tempBall.setxEndSrc(256 * actualTextureFrame);

                tempBall.setyBeginSrc(300 * (ballTextureType - 1));
                tempBall.setyEndSrc(300 * (ballTextureType));

                tempBall.setActualTextureFrame(actualTextureFrame - 1);
                if(actualTextureFrame-1<=0)
                {
                    tempBall.setBallTextureType(0);
                }

            }

        }




    }
}
