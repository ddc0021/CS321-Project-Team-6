package com.company;
import javax.swing.*;
import java.awt.*;

public class Main_GUI {
  public static boolean set_Hulu = false;
    public static boolean set_FauxFlix = false;

    public static void Initialization()
    {
        JCheckBox box_Hulu = new JCheckBox("Hulu");
        JCheckBox box_FauxFlix = new JCheckBox("FauxFlix");
        JButton var_go = new JButton("Go");
        Object[] my_options = {box_Hulu, box_FauxFlix, var_go};
        JOptionPane.showOptionDialog(null,
                "Please select which platforms you would like to scrape data from",
                "Initialization",
                JOptionPane.YES_OPTION,
                JOptionPane.PLAIN_MESSAGE,
                null,
                my_options,
                my_options[0]);

        if (box_Hulu.isSelected())
        {
            set_Hulu = true;
            System.out.println("Hulu button works, Hulu_selected has been set to true");
            //Implement the Hulu-scraping code here
        }
        if (box_FauxFlix.isSelected())
        {
            set_FauxFlix = true;
            System.out.println("FauxFlix button works, FauxFlix_selected has been set to true");
            //Implement the FauxFlix-scraping code here
        }
        else
        {
            System.out.println("Oops");
            //Close the program code goes here
        }
    }

    public static void Search()
    {
        JFrame my_frame = new JFrame();
        String[] list_of_genres = {"Action", "Adventure", "Comedy", "Drama", "Horror", "Sci-Fi", "Suspense"};

        JTextArea my_textarea = new JTextArea(5,20);
        JPanel search_by_name = new JPanel();
        search_by_name.add(my_textarea);
        if(set_Hulu == true)
        {
            JCheckBox box_Hulu = new JCheckBox("Hulu");
            search_by_name.add(box_Hulu);
        }
        if(set_FauxFlix)
        {
            JCheckBox box_FauxFlix = new JCheckBox("FauxFlix");
            search_by_name.add(box_FauxFlix);
        }
        JButton button_SearchN = new JButton ("Search");
        search_by_name.add(button_SearchN);

        JComboBox my_combobox = new JComboBox(list_of_genres);
        my_combobox.setSelectedIndex(0);
        JPanel search_by_genre = new JPanel();
        search_by_genre.add(my_combobox);
        if(set_Hulu == true)
        {
            JCheckBox box_Hulu = new JCheckBox("Hulu");
            search_by_genre.add(box_Hulu);
        }
        if(set_FauxFlix)
        {
            JCheckBox box_FauxFlix = new JCheckBox("FauxFlix");
            search_by_genre.add(box_FauxFlix);
        }
        JButton button_SearchG = new JButton ("Search");
        search_by_genre.add(button_SearchG);


        JTabbedPane my_search = new JTabbedPane();

        my_search.setBounds(50,50,300,200);
        my_search.add("Search By Name", search_by_name);
        my_search.add("Search By Genre", search_by_genre);
        my_frame.add(my_search);
        my_frame.setSize(400,400);
        my_frame.setLayout(null);
        my_frame.setVisible(true);
    }

    public static void Results()
    {

    }
}
