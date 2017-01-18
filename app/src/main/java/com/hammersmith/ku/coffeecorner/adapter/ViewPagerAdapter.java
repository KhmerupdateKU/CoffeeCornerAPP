package com.hammersmith.ku.coffeecorner.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hammersmith.ku.coffeecorner.model.Image;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends PagerAdapter {
    Context ssContext;
    private List<Image> images = new ArrayList<>();
    int position;

    public ViewPagerAdapter(Context ssContext, List<Image> images) {
        this.ssContext = ssContext;
        this.images = images;
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View ssView, Object ssObject) {
        return ssView == ((ImageView) ssObject);
    }

    @Override
    public Object instantiateItem(ViewGroup ssContainer, int ssPosition) {
        ImageView ssImageView = new ImageView(ssContext);
        ssImageView.setPadding(0, 0, 0, 0);
        ssImageView.setAdjustViewBounds(true);
        ssImageView.setImageResource(images.get(ssPosition).getImageUrl());

        ((ViewPager) ssContainer).addView(ssImageView, 0);
        return ssImageView;
    }

    @Override
    public void destroyItem(ViewGroup ssContainer, int ssPosition,
                            Object ssObject) {
        ((ViewPager) ssContainer).removeView((ImageView) ssObject);
    }
}
