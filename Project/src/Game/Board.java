package Game;

import Main.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;

import java.util.ArrayList;
import java.util.concurrent.ScheduledThreadPoolExecutor;

import static Game.GameResourcesKeeper.*;

import static Game.ScreenScaler.scaleValueForScreen;
import static Main.ScreenView.listenersCreated;
import static java.lang.Math.random;
import static java.util.concurrent.TimeUnit.MILLISECONDS;
import static java.util.concurrent.TimeUnit.SECONDS;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//Bubble invasion!
public class Board extends JPanel {

    //Background,Panel,Flame,Player,BubbleBlack[4]-Bubble[11],player,fireball
    public static BufferedImage[] arrayOfTextures=new BufferedImage[14];
    public static SoundReproducer[] arrayOfSounds=new SoundReproducer[3];

    public static int wWidth,wHeight;

    private static ScheduledThreadPoolExecutor eventPool;

    static public GameResourcesKeeper gameResources;
    static public Box[] arrayOfBoxes=new Box[6];
    static public InfoBox[] arrayOfInfoBoxes=new InfoBox[1];
    static public ArrayList<StringPainter> listOfInfoBoxStrings=new ArrayList<>();
    public static ArrayList<StringPainter> listOfPanelStrings=new ArrayList<>();

    public static Bubble[] arrayOfBubbles;
    public static Player[] arrayOfPlayers;
    public static FireBall[] arrayOfFireBalls;

    ListenForMouse lForMouse=new ListenForMouse();
    ListenForMouseMove lForMouseMove=new ListenForMouseMove();
    ListenForKeyboard lForKeyboard=new ListenForKeyboard();






    public Board()
    {
        addMouseListener(lForMouse);
        addMouseMotionListener(lForMouseMove);
        if(listenersCreated==false) {
            Main.Game.Screen.addKeyListener(lForKeyboard);
            Main.Game.Screen.addComponentListener(new ComponentAdapter() {
                public void componentResized(ComponentEvent componentEvent) {
                    if(isDescriptionPanelOnTop==true&&isGameOver==false) {
                        int multiplier = getGameSpeedMultiplier();
                        setGameSpeedMultiplier(multiplier * 2);
                    }
                    isDescriptionPanelOnTop = false;
                    isDescriptionAvailable=false;
                }
            });
            listenersCreated=true;
        }

        initBoard();
        addThreadsToPool();
    }

    private void initBoard()
    {
        //Do not change order!
        loadImages();
        loadGameSound();
        gameResources=new GameResourcesKeeper();

        new FileDataReader("data/"+GameResourcesKeeper.gameLevelFileName.toString());
        createGameElements();


    }

    private void loadImages()
    {
        arrayOfTextures[0]=Game_ImageLoader.loadImage("img/background/tlo2.png");
        arrayOfTextures[1]=Game_ImageLoader.loadImage("img/panel/panel.png");
        arrayOfTextures[2]=Game_ImageLoader.loadImage("img/flame/flame.png");
        arrayOfTextures[3]=Game_ImageLoader.loadImage("img/player/player.png");
        arrayOfTextures[4]=Game_ImageLoader.loadImage("img/bubble/BubbleBlack.png");
        arrayOfTextures[5]=Game_ImageLoader.loadImage("img/bubble/BubbleBlue.png");
        arrayOfTextures[6]=Game_ImageLoader.loadImage("img/bubble/BubbleGreen.png");
        arrayOfTextures[7]=Game_ImageLoader.loadImage("img/bubble/BubbleOrange.png");
        arrayOfTextures[8]=Game_ImageLoader.loadImage("img/bubble/BubblePurple.png");
        arrayOfTextures[9]=Game_ImageLoader.loadImage("img/bubble/BubbleRed.png");
        arrayOfTextures[10]=Game_ImageLoader.loadImage("img/bubble/BubbleWhite.png");
        arrayOfTextures[11]=Game_ImageLoader.loadImage("img/bubble/BubbleYellow.png");
        arrayOfTextures[12]=Game_ImageLoader.loadImage("img/player/player.png");
        arrayOfTextures[13]=Game_ImageLoader.loadImage("img/flame/flame.png");

    }

