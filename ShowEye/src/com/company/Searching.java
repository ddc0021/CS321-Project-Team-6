package com.company;

import java.io.File;
import java.io.FileReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Scanner;
import javax.xml.crypto.Data;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.String;


public class Searching {
    private File file;
    private String CurrentData;
    private Entertainment DataStore;
    private String CompareString;
    private ArrayList<Entertainment> Entertainment_Holder;

    public void Searching()
    {
        file = null;
        CompareString = "";
        DataStore = null;
        Entertainment_Holder = null;
    }


    public Entertainment Search_by_Name (String userString, String pathname)
    {
        // Reading the file name.
        file = new File(pathname);

        String CurrentData = "\"name\":\"";

        // Scanner input = new Scanner(System.in);
        CurrentData = CurrentData + userString + "\"";

        try {
            Reader fileReader = new FileReader(file);
        }
        catch(java.io.FileNotFoundException e){
            return null;
        }

        try {

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine())
            {
                CompareString = scanner.nextLine();
                if (CompareString.contains(CurrentData))
                {

                    DataStore = new Entertainment();
                    DataStore.setTitle(CompareString);
                    CompareString = scanner.nextLine();
                    DataStore.setDescription(CompareString);
                    CompareString = scanner.nextLine();
                    DataStore.setRating(CompareString);
                    CompareString = scanner.nextLine();
                    DataStore.setGenres(CompareString);
                    CompareString = scanner.nextLine();
                    DataStore.setPlatform(CompareString);

                    return DataStore;
                }

            }
        }
        catch(java.io.FileNotFoundException e) {
            return null;
        }

        return null;

    }

    /**
     *
     * @param userString
     * @param pathname
     * @return
     */
    public ArrayList<Entertainment> Search_by_Genre (String userString, String pathname)
    {
        // Reading the file name.
        file = new File(pathname);

        //String CurrentData = "\"name\": \"";

        // Scanner input = new Scanner(System.in);
        // CurrentData = CurrentData + userString + "\"";

        try {
            Reader fileReader = new FileReader(file);
        }
        catch(java.io.FileNotFoundException e){
            return null;
        }

        try {
            Scanner scanner = new Scanner(file);
            Entertainment_Holder = new ArrayList<Entertainment>();
            String genre, description, rating;

            while (scanner.hasNextLine())
            {
                CompareString = scanner.nextLine();
                if (CompareString.contains("\"name\""))
                {
                    description = scanner.nextLine();
                    rating = scanner.nextLine();
                    genre = scanner.nextLine();

                    if(genre.contains(userString)) //If genre string contains user genre, then branch i
                    {
                        DataStore = new Entertainment(); //Set new instance of Entertainment obj

                        DataStore.setTitle(CompareString);
                        DataStore.setDescription(description);
                        DataStore.setRating(rating);
                        DataStore.setGenres(genre);
                        DataStore.setPlatform(scanner.nextLine());

                        Entertainment_Holder.add(DataStore);
                    }

                }

            }
            return Entertainment_Holder;
        }
        catch(java.io.FileNotFoundException e)
        {
            return null;
        }
    }
}


