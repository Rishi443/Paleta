package com.example.paletapaletteeditor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    // private Button bAddColor, bAccessibility;
    private Button bExport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // bAddColor = findViewById(R.id.AddColor);
        // bAccessibility = findViewById(R.id.Accessibility);
        bExport = findViewById(R.id.Export);

        bExport.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        openExport();
                    }
                }
        );
    }

    public void openExport() {

        String output = "This is placeholder text.  We will add color values and format accordingly later on.";
        Intent sendIntent = new Intent();
        sendIntent.setAction( Intent.ACTION_SEND );
        sendIntent.putExtra( Intent.EXTRA_TEXT, output );

        Intent shareIntent = Intent.createChooser( sendIntent, null );
        startActivity( shareIntent );
        }
}