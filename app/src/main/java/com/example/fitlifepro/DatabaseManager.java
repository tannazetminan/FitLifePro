package com.example.fitlifepro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;

import java.sql.SQLDataException;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager (Context ctx) {
        context = ctx;
    }

    public DatabaseManager open() throws SQLDataException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insert (String user_email, String user_name, String birthday, int weight, int height, String gender, String fitness_level) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.USER_EMAIL, user_email);
        contentValues.put(DatabaseHelper.USER_NAME, user_name);
        contentValues.put(DatabaseHelper.BIRTHDAY, birthday);
        contentValues.put(DatabaseHelper.WEIGHT, weight);
        contentValues.put(DatabaseHelper.HEIGHT, height);
        contentValues.put(DatabaseHelper.GENDER, gender);
        contentValues.put(DatabaseHelper.FITNESS_LEVEL, fitness_level);

        database.insert(DatabaseHelper.DATABASE_TABLE, null, contentValues);
    }

    public Cursor fetch() {
        String [] columns = new String[] {DatabaseHelper.USER_EMAIL, DatabaseHelper.USER_NAME, DatabaseHelper.BIRTHDAY, DatabaseHelper.WEIGHT, DatabaseHelper.HEIGHT, DatabaseHelper.GENDER, DatabaseHelper.FITNESS_LEVEL};
        Cursor cursor = database.query(DatabaseHelper.DATABASE_TABLE, columns, null,null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(String user_email, String user_name, String birthday, int weight, int height, String gender, String fitness_level) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.USER_EMAIL, user_email);
        contentValues.put(DatabaseHelper.USER_NAME, user_name);
        contentValues.put(DatabaseHelper.BIRTHDAY, birthday);
        contentValues.put(DatabaseHelper.WEIGHT, weight);
        contentValues.put(DatabaseHelper.HEIGHT, height);
        contentValues.put(DatabaseHelper.GENDER, gender);
        contentValues.put(DatabaseHelper.FITNESS_LEVEL, fitness_level);

        String whereClause = DatabaseHelper.USER_EMAIL + "=?";
        String[] whereArgs = { user_email };

        int ret = database.update(DatabaseHelper.DATABASE_TABLE, contentValues, whereClause, whereArgs);
        return ret;
    }
}
