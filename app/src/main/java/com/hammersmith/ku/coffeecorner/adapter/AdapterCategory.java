package com.hammersmith.ku.coffeecorner.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hammersmith.ku.coffeecorner.CategoryView;
import com.hammersmith.ku.coffeecorner.R;
import com.hammersmith.ku.coffeecorner.model.Category;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ku on 1/10/17.
 */


    public class AdapterCategory extends RecyclerView.Adapter<AdapterCategory.MyViewHolder> {

        private Context context;
        private Activity activity;
        private List<Category> categories;



        public AdapterCategory(Activity activity, List<Category> categories ) {
            this.activity = activity;
            this.categories = categories;
        }


        @Override
        public AdapterCategory.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_category, parent, false);
            AdapterCategory.MyViewHolder myViewHolder = new AdapterCategory.MyViewHolder(view);
            return myViewHolder;
        }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        holder.title.setText(categories.get(position).getTitle());
        holder.sub_title.setText(categories.get(position).getShortDescription());
        Uri uri = Uri.parse("http://ppcoffeecorner.com/" + categories.get(position).getImageView());
        Context context = holder.imageView.getContext();
        Picasso.with(context).load(uri).into(holder.imageView);
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, CategoryView.class);
                intent.putExtra("title",categories.get(position).getTitle());
                intent.putExtra("image","http://ppcoffeecorner.com/" + categories.get(position).getImageView());
                intent.putExtra("id",categories.get(position).getId());
                activity.startActivity(intent);

            }
        });

    }



        @Override
        public int getItemCount() {
            return categories.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView title, sub_title ;



            public MyViewHolder(View itemView) {
                super(itemView);
                imageView = (ImageView) itemView.findViewById(R.id.round_Image);
                title = (TextView) itemView.findViewById(R.id.title);
                sub_title = (TextView) itemView.findViewById(R.id.subTittle);

        }


    }

}
