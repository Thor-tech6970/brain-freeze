package com.example.brainfreeze;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView addTextView, timerTextView, resultTextView,scoreTextView,finalTextView;

    int correctOption ,i;

    int correct = 0;

    int total=0;

    List options = new ArrayList();

    Button button0 , button1 , button2 , button3, playAgainButton;

    CountDownTimer countDownTimer;

    public void next(View view){

        options.clear();

        if(view.getTag().toString().equals(Integer.toString(correctOption))){

            correct++;

            total++;

            scoreTextView.setText(+correct + "/" +total);

            resultTextView.setText("Correct !");

        }

        else{

            total++;

            scoreTextView.setText(+correct + "/" +total);

            resultTextView.setText("Incorrect !");
        }

        newQuestion(findViewById(R.id.timerTextView));



    }



    public void newQuestion(View view){


        Random rand = new Random();

        int a = rand.nextInt(21);

        int b = rand.nextInt(21);

        addTextView.setText(a + "+" + b );

        correctOption = rand.nextInt(4);

        for ( i=0;i<=3;i++){

            if(i==correctOption){

                options.add(a+b);

            }

            else {
                int opt = rand.nextInt(41);
                while (options.contains(opt)){
                    opt = rand.nextInt(41);
                }
                options.add(opt);


            }


        }





        button0.setText(options.get(0).toString());
        button1.setText(options.get(1).toString());
        button2.setText(options.get(2).toString());
        button3.setText(options.get(3).toString());



    }

    public void playAgain(View view){

        timerTextView.setText("30s");

        scoreTextView.setText("0/0");

        playAgainButton.setVisibility(View.GONE);

        resultTextView.setText("");

        finalTextView.setText("");

        button0.setEnabled(true);

        button1.setEnabled(true);

        button2.setEnabled(true);

        button3.setEnabled(true);

        startAgain();

    }

    public void startAgain(){

        correct=0;

        total=0;

        countDownTimer = new CountDownTimer(30000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText((int)millisUntilFinished/1000 + "s");
            }

            @Override
            public void onFinish() {

                resultTextView.setText("Time over!");

                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);

                finalTextView.setText("Score:" + correct + "/" + total);

                playAgainButton.setVisibility(View.VISIBLE);



            }
        }.start();

        newQuestion(findViewById(R.id.timerTextView));

    }






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addTextView = (TextView) findViewById(R.id.addTextView);

        button0 = (Button) findViewById(R.id.button0);

        button1 = (Button) findViewById(R.id.button1);

        button2 = (Button) findViewById(R.id.button2);

        button3 = (Button) findViewById(R.id.button3);

        playAgainButton = (Button) findViewById(R.id.playAgainButton);

        timerTextView = (TextView) findViewById(R.id.timerTextView);

        resultTextView = (TextView) findViewById(R.id.resultTextView);

        scoreTextView = (TextView) findViewById(R.id.scoreTextView);

        finalTextView = (TextView) findViewById(R.id.finalTextView);

        playAgainButton.setVisibility(View.GONE);

        startAgain();




    }
}
