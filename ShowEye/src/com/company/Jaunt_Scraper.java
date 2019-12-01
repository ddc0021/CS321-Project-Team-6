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

    public Jaunt_Scraper() //Must place scraper functionality in this class!!!
    {
        //File path is specific to David's Mac
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
