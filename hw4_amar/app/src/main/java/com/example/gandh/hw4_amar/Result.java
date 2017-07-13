package com.example.gandh.hw4_amar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by gandh on 2/10/2017.
 */

public class Result extends AppCompatActivity {

        ArrayList<Questions> data = new ArrayList<>();
    LinearLayout l2 ;
    SeekBar sb;
    TextView perc;
    Button finish;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.triviastats);
        setTitle("Trivia Stats");
        sb = (SeekBar) findViewById(R.id.seekBar);
        perc = (TextView) findViewById(R.id.textView10);
        data = (ArrayList<Questions>) getIntent().getExtras().getSerializable("key");
        l2 = (LinearLayout) findViewById(R.id.l2);
        int count[] = new int[data.size()];
         int i=0;
        double p =0;
        sb.setMax(data.size());

        for (Questions q :
                data) {

            TextView tv1 = new TextView(this);
            TextView tv2 = new TextView(this);
            TextView tv3 = new TextView(this);
            TextView tv4 = new TextView(this);
            tv1.setText(q.getText());
            tv2.setText(q.getChoices().getSanswer());
            tv3.setText(q.getChoices().getChoice()[q.getChoices().getAnswer()-1]);
            tv4.setText("\n");

            tv1.setPadding(10,0,20,0);
            tv2.setPadding(10,0,20,0);
            tv3.setPadding(10,0,20,0);

            tv2.setBackgroundColor(0xfff00000);
            if(tv2.getText()==tv3.getText())
            {
                sb.setProgress(++i);
                p++;
                double perc1= ((p/data.size())*100);
                perc.setText(perc1+"%");
            }
            else {
                l2.addView(tv1);
                l2.addView(tv2);
                l2.addView(tv3);
                l2.addView(tv4);
            }
        }

        findViewById(R.id.button5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                finish();

            }
        });
    }


}
