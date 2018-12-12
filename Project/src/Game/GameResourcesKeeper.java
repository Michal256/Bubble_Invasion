package Game;

import java.util.ArrayList;

public class GameResourcesKeeper {

    public static int gameTime;
    public static int gameMinuteDisplay;
    public static int gameSecondsDisplay;
    public static int gameScore;
    public static int gameBadAnswerScore;
    public static ArrayList<String> gameLevelWordsContainer;
    public static ArrayList<String> gameLevelWordsContainerOrdered;
    public static ArrayList<Integer> gameHowManyBubblesWaveContainer;
    public static int gameBubblesActuallyCreated;

    //Creates idOfDisplayedWordContainer. This array have id to all words, which user need to destroy.
    //Order of idOfDisplayedWordContainer is the order of waves.
    //So it need to be converted(multiply 4) plus 1 // if we want to get id of English word from container ordered
    public static int[] idOfDisplayedWordContainer;

    //Used to number waves while creating them in the first place.
    public static int numberOfWavesCreated;
    public static int gameActualWave;
    public static int gameNumberOfBubblesToActualWave;
    public static int gameActualNumberOfFireBalls;

    public static Object gameLevelFileName="none";
    public static int howManyWavesToDisplayOption;
    public static int bubblesLanguageOption;
    public static String playerName="Player1";

    public static boolean bringMeaNewWord;
    public static boolean isDescriptionPanelOnTop;
    public static boolean isDescriptionAvailable;
    public static boolean isSoundTurnedOn;

    public static boolean gameStarted;
    public static boolean isGameOver;
    private static int gameSpeedMultiplier;
    public static boolean isGamePaused;


    public GameResourcesKeeper()
    {
        gameTime=0;
        gameMinuteDisplay=0;
        gameSecondsDisplay=0;
        gameScore=0;
        gameBadAnswerScore=0;
        gameLevelWordsContainer=new ArrayList<>();
        gameLevelWordsContainerOrdered=new ArrayList<>();
        gameHowManyBubblesWaveContainer=new ArrayList<>();
        gameBubblesActuallyCreated=0;
        numberOfWavesCreated=0;
        gameActualWave=1;
        gameSpeedMultiplier=2;//Can't be 0 value


        bringMeaNewWord=true;
        isDescriptionPanelOnTop=false;
        isDescriptionAvailable=false;
        isSoundTurnedOn=true;

        gameStarted=false;
        isGameOver=false;
        isGamePaused=false;


        gameNumberOfBubblesToActualWave=0;
        gameActualNumberOfFireBalls=0;
    }

    public static int getGameSpeedMultiplier() {
        return gameSpeedMultiplier;
    }

    public static void setGameSpeedMultiplier(int bubbleFallingSpeedMultiplier) {
        GameResourcesKeeper.gameSpeedMultiplier = bubbleFallingSpeedMultiplier;
    }
}
