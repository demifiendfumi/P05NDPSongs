package com.myapplicationdev.android.p05_ndpsongs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {
    TextView tvID;
    EditText etShowTitle, etShowSinger, etShowYear;
    RadioGroup rdGroup;
    Button btnUpdate, btnDelete, btnCancel;
    Song data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        tvID = (TextView)findViewById(R.id. tvSetID);
        etShowTitle = (EditText)findViewById(R.id.etSetTitle);
        etShowSinger = (EditText)findViewById(R.id. etSetSinger);
        etShowYear = (EditText)findViewById(R.id. etSetYear);
        rdGroup = (RadioGroup)findViewById(R.id. radioGroup1);
        btnUpdate = (Button)findViewById(R.id. btnUpdate);
        btnDelete = (Button)findViewById(R.id. btnDelete);
        btnCancel = (Button)findViewById(R.id. btnCancel);

        final Intent i = getIntent();
        data = (Song) i.getSerializableExtra("data");

        tvID.setText(String.valueOf(data.getId()));
        etShowTitle.setText(data.getTitle());
        etShowSinger.setText(data.getSinger());
        etShowYear.setText(String.valueOf(data.getYear()));
        int star = data.getStars();
        ((RadioButton)rdGroup.getChildAt(star-1)).setChecked(true);

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                data.setTitle(etShowTitle.getText().toString());
                data.setSinger(etShowSinger.getText().toString());
                data.setYear(Integer.parseInt(etShowYear.getText().toString()));
                int selectedButtonId = rdGroup.getCheckedRadioButtonId();
                RadioButton rb = (RadioButton)findViewById(selectedButtonId);
                data.setStars(Integer.parseInt(rb.getText().toString()));
                dbh.updateSong(data);
                dbh.close();
                i.putExtra("requestCode",9);
                setResult(RESULT_OK,i);
                finish();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DBHelper dbh = new DBHelper(ThirdActivity.this);
                dbh.deleteSong(data.getId());
                dbh.close();
                i.putExtra("requestCode",9);
                setResult(RESULT_OK,i);
                finish();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
