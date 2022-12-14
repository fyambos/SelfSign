package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DataClass> dataList;
    MyAdapter adapter;
    DataClass androidData;
    SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        searchView = findViewById(R.id.search);

        searchView.clearFocus();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });

        GridLayoutManager gridLayoutManager = new GridLayoutManager(MainActivity.this, 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        dataList = new ArrayList<>();

        androidData = new DataClass("Camera", R.string.camera, "Java", R.drawable.camera_detail);
        dataList.add(androidData);

        androidData = new DataClass("RecyclerView", R.string.recyclerview, "Android", R.drawable.list_detail);
        dataList.add(androidData);

        androidData = new DataClass("Date Picker", R.string.date, "Math", R.drawable.date_detail);
        dataList.add(androidData);

        androidData = new DataClass("EditText", R.string.edit, "Javascript", R.drawable.edit_detail);
        dataList.add(androidData);

        androidData = new DataClass("Rating Bar", R.string.rating, "Anglais", R.drawable.rating_detail);
        dataList.add(androidData);
        androidData = new DataClass("Camera", R.string.camera, "Java", R.drawable.camera_detail);
        dataList.add(androidData);

        androidData = new DataClass("RecyclerView", R.string.recyclerview, "Android", R.drawable.list_detail);
        dataList.add(androidData);

        androidData = new DataClass("Date Picker", R.string.date, "Math", R.drawable.date_detail);
        dataList.add(androidData);

        androidData = new DataClass("EditText", R.string.edit, "Javascript", R.drawable.edit_detail);
        dataList.add(androidData);

        androidData = new DataClass("Rating Bar", R.string.rating, "Anglais", R.drawable.rating_detail);
        dataList.add(androidData);

        adapter = new MyAdapter(MainActivity.this, dataList);
        recyclerView.setAdapter(adapter);
    }

    private void searchList(String text){
        List<DataClass> dataSearchList = new ArrayList<>();
        for (DataClass data : dataList){
            if (data.getDataTitle().toLowerCase().contains(text.toLowerCase())) {
                dataSearchList.add(data);
            }
        }
        if (dataSearchList.isEmpty()){
            Toast.makeText(this, "Pas des resultat", Toast.LENGTH_SHORT).show();
        } else {
            adapter.setSearchList(dataSearchList);
        }
    }
}