    private void loadGameSound()
    {
        arrayOfSounds[0]=new SoundReproducer("sound/fireball.wav");
        arrayOfSounds[1]=new SoundReproducer("sound/bubblepop.wav");
        arrayOfSounds[2]=new SoundReproducer("sound/shimmer.wav");
    }

    private void createGameElements()
    {
        createPanelText();
        createPanelBoxes();
        arrayOfBubbles=new Bubble[3000];
        arrayOfPlayers=new Player[1];
        arrayOfFireBalls=new FireBall[7000];

        createBubblesWithWords();
        createPlayer();


    }

    private void createPanelText()
    {
        //Do not change positions!
        int textSize=16;
        listOfPanelStrings.add(new StringPainter("G: ",155,649+textSize-3));
        listOfPanelStrings.add(new StringPainter("Time: "+gameResources.gameTime,155,683));
        listOfPanelStrings.add(new StringPainter("Speed up",40,661+textSize-3));
        listOfPanelStrings.add(new StringPainter("Menu",866,661+textSize-3));

        textSize=18;
        listOfPanelStrings.add(new StringPainter("(Click or press any key to start)",311,665+(textSize/2)-3));

        textSize=16;
        listOfPanelStrings.add(new StringPainter("B:",250,649+textSize-3));

        textSize=14;
        listOfPanelStrings.add(new StringPainter("Multiplier:",40,694+textSize-2));

        textSize=10;
        listOfPanelStrings.add(new StringPainter("Description(D)",311,691+(textSize/2)-2));

        //Description Box
        textSize=18;
        listOfInfoBoxStrings.add(new StringPainter("Slowko",0,170+textSize));

        textSize=14;
        listOfInfoBoxStrings.add(new StringPainter("Description: ",243,186+18+8+textSize));
        listOfInfoBoxStrings.add(new StringPainter("",223,225+12+textSize));
        listOfInfoBoxStrings.add(new StringPainter("",223,249+14+textSize));
        listOfInfoBoxStrings.add(new StringPainter("",223,273+16+textSize));
        listOfInfoBoxStrings.add(new StringPainter("",223,297+18+textSize));
        listOfInfoBoxStrings.add(new StringPainter("",223,321+20+textSize));
        listOfInfoBoxStrings.add(new StringPainter("",223,345+22+textSize));
        listOfInfoBoxStrings.add(new StringPainter("",223,369+24+textSize));
        listOfInfoBoxStrings.add(new StringPainter("",223,393+26+textSize));
        listOfInfoBoxStrings.add(new StringPainter("",223,417+28+textSize));
        listOfInfoBoxStrings.add(new StringPainter("",223,441+30+textSize));
        listOfInfoBoxStrings.add(new StringPainter("",223,465+32+textSize));
        listOfInfoBoxStrings.add(new StringPainter("",223,489+34+textSize));

    }

    private void createPanelBoxes()
    {
        //Speed up
        arrayOfBoxes[0]=new Box(20,649,120,41,10);
        //Exit
        arrayOfBoxes[1]=new Box(866,649,120,41,10);
        //Middle
        arrayOfBoxes[2]=new Box(311,642,384,56,10);
        //Description under word
        arrayOfBoxes[3]=new Box(311,684,384,14,0);
        //Information box
        arrayOfInfoBoxes[0]=new InfoBox(arrayOfTextures[1],203,150,600,390,10);

    }

    /**
     * This method must create all bubbles on their proper positions, before game can be resized by an user.
     */
    private void createBubblesWithWords()
    {
        //Creates idOfDisplayedWordContainer. This array have id to all words, which user need to destroy.
        //Order of idOfDisplayedWordContainer is the order of waves.
        createWordContainerOfDisplayedWords();

        chooseEngSynonyms();

        changeTheLanguageOfTheBubbleContent();

        for(int i=0;i<GameResourcesKeeper.idOfDisplayedWordContainer.length;i++) {
            createOneWaveOfBubbles();
        }

        //Check if player wanted to have only 20 or 40 waves
        if(GameResourcesKeeper.howManyWavesToDisplayOption<numberOfWavesCreated)
        {
            numberOfWavesCreated=howManyWavesToDisplayOption;
        }

    }

