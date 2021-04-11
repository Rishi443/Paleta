package com.example.paleta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.palette.graphics.Palette;

import android.graphics.Bitmap;
import android.os.Bundle;

import java.util.List;

public class PaletteEditorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette_editor);
    }

   //Creates palette. These methods should be placed in review picture activity instead
    public void createPaletteAsync(Bitmap bitmap) {
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            public void onGenerated(Palette p) {
                OurPalette newPalette = new OurPalette(p);
            }
        });
    }

    public void savePalette(OurPalette p){
        //Have to add shared preferences code and a global list instead of this one
        List<OurPalette> newOurPalette = null;
        newOurPalette.add(p);
    }

}