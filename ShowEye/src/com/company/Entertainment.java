package com.company;

public class Entertainment {

    private String var_Title;
    private String var_Genres; // This might need to be an array string for shows with multiple genres

    public Entertainment()
    {
        var_Title = "";
        var_Genres = "";
    }

    public void setTitle(String title)
    {
        var_Title = title;
    }

    public void setGenres(String genre)
    {
        var_Genres = genre;
    }

    public String getTitle()
    {
        return var_Title;
    }

    public String getGenres()
    {
        return var_Genres;
    }

    public String ToString()
    {
        String msg;
        msg = var_Title + " - " + var_Genres;
        return msg;
    }
}
