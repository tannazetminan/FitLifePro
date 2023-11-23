package com.example.fitlifepro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;

public class UserDatabaseManager {
    private UserDatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public UserDatabaseManager(Context ctx) {
        context = ctx;
    }

    public UserDatabaseManager open() throws SQLDataException {
        dbHelper = new UserDatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert (String user_email, String user_name, String birthday, int weight, int height, String gender, String fitness_level) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserDatabaseHelper.USER_EMAIL, user_email);
        contentValues.put(UserDatabaseHelper.USER_NAME, user_name);
        contentValues.put(UserDatabaseHelper.BIRTHDAY, birthday);
        contentValues.put(UserDatabaseHelper.WEIGHT, weight);
        contentValues.put(UserDatabaseHelper.HEIGHT, height);
        contentValues.put(UserDatabaseHelper.GENDER, gender);
        contentValues.put(UserDatabaseHelper.FITNESS_LEVEL, fitness_level);

        database.insert(UserDatabaseHelper.DATABASE_TABLE, null, contentValues);
    }

    public Cursor fetch() {
        String [] columns = new String[] {UserDatabaseHelper.USER_EMAIL, UserDatabaseHelper.USER_NAME, UserDatabaseHelper.BIRTHDAY, UserDatabaseHelper.WEIGHT, UserDatabaseHelper.HEIGHT, UserDatabaseHelper.GENDER, UserDatabaseHelper.FITNESS_LEVEL};
        Cursor cursor = database.query(UserDatabaseHelper.DATABASE_TABLE, columns, null,null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(String user_email, String user_name, String birthday, int weight, int height, String gender, String fitness_level) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(UserDatabaseHelper.USER_EMAIL, user_email);
        contentValues.put(UserDatabaseHelper.USER_NAME, user_name);
        contentValues.put(UserDatabaseHelper.BIRTHDAY, birthday);
        contentValues.put(UserDatabaseHelper.WEIGHT, weight);
        contentValues.put(UserDatabaseHelper.HEIGHT, height);
        contentValues.put(UserDatabaseHelper.GENDER, gender);
        contentValues.put(UserDatabaseHelper.FITNESS_LEVEL, fitness_level);

        String whereClause = UserDatabaseHelper.USER_ID + "= 1";

        int ret = database.update(UserDatabaseHelper.DATABASE_TABLE, contentValues, whereClause, null);
        return ret;
    }

    public void delete() {
        database.delete(UserDatabaseHelper.DATABASE_TABLE, null, null);
    }

    public boolean hasData() {
        boolean hasData = false;
        Cursor cur = database.rawQuery("SELECT COUNT(*) FROM " + UserDatabaseHelper.DATABASE_TABLE, null);
        if (cur != null) {
            cur.moveToFirst();                       // Always one row returned.
            if (cur.getInt (0) == 0) {               // Zero count means empty table.
                hasData = false;
            } else {hasData = true;}
        }

        return hasData;
    }
}

