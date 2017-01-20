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
import com.hammersmith.ku.coffeecorner.model.Order;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ku on 1/16/17.
 */


public class AdapterOrder extends RecyclerView.Adapter<AdapterOrder.MyViewHolder> {

    private Context context;
    private Activity activity;
    private List<Order> order;



    public AdapterOrder(Activity activity, List<Order> order ) {
        this.activity = activity;
        this.order = order;
    }


    @Override
    public AdapterOrder.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cart, parent, false);
        AdapterOrder.MyViewHolder myViewHolder = new AdapterOrder.MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(order.get(position).getTitle());
        Float totalPrice = order.get(position).getPrice() * order.get(position).getQuantity();
        holder.price.setText("$ " + String.valueOf(totalPrice));
        holder.quantity.setText(" " + order.get(position).getQuantity());

        Context context = holder.imageView.getContext();
        Uri uri = Uri.parse(order.get(position).getImageView());
        Picasso.with(context).load(uri).into(holder.imageView);

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(activity, CategoryView.class);

                activity.startActivity(intent);

            }
        });

    }



    @Override
    public int getItemCount() {
        return order.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title,quantity, price;




        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.round_ImageCart);
            title = (TextView) itemView.findViewById(R.id.title_order);
            price = (TextView) itemView.findViewById(R.id.priceCart);
            quantity = (TextView) itemView.findViewById(R.id.qty_order);



        }


    }

}
