package com.example.paleta;

import androidx.palette.graphics.Palette;

import java.util.Date;
import java.util.List;

public class OurPalette {
    public OurPalette(Palette palette){
        List<Palette.Swatch> allSwatches = palette.getSwatches();
        String name;
        String createdDate;
        Palette.Swatch color1 = allSwatches.get(0);
        Palette.Swatch color2 = allSwatches.get(3);
        Palette.Swatch color3 = allSwatches.get(7);
        Palette.Swatch color4 = allSwatches.get(11);

    }

}
