package com.company;

import javax.swing.*;

// Take code from the constructor and turn it into a function if that is what you need
public class Result_Table {
    private JPanel searchResults;
    private String[] genres = {"Action", "Horror", "Comedy"};
    private JFrame frame;
    public Result_Table(String search) {
        // Box and scrollbar
        searchResults = new JPanel();
        searchResults.setSize(400, 400);
        searchResults.setLayout(new BoxLayout(searchResults, BoxLayout.PAGE_AXIS));
        JScrollPane srScroll = new JScrollPane(searchResults, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        srScroll.setSize(400, 400);

        // test entertainment
        Entertainment e1 = new Entertainment("Movie 1", "Action"),
                e2 = new Entertainment("Movie 2", "Action"),
                e3 = new Entertainment("Movie 3", "Horror");

        boolean isGenre = false;
        for (String genre: genres) {
            if (genre.equals(search)) {
                isGenre = true;
                break;
            }
        }
        if (isGenre) {
            if (search.equals(e1.getGenre()))
                searchResults.add(e1.getTable());
            if (search.equals(e2.getGenre()))
                searchResults.add(e2.getTable());
            if (search.equals(e3.getGenre()))
                searchResults.add(e3.getTable());
        }
        frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250,100);
        frame.add(srScroll);
        frame.setVisible(true);
    }
}
