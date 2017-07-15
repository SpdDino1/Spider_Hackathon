package com.example.vikramkumaresan.v1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class Gallery extends AppCompatActivity {
    static ListView list;
    static ArrayAdapter adapt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        list = (ListView)findViewById(R.id.listview);
        adapt = new CustomAdapter(this,MainActivity.images);
        list.setAdapter(adapt);

        if(MainActivity.images.size()==0){
            Toast.makeText(this,"No pictures to show....",Toast.LENGTH_LONG).show();
        }
    }

    public static void notify_the_delete(){
        adapt.notifyDataSetChanged();
        list.invalidateViews();
    }
}
