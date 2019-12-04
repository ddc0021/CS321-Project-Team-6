package com.company;


import java.util.ArrayList;

public class Main {

    public static void main(String[] args)
    {
        //Instantiate parser object to be used later.
        Parser_Interface m_Parser= new Parse_Hulu();
        
        //Instantiate scraper object that will scrape the specified websites upon initialization.
        Jaunt_Scraper m_Scraper = new Jaunt_Scraper();
        
        //Instantiate an instance of MainGUI.
        MainGUI application = new MainGUI();
        
        //Call the first method of Initalization and pass it the parser and scraper objects.
        application.Initialization((Parse_Hulu) m_Parser, m_Scraper);
        
    }
}
