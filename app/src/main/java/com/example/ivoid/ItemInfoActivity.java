package com.example.ivoid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ivoid.Model.Champion;
import com.example.ivoid.Model.Item;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ItemInfoActivity extends AppCompatActivity {
    private Retrofit retrofit = null;
    private int itemId;
    //view
    TextView itemNameTextView;
    ImageView itemIconImageView;
    TextView itemPriceBuyTextView;
    TextView itemPlainTextTextView;
    TextView itemDescriptionTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_info);
        itemId = getIntent().getIntExtra("itemId",0);
        //bind views
        itemNameTextView = (TextView) findViewById(R.id.item_name_text_view);
        itemIconImageView = (ImageView) findViewById(R.id.item_icon_image_view);
        itemPriceBuyTextView = (TextView) findViewById(R.id.item_price_buy_text_view);
        itemPlainTextTextView = (TextView) findViewById(R.id.item_plaintext_text_view);
        itemDescriptionTextView = (TextView) findViewById(R.id.item_description_text_view);

        //api call
        getAPIData();
    }
    //API call
    public void getAPIData() {
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://na1.api.riotgames.com")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        ApiClient client = retrofit.create(ApiClient.class);
        //call for championMap
        Call<Item> callItem = client.reposForItem(String.valueOf(itemId));
        callItem.enqueue(new Callback<Item>() {
            @Override
            public void onResponse(Call<Item> call, Response<Item> response) {
                Item responseItem = response.body();
                itemNameTextView.setText(responseItem.getName());
                itemPriceBuyTextView.setText(String.valueOf(responseItem.getPrice().getTotal()));
                itemPlainTextTextView.setText(responseItem.getPlainText());
                itemDescriptionTextView.setText(responseItem.getDescription());
                Toast.makeText(ItemInfoActivity.this, "Item Response Success", Toast.LENGTH_LONG).show();
            }
            @Override
            public void onFailure(Call<Item> call, Throwable t) {
                Toast.makeText(ItemInfoActivity.this, "Item Response Failed", Toast.LENGTH_LONG).show();
            }
        });
    }
}
