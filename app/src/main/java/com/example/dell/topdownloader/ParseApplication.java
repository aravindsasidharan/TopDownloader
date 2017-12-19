package com.example.dell.topdownloader;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
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

    public static boolean process()
    {
        boolean status = true;
        Application currentRecord;
        boolean inEntry = false;
        String textValue ="";
        try {
            XmlPullParserFactory  factory= XmlPullParserFactory.newInstance();l
            factory.setNamespaceAware(true);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput(new StringReader(this.xmlData));
            int eventType = xpp.getEventType();
            while(eventType != XmlPullParser.END_DOCUMENT)
            {
              String tagName = xpp.getName();
              switch(eventType)
              {
                  case XmlPullParser.START_TAG:
                      Log.d("ParseApplications", "Starting tag for" + tagName);
                      if(tagName.equalsIgnoreCase("entry"))
                      {
                          inEntry = true;
                          currentRecord = new Application();


                      }
                      break;
                      case XmlPullParser.END_TAG:
                          Log.d("ParseApplications", "Starting tag for" + tagName);
                          break;
                  default:
              }
              eventType = xpp.next();
            }


        }catch (Exception e)
        {
            status = false;
            e.printStackTrace();


        }
        return true;

    }
}

