package GameMenu;

import Game.GameResourcesKeeper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class RankingFileCreatorForNewbies
{
    public RankingFileCreatorForNewbies(String filePath)
    {
        boolean exists=checkIfFileExists();
        if(exists==false) {
            writeToFileNewData(filePath);
        }
    }

    private boolean checkIfFileExists()
    {
        final File folder=new File("ranking/");
        boolean exists=false;
        for(final File fileEntry:folder.listFiles())
        {
            if(!(fileEntry.isDirectory()))
            {
                if(fileEntry.getName().equals(GameResourcesKeeper.gameLevelFileName.toString()))
                {
                    exists=true;
                }
            }
        }
        return exists;
    }

    public void writeToFileNewData(String filePath)
    {
        FileOutputStream outputStream=null;
        try {
            outputStream = new FileOutputStream(filePath);
            String tempString="";
            byte[] strToBytes = tempString.getBytes();
            outputStream.write(strToBytes);
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
        finally
        {
            if(outputStream !=null)
            {
                try
                {
                    outputStream.close();
                }
                catch(IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}
