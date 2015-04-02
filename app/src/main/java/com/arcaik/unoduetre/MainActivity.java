package com.arcaik.unoduetre;

import android.os.CountDownTimer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;


public class MainActivity extends ActionBarActivity {

    int[] array=new int[3];
    int[] arrayNumeriRandom={-1,-2,-3,1,2,3};
    int sommaNumeri;
    TextView textView;
    TextView textViewPunteggio;
    TextProgressBar buttonUno;
    TextProgressBar buttonDue;
    TextProgressBar buttonTre;
    Random random=new Random();
    String string0,string1,string2;
    boolean risultatoValido;
    int punteggio=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonUno=(TextProgressBar)findViewById(R.id.buttonUno);
        buttonDue=(TextProgressBar)findViewById(R.id.buttonDue);
        buttonTre=(TextProgressBar)findViewById(R.id.buttonTre);
        buttonUno.setText("1");
        buttonUno.setTextSize(50);
        buttonDue.setText("2");
        buttonDue.setTextSize(50);
        buttonTre.setText("3");
        buttonTre.setTextSize(50);
        buttonUno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int valore=1;
                if(valore==sommaNumeri){
                    punteggio++;
                    textViewPunteggio.setText(Integer.toString(punteggio));
                    gioca();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Risposta Sbagliata", Toast.LENGTH_SHORT).show();
                    disableButton();
                }
            }
        });
        buttonDue=(TextProgressBar)findViewById(R.id.buttonDue);
        buttonDue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              int  valore=2;
                if(valore==sommaNumeri) {
                    punteggio++;
                    textViewPunteggio.setText(Integer.toString(punteggio));
                    gioca();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Risposta Sbagliata", Toast.LENGTH_SHORT).show();
                    disableButton();
                }
            }
        });
        buttonTre=(TextProgressBar)findViewById(R.id.buttonTre);
        buttonTre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               int valore=3;
                if(valore==sommaNumeri){
                    punteggio++;
                    textViewPunteggio.setText(Integer.toString(punteggio));
                    gioca();
                }
                else {
                    Toast.makeText(getApplicationContext(), "Risposta Sbagliata", Toast.LENGTH_SHORT).show();
                    disableButton();
                }
            }
        });
        textView=(TextView)findViewById(R.id.textViewEquazione);
        textViewPunteggio=(TextView)findViewById(R.id.textViewPunteggio);
    }
    public void onStart(){
        super.onStart();
        gioca();
    }

    public void numeriRandom(){
       risultatoValido=false;
        do {
            sommaNumeri=0;
            for(int i=0;i<3;i++) {
                    array[i] = arrayNumeriRandom[random.nextInt(6)];
                    sommaNumeri += array[i];
                }
            if(sommaNumeri>=1 && sommaNumeri<=3)
                risultatoValido=true;

        }while (risultatoValido==false);

    }

    public void gioca() {
        if(punteggio>0)
            countDownTimer.start();
        numeriRandom();
        string0=Integer.toString(array[0]);
        if (array[1] >= 0) {
            string1 = "+" + Integer.toString(array[1]);
        } else
            string1 = Integer.toString(array[1]);
        if (array[2] >= 0) {
            string2 = "+" + Integer.toString(array[2]);
        } else
            string2 = Integer.toString(array[2]);
        textView.setText(string0 + string1 + string2 + "=");
    }

        CountDownTimer countDownTimer = new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                buttonUno.setProgress((int) millisUntilFinished/1000);
                buttonDue.setProgress((int) millisUntilFinished/1000);
                buttonTre.setProgress((int) millisUntilFinished/1000);
            }

            public void onFinish() {
                buttonUno.setProgress(0);
                buttonDue.setProgress(0);
                buttonTre.setProgress(0);
                disableButton();
            }
        };

    public void disableButton(){
        countDownTimer.cancel();
        buttonUno.setEnabled(false);
        buttonDue.setEnabled(false);
        buttonTre.setEnabled(false);
        buttonUno.setProgress(0);
        buttonDue.setProgress(0);
        buttonTre.setProgress(0);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
