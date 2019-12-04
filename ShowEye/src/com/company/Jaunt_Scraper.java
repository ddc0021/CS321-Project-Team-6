package com.company;

import java.io.File;
import java.io.FileWriter;
import com.jaunt.*;

public class Jaunt_Scraper {
    private File m_File;
    private File m_Dir;
    private FileWriter m_FileWriter;
    private UserAgent m_UserAgent;
    private String m_WebData;
    private String m_FilePath;
    private static Jaunt_Scraper Instance = null;

    /**
     *Constructor to create the instance for the singleton class of Jaunt_Scraper
     *
     */
    private Jaunt_Scraper()
    {
        m_Dir = new File("Data");
        m_Dir.mkdirs();
        m_File = new File(m_Dir,"Hulu_Data.txt");
        m_FilePath = m_File.getPath();
        m_UserAgent = new UserAgent();
        m_WebData = "";
        if (CreateFile() == true)
        {
            try
            {
                m_FileWriter = new FileWriter(m_File);
            }
            catch(java.io.IOException e)
            {
                System.out.println("ERROR: File not found");
            }
        }
        else System.out.println("Failed to create File");

        this.WriteFile();
    }

    /**
     * Returns a valid instance of the singleton design of the Jaunt_Scraper class.
     *
     * @return Instance
     */
    public static Jaunt_Scraper GetInstance()
    {
        if(Instance == null)
        {
            Instance = new Jaunt_Scraper();
        }
        return Instance;
    }

    /**
     * The method creates a try/catch which will instantiate v named from the implementation of the jaunt scarper libraries where the user agent will
     * be sent to the hulu website to scrape the pages and will assign it to th string m_WebData which is then returned. If it fails the try/catch
     * it will return the variable m_WebData loaded with the exception message and return it.
     *
     * @return string m_WebData
     */
    public String ScrapeData()
    {
        try
        {
            m_UserAgent = new UserAgent();
            m_UserAgent.visit("https://www.hulu.com/search?");
            m_WebData = m_UserAgent.doc.innerHTML();
            return m_WebData;
        }
        catch(com.jaunt.JauntException e)
        {
            return m_WebData;
        }
    }

    /**
     * Try/catch initializes a If statement to check if a new file needs to be created to to store the scaped data (Hulu_Data.txt) and return true. If the
     * file already exists it will print out a file already exists and returns true.
     *
     * @return boolian.
     */
    private boolean CreateFile()
    {
        try
        {
            if (m_File.createNewFile())
            {
                System.out.println("File is created!");
                return true;
            }
            else
            {
                System.out.println("File already exists.");
                return true;
            }
        }
        catch(java.io.IOException e)
        {
            System.out.println("Error: File not created");
            return false;
        }
    }

    /**
     *
     *  Method creates a try/catch to take the scrape data function ScrapeData() and writes it to the file
     *  obtained in the constructed of the jaunt_Scraper class. If it fails to locate the file it will fall to
     *  the catch and print out and exception message.
     *
     */
    public void WriteFile()
    {
        try
        {
            m_FileWriter.write(ScrapeData());
        }
        catch (java.io.IOException Love)
        {
            System.out.println("ERROR: File being written to was not found!");
        }
    }

    public String GetPath()
    {
        return m_FilePath;
    }

    public File GetDirectory()
    {
        return m_Dir;
    }

}
