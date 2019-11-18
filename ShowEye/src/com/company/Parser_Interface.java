package com.company;

import com.jaunt.component.*;
import com.jaunt.*;
import java.io.*;
import java.util.*;

public interface Parser_Interface {
    //Writer m_FileWriter = null;
    // String m_WebData = null;

    public ArrayList<String> ParseData(String FilePath);
    public void WriteData(ArrayList<String> FileData);
}
