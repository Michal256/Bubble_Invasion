package Game;


import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileDataReader
{
    public FileDataReader(String filePath)
    {
        readFileContent(filePath);
        setGameLevelWordsContainers();
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
                    GameResourcesKeeper.gameLevelWordsContainer.add(line);
                }
            }
            catch (IOException e)
            {
                System.out.println(e);
            }
    }

    private void setGameLevelWordsContainers()
    {
        //| - vertical bar
        String part;

        for(String arrayLine:GameResourcesKeeper.gameLevelWordsContainer)
        {
            int verticalBarIndex = 0;
            do {
                int index = arrayLine.indexOf("|", verticalBarIndex);

                if (index != -1) {
                    part = arrayLine.substring(verticalBarIndex, index);
                } else {
                    part = arrayLine.substring(verticalBarIndex);
                }

                GameResourcesKeeper.gameLevelWordsContainerOrdered.add(part);
                verticalBarIndex = index + 1;
            } while ((verticalBarIndex - 1) != -1);

        }
    }

}
