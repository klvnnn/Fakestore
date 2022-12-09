package com.example.fakestore;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class DetailActivity extends AppCompatActivity {

    TextView title, desc;
    ImageView imageView;
    String vtitle, vimage, vdesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();

        vtitle = intent.getStringExtra("title");
        vimage = intent.getStringExtra("image");
        vdesc = intent.getStringExtra("desc");


        title = findViewById(R.id.title);
        desc = findViewById(R.id.desc);
        imageView = findViewById(R.id.imageView);

        Glide.with(this).load(vimage).centerCrop().into(imageView);
        title.setText(vtitle);
        desc.setText(vdesc);

    }
}