    private void chooseEngSynonyms()
    {
        for(int i=0;i<idOfDisplayedWordContainer.length;i++)
        {
            String myLittleWord=GameResourcesKeeper.gameLevelWordsContainerOrdered.get((i*4+1));
            myLittleWord=randomWordSynonym(myLittleWord);

            GameResourcesKeeper.gameLevelWordsContainerOrdered.set(i*4+1,myLittleWord);
        }
    }

    private void changeTheLanguageOfTheBubbleContent()
    {
        //Change bubble content language
        //0-ENG 1-PL
        if(GameResourcesKeeper.bubblesLanguageOption==1)
        {
            int[] containerWithIdOfLines=GameResourcesKeeper.idOfDisplayedWordContainer;
            String wordPL;
            String wordENG;
            for(int i=0;i<containerWithIdOfLines.length;i++)
            {
                wordPL=GameResourcesKeeper.gameLevelWordsContainerOrdered.get((containerWithIdOfLines[i]*4));
                wordENG=GameResourcesKeeper.gameLevelWordsContainerOrdered.get((containerWithIdOfLines[i]*4+1));

                GameResourcesKeeper.gameLevelWordsContainerOrdered.set((containerWithIdOfLines[i])*4,wordENG);
                GameResourcesKeeper.gameLevelWordsContainerOrdered.set((containerWithIdOfLines[i])*4+1,wordPL);

            }
        }
    }

    private void createWordContainerOfDisplayedWords()
    {
        //I need additional array to shuffle numbers.
        ArrayList<Integer> temporaryContainerForIdNumbers=new ArrayList<>();
        for(int x=0;x<GameResourcesKeeper.gameLevelWordsContainer.size();x++)
        {
            temporaryContainerForIdNumbers.add(x);
        }


        GameResourcesKeeper.idOfDisplayedWordContainer=new int[GameResourcesKeeper.gameLevelWordsContainer.size()];
        for(int i=0;i<GameResourcesKeeper.idOfDisplayedWordContainer.length;i++)
        {
            int sizeOfTemporaryContainer=temporaryContainerForIdNumbers.size();
            int chosenIdOfTemporaryContainer=(int)(random()*sizeOfTemporaryContainer);
            GameResourcesKeeper.idOfDisplayedWordContainer[i]=temporaryContainerForIdNumbers.get(chosenIdOfTemporaryContainer);

            temporaryContainerForIdNumbers.remove(chosenIdOfTemporaryContainer);

        }

    }

