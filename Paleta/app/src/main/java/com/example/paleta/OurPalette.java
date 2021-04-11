package com.example.paleta;

import androidx.palette.graphics.Palette;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class OurPalette {

    String name;
    Date createdDate;
    Date lastModified;
    List<Palette.Swatch> allSwatches = new ArrayList<>(16);
    List<Palette.Swatch> savedSwatches = new ArrayList<>(16);

    public OurPalette(Palette palette){
        allSwatches = palette.getSwatches();
        savedSwatches.add(allSwatches.get(0));
        savedSwatches.add(allSwatches.get(3));
        savedSwatches.add(allSwatches.get(7));
        savedSwatches.add(allSwatches.get(11));


    }

    public void addColor(Palette.Swatch color){
        savedSwatches.add(color);

    }

    public void changeName(String newName){
        this.name = newName;
    }

    public void updateDateModified(Date newDate){
        lastModified = newDate;
    }
}
