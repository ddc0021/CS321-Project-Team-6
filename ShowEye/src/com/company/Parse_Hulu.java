package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class Parse_Hulu implements Parser_Interface{
    private FileReader var_FileReader;
    private static Parse_Hulu Instance = null;
    private Scanner var_ReadData;
    private ArrayList<String> m_ParsedData;
    private FileWriter var_FileWriter;
    private File var_File;
    private String  sentence;
    private int end;

    /**
     *
     *Constructor to create the instance for the singleton class of Parse_Hulu.
     *
     */
    private Parse_Hulu()
    {
        var_File = null;
        var_FileReader = null;
        var_FileWriter = null;
        end = 0;
        m_ParsedData = new ArrayList<String>();
    }

    /**
     *
     * Returns a valid instance of the singleton design of the Parse_Hulu class.
     *
     * @return
     */
    public static Parse_Hulu GetInstance()
    {
        if(Instance == null)
        {
            Instance = new Parse_Hulu();
        }
        return Instance;
    }

    /**
     * ParseData() is responsible for parsing scraped data obtained from a scrapper object. The method will output the parsed data into a JSON format data file.
     * ParseData will take in a filepath generated by Jaunt_Scraper and will parse it to the desired format and will write it to a json file PD.json where the directory is added
     * into a data file in the projects directory.
     * @param FilePath is the FilePath where the scraper stores its initial data file.
     * @param Directory is the Directory where the FilePath data is stored after its creation.
     */
    @Override
    public void ParseData(String FilePath, File Directory)
    {
        try
        {
            // Initializes var_FileReader to a new instance of FileReader and passes FilePath.
            var_FileReader = new FileReader(FilePath);

            // Initializes var_ReadData as a new scanner object and is given var_FileReader.
            var_ReadData = new Scanner(var_FileReader);

            if (var_ReadData.hasNext()) //Check if data file has data
            {
                sentence = new String ( Files.readAllBytes( Paths.get(FilePath))); //Store all file data in a String

                while((sentence.indexOf("\"series\"") != -1 && sentence.indexOf("\"premiereDate\"") != -1)) //Check if series and premiereDate exist in current string
                {
                    sentence = sentence.substring(sentence.indexOf("\"series\"")); //Rewrite sentence to start at the 1st instance of series.
                    end = sentence.indexOf("\"premiereDate\"");
                    String Temp = "{ \n" + sentence.substring(9, end-1) + ",\n\"platform\":\"Hulu\"" + " \n}, \n"; // adds the platform to the end of each show record.
                    sentence = sentence.substring(end);
                    m_ParsedData.add(Temp);
                }

                var_File = new File(Directory, "PD.json");
                try
                {
                    var_FileWriter = new FileWriter(var_File);
                    m_ParsedData = FormatData(m_ParsedData);
                    WriteData(DataCorrection(m_ParsedData));
                }
                catch(java.io.IOException e)
                {
                    System.out.println("Error: Data file not read correctly");
                }

                return;
            }
            else return;
        }

        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            System.out.println("ERROR: File not found");
            return;
        }

        catch(java.io.IOException e)
        {
            System.out.println("ERROR: Data file not read correctly");
            return;
        }
    }

    /**
     * WriteData() is responsible for Writing data to a json file PD.json. Will step through the FormattedData ArrayList and will
     * write it to var_FileWriter and then flush var_FileWriter.
     *
     * @param FormattedData
     */
    @Override
    public void WriteData(ArrayList<String> FormattedData)
    {
        int count = 0;
        if(var_FileWriter != null)
        {
            for (String s: FormattedData)
            {
                try {
                    var_FileWriter.write(s);
                    var_FileWriter.flush();
                    count++;
                } catch (java.io.IOException e) {
                    System.out.println("");
                }
            }
        }
    }

    /**
     * FormatData() is responsible for formatting Hulu Json data into a more simple cut_down and readable JSON file
     *
     * @param ParsedData
     * @return ArrayList<String> CorrectedData
     */
    public ArrayList<String> FormatData(ArrayList<String> ParsedData)
    {
        int Index, GenStr;
        ArrayList<String> CorrectedData = new ArrayList<String>();

        for (String sentence : ParsedData)
        {
            String newSentence = sentence;

            while(newSentence.contains(",\""))
            {
                Index = newSentence.indexOf(",\"");
                GenStr = newSentence.indexOf("genres");
                if(Index < GenStr) {
                    newSentence = newSentence.substring(0, Index + 1) + "\n";
                    newSentence = newSentence + (sentence.substring(Index + 1));
                }
                else break;
            }
            CorrectedData.add(newSentence);
        }
        return CorrectedData;
    }

    /**
     *Fixes any issues known with the unicode within the scraped data from hulu such as replacing "&" with "and".
     *
     * @param Data
     * @return ArrayList<String> CorrectedData
     */
    private ArrayList<String> DataCorrection(ArrayList<String> Data)
    {
        int Index, StepInterval;
        ArrayList<String> CorrectedData = new ArrayList<String>();

        for (String sentence : Data)
        {
            String newSentence = sentence;
            StepInterval = 6;
            while(newSentence.contains("\\u0026"))
            {
                Index = newSentence.indexOf("\\u0026");
                newSentence = newSentence.substring(0, Index);
                newSentence = newSentence + "and"  + sentence.substring(Index + StepInterval);
                StepInterval = StepInterval + 3;
            }
            CorrectedData.add(newSentence);
        }
        return CorrectedData;
    }
}
