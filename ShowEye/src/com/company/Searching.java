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

    /**
     *  Will store a shows data into an entertainment object called DataStore, The user needs to provide a title/name.
     *  Method will return after the user has input the show title and selected which platform or both platforms they wish to search through.
     *
     * @param userString a user given string that will hold the title/name of the show/movie that the user would like to locate within the Json files.
     * @param pathname is the path to the json files FauxFlix and hulu data file.
     * @return the entertainment object Datastore
     */
    public Entertainment Search_by_Name (String userString, String pathname)
    {
        // Reading the file name.
        file = new File(pathname);

        // Initializing a string to format the string provided by the user to put into a more searchable format that
        // will return with more accurate results.
        String CurrentData = "\"name\":\"";

        // Scanner input = new Scanner(System.in);
        CurrentData = CurrentData + userString + "\"";

        // Try/catch that makes sure the file path exists. if not throw an exception.
        try {
            Reader fileReader = new FileReader(file);
        }
        catch(java.io.FileNotFoundException e){
            return null;
        }

        try {

            // New Scanner object to read through each element of the files.
            Scanner scanner = new Scanner(file);

            // While loop to step through the file.
            while (scanner.hasNextLine())
            {
                CompareString = scanner.nextLine();

                // While the scanner has the next line it wil check to see if the current line of data contains any of the contents of the CurrentData string.
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
     * Will return
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

