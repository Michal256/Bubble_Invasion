package Game;

import javax.swing.*;

import static Game.GameResourcesKeeper.gameStarted;
import static Game.GameResourcesKeeper.gameTime;
import static Game.GameResourcesKeeper.isGamePaused;

public class GameTimer extends JPanel implements Runnable {

        private JFrame Screen;

        public GameTimer()
        {
            this.Screen= Main.Game.Screen;
        }

        public void run()
        {

            try
            {
                if(gameStarted==true&&isGamePaused==false) {
                    gameTime = gameTime + 1;
                    Screen.repaint();
                }
                Thread.sleep(1);
            }
            catch(InterruptedException e)
            {
                System.out.println(e);
            }

        }



    }