package com.example.fitlifepro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ReminderHelper extends SQLiteOpenHelper
{
    private SQLiteDatabase db;
    public static final String DATABASE_NAME = "ReminderDB.db";
    public static final int DATABASE_VERSION = 1;
    public static final String TABLE_NAME = "reminderdb";
    public static final String _ID = "_id";
    public static final String COLUMN_DATE = "date";
    public static final String COLUMN_MEMO = "memo";

    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE " +
            TABLE_NAME + "(" + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            COLUMN_DATE + " TEXT," +
            COLUMN_MEMO + " TEXT)";

    private static final String SQL_DELETE_ENTRIES = "DROP TABLE IF EXISTS " + TABLE_NAME;

    // Constructor
    public ReminderHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        // this.db = db;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_ENTRIES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    public void insertDate(String myDate, String myMemo){
        db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_DATE, myDate);
        values.put(COLUMN_MEMO, myMemo);
        db.insert(TABLE_NAME, null, values);
    }

    public List<Reminder> loadReminders(){
        List<Reminder> reminderList = new ArrayList<>();
        db = this.getReadableDatabase();
        String[] columns = {COLUMN_DATE, COLUMN_MEMO};
        Cursor cursor = db.query(TABLE_NAME, columns,
                null, null, null, null, null);
        cursor.moveToFirst();
        for(int i = 0; i < cursor.getCount(); i++){
            Reminder eachReminder = new Reminder(cursor.getString(0), cursor.getString(1));
            reminderList.add(eachReminder);
            cursor.moveToNext();
        }
        cursor.close();
        return reminderList;
    }

    public void deleteReminder(String myMemo){
        db = this.getWritableDatabase();
        String where = COLUMN_MEMO + " LIKE ?";
        String[] columns = {myMemo};
        db.delete(TABLE_NAME, where, columns);
        db.close();
    }
}
