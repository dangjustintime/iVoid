package com.example.ivoid;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.bluehomestudio.luckywheel.LuckyWheel;
import com.bluehomestudio.luckywheel.WheelItem;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gage on 10/20/2017.
 */
//uses https://android-arsenal.com/details/1/5640 API
    //LuckyWheel!

public class RandomWheelActivity extends AppCompatActivity {
    private LuckyWheel mChampWheel;
    private Button mSpinWheel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.randomwheel);
        mChampWheel = (LuckyWheel) findViewById(R.id.champwheel);
        mSpinWheel = (Button) findViewById(R.id.spinButton);
        List<WheelItem> wheelItems = new ArrayList<>();
        wheelItems.add(new WheelItem(Color.RED, convertStringToBitmap("Hello World!")));
        wheelItems.add(new WheelItem(Color.GREEN, convertStringToBitmap("Goodbye World!")));
        wheelItems.add(new WheelItem(Color.BLUE, convertStringToBitmap("Goodbye World!")));
        wheelItems.add(new WheelItem(R.color.gold, convertStringToBitmap("LOL!")));
        wheelItems.add(new WheelItem(Color.RED, convertStringToBitmap("Goodbye World!")));
        wheelItems.add(new WheelItem(Color.GREEN, convertStringToBitmap("Goodbye World!")));
        wheelItems.add(new WheelItem(Color.BLUE, convertStringToBitmap("Goodbye World!")));
        mChampWheel.addWheelItems(wheelItems);

        mSpinWheel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mChampWheel.rotateWheelTo(0);
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
}