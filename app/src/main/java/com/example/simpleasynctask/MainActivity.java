package com.example.simpleasynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * L'application SimpleAsyncTask contient un bouton qui lance une AsyncTask
 * qui dort dans le thread asynchrone pendant une durée aléatoire.
 */
public class MainActivity extends AppCompatActivity {

    private static final String TEXT_STATE = "currentText";

    private TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = findViewById(R.id.textView1);

        if(savedInstanceState!=null) {
            mTextView.setText(savedInstanceState.getString(TEXT_STATE));
        }
    }

    public void startTask(View view) {

        mTextView.setText(R.string.napping);

        new SimpleAsyncTask(mTextView).execute();
    }

    /**
     * Enregistre le contenu de TextView à restaurer lors d'un changement de configuration.
     * @param outState -> Le bundle dans lequel l'état de l'activité est enregistré
     * lorsqu'elle est détruite spontanément.
     */
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(TEXT_STATE, mTextView.getText().toString());
    }
}