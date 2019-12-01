package com.company;

import javax.swing.*;

public class Entertainment {

    private String var_Title;
    private String var_Genres; // This might need to be an array string for shows with multiple genres
    private String var_Rating;
    private String var_Description;
    private String var_Platform;

    public Entertainment()
    {
        this.var_Title = "";
        this.var_Genres = "";
        this.var_Rating = "";
        this.var_Description = "";
        this.var_Platform = "";
    }

    public void setTitle(String title)
    {
        var_Title = title;
    }

    public void setGenres(String genre)
    {
        var_Genres = genre;
    }

    public void setRating(String Rating)
    {
        var_Rating = Rating;
    }

    public void setDescription(String Description)
    {
        var_Description = Description;
    }

    public void setPlatform(String Platform)
    {
        var_Platform = Platform;
    }

    public String getName()
    {
        return var_Title;
    }

    public String getGenre()
    {
        return var_Genres;
    }

    public String getRating()
    {
        return var_Rating;
    }

    public String getDescription()
    {
        return  var_Description;
    }

    public String getPlatform()
    {
        return var_Platform;
    }

    public JTextArea getTable()
    {
        JTextArea result = new JTextArea();
        result.append(var_Title + "; " + var_Genres);
        return result;
    }
}
