package com.myapplicationdev.android.p05_ndpsongs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class MainActivity extends AppCompatActivity {
    EditText etTitle, etSinger, etYear;
    RadioGroup rdGroup;
    Button btnInsert, btnShowList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etTitle = (EditText)findViewById(R.id. etSongTitle);
        etSinger = (EditText)findViewById(R.id. etSingers);
        etYear = (EditText)findViewById(R.id. etYear);
        btnInsert = (Button)findViewById(R.id. btnInsert);
        btnShowList = (Button)findViewById(R.id. btnShowList);
        rdGroup = (RadioGroup)findViewById(R.id. radioGroup);

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = etTitle.getText().toString();
                String singer = etSinger.getText().toString();
                int year = Integer.parseInt(etYear.getText().toString());
                int selectedButtonId = rdGroup.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)findViewById(selectedButtonId);
                DBHelper db = new DBHelper(MainActivity.this);
                db.getWritableDatabase();

                db.insertSong(title, singer, year , Integer.parseInt(rb.getText().toString()));
                db.close();

            }
        });

        btnShowList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, SecondActivity.class);
                startActivity(i);
            }
        });
    }
}
