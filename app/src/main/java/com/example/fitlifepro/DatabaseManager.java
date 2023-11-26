package com.example.fitlifepro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLDataException;

public class DatabaseManager {
    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase database;

    public DatabaseManager(Context ctx) {
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

    public void insertUser (String user_email, String user_name, String birthday, int height, int weight, String gender, String fitness_level) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.USER_EMAIL, user_email);
        contentValues.put(DatabaseHelper.USER_NAME, user_name);
        contentValues.put(DatabaseHelper.BIRTHDAY, birthday);
        contentValues.put(DatabaseHelper.HEIGHT, height);
        contentValues.put(DatabaseHelper.WEIGHT, weight);
        contentValues.put(DatabaseHelper.GENDER, gender);
        contentValues.put(DatabaseHelper.FITNESS_LEVEL, fitness_level);

        database.insert(DatabaseHelper.USER_DATABASE_TABLE, null, contentValues);
    }

    public void insertPlan (String start_date, int length_of_plan, Boolean monday, Boolean tuesday, Boolean wednesday, Boolean thursday, Boolean friday, Boolean saturday, Boolean sunday, Boolean chest_activity, boolean abdominal_activity, boolean arm_activity, boolean leg_activity) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.START_DATE, start_date);
        contentValues.put(DatabaseHelper.LENGTH_OF_PLAN, length_of_plan);
        contentValues.put(DatabaseHelper.MONDAY, monday);
        contentValues.put(DatabaseHelper.TUESDAY, tuesday);
        contentValues.put(DatabaseHelper.WEDNESDAY, wednesday);
        contentValues.put(DatabaseHelper.THURSDAY, thursday);
        contentValues.put(DatabaseHelper.FRIDAY, friday);
        contentValues.put(DatabaseHelper.SATURDAY, saturday);
        contentValues.put(DatabaseHelper.SUNDAY, sunday);
        contentValues.put(DatabaseHelper.CHEST_ACTIVITY, chest_activity);
        contentValues.put(DatabaseHelper.ABDOMINAL_ACTIVITY, abdominal_activity);
        contentValues.put(DatabaseHelper.ARM_ACTIVITY, arm_activity);
        contentValues.put(DatabaseHelper.LEG_ACTIVITY, leg_activity);

        database.insert(DatabaseHelper.WP_DATABASE_TABLE, null, contentValues);
    }

    public Cursor fetchUser() {
        String [] columns = new String[] {DatabaseHelper.USER_EMAIL, DatabaseHelper.USER_NAME, DatabaseHelper.BIRTHDAY, DatabaseHelper.HEIGHT, DatabaseHelper.WEIGHT, DatabaseHelper.GENDER, DatabaseHelper.FITNESS_LEVEL};
        Cursor cursor = database.query(DatabaseHelper.USER_DATABASE_TABLE, columns, null,null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public Cursor fetchPlan() {
        String [] columns = new String[] {DatabaseHelper.START_DATE,
                DatabaseHelper.LENGTH_OF_PLAN, DatabaseHelper.MONDAY,
                DatabaseHelper.TUESDAY, DatabaseHelper.WEDNESDAY,
                DatabaseHelper.THURSDAY, DatabaseHelper.FRIDAY,
                DatabaseHelper.SATURDAY, DatabaseHelper.SUNDAY,
                DatabaseHelper.CHEST_ACTIVITY, DatabaseHelper.ABDOMINAL_ACTIVITY,
                DatabaseHelper.ARM_ACTIVITY, DatabaseHelper.LEG_ACTIVITY
        };
        Cursor cursor = database.query(DatabaseHelper.WP_DATABASE_TABLE, columns, null,null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int updateUser(String user_email, String user_name, String birthday, int height, int weight, String gender, String fitness_level) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DatabaseHelper.USER_EMAIL, user_email);
        contentValues.put(DatabaseHelper.USER_NAME, user_name);
        contentValues.put(DatabaseHelper.BIRTHDAY, birthday);
        contentValues.put(DatabaseHelper.HEIGHT, height);
        contentValues.put(DatabaseHelper.WEIGHT, weight);
        contentValues.put(DatabaseHelper.GENDER, gender);
        contentValues.put(DatabaseHelper.FITNESS_LEVEL, fitness_level);

        String whereClause = DatabaseHelper.USER_ID + "= 1";

        int ret = database.update(DatabaseHelper.USER_DATABASE_TABLE, contentValues, whereClause, null);
        return ret;
    }

    public void deleteUser() {
        database.delete(DatabaseHelper.USER_DATABASE_TABLE, null, null);
    }

    public boolean hasData() {
        boolean hasData = false;
        Cursor cur = database.rawQuery("SELECT COUNT(*) FROM " + DatabaseHelper.USER_DATABASE_TABLE, null);
        if (cur != null) {
            cur.moveToFirst();                       // Always one row returned.
            if (cur.getInt (0) == 0) {               // Zero count means empty table.
                hasData = false;
            } else {hasData = true;}
        }

        return hasData;
    }
}

