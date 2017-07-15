package com.example.vikramkumaresan.v1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class CustomAdapter extends ArrayAdapter {
    public static int focused=-1;
    public static int to_del=-1;

    Context ctx;
    public CustomAdapter(@NonNull Context context, ArrayList<String> paths) {
        super(context, R.layout.custom_row, paths);
        ctx=context;
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflate = LayoutInflater.from(getContext());
        final View custom_view= inflate.inflate(R.layout.custom_row,parent,false);

        ImageView img = (ImageView)custom_view.findViewById(R.id.img);
        ImageView close = (ImageView)custom_view.findViewById(R.id.close_button);

        Picasso.with(ctx).load(Uri.parse((String)getItem(position))).fit().centerCrop().into(img);

        img.setOnClickListener(
                new ImageView.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        focused=position;
                        Intent focus = new Intent(ctx,Pic_Focus.class);
                        ctx.startActivity(focus);
                    }
                }
        );

        close.setOnClickListener(
                new ImageView.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity.deleteImage(position);
                    }
                }
        );

        return custom_view;
    }
}
