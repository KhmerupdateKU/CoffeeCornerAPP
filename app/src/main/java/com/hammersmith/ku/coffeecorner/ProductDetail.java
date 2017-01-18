package com.hammersmith.ku.coffeecorner;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ProductDetail extends AppCompatActivity {
    int textValue = 1;
    String id,image_url,price,desc,title;
    private Button increase , decrease, order;
    private TextView amount, description, priceView ;

//    private List<>
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_product_detail);
        increase = (Button) findViewById(R.id.increase) ;
        decrease = (Button) findViewById(R.id.decrease);
        order = (Button) findViewById(R.id.btn_order);
        amount = (TextView) findViewById(R.id.orderAmount);
        description = (TextView) findViewById(R.id.descriptionDetail);
        imageView = (ImageView) findViewById(R.id.imageProductDetail);
        priceView = (TextView) findViewById(R.id.priceDetail);
        /// git

        id = getIntent().getStringExtra("id");
        image_url = getIntent().getStringExtra("image");
        price = getIntent().getStringExtra("price");
        desc = getIntent().getStringExtra("desc");
        title = getIntent().getStringExtra("name");

        priceView.setText(price);
        description.setText(desc);

        Uri uri = Uri.parse(image_url);

        Context context = imageView.getContext();
        Picasso.with(context).load(uri).into(imageView);

        getSupportActionBar().setTitle(title);
        getSupportActionBar().setHomeButtonEnabled(true);


        amount.setText(Integer.toString(textValue));

        increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textValue++;
                amount.setText(Integer.toString(textValue));
            }
        });

        decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (textValue>=1) {

                    amount.setText(Integer.toString(textValue));
                    textValue--;
                }

            }
        });


   ImageView imageView = (ImageView) findViewById(R.id.imageProductDetail);
imageView.setOnClickListener(new View.OnClickListener() {
@Override
public void onClick(View v) {
Intent intent = new Intent(ProductDetail.this, ImageActivity.class);
startActivity(intent);

   }
});

        order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductDetail.this, CartActivity.class);
                startActivity(intent);

            }
        });

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cart, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Toast.makeText(getApplicationContext(),"Back button clicked", Toast.LENGTH_SHORT).show();
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
