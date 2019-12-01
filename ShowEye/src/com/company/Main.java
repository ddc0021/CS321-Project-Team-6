package com.company;


import java.util.ArrayList;

public class Main {

    public static void main(String[] args)
    {
        //MUST CREATE AND CALL SCRAPER TO SCRAPE DATA
        Parser_Interface m_Parser= new Parse_Hulu();
        Jaunt_Scraper m_Scraper = new Jaunt_Scraper();
        MainGUI application = new MainGUI();
        application.Initialization((Parse_Hulu) m_Parser, m_Scraper);
        // m_Parser.FormatData(a, m_Scraper.GetDirectory());
    }
}
