package com.example.wallpapers;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.io.IOException;
import java.util.Objects;

public class SetWallPaper extends AppCompatActivity {


    Intent intent;
    ImageView imageView;
    Button setWallpaper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setwallpaper);
        intent = getIntent();
        imageView = findViewById(R.id.finalimage);
        setWallpaper = findViewById(R.id.setWallpaper);
        Objects.requireNonNull(getSupportActionBar()).hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Установим картинку в предпросмотр
        String url = intent.getStringExtra("image");
        Glide.with(getApplicationContext()).load(url).into(imageView);


        // Установим картинку на рабочий стол
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());

        setWallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                    wallpaperManager.setBitmap(bitmap);
                    Toast.makeText(SetWallPaper.this, "Done", Toast.LENGTH_SHORT).show();
                }
                catch(IOException ex){
                    ex.printStackTrace();
                }
            }
        });



    }
}