    private void createOneWaveOfBubbles()
    {

        int howManyBubblesInLine=(int)(random()*5+2);
        int containerOfRandomWordId[]=new int[howManyBubblesInLine];//Container for bubbles of actual wave

        int xCord=0;
        int heightOfBubble=60;
        int yCord=-1*heightOfBubble;
        int additionalWidth=30;
        int lengthOfWord;



        //Code will get proper unique id of words, which will fit on the screen.
        //Bubbles must fit on the Screen, so it will random until it find proper bubbles for screen size
        int totalLengthOfBubbles;
        int safetyCounter=0;
        do {
            totalLengthOfBubbles=0;
            //Random a bubble for displayed word
            int idOfBubbleTheChosenOne=(int)(random()*howManyBubblesInLine);

            for(int i=0;i<howManyBubblesInLine;i++)
            {
                //I must assign displayed word to one of bubble
                int idOfRandomElementFromWordsContainer;
                if(i==idOfBubbleTheChosenOne)
                {
                    idOfRandomElementFromWordsContainer=GameResourcesKeeper.idOfDisplayedWordContainer[numberOfWavesCreated];
                }
                else {
                    idOfRandomElementFromWordsContainer = randomUniqueIdNumberForWave(howManyBubblesInLine,containerOfRandomWordId,idOfBubbleTheChosenOne);
                }

                containerOfRandomWordId[i] = (idOfRandomElementFromWordsContainer * 4) + 1;

                lengthOfWord=11*gameLevelWordsContainerOrdered.get(idOfRandomElementFromWordsContainer*4+1).length();

                totalLengthOfBubbles+=lengthOfWord+additionalWidth;

            }
            safetyCounter++;

            //Protect app from having words with only long length.
            if(safetyCounter>=1000)
            {
                if(howManyBubblesInLine>1)
                {
                    howManyBubblesInLine--;
                }
                else
                {
                    totalLengthOfBubbles=1005;
                    xCord=0;
                }
                safetyCounter=0;
            }
        }while((totalLengthOfBubbles+xCord)>1006);

        //Move bubbles to be on the middle of board
        int distanceLeft=(1006-totalLengthOfBubbles);
        if(howManyBubblesInLine==0)
        {
            howManyBubblesInLine=1;
        }
        int additionalDistanceBetweenBubbles=distanceLeft/(howManyBubblesInLine);
        xCord+=additionalDistanceBetweenBubbles/2;

        //Create Bubble objects with id container.
        for(int i=0;i<howManyBubblesInLine;i++) {
            int randomCNumber = (int) (random() * 8 + 4);
            int randomYCord=yCord-(int)(random()*2*heightOfBubble);

            arrayOfBubbles[gameBubblesActuallyCreated] = new Bubble(arrayOfTextures[randomCNumber], xCord, randomYCord, additionalWidth, heightOfBubble, containerOfRandomWordId[i]);
            gameBubblesActuallyCreated++;
            lengthOfWord = 11 * gameLevelWordsContainerOrdered.get(containerOfRandomWordId[i]).length();

            xCord += lengthOfWord + additionalWidth+additionalDistanceBetweenBubbles;
        }

        GameResourcesKeeper.gameHowManyBubblesWaveContainer.add(howManyBubblesInLine);
        GameResourcesKeeper.numberOfWavesCreated++;
    }


    /**
     * This is a method which returns unique id for remaining bubbles.
     * @param howManyBubblesInLine How many bubbles is actually?
     * @param containerOfRandomId Id container of 5 bubbles
     * @return unique id of line in the gameLevelWordsContainer
     */
    private int randomUniqueIdNumberForWave(int howManyBubblesInLine,int[] containerOfRandomId,int idOfBubbleTheChosenOne)
    {
        int idOfRandomElementFromWordsContainer;

        boolean availableInContainerOfRandomId;
        do{
            availableInContainerOfRandomId = false;

            idOfRandomElementFromWordsContainer = (int) (random() * GameResourcesKeeper.gameLevelWordsContainer.size());
            for (int y = 0; y < containerOfRandomId.length; y++) {
                if ((containerOfRandomId[y] == (idOfRandomElementFromWordsContainer * 4 + 1))||
                        ((idOfRandomElementFromWordsContainer*4+1)==(idOfBubbleTheChosenOne*4+1))) {
                    availableInContainerOfRandomId = true;
                    break;
                }
            }
        }while (availableInContainerOfRandomId == true);


        return idOfRandomElementFromWordsContainer;
    }

    private String randomWordSynonym(String myLittleWord)
    {
        int indexOfTag= myLittleWord.indexOf("<?&?>");
        if(indexOfTag>0)
        {
            int indexOfQMarkHashBegin=indexOfTag+4;
            int indexOfQMarkHashEnd;
            String sub;

            ArrayList<String> arrayOfSynonyms=new ArrayList<>();
            do {
                indexOfQMarkHashBegin=myLittleWord.indexOf("?#",indexOfQMarkHashBegin);
                indexOfQMarkHashEnd=myLittleWord.indexOf("?#",indexOfQMarkHashBegin+2);

                if(indexOfQMarkHashEnd>0&&indexOfQMarkHashBegin>0) {
                    sub=myLittleWord.substring(indexOfQMarkHashBegin+2,indexOfQMarkHashEnd);
                    arrayOfSynonyms.add(sub);
                }
                else if(indexOfQMarkHashBegin>0)
                {
                    sub=myLittleWord.substring(indexOfQMarkHashBegin+2);
                    arrayOfSynonyms.add(sub);
                }
                indexOfQMarkHashBegin=indexOfQMarkHashEnd;
            }while(indexOfQMarkHashEnd>0);

            //Delete last ending
            if(arrayOfSynonyms.size()>0) {
                String lastSynonym = arrayOfSynonyms.get(arrayOfSynonyms.size() - 1);
                int endingIndexOfTag = lastSynonym.indexOf("</?&?>");
                if (endingIndexOfTag > 0) {
                    arrayOfSynonyms.set(arrayOfSynonyms.size() - 1, lastSynonym.substring(0, endingIndexOfTag));
                }

                int idOfElement = (int) (Math.random() * (arrayOfSynonyms.size() + 1));
                if (idOfElement > 0) {
                    myLittleWord = arrayOfSynonyms.get(idOfElement - 1);

                } else {
                    myLittleWord = myLittleWord.substring(0, indexOfTag);

                }
            }

        }
        return myLittleWord;
    }


