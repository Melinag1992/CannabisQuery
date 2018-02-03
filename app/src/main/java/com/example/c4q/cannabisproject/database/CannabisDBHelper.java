package com.example.c4q.cannabisproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.c4q.cannabisproject.model.CannabisStrain;

import java.util.ArrayList;
import java.util.List;

import static com.example.c4q.cannabisproject.database.CannabisDBContract.CannabisEntry.COLUMN_NAME_IMAGE;
import static com.example.c4q.cannabisproject.database.CannabisDBContract.CannabisEntry.COLUMN_NAME_NAME;
import static com.example.c4q.cannabisproject.database.CannabisDBContract.CannabisEntry.COLUMN_NAME_OCPC;
import static com.example.c4q.cannabisproject.database.CannabisDBContract.CannabisEntry.COLUMN_NAME_STATUS;
import static com.example.c4q.cannabisproject.database.CannabisDBContract.CannabisEntry.COLUMN_NAME_URL;
import static com.example.c4q.cannabisproject.database.CannabisDBContract.CannabisEntry.TABLE_NAME;
import static com.example.c4q.cannabisproject.database.CannabisDBContract.CannabisEntry._STATUS_FAV;
import static com.example.c4q.cannabisproject.database.CannabisDBContract.CannabisEntry._STATUS_WISH;

/**
 * Created by amirahoxendine on 2/3/18.
 */

public class CannabisDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "cannabis.db";
    private static final int SCHEMA_VERSION = 1;
    private static final String TAG = "cannabisDBHelper";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME +
                    " (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COLUMN_NAME_OCPC + " TEXT, " +
                    COLUMN_NAME_IMAGE + " TEXT, " +
                    COLUMN_NAME_URL + " TEXT, " +
                    COLUMN_NAME_NAME + " TEXT, " +
                    COLUMN_NAME_STATUS + " TEXT);";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public CannabisDBHelper(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
        Log.d(TAG, "onCreate: onCreate method called");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        Log.d(TAG, "onUpgrade: Entries Deleted");
        onCreate(db);
        Log.d(TAG, "onUpgrade: New table created");
    }

    public boolean strainExists(CannabisStrain cannabisStrain){
        //this methods checks to see if strain is already in db
        String ocpc = cannabisStrain.getOcpc();
        Cursor cursor = this.getReadableDatabase()
                .rawQuery("SELECT * FROM "
                        + TABLE_NAME
                        + " WHERE "
                        + COLUMN_NAME_OCPC + " ='"
                        + ocpc
                        + "';", null);
        if (cursor.getCount() == 0){
            cursor.close();
            Log.d(TAG, "strainExists: strain not in db");
            return false;
        }
        cursor.close();
        Log.d(TAG, "strainExists: strain in db");
        return true;
    }

    public boolean insertStrain(CannabisStrain cannabisStrain, String status){
        SQLiteDatabase db = this.getWritableDatabase();
        //onCreate(db);
        if (strainExists(cannabisStrain)){
            Log.d(TAG, "insertStrain: strain not added");
            db.close();
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_NAME_OCPC, cannabisStrain.getOcpc());
        contentValues.put(COLUMN_NAME_IMAGE, cannabisStrain.getImage());
        contentValues.put(COLUMN_NAME_URL, cannabisStrain.getUrl());
        contentValues.put(COLUMN_NAME_NAME, cannabisStrain.getName());
        contentValues.put(COLUMN_NAME_STATUS, status);

        long rowID = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        Log.d(TAG, "insertStrain: new strain added" + rowID);

        return true;
    }

    public List<CannabisStrain> getFavStrainList(){
        List<CannabisStrain> favStrainList = new ArrayList<>();
        Cursor cursor = this.getReadableDatabase()
                .rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME_STATUS + " ='" + _STATUS_FAV + "';", null);

        addToList(cursor, favStrainList);
        return favStrainList;
    }

    public List<CannabisStrain> getWishStrainList(){
        List<CannabisStrain> wishStrainList = new ArrayList<>();
        Cursor cursor = this.getReadableDatabase()
                .rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + COLUMN_NAME_STATUS + " ='" + _STATUS_WISH + "';", null);

        addToList(cursor, wishStrainList);
        return wishStrainList;
    }

    public void addToList(Cursor cursor, List<CannabisStrain> strainList){
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    CannabisStrain job = new CannabisStrain(
                            cursor.getString(cursor.getColumnIndex(COLUMN_NAME_OCPC)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_NAME_IMAGE)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_NAME_URL)),
                            cursor.getString(cursor.getColumnIndex(COLUMN_NAME_NAME)));
                    strainList.add(job);
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
    }


}
