package GameMenu;



import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import Game.GameResourcesKeeper;

public class SelectLevel {

    private JPanel panel1;
    private JComboBox level;
    private JRadioButton a20Waves;
    private JRadioButton a40Waves;
    private JRadioButton allWaves;
    private JRadioButton typeEng;
    private JRadioButton typePl;
    private JButton okButton;
    private JButton backButton;
    private JTextField player1TextField;

    public SelectLevel() {
        Main.Game.Screen.getContentPane().removeAll();
        Main.Game.Screen.getContentPane().invalidate();
        Main.Game.Screen.getContentPane().validate();
        createUIComponents();
        enterLevelsToComboBox();
        setInitialOptions();

        LActionListener lForAction=new LActionListener();
        backButton.addActionListener(lForAction);
        okButton.addActionListener(lForAction);
    }

    private void createUIComponents() {
        Main.Game.Screen.add(panel1);
    }

    private void setInitialOptions()
    {
        if(GameResourcesKeeper.gameLevelFileName!="") {
            level.setSelectedItem(GameResourcesKeeper.gameLevelFileName);
        }
        if(GameResourcesKeeper.playerName!="Player1")
        {
            player1TextField.setText(GameResourcesKeeper.playerName);
        }
        if(GameResourcesKeeper.bubblesLanguageOption==1)
        {
            typePl.setSelected(true);
        }
        if(GameResourcesKeeper.howManyWavesToDisplayOption==20)
        {
            a20Waves.setSelected(true);
        }
        else if(GameResourcesKeeper.howManyWavesToDisplayOption==40)
        {
            a40Waves.setSelected(true);
        }
    }

    private void enterLevelsToComboBox()
    {
        final File folder=new File("data/");
        listFileFromFolder(folder);

    }
    private void listFileFromFolder(final File folder)
    {
        for(final File fileEntry:folder.listFiles())
        {
            if(!(fileEntry.isDirectory()))
            {
                level.addItem(fileEntry.getName());
            }
        }
    }

    private class LActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == backButton) {
                new GameMenu.Menu();
                Main.Game.Screen.setVisible(true);
            }
            else if(e.getSource()==okButton)
            {
                Object content=level.getSelectedItem();
                if(content.toString()!="") {
                    GameResourcesKeeper.gameLevelFileName = content;
                    approveJRadioChanges();
                    if(player1TextField.getText()!="") {
                        GameResourcesKeeper.playerName = player1TextField.getText();
                    }

                    new GameMenu.Menu();
                    Main.Game.Screen.setVisible(true);
                }
            }
        }

    }

    private void approveJRadioChanges()
    {
        if(a20Waves.isSelected())
        {
            GameResourcesKeeper.howManyWavesToDisplayOption=20;
        }
        else if(a40Waves.isSelected())
        {
            GameResourcesKeeper.howManyWavesToDisplayOption=40;
        }
        else if(allWaves.isSelected())
        {
            GameResourcesKeeper.howManyWavesToDisplayOption=3000;
        }

        if(typeEng.isSelected())
        {
            GameResourcesKeeper.bubblesLanguageOption=0;
        }
        else if(typePl.isSelected())
        {
            GameResourcesKeeper.bubblesLanguageOption=1;
        }
    }
}
