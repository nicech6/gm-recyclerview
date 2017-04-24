package com.cuihai.gmrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;


import com.cuihai.mylibrary.GMRvAdapter;
import com.cuihai.mylibrary.GMRvViewHolder;


import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    GMRvAdapter<String> adapter;
    List<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for (int i = 0; i < 100; i++) {
            list.add("item"+i);
        }
        recyclerView = (RecyclerView) findViewById(R.id.rv);
        adapter = new GMRvAdapter<String>(getApplication(), R.layout.item_rv, list) {
            @Override
            public void convert(GMRvViewHolder easyRvViewHolder, String s, int position) {
                TextView tv = easyRvViewHolder.getView(R.id.item_rv);
                tv.setText(list.get(position));
            }
        };
        recyclerView.setAdapter(adapter);
        LinearLayoutManager manager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
    }
}
