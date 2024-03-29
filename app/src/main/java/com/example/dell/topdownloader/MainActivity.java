package com.example.dell.topdownloader;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class MainActivity extends AppCompatActivity {

    private Button btnParse;
    private String mFileContents;
    private ListView listApps;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnParse =(Button) findViewById(R.id.btnParse);
       btnParse.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               ParseApplication parseApplications = new ParseApplication(mFileContents);
               ParseApplication.process();

           }
       });
        listApps = (ListView) findViewById(R.id.xmlListView);

        DownloadData downloadData = new DownloadData();
        downloadData.execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml");


    }

    private class DownloadData extends AsyncTask<String, Void, String> {



        @Override
        protected String doInBackground(String... strings) {
            mFileContents = downloadXMLFile(strings[0]);
            if (mFileContents == null) {
                Log.d("DownloadDATA", "Error downloading");
            }
            return mFileContents;
        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            Log.d("DownloadData", "Error downloading");

        }


        private String downloadXMLFile(String urlPath) {
            StringBuilder tempBuffer = new StringBuilder();
            try {
                URL url = new URL(urlPath);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                int response = connection.getResponseCode();
                Log.d("DownloadDAta", "THe response code" + response);
                InputStream is = connection.getInputStream();
                InputStreamReader isr = new InputStreamReader(is);

                int charRead;
                char[] inputBuffer = new char[500];
                while (true) {
                    charRead = isr.read(inputBuffer);
                    if (charRead <= 0) {
                        break;
                    }
                    tempBuffer.append(String.copyValueOf(inputBuffer, 0, charRead));
                }
                return tempBuffer.toString();
            } catch (IOException e) {
                Log.d("DownloadData", "Io exception reading data" + e.getMessage());
                e.printStackTrace();
            }
            catch(SecurityException e)
            {
                Log.d("DownloadData","Security exception Needs permission?" + e.getMessage());
            }


            return null;

        }

    }
}