    private void createPlayer()
    {
        arrayOfPlayers[0]=new Player(arrayOfTextures[12],503-96/2,632-96,96,96);
    }


    @Override
    public void paintComponent(Graphics g)
    {
        //Get proper Screen width and height
        this.wWidth=getWidth();
        this.wHeight=getHeight();

        new BasicElementsDrawer(g,arrayOfTextures);

        drawPlayer(g);
        drawFireBalls(g);
        checkIfGameIsOver();



    }

    private void drawPlayer(Graphics g)
    {
        arrayOfPlayers[0].paintComponent(g);
    }

    private void drawFireBalls(Graphics g)
    {
        if(isGameOver==false) {
            for (int i = 0; i < GameResourcesKeeper.gameActualNumberOfFireBalls; i++) {
                if (arrayOfFireBalls[i].getBallTextureType() != 0) {
                    arrayOfFireBalls[i].paintComponent(g);
                }
            }
        }
    }

    private void checkIfGameIsOver()
    {
        if(isGameOver==true&&isGamePaused==false)
        {
            //eventPool.shutdown();
            isGamePaused=true;
            isDescriptionPanelOnTop=true;
            new RankingUpdater();
        }
    }


    private void castFireball()
    {
        Player tempPlayer=arrayOfPlayers[0];
        if(tempPlayer.isCastingSpell()==false)
        {
            tempPlayer.setCastingSpell(true);
            tempPlayer.setTextureDirection(4);

            arrayOfFireBalls[GameResourcesKeeper.gameActualNumberOfFireBalls] = new FireBall(arrayOfTextures[13], tempPlayer.getxCord(), tempPlayer.getyCord(), 128, 128);
            GameResourcesKeeper.gameActualNumberOfFireBalls++;

            //Play sound
            arrayOfSounds[0].playSound();
        }
    }

    private void showDescription()
    {
        if(isGameOver==false&&isDescriptionAvailable==true) {
            isDescriptionPanelOnTop = true;
            setGameSpeedMultiplier(getGameSpeedMultiplier()/2);
        }
    }

    private void hideDescription()
    {
        isDescriptionPanelOnTop = false;
        setGameSpeedMultiplier(getGameSpeedMultiplier()*2);
    }

    private class ListenForMouse implements MouseListener
    {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(gameStarted==false) {
                gameStarted = true;
            }
            else if(isDescriptionPanelOnTop==false)
            {
                //Fire a fireBall
                if (e.getX() >= scaleValueForScreen(0, 0) && e.getX() <= (scaleValueForScreen(1006, 0))
                        && e.getY() >= scaleValueForScreen(0, 1) && e.getY() <= (scaleValueForScreen(591, 1))) {
                    if (isGameOver == false) {
                        castFireball();
                    }
                }
            }

