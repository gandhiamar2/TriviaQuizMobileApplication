package com.example.gandh.hw4_amar;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Asyncstarttrivia.triviadataintf {
    ImageView iv;
    Button Exit, start;
    ProgressBar pd;
    TextView loading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Trivia App");
        iv = (ImageView) findViewById(R.id.imageView);
        Exit = (Button) findViewById(R.id.button);
        start = (Button) findViewById(R.id.button2);
        pd = (ProgressBar) findViewById(R.id.progressBar2);
        loading = (TextView) findViewById(R.id.textView5);
        iv.setVisibility(iv.INVISIBLE);
        start.setEnabled(false);
        pd.setVisibility(pd.VISIBLE);
        if (isConnected()) {
            new Asyncstarttrivia(this).execute("http://dev.theappsdr.com/apis/trivia_json/index.php");
        }
        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

            }
        });

    }

    @Override
    public void dataintfmethod(final ArrayList<Questions> data) {
        pd.setVisibility(pd.INVISIBLE);
        loading.setVisibility(pd.INVISIBLE);
        iv.setVisibility(iv.VISIBLE);
        start.setEnabled(true);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ia = new Intent(MainActivity.this, Triviaactivity.class);
                ia.putExtra("key", data);
                startActivity(ia);
            }
        });
    }

    public boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo nf = cm.getActiveNetworkInfo();
        if (nf != null && nf.isConnected()) {
            return true;
        } else {
            return false;
        }
    }
}
