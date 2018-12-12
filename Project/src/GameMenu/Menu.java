package GameMenu;

import Main.Game;
import Game.Board;
import Game.GameResourcesKeeper;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame
{
    private JFrame Screen;
    private JPanel buttonPanel,northPanel,southPanel,westPanel,eastPanel;
    private JButton startButton,selectLevelButton,optionButton,rankingButton,creditsButton,exitButton;
    public Menu()
    {
        Screen=Game.Screen;
        Screen.getContentPane().removeAll();
        Screen.getContentPane().invalidate();
        Screen.getContentPane().validate();

        Screen.setLayout(new BorderLayout());

        northPanel=new JPanel();
        southPanel=new JPanel();
        westPanel=new JPanel();
        eastPanel=new JPanel();
        buttonPanel=new JPanel();

        northPanel.setLayout(new GridLayout(1,3));
        southPanel.setLayout(new GridLayout(1,3));
        westPanel.setLayout(new GridLayout(1,1));
        eastPanel.setLayout(new GridLayout(1,1));
        buttonPanel.setLayout(new GridLayout(12,1));

        northPanel.setPreferredSize(new Dimension(Screen.getWidth(),280));

        startButton=new JButton("Play");
        selectLevelButton=new JButton("Select Level");
        optionButton=new JButton("Key bindings");
        rankingButton=new JButton("Ranking");
        creditsButton=new JButton("Credits");
        exitButton=new JButton("Exit");

        if(GameResourcesKeeper.playerName.length()>0&&GameResourcesKeeper.gameLevelFileName!="none")
        {
            startButton.setEnabled(true);
            rankingButton.setEnabled(true);
        }
        else {
            startButton.setEnabled(false);
            rankingButton.setEnabled(false);
        }

        buttonPanel.add(startButton);
        buttonPanel.add(Box.createVerticalStrut(1));
        buttonPanel.add(Box.createVerticalStrut(1));

        buttonPanel.add(selectLevelButton);
        buttonPanel.add(Box.createVerticalStrut(1));
        buttonPanel.add(optionButton);
        buttonPanel.add(Box.createVerticalStrut(1));
        buttonPanel.add(rankingButton);
        buttonPanel.add(Box.createVerticalStrut(1));
        buttonPanel.add(creditsButton);
        buttonPanel.add(Box.createVerticalStrut(1));
        buttonPanel.add(exitButton);


        Panel_ImageLoader img=new Panel_ImageLoader("img/logo/logo.png");

        northPanel.add(img);

        southPanel.add(Box.createRigidArea(new Dimension(0,80)));
        eastPanel.add(Box.createRigidArea(new Dimension(220,0)));
        westPanel.add(Box.createRigidArea(new Dimension(220,0)));


        Screen.add(buttonPanel,BorderLayout.CENTER);
        Screen.add(northPanel,BorderLayout.NORTH);
        Screen.add(southPanel,BorderLayout.SOUTH);
        Screen.add(eastPanel,BorderLayout.EAST);
        Screen.add(westPanel,BorderLayout.WEST);

        ListenForButton lForButton=new ListenForButton();
        startButton.addActionListener(lForButton);
        selectLevelButton.addActionListener(lForButton);
        optionButton.addActionListener(lForButton);
        rankingButton.addActionListener(lForButton);
        creditsButton.addActionListener(lForButton);
        exitButton.addActionListener(lForButton);

    }

    private class ListenForButton extends JFrame implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource()==startButton)
            {
                new RankingFileCreatorForNewbies("ranking/"+GameResourcesKeeper.gameLevelFileName.toString());
                Screen.setResizable(true);
                Board GameArea=new Board();


                Screen.getContentPane().removeAll();
                Screen.getContentPane().invalidate();
                Screen.getContentPane().validate();

                Screen.setContentPane(GameArea);

            }
            else if(e.getSource()==selectLevelButton)
            {
                new SelectLevel();
                Screen.setVisible(true);
            }
            else if(e.getSource()==optionButton)
            {
                new KeyBindings();
                Screen.setVisible(true);
            }
            else if(e.getSource()==rankingButton)
            {
                new Ranking();
                Screen.setVisible(true);
            }
            else if(e.getSource()==creditsButton)
            {
                new Credits();
                Screen.setVisible(true);
            }
            else if(e.getSource()==exitButton)
            {
                System.exit(0);
            }
        }
    }
}
