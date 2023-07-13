package com.example.simpleasynctask;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void, Integer, String> {

    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;

    SimpleAsyncTask(TextView tv, ProgressBar progressBar) {
        mTextView = new WeakReference<>(tv);
        mProgressBar = new WeakReference<>(progressBar);
    }

    @Override
    protected String doInBackground(Void... voids) {
        Random r = new Random();
        int n = r.nextInt(11);

        int sleepTime = n * 200;
        int progressStep = 10;

        try {
            for (int i = 0; i <= progressStep; i++) {
                Thread.sleep(sleepTime / progressStep);
                publishProgress((i * 100) / progressStep);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Enfin réveillé après avoir dormi pendant " + sleepTime + " millisecondes !";
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        int progress = values[0];
        ProgressBar progressBar = mProgressBar.get();
        if (progressBar != null) {
            progressBar.setProgress(progress);
        }
    }

    @Override
    protected void onPostExecute(String result) {
        TextView textView = mTextView.get();
        if (textView != null) {
            textView.setText(result);
        }
        ProgressBar progressBar = mProgressBar.get();
        if (progressBar != null) {
            progressBar.setProgress(0);
        }
    }
}
