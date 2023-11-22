package com.example.fitlifepro;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "FITLIFEPRO.DB";
    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_TABLE = "USERS";
    public static final String USER_ID = "_ID";
    public static final String USER_NAME = "user_name";
    public static final String USER_EMAIL = "user_email";
    public static final String BIRTHDAY = "birthday";
    public static final String HEIGHT = "height";
    public static final String WEIGHT = "weight";
    public static final String GENDER = "gender";
    public static final String FITNESS_LEVEL = "fitness_level";

    private static final String CREATE_DB_QUERY = "CREATE TABLE " + DATABASE_TABLE + " ( " +
            USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USER_EMAIL + " TEXT NOT NULL, " +
            USER_NAME + " TEXT NOT NULL, " +
            BIRTHDAY + " DATE NOT NULL, " +
            HEIGHT + " DECIMAL(10,1) NOT NULL, " +
            WEIGHT + " DECIMAL(10,1) NOT NULL, " +
            GENDER + " TEXT NOT NULL, " +
            FITNESS_LEVEL + " TEXT NOT NULL);";

    public DatabaseHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_DB_QUERY);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
    }
}
