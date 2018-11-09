package com.example.betul.betulerdoganquiz;

import android.annotation.SuppressLint;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{

    Button stopActivity;
    Button pauseActivity;
    Button playActivity;
    Spinner spinnerActivity;
    MediaPlayer mp;
    TextView songText;
    TextView timeText;
    //String[] songNames={"Another Brick In The Wall","Money","Wish You Were Here"};
    int length=0;
    String[] zaman={"3.49","6.32","4.53"};
    ImageView imageSrc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stopActivity=findViewById(R.id.stopButton);
        pauseActivity=findViewById(R.id.pauseButton);
        playActivity=findViewById(R.id.playButton);
        spinnerActivity=findViewById(R.id.spinner);
        songText=findViewById(R.id.sarkiAd);
        timeText=findViewById(R.id.sureGoster);
        final String[] songNames=getResources().getStringArray(R.array.songNames);
        final String[] times=getResources().getStringArray(R.array.times);
        imageSrc=findViewById(R.id.imageView);

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,songNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerActivity.setAdapter(adapter);
        spinnerActivity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                length=0;
                stopPlaying();
                String item=parent.getItemAtPosition(position).toString();
                songText.setText(item);
                if(item.equals("Another Brick In The Wall")){
                    timeText.setText(zaman[0]);
                    imageSrc.setImageResource(R.drawable.floyd);
                }
                else if(item.equals("Money")){
                    timeText.setText(zaman[1]);
                    imageSrc.setImageResource(R.drawable.pink);
                }
                else if(item.equals("Wish You Were Here")){
                    timeText.setText(zaman[2]);
                    imageSrc.setImageResource(R.drawable.pink2);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        playActivity.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v) {

                if (mp != null && mp.isPlaying()) {
                    mp.stop();
                }

                else if (spinnerActivity.getSelectedItem().toString().equals("Another Brick In The Wall")){
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.another);
                    if(mp.isPlaying()){
                        mp.start();}
                    else {
                        mp.seekTo(length);
                        mp.start();
                    }

                }
                else if (spinnerActivity.getSelectedItem().toString().equals("Money")){
                    mp = MediaPlayer.create(getApplicationContext(), R.raw.money);
                    if(mp.isPlaying()){
                        mp.start();}
                    else{
                        mp.seekTo(length);
                        mp.start();
                    }
                }
                else if (spinnerActivity.getSelectedItem().toString().equals("Wish You Were Here")){

                    mp = MediaPlayer.create(getApplicationContext(), R.raw.wishyou);
                    if(mp.isPlaying()){
                        mp.start();}
                    else{
                        mp.seekTo(length);
                        mp.start();
                    }
                }



            }
        });
        stopActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopPlaying();
                length=0;
            }
        });
        pauseActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mp==null){

                }
                else {
                    mp.pause();
                    length = mp.getCurrentPosition();
                }

            }
        });


    }
    private void stopPlaying() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }

}
