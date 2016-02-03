package com.ufo.smartedittextsimple;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.ufo.smartedittextsimple.view.SmartEditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SmartEditText smartEditText = (SmartEditText) findViewById(R.id.smartET);
//        smartEditText.setMaxLength(60);
//        smartEditText.setTextColor();
//        smartEditText.setCornerTextColor();
//        smartEditText.setTextSize();
        smartEditText.setOnInputOutLengthListener(new SmartEditText.OnInputOutLengthListener() {
            @Override
            public void onInputOutLength(int length) {
                Toast.makeText(MainActivity.this, "out Length:"+length, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
