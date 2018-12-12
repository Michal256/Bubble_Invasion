package Game;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class RankingUpdater
{
    String tempString;
    private String fileName;
    public RankingUpdater()
    {
        tempString="";
        this.fileName="ranking/"+GameResourcesKeeper.gameLevelFileName.toString();
        readFileContent(fileName);
        writeToFileNewData();
    }
    private void readFileContent(String filePath)
    {
        InputStream inputStream=null;

        try
        {
            File file=new File(filePath);
            inputStream=new FileInputStream(file);
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
            BufferedReader br=new BufferedReader(new InputStreamReader(inputStream,StandardCharsets.UTF_8));

            String line;
            while((line=br.readLine())!=null)
            {
               tempString+=line+System.getProperty("line.separator");
            }
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }

    public void writeToFileNewData()
    {
        FileOutputStream outputStream=null;
        try {
            outputStream = new FileOutputStream(fileName);
            tempString += GameResourcesKeeper.playerName + "|" + (GameResourcesKeeper.gameScore - GameResourcesKeeper.gameBadAnswerScore) +
                    "|" + GameResourcesKeeper.gameTime+System.getProperty("line.separator");
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
