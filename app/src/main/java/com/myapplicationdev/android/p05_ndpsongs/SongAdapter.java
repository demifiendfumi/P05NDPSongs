package com.myapplicationdev.android.p05_ndpsongs;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.myapplicationdev.android.p05_ndpsongs.R;
import com.myapplicationdev.android.p05_ndpsongs.Song;

import java.util.ArrayList;

public class SongAdapter extends ArrayAdapter<Song> {
    Context context;
    ArrayList<Song> songs;
    int resource;
    TextView tvTitle, tvSinger, tvYear;
    ImageView iv1, iv2, iv3, iv4, iv5;

    public SongAdapter(Context context, int resource, ArrayList<Song> songs) {
        super(context, resource, songs);
        this.context = context;
        this.songs = songs;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.d("getView", "show" + position);
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row, parent, false);

        //Match the UI components with Java variables
        tvTitle = (TextView) rowView.findViewById(R.id.tvTitleSet);
        tvSinger = (TextView) rowView.findViewById(R.id.tvSingerSet);
        tvYear = (TextView) rowView.findViewById(R.id.tvYearSet);
        iv1 = (ImageView) rowView.findViewById(R.id.imageView1star);
        iv2 = (ImageView) rowView.findViewById(R.id.imageView2star);
        iv3 = (ImageView) rowView.findViewById(R.id.imageView3star);
        iv4 = (ImageView) rowView.findViewById(R.id.imageView4star);
        iv5 = (ImageView) rowView.findViewById(R.id.imageView5star);

        Song song = songs.get(position);

        tvTitle.setText(song.getTitle());
        tvYear.setText(String.valueOf(song.getYear()));
        tvSinger.setText(song.getSinger());
        //Check if the property for stars >= 5, if so, "light" up the stars
        if (song.getStars() >= 5) {
            iv5.setImageResource(android.R.drawable.btn_star_big_on);
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        } else if (song.getStars() >= 4) {
            iv5.setImageResource(android.R.drawable.btn_star_big_off);
            iv4.setImageResource(android.R.drawable.btn_star_big_on);
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        } else if (song.getStars() >= 3) {
            iv5.setImageResource(android.R.drawable.btn_star_big_off);
            iv4.setImageResource(android.R.drawable.btn_star_big_off);
            iv3.setImageResource(android.R.drawable.btn_star_big_on);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        } else if (song.getStars() >= 2) {
            iv5.setImageResource(android.R.drawable.btn_star_big_off);
            iv4.setImageResource(android.R.drawable.btn_star_big_off);
            iv3.setImageResource(android.R.drawable.btn_star_big_off);
            iv2.setImageResource(android.R.drawable.btn_star_big_on);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        } else if (song.getStars() >= 1) {
            iv5.setImageResource(android.R.drawable.btn_star_big_off);
            iv4.setImageResource(android.R.drawable.btn_star_big_off);
            iv3.setImageResource(android.R.drawable.btn_star_big_off);
            iv2.setImageResource(android.R.drawable.btn_star_big_off);
            iv1.setImageResource(android.R.drawable.btn_star_big_on);
        } else {
            iv5.setImageResource(android.R.drawable.btn_star_big_off);
            iv4.setImageResource(android.R.drawable.btn_star_big_off);
            iv3.setImageResource(android.R.drawable.btn_star_big_off);
            iv2.setImageResource(android.R.drawable.btn_star_big_off);
            iv1.setImageResource(android.R.drawable.btn_star_big_off);
        }
        return rowView;
    }
}
