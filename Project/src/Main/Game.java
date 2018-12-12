package Main;

import GameMenu.Menu;
import javax.swing.*;


public class Game {
    public static JFrame Screen;

    public static void main(String[] args)
    {
            Screen=new JFrame();
            new Game();

    }

    public Game()
    {
    new ScreenView();
    getMenu();//Menu implements Board to Screen

    }

    public void getMenu()
    {
        new Menu();
        Screen.setVisible(true);
        Screen.setResizable(false);
    }

}
