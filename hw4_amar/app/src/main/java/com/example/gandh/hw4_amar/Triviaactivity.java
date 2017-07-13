package com.example.gandh.hw4_amar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by gandh on 2/10/2017.
 */

public class Triviaactivity extends AppCompatActivity implements ImageAsyncTask.setimage{
    TextView qno, question, load,timer;
    int posi;
    ImageAsyncTask mTask;
    ProgressBar pb2;
    ImageView iv1;
    LinearLayout l1;
    Button next,prev;
    ArrayList<Questions> data;
    RadioGroup RG;
   // int requestcode=100;

    RadioButton RB;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trivia);
        data = (ArrayList<Questions>) getIntent().getExtras().getSerializable("key");
        setTitle("Trivia");
        qno = (TextView) findViewById(R.id.textView2);
        iv1 = (ImageView) findViewById(R.id.imageView2);
        timer = (TextView) findViewById(R.id.textView3);
        pb2 = (ProgressBar) findViewById(R.id.progressBar2);
        question = (TextView) findViewById(R.id.question);
        load = (TextView) findViewById(R.id.textView6) ;
        l1 = (LinearLayout) findViewById(R.id.ll1);
        prev = (Button) findViewById(R.id.button3);
        next = (Button) findViewById(R.id.button4);
        mTask = new ImageAsyncTask(this);
        posi =0;
       CountDownTimer ct = new CountDownTimer(120000,1000 ) {
            @Override
            public void onTick(long millisUntilFinished) {
            timer.setText(("Time Left:"+ millisUntilFinished/1000+ "seconds"));
                timer.setPadding(5,3,5,3);
            }

            @Override
            public void onFinish() {
                Intent ib = new Intent(Triviaactivity.this,Result.class);
                ib.putExtra("key",data);
               // startActivity(ib);
                startActivityForResult(ib,100);
            }
        }.start();
        next.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
                if (posi == data.size()-1)
                {

                    Intent ib = new Intent(Triviaactivity.this,Result.class);
                            ib.putExtra("key",data);
                            //startActivity(ib);
                    startActivityForResult(ib,100);

                }
                else {
                    prev.setEnabled(true);
                    if (posi < data.size() - 1)
                        posi++;
                    datasetter(posi, data);
                   if(posi==data.size()-1)
                    next.setText("Finish");

                }
            }
        });

        prev.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
            @Override
            public void onClick(View v) {
               next.setText("Next");
                posi--;
                datasetter(posi,data);
            }
        });
        datasetter(0,data);


    }

    private void startActivityForResult(int requestcode, int resultcode, Intent data) {

         if(data.getData()!=null) {
           if(resultcode==RESULT_OK)
                finish();
            }


    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public void datasetter(final int posi, final ArrayList<Questions> datataken)
    {
        if(posi==0)
        {
            prev.setEnabled(false);
        }
        l1.removeAllViews();
        qno.setText("");
        question.setText("");
        qno.setText("Q"+(datataken.get(posi).getId()+1));
        qno.setPadding(5,3,5,3);
        question.setText(datataken.get(posi).getText());
         RG = new RadioGroup(this);

        if(datataken.get(posi).getImage().equals("NO"))
        {

                mTask.cancel(true);


            iv1.setVisibility(iv1.INVISIBLE);
            load.setVisibility(load.INVISIBLE);
            pb2.setVisibility(pb2.INVISIBLE);
        }
        else {
            mTask.cancel(true);
            iv1.setVisibility(iv1.INVISIBLE);
            pb2.setVisibility(pb2.VISIBLE);
            load.setVisibility(load.VISIBLE);
          mTask= (ImageAsyncTask) new ImageAsyncTask(this).execute(datataken.get(posi).getImage());
        }
        int radio =-1;
        for (int i=0; i<datataken.get(posi).getChoices().getChoice().length;i++)
        {
            RB = new RadioButton(this) ;
            RB.setText(datataken.get(posi).getChoices().getChoice()[i]);
            RB.setId(View.generateViewId());

              if (RB.getText().equals(datataken.get(posi).getChoices().getSanswer())) {
                  radio= RB.getId();
                    Log.d("demo1",RB.getId()+"");
                }
            RG.setPadding(10,0,0,0);
            RG.addView(RB);
        }
        l1.addView(RG);

       if(radio!=-1)
       {
           RG.check(radio);
       }
        RG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                String radiovalue = ((RadioButton)findViewById(RG.getCheckedRadioButtonId())).getText().toString();
                datataken.get(posi).getChoices().setSanswer(radiovalue);

                Log.d("demo",checkedId+"");
            }
        });
    }

    @Override
    public void settingimage(Bitmap bit) {
        iv1.setVisibility(iv1.VISIBLE);
        load.setVisibility(load.INVISIBLE);
        pb2.setVisibility(pb2.INVISIBLE);
        iv1.setImageBitmap(bit);
    }
}
