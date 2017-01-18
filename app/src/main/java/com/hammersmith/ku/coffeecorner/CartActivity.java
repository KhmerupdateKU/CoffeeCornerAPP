package com.hammersmith.ku.coffeecorner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        recyclerViewCart = (RecyclerView) findViewById(R.id.recyclerViewCart);

        linearLayoutManager = new LinearLayoutManager(this, VERTICAL, false);
        recyclerViewCart.setLayoutManager(linearLayoutManager);

        order.add(new Order(R.drawable.foodfive, "Korean Pork", "1", "$3.50"));
        order.add(new Order(R.drawable.foodfive, "Coca Cola", "1", "$3.00"));

        adapterOrderCart = new  AdapterOrder(this, order);
        recyclerViewCart.setAdapter(adapterOrderCart);
        adapterOrderCart.notifyDataSetChanged();




    }
}
