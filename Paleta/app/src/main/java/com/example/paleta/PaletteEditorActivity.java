package com.example.paleta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class PaletteEditorActivity extends AppCompatActivity {

    private ImageButton color1, color2, color3, color4, color5, color6, color7, color8;
    Button addColor;
    DBHelper db;
    private Integer pid;
    private ArrayList<Integer> savedColors;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette_editor);


        db = new DBHelper(this);
        pid = db.getCurrentPalette();
        savedColors = db.getSavedColors(pid);
        addColor = findViewById(R.id.AddColor);
        color1 = findViewById(R.id.Color1);
        color2 = findViewById(R.id.Color2);
        color3 = findViewById(R.id.Color3);
        color4 = findViewById(R.id.Color4);
        color5 = findViewById(R.id.Color5);
        color6 = findViewById(R.id.Color6);
        color7 = findViewById(R.id.Color7);
        color8 = findViewById(R.id.Color8);

        if(savedColors.size()>=1){color1.setBackgroundColor(savedColors.get(0));}
        if(savedColors.size()>=2){color2.setBackgroundColor(savedColors.get(1));}
        if(savedColors.size()>=3){color3.setBackgroundColor(savedColors.get(2));}
        if(savedColors.size()>=4){color4.setBackgroundColor(savedColors.get(3));}
        if(savedColors.size()>=5){color5.setBackgroundColor(savedColors.get(4));}
        if(savedColors.size()>=6){color6.setBackgroundColor(savedColors.get(5));}
        if(savedColors.size()>=7){color7.setBackgroundColor(savedColors.get(6));}
        if(savedColors.size()>=8){color8.setBackgroundColor(savedColors.get(7));}

        addColor.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick( View v ) {
                openAddColor();
            }
        });


    }

    public void openAddColor(){
        Intent intent = new Intent( this, AddColor.class );
        startActivity( intent );
    }

}
