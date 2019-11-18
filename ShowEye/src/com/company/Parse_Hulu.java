package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;
import java.util.regex.Pattern;

public class Parse_Hulu implements Parser_Interface{
    private FileReader var_FileReader;
    private Scanner var_ReadData;
    private ArrayList<String> m_ParsedData;
    private FileWriter var_FileWriter;
    private File var_File;

    public Parse_Hulu()
    {
        var_File = null;
        var_FileReader = null;
        var_FileWriter = null;
    }

    @Override
    public ArrayList<String> ParseData(String FilePath)
    {
        Integer i = 1;
        boolean flag = false;
        boolean corrupt = false;

        try
        {
            var_FileReader = new FileReader(FilePath);
            var_ReadData = new Scanner(var_FileReader);

            while (var_ReadData.hasNext()) {
                String sentence = var_ReadData.nextLine();
                if (!sentence.equals("")) {
                    Entertainment Temp = new Entertainment();

                    //System.out.println("Line Number " + i + " Contains these tokens");
                    String[] arr = sentence.split(" ");

                    for (String word : arr) {
                        if (word.matches("[a-zA-Z]+") == true) {
                            if (flag == false) {
                                Temp.setTitle(word);
                                flag = true;
                            } else if (flag == true) {
                                Temp.setGenres(word);
                                flag = false;
                            }
                        }

                    }
                    corrupt = false;
                }
            }

            return null; //Placeholder ---------------- End of code to fix

        }

        catch (FileNotFoundException e)
        {
            e.printStackTrace();
            System.out.println("ERROR: File not found");
            return null;
        }

        catch(java.io.IOException e)
        {
            return null;
        }

    }

    @Override
    public void WriteData(ArrayList<String> FileData)
    {

    }
}
