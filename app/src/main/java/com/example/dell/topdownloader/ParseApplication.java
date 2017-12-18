package com.example.dell.topdownloader;

import java.util.ArrayList;

/**
 * Created by DELL on 12/18/2017.
 */

public class ParseApplication {

    private String xmlData;
    private ArrayList<Application>applications;


    public ParseApplication(String xmlData)
    {
        this.xmlData = xmlData;
        applications = new ArrayList<>();
    }

    public ArrayList<Application> getApplications() {
        return applications;
    }
}
