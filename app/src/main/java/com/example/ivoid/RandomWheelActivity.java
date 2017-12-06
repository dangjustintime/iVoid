package com.example.ivoid;

import android.content.Intent;
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

import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Gage on 10/20/2017.
 */
//uses https;//android-arsenal.com/details/1/5640 API
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
                                String champ = Top[random];
                                //champ = champ.replaceAll("[^a-zA-Z]", "");
                                String[] stringarray = getApplicationContext().getResources().getStringArray(R.array.characters);
                                int id = getChampId(champ);
                                Intent i =  new Intent(RandomWheelActivity.this, ChampionInfoActivity.class);
                                i.putExtra("championId", id);
                                startActivity(i);
                            
                            }
                        });
                    }
                });
            }
        });
    }
    public int getChampId(String champ){
        switch(champ){
             case "Aatrox":      return 266; 
             case "Thresh":      return 412; 
            case "Tryndamere":   return 23;  
            case "Gragas":       return 79;  
            case "Cassiopeia":   return 69;  
             case "Aurelion Sol":  return 136; 
            case "Ryze":         return 13;  
            case "Poppy":        return 78;  
            case "Sion":         return 14;  
            case "Annie":        return 1;   
             case "Jhin":        return 202; 
            case "Karma":        return 43;  
             case "Nautilus":    return 111; 
             case "Kled":        return 240; 
            case "Lux":          return 99;  
             case "Ahri":        return 103; 
            case "Olaf":         return 2;   
             case "Viktor":      return 112; 
            case "Anivia":       return 34;  
            case "Singed":       return 27;  
            case "Garen":        return 86;  
             case "Lissandra":   return 127; 
            case "Maokai":       return 57;  
            case "Morgana":      return 25;  
            case "Evelynn":      return 28;  
             case "Fizz":        return 105; 
            case "Heimerdinger": return 74;  
             case "Zed":         return 238; 
            case "Rumble":       return 68;  
            case "Mordekaiser":  return 82;  
            case "Sona":         return 37;  
            case "Kog'Maw":      return 96;  
            case "Katarina":     return 55;  
             case "Lulu":        return 117; 
            case "Ashe":         return 22;  
            case "Karthus":      return 30;  
            case "Alistar":      return 12;  
             case "Darius":      return 122; 
            case "Vayne":        return 67;  
             case "Varus":       return 110; 
            case "Udyr":         return 77;  
            case "Leona":        return 89;  
             case "Jayce":       return 126; 
             case "Syndra":      return 134; 
            case "Pantheon":     return 80;  
            case "Riven":        return 92;  
             case "Kha'Zix":     return 121; 
             case "Corki":       return 42;  
             case "Azir":        return 268; 
            case "Caitlyn":      return 51;  
            case "Nidalee":      return 76;  
            case "Kennen":       return 85;  
            case "Galio":        return 3;   
            case "Veigar":       return 45;  
             case "Bard":        return 432; 
             case "Gnar":        return 150; 
             case "Malzahar":    return 90;  
             case "Graves":      return 104; 
             case "Vi":          return 254; 
            case "Kayle":        return 10;  
            case "Irelia":       return 39;  
            case "Lee Sin":      return 64;  
             case "Illaoi":      return 420; 
            case "Elise":        return 60;  
             case "Volibear":    return 106; 
            case "Nunu":         return 20;  
            case "Twisted Fate": return 4;   
            case "Jax":          return 24;  
             case "Shyvana":     return 102; 
             case "Kalista":     return 429; 
            case "Dr. Mundo":    return 36;  
             case "Ivern":       return 427; 
             case "Diana":       return 131; 
             case "Tahm Kench":  return 223; 
            case "Brand":        return 63;  
             case "Sejuani":     return 113; 
            case "Vladimir":     return 8;   
             case "Zac":         return 154; 
             case "Rek'Sai":     return 421; 
             case "Quinn":       return 133; 
            case "Akali":        return 84;  
             case "Taliyah":     return 163; 
            case "Tristana":     return 18;  
             case "Hecarim":     return 120; 
            case "Sivir":        return 15;  
             case "Lucian":      return 236; 
             case "Rengar":      return 107; 
            case "Warwick":      return 19;  
            case "Skarner":      return 72;  
            case "Malphite":     return 54;  
             case "Yasuo":       return 157; 
             case "Xerath":      return 101; 
            case "Teemo":        return 17;  
            case "Nasus":        return 75;  
            case "Renekton":     return 58;  
             case "Draven":      return 119; 
            case "Shaco":        return 35;  
            case "Swain":        return 50;  
            case "Talon":        return 91;  
            case "Janna":        return 40;  
             case "Ziggs":       return 115; 
             case "Ekko":        return 245; 
            case "Orianna":      return 61;  
             case "Fiora":       return 114; 
            case "Fiddlesticks": return 9;   
            case "Cho'Gath":     return 31;  
            case "Rammus":       return 33;  
            case "LeBlanc":      return 7;   
            case "Soraka":       return 16;  
            case "Zilean":       return 26;  
            case "Nocturne":     return 56;  
             case "Jinx":        return 222; 
            case "Yorick":       return 83;  
            case "Urgot":        return 6;   
             case "Kindred":     return 203; 
            case "Miss Fortune": return 21;  
            case "Wukong":       return 62;  
            case "Blitzcrank":   return 53;  
            case "Shen":         return 98;  
             case "Braum":       return 201; 
            case "Xin Zhao":     return 5;   
            case "Twitch":       return 29;  
            case "Master Yi":    return 11;  
            case "Taric":        return 44;  
            case "Amumu":        return 32;  
            case "Gangplank":    return 41;  
            case "Trundle":      return 48;  
            case "Kassadin":     return 38;  
             case "Vel'Koz":     return 161; 
             case "Zyra":        return 143; 
             case "Nami":        return 267; 
            case "Jarvan IV":    return 59;  
            case "Ezreal":       return 81; 
            default:
                return 0; 
        }
    }
}