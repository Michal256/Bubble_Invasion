package Game;

public class PlayerWalkingAnimator
{
    public PlayerWalkingAnimator()
    {
            changePlayerTexture();

    }

    private void changePlayerTexture()
    {
        Player tempPlayer=Board.arrayOfPlayers[0];
        //xSrc
        int textureFrame=tempPlayer.getActualTextureFrame();
        //ySrc
        int textureDirection=tempPlayer.getTextureDirection();

        if(textureFrame>=10&&tempPlayer.isCastingSpell()==false)
        {
            tempPlayer.setActualTextureFrame(1);
        }

        //Cast
        if(textureFrame>=8&&tempPlayer.isCastingSpell()==true)
        {
            tempPlayer.setActualTextureFrame(1);
        }


        if(textureFrame>=1&&textureFrame<=9&&tempPlayer.isCastingSpell()==false)
        {
            //Count from 1
            tempPlayer.setxBeginSrc(64*(textureFrame-1));
            tempPlayer.setxEndSrc(64*textureFrame);

            //Count from 0
            tempPlayer.setyBeginSrc(64*(textureDirection));
            tempPlayer.setyEndSrc(64*(textureDirection+1));

            tempPlayer.setActualTextureFrame(textureFrame+1);
        }
        else if(textureFrame>=1&&textureFrame<=7&&tempPlayer.isCastingSpell()==true)
        {
            textureDirection=4;
            //Count from 1
            tempPlayer.setxBeginSrc(64*(textureFrame-1));
            tempPlayer.setxEndSrc(64*textureFrame);

            //Count from 0
            tempPlayer.setyBeginSrc(64*(textureDirection));
            tempPlayer.setyEndSrc(64*(textureDirection+1));

            tempPlayer.setPlayerCooldownCounter(tempPlayer.getPlayerCooldownCounter()-10);
            if(tempPlayer.getPlayerCooldownCounter()<=0)
            {
                tempPlayer.setCastingSpell(false);
                tempPlayer.setTextureDirection(0);
                tempPlayer.setPlayerCooldownCounter(tempPlayer.getPlayerSpellCastCooldown());
            }


            tempPlayer.setActualTextureFrame(textureFrame+1);
        }

    }

}
