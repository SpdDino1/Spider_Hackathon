package com.example.vikramkumaresan.v1;

import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class Pic_Focus extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pic__focus);

        ImageView img = (ImageView)findViewById(R.id.img);
        Picasso.with(this).load(Uri.parse(MainActivity.images.get(CustomAdapter.focused))).into(img);
        CustomAdapter.focused=-1;

    }
}
