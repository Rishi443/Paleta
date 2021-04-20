package com.example.paleta;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "PaletteData.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create Table Palettedetails(pid integer primary key,name TEXT)");
        db.execSQL("create Table PaletteSavedColors(rid integer primary key, pid integer,  rgb integer, foreign key (pid) references Palettedetails (pid))");
        db.execSQL("create Table PaletteAllColors(aid integer primary key, pid integer, rgb integer, foreign key (pid) references Palettedetails (pid))");
        db.execSQL("create Table CurrentPalette(pid nteger)");
        db.execSQL("create Table CurrentColor(rid integer)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop Table if exists Palettedetails");
        db.execSQL("drop Table if exists PaletteSavedColors");
        db.execSQL("drop Table if exists PaletteAllColors");
    }

    public Integer insertPaletteData(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        long result = db.insert("Palettedetails", null, contentValues);
        Cursor mCursor = db.rawQuery("SELECT *  FROM  Palettedetails", null);
        mCursor.moveToLast();
        Integer pid = mCursor.getInt(0);
        return pid;

    }

    public Boolean insertSavedColors(Integer rgb, Integer pid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("pid", pid);
        //contentValues.put("name", name);
        contentValues.put("rgb", rgb);
        long result = db.insert("PaletteSavedColors", null, contentValues);
        if(result==1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean insertAllColors(Integer rgb, Integer pid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("pid", pid);
        //contentValues.put("name", name);
        contentValues.put("rgb", rgb);
        long result = db.insert("PaletteAllColors", null, contentValues);
        if(result==1){
            return false;
        }else{
            return true;
        }
    }

    public Boolean updatePaletteData(String name, Integer pid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        Cursor cursor = db.rawQuery("Select * from Palettedetails where pid = ?", new String[]{String.valueOf(pid)});
        if(cursor.getCount()>0) {


            long result = db.update("Palettedetails", contentValues, "pid=?", new String[]{String.valueOf(pid)});

            if (result == 1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Boolean updateSavedColor(Integer rgb, Integer rid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        //contentValues.put("rid", rid);
        //contentValues.put("name", name);
        contentValues.put("rgb", rgb);
        Cursor cursor = db.rawQuery("Select * from PaletteSavedColors where rid = ?", new String[]{String.valueOf(rid)});

        if(cursor.getCount()>0) {


            long result = db.update("PaletteSavedColors", contentValues, "rid=?", new String[]{String.valueOf(rid)});

            if (result == 1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Boolean deletePaletteData(Integer pid) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from Palettedetails where pid = ?", new String[]{String.valueOf(pid)});
        if(cursor.getCount()>0) {
            long result = db.delete("Palettedetails", "pid=?", new String[]{String.valueOf(pid)});
            if (result == 1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Boolean deleteSavedColor(Integer rid) {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from PaletteSavedColors where rid = ?", new String[]{String.valueOf(rid)});
        if(cursor.getCount()>0) {
            long result = db.delete("PaletteSavedColors", "rid=?", new String[]{String.valueOf(rid)});
            if (result == 1) {
                return false;
            } else {
                return true;
            }
        }else{
            return false;
        }
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery("Select * from Palettedetails", null);
        return cursor;
    }

    public ArrayList<Integer> getPids() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Integer> test = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from Palettedetails", null);
        if (cursor.moveToFirst()) {
            do {
                test.add(cursor.getInt(cursor.getColumnIndex("pid")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return test;
    }

    public ArrayList<Integer> getSavedColors(Integer pid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Integer> test = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from PaletteSavedColors where pid=?", new String[]{String.valueOf(pid)});
        if (cursor.moveToFirst()) {
            do {
                test.add(cursor.getInt(cursor.getColumnIndex("rgb")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return test;
    }

    public ArrayList<Integer> getAllColors(Integer pid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Integer> test = new ArrayList<>();
        Cursor cursor = db.rawQuery("Select * from PaletteAllColors where pid=?", new String[]{String.valueOf(pid)});
        if (cursor.moveToFirst()) {
            do {
                test.add(cursor.getInt(cursor.getColumnIndex("rgb")));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return test;
    }


    public void setCurrentPalette(Integer pid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("pid", pid);
        Cursor cursor = db.rawQuery("Select * from CurrentPalette where pid = ?", new String[]{String.valueOf(pid)});
        if(cursor.getCount()>0) {
            long result = db.update("CurrentPalette", contentValues, "pid=?", new String[]{String.valueOf(pid)});
        }else{
            long result2 = db.insert("CurrentPalette", null, contentValues);
        }
    }

    public void setCurrentColor(Integer rid) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("rid", rid);
        Cursor cursor = db.rawQuery("Select * from CurrentColor where rid = ?", new String[]{String.valueOf(rid)});
        if(cursor.getCount()>0) {
            long result = db.update("CurrentColor", contentValues, "rid=?", new String[]{String.valueOf(rid)});
        }else{
            long result2 = db.insert("CurrentColor", null, contentValues);
        }
    }

    public Integer getCurrentPalette(){
        SQLiteDatabase db = this.getWritableDatabase();
        Integer test = 0;
        Cursor cursor = db.rawQuery("Select * from CurrentPalette", null);
        if (cursor.moveToFirst()) {
            test = cursor.getInt(cursor.getColumnIndex("pid"));
        }
        cursor.close();
        return test;
    }

    public Integer getCurrentColor(){
        SQLiteDatabase db = this.getWritableDatabase();
        Integer test = 0;
        Cursor cursor = db.rawQuery("Select * from CurrentColor", null);
        if (cursor.moveToFirst()) {
            test = cursor.getInt(cursor.getColumnIndex("rid"));
        }
        cursor.close();
        return test;
    }

    public Integer getASavedColor( Integer rid){
        SQLiteDatabase db = this.getWritableDatabase();
        Integer test = 0;
        Cursor cursor = db.rawQuery("Select * from PaletteSavedColors where rid=?", new String[]{String.valueOf(rid)});
        if (cursor.moveToFirst()) {
            test = cursor.getInt(cursor.getColumnIndex("rgb"));
        }
        cursor.close();
        return test;
    }

}
