package com.company;

import javax.swing.*;

public class Entertainment {

    //Initialize each piece of data to be collected.
    private String var_Title;
    private String var_Genres; 
    private String var_Rating;
    private String var_Description;
    private String var_Platform;

    //Public constructor.
    public Entertainment()
    {
        this.var_Title = "";
        this.var_Genres = "";
        this.var_Rating = "";
        this.var_Description = "";
        this.var_Platform = "";
    }

    //var_Title setter.
    public void setTitle(String title)
    {
        var_Title = title;
    }

    //var_Genres setter.
    public void setGenres(String genre)
    {
        var_Genres = genre;
    }

    //var_Rating setter.
    public void setRating(String Rating)
    {
        var_Rating = Rating;
    }

    //var_Description setter.
    public void setDescription(String Description)
    {
        var_Description = Description;
    }

    //var_Platform setter.
    public void setPlatform(String Platform)
    {
        var_Platform = Platform;
    }
    
    //var_Title getter.
    public String getName()
    {
        return var_Title;
    }

    //var_Genres getter.
    public String getGenre()
    {
        return var_Genres;
    }

    //var_Rating getter.
    public String getRating()
    {
        return var_Rating;
    }

    //var_Description getter.
    public String getDescription()
    {
        return  var_Description;
    }

    //var_Platform getter.
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
