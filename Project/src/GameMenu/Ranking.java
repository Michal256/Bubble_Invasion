package GameMenu;

import Game.GameResourcesKeeper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;


public class Ranking {
    private JPanel panel1;
    private JButton backButton;
    private JTable table1;
    private JScrollPane scrollPane1;
    private JLabel levelInfo;
    ArrayList<String> arrayOfPlayersResults=new ArrayList<>();

    public Ranking() {
        Main.Game.Screen.getContentPane().removeAll();
        Main.Game.Screen.getContentPane().invalidate();
        Main.Game.Screen.getContentPane().validate();
        createUIComponents();

        loadTopTenData();

        String levelInfoS=GameResourcesKeeper.gameLevelFileName.toString();
        levelInfo.setText("Level: "+levelInfoS.substring(0,levelInfoS.length()-4));
        LActionListener lForAction=new LActionListener();
        backButton.addActionListener(lForAction);

    }

    private void createUIComponents() {
        Main.Game.Screen.add(panel1);
    }

    private void  loadTopTenData()
    {
        //Load to an arrayOfPlayersResult
        readFileContent("ranking/"+GameResourcesKeeper.gameLevelFileName.toString());
        ArrayList<String> arrayOfParts=new ArrayList<>();
        for(String line:arrayOfPlayersResults)
        {
            getSeparatedParts(line,arrayOfParts);
        }

        createTableModel(table1,arrayOfParts);


        scrollPane1.getViewport().setBackground(new Color(50,50,50));

        table1.add(new JScrollPane());

    }

    private void createTableModel(JTable table1, ArrayList<String> arrayOfParts)
    {
        DefaultTableModel model=new DefaultTableModel();
        model.addColumn("No.");
        model.addColumn("Player name");
        model.addColumn("Score(G-B)");
        model.addColumn("Time");

        int tempScore;
        int tempScoreNext;

        //Bubble sort
        for(int j=0;j<(arrayOfParts.size()/3)-1;j++)
        {
            for(int i=0;i<((arrayOfParts.size()/3)-1)-j;i++)
            {
                tempScore = Integer.parseInt(arrayOfParts.get(i * 3 + 1));
                tempScoreNext = Integer.parseInt(arrayOfParts.get((i + 1) * 3 + 1));
                if (tempScoreNext > tempScore) {
                    String temp = arrayOfParts.get(i * 3 + 1);
                    arrayOfParts.set(i * 3 + 1, arrayOfParts.get((i + 1) * 3 + 1));
                    arrayOfParts.set((i + 1) * 3 + 1, temp);

                    temp = arrayOfParts.get(i * 3);
                    arrayOfParts.set(i * 3, arrayOfParts.get((i + 1) * 3));
                    arrayOfParts.set((i + 1) * 3, temp);

                    temp = arrayOfParts.get(i * 3 + 2);
                    arrayOfParts.set(i * 3 + 2, arrayOfParts.get((i + 1) * 3 + 2));
                    arrayOfParts.set((i + 1) * 3 + 2, temp);

                }
            }
        }

        int No=0;
        for(int i=0;i<(arrayOfParts.size()/3);i++)
        {

                No++;
                if (arrayOfParts.size() - 1 >= (i * 3) + 2) {
                    model.addRow(new Object[]{No, arrayOfParts.get(i * 3), arrayOfParts.get(i * 3 + 1), getGameDisplayTime(arrayOfParts.get(i * 3 + 2))});
                } else {
                    System.out.println("Error");
                }

        }

        table1.setModel(model);

        for(int i=0;i<No;i++) {
            table1.setRowHeight(i, 45);
        }
    }


    private String getGameDisplayTime(String gameSecondsString)
    {
        String gameDisplayTime="";
        int gameSeconds=Integer.parseInt(gameSecondsString);
        int gameMinutesFormat=0;
        int gameSecondsFormat=0;

        gameMinutesFormat=gameSeconds/60;
        int gameMinuteDisplayInSeconds=gameMinutesFormat*60;

        gameSecondsFormat=gameSeconds-gameMinuteDisplayInSeconds;

        if(gameMinutesFormat<10)
        {
            gameDisplayTime+="0";
        }

        gameDisplayTime+=gameMinutesFormat+":";

        if(gameSecondsFormat<10)
        {
            gameDisplayTime+="0";
        }

        gameDisplayTime+=gameSecondsFormat;
        return gameDisplayTime;
    }

    private void getSeparatedParts(String line,ArrayList<String> arrayOfParts)
    {
        int indexOfBSeparator=0;
        do {
            int indexOfESeparator=line.indexOf("|",indexOfBSeparator);
            if(indexOfESeparator>0) {
                arrayOfParts.add(line.substring(indexOfBSeparator, indexOfESeparator));
                indexOfBSeparator = indexOfESeparator + 1;
            }
            else
            {
                arrayOfParts.add(line.substring(indexOfBSeparator));
                indexOfBSeparator=-1;
            }

        }while(indexOfBSeparator>0);
    }
    private class LActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == backButton) {
                new GameMenu.Menu();
                Main.Game.Screen.setVisible(true);
            }
        }
    }

    private void readFileContent(String filePath)
    {
        InputStream inputStream=null;

        try
        {
            new RankingFileCreatorForNewbies(filePath);
            File file = new File(filePath);
            inputStream = new FileInputStream(file);
            readFromInputStream(inputStream);


        }

        catch(IOException e)
        {
            System.out.println(e);
        }

        finally
        {
            if(inputStream !=null)
            {
                try
                {
                    inputStream.close();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }

    }

    private void readFromInputStream(InputStream inputStream)
    {

        try
        {
            BufferedReader br=new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while((line=br.readLine())!=null)
            {
               arrayOfPlayersResults.add(line);
            }
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }



}
