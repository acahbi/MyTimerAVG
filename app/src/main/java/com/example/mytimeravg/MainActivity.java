package com.example.mytimeravg;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;



public class MainActivity extends AppCompatActivity {

    Long    StartDate = null,
            OutTime   = null;
    String  MyFormatTime = "00:00:00.000";
    Integer CntPop       = 0,
            TotalTime    = 0;

    TextView tvCurrentTime,
             tvPop,
             tvAVGTime;
    Button   btStartStop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvCurrentTime = findViewById(R.id.tvCurrent);
        tvPop         = findViewById(R.id.tvCount);
        tvAVGTime     = findViewById(R.id.tvAVG);
        btStartStop   = findViewById(R.id.btStart);
    }

    public void onClickStartStop(View view){
        if (StartDate == null){
            StartDate = System.currentTimeMillis();//Calendar.getInstance().getTime();
            tvCurrentTime.setText(MyFormatTime);
            btStartStop.setText(R.string.strStop);
        }
        else {
            OutTime = System.currentTimeMillis() - StartDate;
            StartDate = null;
            Integer fulltime = Integer.valueOf(String.valueOf(OutTime));
            //  Для текущего времени
            String milisec, secs, mins, hours;
            milisec  = String.valueOf(fulltime % 1000);
            secs     = String.valueOf(fulltime / 1000 % 60);
            mins     = String.valueOf(fulltime / 1000 / 60 % 60);
            hours    = String.valueOf(fulltime / 1000 / 3600);

            //  Для среднего времени
            TotalTime += fulltime;
            CntPop += 1;
            String milisec_avg, secs_avg, mins_avg, hours_avg;
            milisec_avg  = String.valueOf(TotalTime / CntPop % 1000);
            secs_avg     = String.valueOf(TotalTime / CntPop / 1000 % 60);
            mins_avg     = String.valueOf(TotalTime / CntPop / 1000 / 60 % 60);
            hours_avg    = String.valueOf(TotalTime / CntPop / 1000 / 3600);

            tvAVGTime.setText(String.format("%2s", hours_avg).replace(" ", "0") + ":" +
                              String.format("%2s", mins_avg).replace(" ", "0")  + ":" +
                              String.format("%2s", secs_avg).replace(" ", "0")  + "." +
                              String.format("%3s", milisec_avg).replace(" ", "0"));
            tvPop.setText(String.valueOf(CntPop));
            tvCurrentTime.setText(String.format("%2s", hours).replace(" ", "0") + ":" +
                                  String.format("%2s", mins).replace(" ", "0") + ":" +
                                  String.format("%2s", secs).replace(" ", "0") + "." +
                                  String.format("%2s", milisec).replace(" ", "0"));
            btStartStop.setText(R.string.strStart);
        }
    }

    public void onClickbtClear(View view){
        tvCurrentTime.setText(MyFormatTime);
        tvAVGTime.setText(MyFormatTime);
        btStartStop.setText(R.string.strStart);
        CntPop = 0;
        tvPop.setText("0");
        StartDate = null;
        TotalTime = 0;
    }

    public void onClickInstruction(View view){
        Intent intent = new Intent(this, History.class);
        startActivity(intent);
    }
}
