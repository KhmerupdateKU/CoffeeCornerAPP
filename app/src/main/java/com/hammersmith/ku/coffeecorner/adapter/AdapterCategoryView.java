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

import com.hammersmith.ku.coffeecorner.ProductDetail;
import com.hammersmith.ku.coffeecorner.R;
import com.hammersmith.ku.coffeecorner.model.CategoryViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ku on 1/10/17.
 */

public class AdapterCategoryView extends RecyclerView.Adapter<AdapterCategoryView.MyViewHolder> {

    private Context context;
    private Activity activity;
    private List<CategoryViewModel> categoriesview;



    public AdapterCategoryView(Activity activity, List<CategoryViewModel> categoriesview ) {
        this.activity = activity;
        this.categoriesview = categoriesview;

    }


    @Override
    public AdapterCategoryView.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_category_view, parent, false);
        AdapterCategoryView.MyViewHolder myViewHolder = new AdapterCategoryView.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
//        holder.imageView.setImageResource(categoriesview.get(position).getImageView());
        holder.title.setText(categoriesview.get(position).getTitle());
        holder.price.setText(categoriesview.get(position).getPrice());
        Context context = holder.imageView.getContext();
        Uri uri = Uri.parse("http://ppcoffeecorner.com/" + categoriesview.get(position).getImageView());
        Picasso.with(context).load(uri).into(holder.imageView);
//        holder.sub_title.setText(categoriesview.get(position).getshortDescription());
//
//
//
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(activity, ProductDetail.class);
//
//                activity.startActivity(intent);

                Intent intent = new Intent(activity, ProductDetail.class);
                intent.putExtra("price",categoriesview.get(position).getPrice());
                intent.putExtra("id",categoriesview.get(position).getId());
                intent.putExtra("desc",categoriesview.get(position).getDesc());
                intent.putExtra("image","http://ppcoffeecorner.com/" + categoriesview.get(position).getImageView());
                intent.putExtra("name",categoriesview.get(position).getTitle());
                activity.startActivity(intent);

            }
        });

    }



    @Override
    public int getItemCount() {
        return categoriesview.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, sub_title, price ;



        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.round_Image2);
            title = (TextView) itemView.findViewById(R.id.titleCategoryView);
            price = (TextView) itemView.findViewById(R.id.priceCategoryView);

        }


    }

}
