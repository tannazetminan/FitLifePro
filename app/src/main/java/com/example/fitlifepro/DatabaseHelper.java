package com.example.fitlifepro;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "FITLIFEPRO.DB";
    public static final int DATABASE_VERSION = 1;

    //variables for user database
    public static final String USER_DATABASE_TABLE = "USERS";
    public static final String USER_ID = "_ID";
    public static final String USER_NAME = "user_name";
    public static final String USER_EMAIL = "user_email";
    public static final String BIRTHDAY = "birthday";
    public static final String HEIGHT = "height";
    public static final String WEIGHT = "weight";
    public static final String GENDER = "gender";
    public static final String FITNESS_LEVEL = "fitness_level";

    //variables for workout plan database
    public static final String WP_DATABASE_TABLE = "WORKOUT_PLAN";
    public static final String PLAN_ID = "PLAN_ID";
    public static final String MONDAY = "monday";
    public static final String TUESDAY = "tuesday";
    public static final String WEDNESDAY = "wednesday";
    public static final String THURSDAY = "thursday";
    public static final String FRIDAY = "friday";
    public static final String SATURDAY = "saturday";
    public static final String SUNDAY = "sunday";
    public static final String START_DATE = "start_date";
    public static final String LENGTH_OF_PLAN = "length_of_plan";
    public static final String CHEST_ACTIVITY = "chest_activity";
    public static final String ABDOMINAL_ACTIVITY = "abdominal_activity";
    public static final String ARM_ACTIVITY = "arm_activity";
    public static final String LEG_ACTIVITY = "leg_activity";
    public static final String TOTAL_DAYS = "total_days";
    public static final String DONE_DAYS = "done_days";

    //variables for progress tracker database
    public static final String TRACKER_DATABASE_TABLE = "DAYS_TRACKER";

    public static final String DAY_ACTIVITY = "day_activity";
    public static final String DAY_OF_WEEK = "day_of_week";
    public static final String DAY_ID = "day_id";

    private static final String CREATE_USERTABLE_QUERY = "CREATE TABLE " + USER_DATABASE_TABLE + " ( " +
            USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            USER_EMAIL + " TEXT NOT NULL, " +
            USER_NAME + " TEXT NOT NULL, " +
            BIRTHDAY + " DATE NOT NULL, " +
            HEIGHT + " DECIMAL(10,1) NOT NULL, " +
            WEIGHT + " DECIMAL(10,1) NOT NULL, " +
            GENDER + " TEXT NOT NULL, " +
            FITNESS_LEVEL + " TEXT NOT NULL);";

    private static final String CREATE_WPDB_QUERY = "CREATE TABLE " + WP_DATABASE_TABLE + " ( " +
            PLAN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            START_DATE + " DATE NOT NULL, " +
            LENGTH_OF_PLAN + " INTEGER NOT NULL, " +
            MONDAY + " BOOLEAN NOT NULL, " +
            TUESDAY + " BOOLEAN NOT NULL, " +
            WEDNESDAY + " BOOLEAN NOT NULL, " +
            THURSDAY + " BOOLEAN NOT NULL, " +
            FRIDAY + " BOOLEAN NOT NULL, " +
            SATURDAY + " BOOLEAN NOT NULL, " +
            SUNDAY + " BOOLEAN NOT NULL, " +
            CHEST_ACTIVITY + " BOOLEAN NOT NULL, " +
            ABDOMINAL_ACTIVITY + " BOOLEAN NOT NULL, " +
            ARM_ACTIVITY + " BOOLEAN NOT NULL, " +
            LEG_ACTIVITY + " BOOLEAN NOT NULL, " +
            TOTAL_DAYS + " TEXT NOT NULL, " +
            DONE_DAYS + " TEXT NOT NULL);";

    private static final String CREATE_TRACKER_QUERY = "CREATE TABLE " + TRACKER_DATABASE_TABLE + " ( " +
            DAY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            DAY_OF_WEEK + " TEXT NOT NULL, " +
            DAY_ACTIVITY + " TEXT NOT NULL);";

    public DatabaseHelper(@Nullable Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(CREATE_USERTABLE_QUERY);
            db.execSQL(CREATE_WPDB_QUERY);
            db.execSQL(CREATE_TRACKER_QUERY);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + USER_DATABASE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + WP_DATABASE_TABLE);
        db.execSQL("DROP TABLE IF EXISTS " + TRACKER_DATABASE_TABLE);
    }
}