            if(isDescriptionPanelOnTop==true&&isGameOver==false) {
                hideDescription();
            }
            else {
                //Game Restart
                if (e.getX() >= scaleValueForScreen(311, 0) && e.getX() <= scaleValueForScreen(695, 0)
                        && e.getY() >= scaleValueForScreen(642, 1) && e.getY() <= scaleValueForScreen(698, 1)&&isGameOver==true) {
                    eventPool.shutdown();
                    Game.Screen.setVisible(true);
                    Main.Game.Screen.setResizable(true);
                    Board GameArea = new Board();


                    Main.Game.Screen.getContentPane().removeAll();
                    Main.Game.Screen.getContentPane().invalidate();
                    Main.Game.Screen.getContentPane().validate();

                    Main.Game.Screen.setContentPane(GameArea);
                    Game.Screen.setVisible(true);
                }

                //Exit Game
                if (e.getX() >= scaleValueForScreen(866, 0) && e.getX() <= (scaleValueForScreen(866, 0) + scaleValueForScreen(120, 0))
                        && e.getY() >= scaleValueForScreen(649, 1) && e.getY() <= (scaleValueForScreen(649, 1) + scaleValueForScreen(41, 1))) {
                    Game.Screen.setVisible(true);
                    Game.Screen.setSize(1024, 768);
                    Game.Screen.setMinimumSize(new Dimension(1024, 768));
                    Game.Screen.setLocationRelativeTo(null);
                    Game.Screen.setResizable(false);
                    eventPool.shutdown();

                    gameStarted = false;
                    new GameMenu.Menu();
                }

                //Speed button
                if (e.getX() >= scaleValueForScreen(20, 0) && e.getX() <= (scaleValueForScreen(20, 0) + scaleValueForScreen(120, 0))
                        && e.getY() >= scaleValueForScreen(649, 1) && e.getY() <= (scaleValueForScreen(649, 1) + scaleValueForScreen(41, 1))) {
                    GameResourcesKeeper.setGameSpeedMultiplier(GameResourcesKeeper.getGameSpeedMultiplier() * 2);
                }

                //Descrption
                if (e.getX() >= scaleValueForScreen(311, 0) && e.getX() <= scaleValueForScreen(695, 0)
                        && e.getY() >= scaleValueForScreen(684, 1) && e.getY() <= scaleValueForScreen(698, 1)) {
                    showDescription();
                }
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    private class ListenForMouseMove implements MouseMotionListener
    {

        @Override
        public void mouseDragged(MouseEvent e) {

        }

        @Override
        public void mouseMoved(MouseEvent e) {
            if(GameResourcesKeeper.getGameSpeedMultiplier()>1) {
                if (e.getX() >= scaleValueForScreen(0, 0) && e.getX() <= scaleValueForScreen(1006, 0)
                        && e.getY() >= scaleValueForScreen(0, 1) && e.getY() <= (scaleValueForScreen(591, 1))) {
                    Player tempPlayer = arrayOfPlayers[0];

                    //To move when difference is greater than 5
                    if ((Math.abs(tempPlayer.getDestinationXCord() - (e.getX() / ScreenScaler.getxScale())) > 5) && tempPlayer.isCastingSpell() == false) {
                        //Player has unscaled values
                        tempPlayer.setDestinationXCord((int) (e.getX() / ScreenScaler.getxScale()));
                        tempPlayer.setProperPlayerDirection();
                    }

                }
            }
        }
    }

    private class ListenForKeyboard implements KeyListener
    {
        @Override
        public void keyTyped(KeyEvent e) {
        }

        @Override
        public void keyPressed(KeyEvent e) {
            if(gameStarted==true) {
                if (e.getKeyCode() == 88 || e.getKeyCode() == 32) {
                    if (isGameOver == false && isDescriptionPanelOnTop == false) {
                        castFireball();
                    }
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {
            if(gameStarted==false) {
                gameStarted = true;
            }
            else {
                if (e.getKeyCode() == 68) {
                    if (isDescriptionPanelOnTop == false) {
                        showDescription();
                    } else {
                        hideDescription();
                    }
                }
                else if(e.getKeyCode()==77)
                {
                    GameResourcesKeeper.isSoundTurnedOn=!(GameResourcesKeeper.isSoundTurnedOn);
                }
            }

        }
    }


    public static void addThreadsToPool()
    {
        eventPool=new ScheduledThreadPoolExecutor(4);
        eventPool.scheduleAtFixedRate(new GameTimer(),0,1,SECONDS);
        eventPool.scheduleAtFixedRate(new BubbleController(),0,40,MILLISECONDS);
        eventPool.scheduleAtFixedRate(new PlayerMovementController(),0,10,MILLISECONDS);
        eventPool.scheduleAtFixedRate(new FireBallController(),0,10,MILLISECONDS);

    }

}
