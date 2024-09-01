package com.example.wallpapers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ArrayList<ImageModel> modelClasslist;
    private RecyclerView recyclerView;
    Adapter adapter;
    CardView mnature, mbus, mcar, mtrain, mtrending;
    EditText edittext;
    ImageButton search;

    int pageNumber=1;
    int quantityPhotos=80;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        recyclerView = findViewById(R.id.recyclerview);
        mnature = findViewById(R.id.nature);
        mbus = findViewById(R.id.bus);
        mcar = findViewById(R.id.car);
        mtrain = findViewById(R.id.train);
        mtrending = findViewById(R.id.trending);
        search = findViewById(R.id.search);
        edittext = findViewById(R.id.editText);



        modelClasslist = new ArrayList<>();
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);
        adapter = new Adapter(getApplicationContext(),modelClasslist);
        recyclerView.setAdapter(adapter);

        gettrendingphotos();


        mnature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = "nature";
                getsearchphotos(query);
            }
        });

        mbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String query = "bus";
                getsearchphotos(query);
            }
        });
        mcar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = "car";
                getsearchphotos(query);
            }
        });
        mtrain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = "train";
                getsearchphotos(query);
            }
        });


        mtrending.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gettrendingphotos();
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = edittext.getText().toString().trim().toLowerCase();

                if(query.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter something", Toast.LENGTH_SHORT).show();
                } else {
                    getsearchphotos(query);
                }

            }
        });

    }








    private void gettrendingphotos() {

        ApiUtilities.getApiInterface().getTrendingImages(pageNumber, quantityPhotos).enqueue(new Callback<SearchModel>(){

            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                modelClasslist.clear();
                if(response.isSuccessful()){
                    modelClasslist.addAll(response.body().getModels());
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(getApplicationContext(), "Not able to get", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });

    }

    private void getsearchphotos(String query) {
        ApiUtilities.getApiInterface().getSearchImages(query, pageNumber, quantityPhotos).enqueue(new Callback<SearchModel>() {
            @Override
            public void onResponse(Call<SearchModel> call, Response<SearchModel> response) {
                modelClasslist.clear();
                if(response.isSuccessful()){
                    modelClasslist.addAll(response.body().getModels());
                    adapter.notifyDataSetChanged();
                }else{
                    Toast.makeText(getApplicationContext(), "Not able to get", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<SearchModel> call, Throwable t) {

            }
        });
    }





}