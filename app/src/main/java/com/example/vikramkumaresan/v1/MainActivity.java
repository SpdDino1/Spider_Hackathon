package com.example.vikramkumaresan.v1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements GestureDetector.OnGestureListener,GestureDetector.OnDoubleTapListener {
    static Context ctx;
    GestureDetectorCompat gest;
    public static ArrayList<String> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gest=new GestureDetectorCompat(this,this);

        images=new ArrayList<>();
        ctx=this;

        SharedPreferences pref = getSharedPreferences("stuff_stored",MODE_PRIVATE);
        int size=pref.getInt("size",0);
        Log.d("TAG","Size Recovered = "+size);

        if(size!=0){
            for (int i=0;i<size;i++){
                images.add(pref.getString(""+i,null));
            }
        }
    }

    public void launch_camera(){
        Intent i = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File pic = FileMaker();

        Uri imagepath = FileProvider.getUriForFile(ctx, BuildConfig.APPLICATION_ID + ".provider", pic);
        images.add(imagepath.toString());

        i.putExtra(MediaStore.EXTRA_OUTPUT, imagepath);
        ((Activity) ctx).startActivityForResult(i, 1);

    }

    private static File FileMaker(){    //Creates an empty 0B file at a location
        try {
            File pic = File.createTempFile("TestImage",".jpg",ctx.getExternalFilesDir("Test"));    //Location = Test Folder in getExternalFilesDir(). File Name = TestImage.jpg
            return pic;
        } catch (IOException e) {
            Toast.makeText(ctx,"Error Making File",Toast.LENGTH_LONG).show();
            return null;
        }
    }

    public void launch_gallery(){
        Intent go = new Intent(this,Gallery.class);
        startActivity(go);
    }

    public static void deleteImage(int pos){
        images.remove(pos);
        Gallery.notify_the_delete();
    }

    @Override
    protected void onPause() {
        SharedPreferences pref = getSharedPreferences("stuff_stored",MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putInt("size",images.size());
        Log.d("TAG","Size = "+images.size());
        edit.apply();

        if(images.size()!=0){
            for(int i=0;i<images.size();i++){
                edit.putString(""+i,images.get(i));
            }
            edit.apply();
        }
        super.onPause();

    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if(velocityX<0){    //left swipe
            launch_gallery();
            return true;
        }
        else if(velocityX>0){
            launch_camera();
            return true;
        }
        else{
            return true;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gest.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==1 && resultCode==RESULT_CANCELED){
            images.remove(images.size()-1);
        }
    }
}
