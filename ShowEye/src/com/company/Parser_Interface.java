package com.company;

import com.jaunt.component.*;
import com.jaunt.*;
import java.io.*;
import java.util.*;

public interface Parser_Interface {
    public void ParseData(String FilePath, File Directory);
    public void WriteData(ArrayList<String> FormattedData);
}
