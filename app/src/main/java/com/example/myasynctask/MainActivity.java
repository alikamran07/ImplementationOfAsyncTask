package com.example.myasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG="MainActivity";
    ProgressBar progressBarHorizontal,progressBarRound;
    TextView msgShow;
    Button bUpload;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        msgShow=findViewById(R.id.textView);
        progressBarHorizontal=findViewById(R.id.progressBar);
        progressBarRound=findViewById(R.id.progressBar4);
        bUpload=findViewById(R.id.btnUpload);

        msgShow.setText("");
        progressBarRound.setVisibility(View.VISIBLE);

    }

    public void uploadData(View view) {
        Upload uploadThread=new Upload();
        uploadThread.execute();

    }

    public void MainThread(View view) {
        Log.i(TAG,"You are in the : "+Thread.currentThread().getName());
        for (int i=0;i<10;i++){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Upload extends AsyncTask<Void,Integer,Void>
    {
        @Override
        protected void onPreExecute() {
            Log.i(TAG,"OnPreExecute: "+Thread.currentThread().getName());
            msgShow.setText("Data is Upload...");
            progressBarRound.setVisibility(View.INVISIBLE);
            bUpload.setEnabled(false);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            Log.i(TAG,"upload Task: Thread: "+Thread.currentThread().getName());
            for (int i=0;i<10;i++){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }
            return null;
        }
        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBarHorizontal.setProgress(values[0]+1);
        }

        @Override
        protected void onPostExecute(Void unused) {
            Log.i(TAG,"OnPostExecute: "+Thread.currentThread().getName());
            msgShow.setText("Data Uploaded...");
            progressBarRound.setVisibility(View.INVISIBLE);
            bUpload.setEnabled(true);
        }
    }
}