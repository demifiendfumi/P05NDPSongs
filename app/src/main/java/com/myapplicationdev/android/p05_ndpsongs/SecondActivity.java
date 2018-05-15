package com.myapplicationdev.android.p05_ndpsongs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    ListView lv;
    ArrayAdapter aa;
    ArrayList<Song> songs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        lv = (ListView) this.findViewById(R.id.Lv);

        songs = new ArrayList<Song>();

        DBHelper db = new DBHelper(SecondActivity.this);

        songs.addAll(db.getAllSongs());
        Log.d("songs", songs.get(0).toString()+"");
        aa = new SongAdapter(SecondActivity.this, R.layout.row, songs);
        lv.setAdapter(aa);
        db.close();

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long identity) {
                Intent i = new Intent(SecondActivity.this,
                        ThirdActivity.class);
                Song data = songs.get(position);
                int id = data.getId();
                String title = data.getTitle();
                String singer = data.getSinger();
                int year = data.getYear();
                int star = data.getStars();

                //Song target = new Song(id, title, singer, year, star);
                Song target = new Song(id, title, singer, year, star);
                i.putExtra("data", target);
                Log.d("Before Intent", "passing over");
                startActivityForResult(i, 9);

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == 9) {
            DBHelper db = new DBHelper(SecondActivity.this);
            songs.clear();
            songs.addAll(db.getAllSongs());
            aa = new SongAdapter(SecondActivity.this, R.layout.row, songs);
            lv.setAdapter(aa);
            aa.notifyDataSetChanged();
            }
        }
    }

