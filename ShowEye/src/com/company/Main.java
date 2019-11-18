package com.company;

public class Main {

    public static void main(String[] args)
    {
        //MUST CREATE AND CALL SCRAPER TO SCRAPE DATA
        Parser_Interface m_Parser= new Parse_Hulu();
        Jaunt_Scraper m_Scraper = new Jaunt_Scraper();
        m_Parser.ParseData(m_Scraper.GetPath());
    }
}
