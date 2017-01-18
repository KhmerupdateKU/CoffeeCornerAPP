package com.hammersmith.ku.coffeecorner.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hammersmith.ku.coffeecorner.ImageActivity;
import com.hammersmith.ku.coffeecorner.R;
import com.hammersmith.ku.coffeecorner.model.Image;

import java.util.List;

/**
 * Created by ku on 1/4/17.
 */

public class AdapterImage extends RecyclerView.Adapter<AdapterImage.MyViewHolder> {
    private Context context;
    private Activity activity;
    private List<Image> images;

    public AdapterImage(Activity activity, List<Image> images){
        this.activity=activity;
        this.images=images;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_image, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;

    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.imageView.setImageResource(images.get(position).getImageUrl());
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.startActivity(new Intent(activity, ImageActivity.class));
            }
        });

    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView= (ImageView) itemView.findViewById(R.id.ring);
        }
    }
}
