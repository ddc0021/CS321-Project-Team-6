package com.company;

import javax.swing.*;

public class Entertainment {
    private String name;
    private String genre;
    public Entertainment(String name, String genre) {
        this.name = name;
        this.genre = genre;
    }
    public String getName() {
        return name;
    }
    public String getGenre() {
        return genre;
    }
    public JTextArea getTable() {
        JTextArea result = new JTextArea();
        result.append(name + "; " + genre);
        return result;
    }
}
