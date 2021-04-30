package com.example.paletapaletteeditor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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
                        openEmailExport();
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
    public void openEmailExport() {

        String output = "Color 1: #22e8f2 \nColor 2: #e8b254 \nColor 3: #eb215a \nColor 4: #036b6b \nColor 5: #66bda4 \nColor 6:  \nColor 7:  \nColor 8:  \n";

        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{"recipient@example.com"});
        i.putExtra(Intent.EXTRA_SUBJECT, "Paleta");
        i.putExtra(Intent.EXTRA_TEXT   , output );
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(MainActivity.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}