package com.hammersmith.ku.coffeecorner;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.hammersmith.ku.coffeecorner.adapter.AdapterOrder;
import com.hammersmith.ku.coffeecorner.model.Order;

import java.util.ArrayList;
import java.util.List;

import static android.support.v7.widget.LinearLayoutManager.VERTICAL;

public class CartActivity extends AppCompatActivity {
    private RecyclerView recyclerViewCart;
    private AdapterOrder adapterOrderCart;
    private LinearLayoutManager linearLayoutManager;
    private List<Order> order = new ArrayList<>();

    private TextView subTotal, grandTotal;

    private Float price = 0.0f, totalPrice = 0.0f;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerViewCart = (RecyclerView) findViewById(R.id.recyclerViewCart);
        subTotal = (TextView) findViewById(R.id.sub_total);
        grandTotal = (TextView) findViewById(R.id.grand_total);



        linearLayoutManager = new LinearLayoutManager(this, VERTICAL, false);
        recyclerViewCart.setLayoutManager(linearLayoutManager);

        order = ProductDetail.orderList;

        adapterOrderCart = new  AdapterOrder(this, order);
        adapterOrderCart.notifyDataSetChanged();
        recyclerViewCart.setAdapter(adapterOrderCart);
        adapterOrderCart.notifyDataSetChanged();
        for (Order item:
             order) {
                price += item.getPrice() * item.getQuantity();
        }

        totalPrice = price + 1;


        subTotal.setText("$" + price);
        grandTotal.setText("$" + totalPrice);


    }
}
