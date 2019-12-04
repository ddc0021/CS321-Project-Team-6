package com.company;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MainGUI {
    public static boolean set_Hulu = false;
    public static boolean set_FauxFlix = false;
    public static boolean search_by_name_flag = false;
    public static boolean search_by_genre_flag = false;
    public static int rows_x = 0;
    public static int columns_y = 5;
    public static ArrayList<Entertainment> m_show_HuluNames = null;
    public static ArrayList<Entertainment> m_show_FauxNames = null;
    public static ArrayList<Entertainment> m_show_HuluGenres = null;
    public static ArrayList<Entertainment> m_show_fauxGenres = null;
    public static Searching my_search = new Searching();


    /**
     *
     * Constructs and displays the Initialization GUI (2 checkboxes and a Go button). Offers users to select FauxFLix
     * and/or Hulu via the two JCheckBoxes. Depending on which boxes are selected, it will call Parse_Hulu to parse the
     * scraped data. It will handle attempting to press go without checking a box first. Once a box has been checked and
     * the go button has been press, flags indicating which platforms were parsed will be set and Search() will be called.
     *
     * @param Parser The instantiated Parse_Hulu object that will be used to parse through the scraped Hulu json fiile.
     * @param Scraper The instantiated Scraper object that will be used to obtain the file name and file path of scraped json file.
     */
    public static void Initialization(Parse_Hulu Parser, Jaunt_Scraper Scraper) {
        
        //Initializes each element of the Initialization GUI and formats them.
        JFrame my_Container = new JFrame();
        my_Container.setTitle("Initialization");
        my_Container.setSize(400, 400);
        my_Container.setLayout(new FlowLayout());
        JPanel my_panel = new JPanel();
        my_panel.setBounds(150, 130, 100, 100);

        JLabel my_instructions = new JLabel("Choose which platforms whose libraries you would like to search from");
        JCheckBox box_Hulu = new JCheckBox("Hulu");
        JCheckBox box_FauxFlix = new JCheckBox("FauxFlix");
        JButton go_button = new JButton("Go");

        go_button.addActionListener(new ActionListener() {

            // Action Listener that handles setting flags for the different platform and calling Search()
            public void actionPerformed(ActionEvent event) {
                if (box_Hulu.isSelected()) {
                    set_Hulu = true;
                    Parser.ParseData(Scraper.GetPath(), Scraper.GetDirectory());
                }
                if (box_FauxFlix.isSelected()) {
                    set_FauxFlix = true;
                }
                if (!box_Hulu.isSelected() && !box_FauxFlix.isSelected()) {

                    //Handles attempting to search without checking at least one check box
                    JOptionPane my_pane_1 = new JOptionPane();

                    JOptionPane.showMessageDialog(null,
                            "You must choose at least one.",
                            "Oops",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                if (box_Hulu.isSelected() || box_FauxFlix.isSelected()) {
                    MainGUI.Search();
                    my_Container.dispatchEvent(new WindowEvent(my_Container, WindowEvent.WINDOW_CLOSING));
                }
            }
        });

        //Further formats the GUI elements of Initialization.
        my_panel.setLayout(new BoxLayout(my_panel, BoxLayout.PAGE_AXIS));

        my_panel.setSize(400,400);
        my_panel.add(my_instructions);
        my_panel.add(box_FauxFlix);
        my_panel.add(box_Hulu);
        my_panel.add(go_button);
        my_Container.add(my_panel);
        my_Container.setSize(450, 150);
        my_Container.setVisible(true);
    }

    /**
     *
     * Constructs and displays the Search GUI (a JTabbedPane with 2 tabs, a JTextArea, a JComboBox, and 2 JCheckBoxes
     * and a Search button for both tabs). If on the first tab, accepts a text input from a user and calls the
     * Search_by_Name method from Searching. If on the seocond tab, allows users to select from a list of genres in a
     * combo box and calls the Search_by_Genre methods from Searching. It will handle attempting to press search without
     * checking a box first. Once the appropriate methods of Searching have been called, it will call Results();
     *
     */
    public static void Search() {
        JFrame my_frame = new JFrame();
        my_frame.setTitle("Search");

        //Creates a list of genres to choose from in the search_by_genre combo box
        String[] list_of_genres = {"Action", "Adult Animation", "Adventure", "Animation", "Business and Finance", "Comedy",
                "Crime", "Dark", "Detective", "Documentary", "Drama", "Family", "Fantasy",  "Horror", "Kids", "Late Night",
                "Legal", "Medical",  "Music", "Mystery", "Political", "Romance", "Reality", "Satire", "Science Fiction",
                "Sitcom", "Sketch Comedy", "Superheroes", "Suspense"};

        //Initializes each element of the Search GUI and formats them.
        JTextArea my_textarea = new JTextArea(1, 25);
        JPanel search_by_name = new JPanel();
        search_by_name.setLayout(new FlowLayout());
        search_by_name.add(my_textarea);
        JCheckBox box_Hulu_N = new JCheckBox("Hulu");
        JCheckBox box_FauxFlix_N = new JCheckBox("FauxFlix");
        JCheckBox box_Hulu_G = new JCheckBox("Hulu");
        JCheckBox box_FauxFlix_G = new JCheckBox("FauxFlix");

        //Adds the checkboxes to the search_by_name panel only if the flags were set in Initialization()
        if (set_Hulu == true)
        {
            search_by_name.add(box_Hulu_N);
        }
        if (set_FauxFlix == true)
        {
            search_by_name.add(box_FauxFlix_N);
        }
        JButton button_SearchN = new JButton("Search");
        search_by_name.add(button_SearchN);

        //Calls Search_by_Name on the appropriate files based on which boxes are checked when button_SearchN is pressed.
        button_SearchN.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event)
            {
                search_by_name_flag = true;
                search_by_genre_flag  = false;
                String my_string = my_textarea.getText();

                if (box_Hulu_N.isSelected() && !my_string.equals(""))
                {
                    m_show_HuluNames = my_search.Search_by_Name(my_string, "C:\\Users\\Nic Bannister\\Documents\\ShowEye_Team6\\Data\\PD.json");

                    if(m_show_HuluNames.size() < 1)
                    {
                        System.out.println("ERROR: Movie/Show not found in database!");
                    }
                }
                if (box_FauxFlix_N.isSelected() && !my_string.equals(""))
                {
                    m_show_FauxNames = my_search.Search_by_Name(my_string, "C:\\Users\\Nic Bannister\\Documents\\ShowEye_Team6\\Data\\FauxFlix.json");

                    if(m_show_FauxNames.size() < 1)
                    {
                        System.out.println("ERROR: Movie/Show not found in FauxFile!");
                    }

                }
                if ((!box_Hulu_N.isSelected() && !box_FauxFlix_N.isSelected()))
                {
                    JOptionPane my_pane_2 = new JOptionPane();

                    JOptionPane.showMessageDialog(null,
                            "You must choose at least one.",
                            "Oops",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                if (box_Hulu_N.isSelected() || box_FauxFlix_N.isSelected())
                {
                    MainGUI.Results();
                    my_frame.dispatchEvent(new WindowEvent(my_frame, WindowEvent.WINDOW_CLOSING));
                }
            }
        });

        JComboBox my_combobox = new JComboBox(list_of_genres);
        my_combobox.setPreferredSize(new Dimension(250, 20));
        my_combobox.setSelectedIndex(0);
        JPanel search_by_genre = new JPanel();
        search_by_genre.add(my_combobox);

        //Adds the checkboxes to the search_by_genre panel only if the flags were set in Initialization()
        if (set_Hulu == true)
        {
            search_by_genre.add(box_Hulu_G);
        }
        if (set_FauxFlix) {
            search_by_genre.add(box_FauxFlix_G);
        }
        JButton button_SearchG = new JButton("Search");
        search_by_genre.add(button_SearchG);

        //Calls Search_by_Genre on the appropriate files based on which boxes are checked when button_SearchG is pressed.
        button_SearchG.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {

                search_by_genre_flag = true;
                search_by_name_flag = false;
                String z = my_combobox.getSelectedItem().toString();

                if (box_Hulu_G.isSelected()) {

                    m_show_HuluGenres = my_search.Search_by_Genre(z, "C:\\Users\\Nic Bannister\\Documents\\ShowEye_Team6\\Data\\PD.json");
                    rows_x = 1;
                }
                if (box_FauxFlix_G.isSelected()) {
                    m_show_fauxGenres = my_search.Search_by_Genre(z, "C:\\Users\\Nic Bannister\\Documents\\ShowEye_Team6\\Data\\FauxFlix.json");
                    columns_y = 5;
                }
                if (!box_Hulu_G.isSelected() && !box_FauxFlix_G.isSelected()) {
                    JOptionPane my_pane_3 = new JOptionPane();

                    JOptionPane.showMessageDialog(null,
                            "You must choose at least one.",
                            "Oops",
                            JOptionPane.INFORMATION_MESSAGE);
                }
                if (box_Hulu_G.isSelected() || box_FauxFlix_G.isSelected()) {
                    MainGUI.Results();
                    my_frame.dispatchEvent(new WindowEvent(my_frame, WindowEvent.WINDOW_CLOSING));
                }
            }
        });

        //Further formats the GUI elements of Search.
        JTabbedPane my_tabbed_search = new JTabbedPane();

        my_tabbed_search.setBounds(50, 50, 300, 100);
        my_tabbed_search.add("Search By Name", search_by_name);
        my_tabbed_search.add("Search By Genre", search_by_genre);
        my_frame.add(my_tabbed_search);
        my_frame.setSize(400, 250);
        my_frame.setLayout(null);
        my_frame.setVisible(true);
    }

    /**
     *
     * Constructs and displays the Results GUI (A JTable and two JButtons to handle flow). Depending on which method(s)
     * from Searching was called in Search(), multiple rows filled with information obtained via the previously mentioned
     * method calls will be added to the JTable. The first button will call Search() again and allow the user to conduct
     * another search, while the second button will close the program.
     *
     */
    public static void Results() {
        
        //Initializes each element of the Results GUI and formats them.
        JFrame frame = new JFrame();
        JPanel panel = new JPanel();
        String[] my_columns = {"Title", "Description", "Rating","Genres","Platform"};
        DefaultTableModel my_model = new DefaultTableModel();
        JTable my_table = new JTable(my_model);
        my_model.addColumn("Title");
        my_model.addColumn("Description");
        my_model.addColumn("Rating");
        my_model.addColumn("Genres");
        my_model.addColumn("Platform");
        int counter = 0;

        //Fill the table with rows that are filled with the search results information.
        if(search_by_name_flag == true && (m_show_FauxNames != null || m_show_HuluNames != null))
        {
            if(m_show_HuluNames != null && m_show_FauxNames != null)
            {
                for (Entertainment ent : m_show_HuluNames)
                {
                    my_model.insertRow(counter, new Object[]{ent.getName(), ent.getDescription(), ent.getRating(), ent.getGenre(), ent.getPlatform()});
                    counter++;
                }
                for (Entertainment ent : m_show_FauxNames)
                {
                    my_model.insertRow(counter, new Object[]{ent.getName(), ent.getDescription(), ent.getRating(), ent.getGenre(), ent.getPlatform()});
                    counter++;
                }
            }
            else if(m_show_HuluNames != null)
            {
                for (Entertainment ent : m_show_HuluNames)
                {
                    my_model.insertRow(counter, new Object[]{ent.getName(), ent.getDescription(), ent.getRating(), ent.getGenre(), ent.getPlatform()});
                    counter++;
                }
            }
            else if(m_show_FauxNames != null)
            {
                for (Entertainment ent : m_show_FauxNames)
                {
                    my_model.insertRow(counter, new Object[]{ent.getName(), ent.getDescription(), ent.getRating(), ent.getGenre(), ent.getPlatform()});
                    counter++;
                }
            }
        }

        else if((m_show_HuluGenres != null || m_show_fauxGenres != null) && search_by_genre_flag == true )
        {
            if(m_show_HuluGenres != null && m_show_fauxGenres != null) {
                for (Entertainment ent : m_show_HuluGenres) {
                    my_model.insertRow(counter, new Object[]{ent.getName(), ent.getDescription(), ent.getRating(), ent.getGenre(), ent.getPlatform()});
                    counter++;
                }
                for (Entertainment ent : m_show_fauxGenres){
                    my_model.insertRow(counter, new Object[]{ent.getName(), ent.getDescription(), ent.getRating(), ent.getGenre(), ent.getPlatform()});
                    counter++;
                }
            }
            else if(m_show_HuluGenres != null)
            {
                for (Entertainment ent : m_show_HuluGenres) {
                    my_model.insertRow(counter, new Object[]{ent.getName(), ent.getDescription(), ent.getRating(), ent.getGenre(), ent.getPlatform()});
                    counter++;
                }
            }
            else if(m_show_fauxGenres != null)
            {
                for (Entertainment ent : m_show_fauxGenres){
                    my_model.insertRow(counter, new Object[]{ent.getName(), ent.getDescription(), ent.getRating(), ent.getGenre(), ent.getPlatform()});
                    counter++;
                }
            }
        }

        else
        {
            System.out.println("ShowEye was unable to find your show. Please try again.");
        }

        JButton search_again = new JButton("Search for Another Show");
        JButton close_program = new JButton("Close Program");

        //Empties the array list and allows the user to conduct another search.
        search_again.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
                m_show_FauxNames = null;
                m_show_HuluNames = null;
                m_show_fauxGenres = null;
                m_show_HuluGenres = null;
                MainGUI.Search();
            }
        });

        //Exits the program.
        close_program.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
            }
        });

        //Further formats the GUI elements of Results.
        panel.setLayout(new BoxLayout(panel, BoxLayout.PAGE_AXIS));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1900, 1000);
        frame.setTitle("Results Page");
        panel.add(new JScrollPane(my_table));
        panel.add(search_again);
        panel.add(close_program);
        frame.add(panel);

        frame.setVisible(true);
    }
}
