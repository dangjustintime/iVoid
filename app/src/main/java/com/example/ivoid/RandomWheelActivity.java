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
                                String url1 = getUrl1(champ);
                                String url2 = getUrl2(champ);
                                i.putExtra("championId", id);
                                i.putExtra("splashUrl1", url1);
                                i.putExtra("splashUrl2", url2);
                                startActivity(i);
                            
                            }
                        });
                    }
                });
            }
        });
    }
    //The Following three functions only exist due to the lack of API calls we have when using the Riot API.
    //If it werent for these limitations, we would just use the API here to get the champion ids, and use that to get the splash art.
    public String getUrl2(String champ){
        switch(champ){
            case "Aatrox":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Aatrox_1.jpg";
            case "Thresh":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Thresh_1.jpg";
            case "Tryndamere":   return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Tryndamere_1.jpg";
            case "Gragas":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Gragas_1.jpg";
            case "Cassiopeia":   return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Cassiopeia_1.jpg";
            case "Aurelion Sol":  return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/AurelionSol_1.jpg";
            case "Ryze":         return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Ryze_1.jpg";
            case "Poppy":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Poppy_1.jpg";
            case "Sion":         return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Sion_1.jpg";
            case "Annie":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Annie_1.jpg";
            case "Jhin":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Jhin_1.jpg";
            case "Karma":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Karma_1.jpg";
            case "Nautilus":    return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Nautilus_1.jpg";
            case "Kled":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Kled_1.jpg";
            case "Lux":          return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Lux_1.jpg";
            case "Ahri":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Ahri_1.jpg";
            case "Olaf":         return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Olaf_1.jpg";
            case "Viktor":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Viktor_1.jpg";
            case "Anivia":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Anivia_1.jpg";
            case "Singed":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Singed_1.jpg";
            case "Garen":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Garen_1.jpg";
            case "Lissandra":   return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Lissandra_1.jpg";
            case "Maokai":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Maokai_1.jpg";
            case "Morgana":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Morgana_1.jpg";
            case "Evelynn":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Evelynn_1.jpg";
            case "Fizz":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Fizz_1.jpg";
            case "Heimerdinger": return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Heimerdinger_1.jpg";
            case "Zed":         return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Zed_1.jpg";
            case "Rumble":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Rumble_1.jpg";
            case "Mordekaiser":  return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Mordekaiser_1.jpg";
            case "Sona":         return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Sona_1.jpg";
            case "Kog'Maw":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/KogMaw_1.jpg";
            case "Katarina":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Katarina_1.jpg";
            case "Lulu":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Lulu_1.jpg";
            case "Ashe":         return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Ashe_1.jpg";
            case "Karthus":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Karthus_1.jpg";
            case "Alistar":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Alistar_1.jpg";
            case "Darius":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Darius_1.jpg";
            case "Vayne":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Vayne_1.jpg";
            case "Varus":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Varus_1.jpg";
            case "Udyr":         return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Udyr_1.jpg";
            case "Leona":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Leona_1.jpg";
            case "Jayce":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Jayce_1.jpg";
            case "Syndra":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Syndra_1.jpg";
            case "Pantheon":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Pantheon_1.jpg";
            case "Riven":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Riven_1.jpg";
            case "Kha'Zix":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Khazix_1.jpg";
            case "Corki":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Corki_1.jpg";
            case "Azir":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Azir_1.jpg";
            case "Caitlyn":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Caitlyn_1.jpg";
            case "Nidalee":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Nidalee_1.jpg";
            case "Kennen":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Kennen_1.jpg";
            case "Galio":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Galio_1.jpg";
            case "Veigar":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Veigar_1.jpg";
            case "Bard":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Bard_1.jpg";
            case "Gnar":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Gnar_1.jpg";
            case "Malzahar":    return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Malzahar_1.jpg";
            case "Graves":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Graves_1.jpg";
            case "Vi":          return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Vi_1.jpg";
            case "Kayle":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Kayle_1.jpg";
            case "Irelia":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Irelia_1.jpg";
            case "Lee Sin":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/LeeSin_1.jpg";
            case "Illaoi":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Illaoi_1.jpg";
            case "Elise":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Elise_1.jpg";
            case "Volibear":    return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Volibear_1.jpg";
            case "Nunu":         return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Nunu_1.jpg";
            case "Twisted Fate": return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/TwistedFate_1.jpg";
            case "Jax":          return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Jax_1.jpg";
            case "Shyvana":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Shyvana_1.jpg";
            case "Kalista":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Kalista_1.jpg";
            case "Dr. Mundo":    return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/DrMundo_1.jpg";
            case "Ivern":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Ivern_1.jpg";
            case "Diana":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Diana_1.jpg";
            case "Tahm Kench":  return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/TahmKench_1.jpg";
            case "Brand":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Bard_1.jpg";
            case "Sejuani":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Sejuani_1.jpg";
            case "Vladimir":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Vladimir_1.jpg";
            case "Zac":         return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Zac_1.jpg";
            case "Rek'Sai":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/RekSai_1.jpg";
            case "Quinn":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Quinn_1.jpg";
            case "Akali":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Akali_1.jpg";
            case "Taliyah":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Taliyah_1.jpg";
            case "Tristana":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Tristana_1.jpg";
            case "Hecarim":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Hecarim_1.jpg";
            case "Sivir":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Sivir_1.jpg";
            case "Lucian":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Lucian_1.jpg";
            case "Rengar":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Rengar_1.jpg";
            case "Warwick":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Warwick_1.jpg";
            case "Skarner":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Skarner_1.jpg";
            case "Malphite":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Malphite_1.jpg";
            case "Yasuo":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yasuo_1.jpg";
            case "Xerath":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Xerath_1.jpg";
            case "Teemo":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Teemo_1.jpg";
            case "Nasus":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Nasus_1.jpg";
            case "Renekton":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Renekton_1.jpg";
            case "Draven":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Draven_1.jpg";
            case "Shaco":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Shaco_1.jpg";
            case "Swain":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Swain_1.jpg";
            case "Talon":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Talon_1.jpg";
            case "Janna":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Janna_1.jpg";
            case "Ziggs":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Ziggs_1.jpg";
            case "Ekko":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Ekko_1.jpg";
            case "Orianna":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Orianna_1.jpg";
            case "Fiora":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Fiora_1.jpg";
            case "Fiddlesticks": return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Fiddlesticks_1.jpg";
            case "Cho'Gath":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Chogath_1.jpg";
            case "Rammus":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Rammus_1.jpg";
            case "LeBlanc":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Leblanc_1.jpg";
            case "Soraka":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Soraka_1.jpg";
            case "Zilean":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Zilean_1.jpg";
            case "Nocturne":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Nocturne_1.jpg";
            case "Jinx":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Jinx_1.jpg";
            case "Yorick":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yorick_1.jpg";
            case "Urgot":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Urgot_1.jpg";
            case "Kindred":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Kindred_1.jpg";
            case "Miss Fortune": return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/MissFortune_1.jpg";
            case "Wukong":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/MonkeyKing_1.jpg";
            case "Blitzcrank":   return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Blitzcrank_1.jpg";
            case "Shen":         return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Shen_1.jpg";
            case "Braum":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Braum_1.jpg";
            case "Xin Zhao":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/XinZhao_1.jpg";
            case "Twitch":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Twitch_1.jpg";
            case "Master Yi":    return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/MasterYi_1.jpg";
            case "Taric":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Taric_1.jpg";
            case "Amumu":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Amumu_1.jpg";
            case "Gangplank":    return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Gangplank_1.jpg";
            case "Trundle":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Trundle_1.jpg";
            case "Kassadin":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Kassadin_1.jpg";
            case "Vel'Koz":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Velkoz_1.jpg";
            case "Zyra":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Zyra_1.jpg";
            case "Nami":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Nami_1.jpg";
            case "Jarvan IV":    return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/JarvanIV_1.jpg";
            case "Ezreal":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Ezreal_1.jpg";
            case "Kayn":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Kayn_1.jpg";
            case "Ornn":         return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Ornn_1.jpg";
            case "Zoe":          return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Zoe_1.jpg";
            default:
                return "";
        }
    }
    public String getUrl1(String champ){
        switch(champ){
            case "Aatrox":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Aatrox_0.jpg";
            case "Thresh":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Thresh_0.jpg";
            case "Tryndamere":   return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Tryndamere_0.jpg";
            case "Gragas":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Gragas_0.jpg";
            case "Cassiopeia":   return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Cassiopeia_0.jpg";
            case "Aurelion Sol":  return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/AurelionSol_0.jpg";
            case "Ryze":         return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Ryze_0.jpg";
            case "Poppy":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Poppy_0.jpg";
            case "Sion":         return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Sion_0.jpg";
            case "Annie":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Annie_0.jpg";
            case "Jhin":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Jhin_0.jpg";
            case "Karma":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Karma_0.jpg";
            case "Nautilus":    return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Nautilus_0.jpg";
            case "Kled":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Kled_0.jpg";
            case "Lux":          return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Lux_0.jpg";
            case "Ahri":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Ahri_0.jpg";
            case "Olaf":         return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Olaf_0.jpg";
            case "Viktor":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Viktor_0.jpg";
            case "Anivia":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Anivia_0.jpg";
            case "Singed":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Singed_0.jpg";
            case "Garen":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Garen_0.jpg";
            case "Lissandra":   return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Lissandra_0.jpg";
            case "Maokai":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Maokai_0.jpg";
            case "Morgana":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Morgana_0.jpg";
            case "Evelynn":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Evelynn_0.jpg";
            case "Fizz":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Fizz_0.jpg";
            case "Heimerdinger": return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Heimerdinger_0.jpg";
            case "Zed":         return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Zed_0.jpg";
            case "Rumble":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Rumble_0.jpg";
            case "Mordekaiser":  return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Mordekaiser_0.jpg";
            case "Sona":         return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Sona_0.jpg";
            case "Kog'Maw":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/KogMaw_0.jpg";
            case "Katarina":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Katarina_0.jpg";
            case "Lulu":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Lulu_0.jpg";
            case "Ashe":         return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Ashe_0.jpg";
            case "Karthus":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Karthus_0.jpg";
            case "Alistar":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Alistar_0.jpg";
            case "Darius":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Darius_0.jpg";
            case "Vayne":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Vayne_0.jpg";
            case "Varus":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Varus_0.jpg";
            case "Udyr":         return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Udyr_0.jpg";
            case "Leona":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Leona_0.jpg";
            case "Jayce":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Jayce_0.jpg";
            case "Syndra":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Syndra_0.jpg";
            case "Pantheon":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Pantheon_0.jpg";
            case "Riven":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Riven_0.jpg";
            case "Kha'Zix":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Khazix_0.jpg";
            case "Corki":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Corki_0.jpg";
            case "Azir":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Azir_0.jpg";
            case "Caitlyn":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Caitlyn_0.jpg";
            case "Nidalee":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Nidalee_0.jpg";
            case "Kennen":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Kennen_0.jpg";
            case "Galio":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Galio_0.jpg";
            case "Veigar":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Veigar_0.jpg";
            case "Bard":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Bard_0.jpg";
            case "Gnar":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Gnar_0.jpg";
            case "Malzahar":    return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Malzahar_0.jpg";
            case "Graves":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Graves_0.jpg";
            case "Vi":          return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Vi_0.jpg";
            case "Kayle":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Kayle_0.jpg";
            case "Irelia":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Irelia_0.jpg";
            case "Lee Sin":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/LeeSin_0.jpg";
            case "Illaoi":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Illaoi_0.jpg";
            case "Elise":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Elise_0.jpg";
            case "Volibear":    return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Volibear_0.jpg";
            case "Nunu":         return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Nunu_0.jpg";
            case "Twisted Fate": return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/TwistedFate_0.jpg";
            case "Jax":          return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Jax_0.jpg";
            case "Shyvana":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Shyvana_0.jpg";
            case "Kalista":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Kalista_0.jpg";
            case "Dr. Mundo":    return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/DrMundo_0.jpg";
            case "Ivern":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Ivern_0.jpg";
            case "Diana":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Diana_0.jpg";
            case "Tahm Kench":  return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/TahmKench_0.jpg";
            case "Brand":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Bard_0.jpg";
            case "Sejuani":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Sejuani_0.jpg";
            case "Vladimir":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Vladimir_0.jpg";
            case "Zac":         return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Zac_0.jpg";
            case "Rek'Sai":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/RekSai_0.jpg";
            case "Quinn":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Quinn_0.jpg";
            case "Akali":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Akali_0.jpg";
            case "Taliyah":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Taliyah_0.jpg";
            case "Tristana":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Tristana_0.jpg";
            case "Hecarim":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Hecarim_0.jpg";
            case "Sivir":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Sivir_0.jpg";
            case "Lucian":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Lucian_0.jpg";
            case "Rengar":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Rengar_0.jpg";
            case "Warwick":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Warwick_0.jpg";
            case "Skarner":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Skarner_0.jpg";
            case "Malphite":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Malphite_0.jpg";
            case "Yasuo":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yasuo_0.jpg";
            case "Xerath":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Xerath_0.jpg";
            case "Teemo":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Teemo_0.jpg";
            case "Nasus":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Nasus_0.jpg";
            case "Renekton":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Renekton_0.jpg";
            case "Draven":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Draven_0.jpg";
            case "Shaco":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Shaco_0.jpg";
            case "Swain":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Swain_0.jpg";
            case "Talon":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Talon_0.jpg";
            case "Janna":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Janna_0.jpg";
            case "Ziggs":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Ziggs_0.jpg";
            case "Ekko":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Ekko_0.jpg";
            case "Orianna":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Orianna_0.jpg";
            case "Fiora":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Fiora_0.jpg";
            case "Fiddlesticks": return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Fiddlesticks_0.jpg";
            case "Cho'Gath":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Chogath_0.jpg";
            case "Rammus":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Rammus_0.jpg";
            case "LeBlanc":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Leblanc_0.jpg";
            case "Soraka":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Soraka_0.jpg";
            case "Zilean":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Zilean_0.jpg";
            case "Nocturne":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Nocturne_0.jpg";
            case "Jinx":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Jinx_0.jpg";
            case "Yorick":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Yorick_0.jpg";
            case "Urgot":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Urgot_0.jpg";
            case "Kindred":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Kindred_0.jpg";
            case "Miss Fortune": return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/MissFortune_0.jpg";
            case "Wukong":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/MonkeyKing_0.jpg";
            case "Blitzcrank":   return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Blitzcrank_0.jpg";
            case "Shen":         return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Shen_0.jpg";
            case "Braum":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Braum_0.jpg";
            case "Xin Zhao":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/XinZhao_0.jpg";
            case "Twitch":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Twitch_0.jpg";
            case "Master Yi":    return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/MasterYi_0.jpg";
            case "Taric":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Taric_0.jpg";
            case "Amumu":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Amumu_0.jpg";
            case "Gangplank":    return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Gangplank_0.jpg";
            case "Trundle":      return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Trundle_0.jpg";
            case "Kassadin":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Kassadin_0.jpg";
            case "Vel'Koz":     return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Velkoz_0.jpg";
            case "Zyra":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Zyra_0.jpg";
            case "Nami":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Nami_0.jpg";
            case "Jarvan IV":    return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/JarvanIV_0.jpg";
            case "Ezreal":       return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Ezreal_0.jpg";
            case "Kayn":        return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Kayn_0.jpg";
            case "Ornn":         return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Ornn_0.jpg";
            case "Zoe":          return "https://ddragon.leagueoflegends.com/cdn/img/champion/splash/Zoe_0.jpg";
            default:
                return "";
        }
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
            case "Kayn":        return 141;
            case "Ornn":         return 516;
            case "Zoe":          return 142;
            default:
                return 0;
        }
    }
}