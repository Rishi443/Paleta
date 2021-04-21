package com.example.paleta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    private Button bCP, p1, p2, p3, p4, p5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Palette Creation
        bCP = (Button) findViewById(R.id.bCP);
        bCP.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                openCP();
            }
        });

        // Palette Editor Buttons
        p1 = (Button) findViewById(R.id.p1);
        p1.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                openPE();
            }
        });
        p2 = (Button) findViewById(R.id.p2);
        p2.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                openPE();
            }
        });
        p3 = (Button) findViewById(R.id.p3);
        p3.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                openPE();
            }
        });
        p4 = (Button) findViewById(R.id.p4);
        p4.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                openPE();
            }
        });
        p5 = (Button) findViewById(R.id.p5);
        p5.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                openPE();
            }
        });

    }
    // Links to Palette Creator Tool
    public void openCP() {
        Intent intent = new Intent( this, Activity2.class );
        startActivity( intent );
    }
    // Links to Palette Editor Tool -- Consider passing a Palette class in with the saved color data.
    public void openPE() {
        Intent intent = new Intent( this, Activity3.class );
        startActivity( intent );
    }
}