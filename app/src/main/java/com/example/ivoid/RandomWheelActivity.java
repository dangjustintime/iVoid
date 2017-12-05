package com.example.ivoid;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.bluehomestudio.luckywheel.LuckyWheel;
import com.bluehomestudio.luckywheel.OnLuckyWheelReachTheTarget;
import com.bluehomestudio.luckywheel.WheelItem;

import java.util.ArrayList;
import java.util.List;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Gage on 10/20/2017.
 */
//uses https://android-arsenal.com/details/1/5640 API
    //LuckyWheel!

public class RandomWheelActivity extends AppCompatActivity {
    private LuckyWheel mChampWheel;
    private Button mSpinWheel;
    private Spinner mClassSelector;
    private Button mGotoChampion;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.randomwheel);
        mChampWheel = (LuckyWheel) findViewById(R.id.champwheel);
        mSpinWheel = (Button) findViewById(R.id.spinButton);
        mClassSelector = (Spinner) findViewById(R.id.spinner_class);
        mGotoChampion = (Button) findViewById(R.id.button_to_champion);
        mGotoChampion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(RandomWheelActivity.this, "Spin the Wheel to Select a Champion!", Toast.LENGTH_SHORT).show();
            }
        });
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.classes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mClassSelector.setAdapter(adapter);
        setmChampWheel(R.array.characters);

        mClassSelector.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0)
                {
                    //Toast.makeText(RandomWheelActivity.this, "Top Lane Selected", Toast.LENGTH_SHORT).show();
                    setmChampWheel(R.array.Top);
                }
                else if(i == 1)
                {
                    //Toast.makeText(RandomWheelActivity.this, "Mid Lane Selected", Toast.LENGTH_SHORT).show();
                    setmChampWheel(R.array.Mid);
                }
                else if(i == 2) {
                    //Toast.makeText(RandomWheelActivity.this, "Jungle", Toast.LENGTH_SHORT).show();
                    setmChampWheel(R.array.Jungle);
                }
                else if(i == 3) {
                    //Toast.makeText(RandomWheelActivity.this, "ADC Selected", Toast.LENGTH_SHORT).show();
                    setmChampWheel(R.array.ADC);
                }
                else if(i == 4) {
                    //Toast.makeText(RandomWheelActivity.this, "Support Selected", Toast.LENGTH_SHORT).show();
                    setmChampWheel(R.array.Support);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }


    public Bitmap convertStringToBitmap(String text) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(80);
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.LEFT);
        float baseline = -paint.ascent();
        int width = (int) (paint.measureText(text) + 0.0f);
        int height = (int) (baseline + paint.descent() + 0.0f);
        Bitmap im = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas can = new Canvas(im);
        can.drawText(text, 0, baseline, paint);
        return im;
    }
    public void setmChampWheel(int id){
        List<WheelItem> wheelItems = new ArrayList<>();
        final String [] Top = getResources().getStringArray(id);
        int count = 0;
        for(int j = 0; j < Top.length; j++)
        {
            if(count % 2 == 0)
                wheelItems.add(new WheelItem(Color.GRAY, convertStringToBitmap(Integer.toString(count))));
            else
                wheelItems.add(new WheelItem(Color.DKGRAY, convertStringToBitmap(Integer.toString(count))));
            count++;
        }

        mChampWheel.addWheelItems(wheelItems);
        mSpinWheel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final int random = ThreadLocalRandom.current().nextInt(Top.length);
                mChampWheel.rotateWheelTo(random);
                mChampWheel.setLuckyWheelReachTheTarget(new OnLuckyWheelReachTheTarget() {
                    @Override
                    public void onReachTarget() {
                        Toast.makeText(RandomWheelActivity.this, "You should play as " + Top[random], Toast.LENGTH_SHORT).show();
                        mGotoChampion.setText("More info for " + Top[random]);
                        mGotoChampion.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                //pass id through intent to ChampionInfoActivity
                            }
                        });
                    }
                });
            }
        });
    }
}