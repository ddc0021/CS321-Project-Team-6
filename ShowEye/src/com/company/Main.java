package com.company;

public class Main {

    public static void main(String[] args)
    {
        //Instantiate parser object to be used later.
        Parser_Interface m_Parser= Parse_Hulu.GetInstance();

        //Instantiate scraper object that will scrape the specified websites upon initialization.
        Jaunt_Scraper m_Scraper = Jaunt_Scraper.GetInstance();

        //Instantiate an instance of MainGUI.
        MainGUI application = new MainGUI();

        //Call the first method of Initialization and pass it the parser and scraper objects.
        application.Initialization((Parse_Hulu) m_Parser, m_Scraper);
    }
}
