package com.hammersmith.ku.coffeecorner;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.hammersmith.ku.coffeecorner.adapter.ViewPagerAdapter;
import com.hammersmith.ku.coffeecorner.model.Image;

import java.util.ArrayList;
import java.util.List;

public class  ImageActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private ViewPagerAdapter mCustomPagerAdapter;
    private List<Image> images = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        mViewPager = (ViewPager) findViewById(R.id.myviewpager);

        images.add(new Image(R.drawable.food));
        images.add(new Image(R.drawable.foodfour));
        images.add(new Image(R.drawable.food));
        images.add(new Image(R.drawable.foodfour));

        images.add(new Image(R.drawable.food));
        images.add(new Image(R.drawable.foodfour));

        images.add(new Image(R.drawable.food));
        images.add(new Image(R.drawable.foodfour));


        mCustomPagerAdapter = new ViewPagerAdapter(ImageActivity.this, images);
        mViewPager.setAdapter(mCustomPagerAdapter);
        mCustomPagerAdapter.notifyDataSetChanged();
    }
}
