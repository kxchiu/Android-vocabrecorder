package edu.uw.cwc8.vocabrecorder;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.util.Log;

import java.sql.SQLDataException;

/**
 * Created by Kevin on 2016/6/16.
 */
public final class WordDatabase {
    private static final String TAG ="****WordDatabase****";

    // Empty constructor so no new database will be created
    public WordDatabase(){}

    public static abstract class WordEntry implements BaseColumns {
        //_ID = "_id"
        public static final String TABLE_NAME = "vocab";
        public static final String COL_WORD = "word";
        public static final String COL_TYPE1 = "type1";
        public static final String COL_DEF1 = "definition1";
        public static final String COL_SYN1 = "synonym1";
        public static final String COL_TYPE2 = "type2";
        public static final String COL_DEF2 = "definition2";
        public static final String COL_SYN2 = "synonym2";
        public static final String COL_TIMESTAMP = "timestamp";
    }

    //constant String for creating the milktea table
    public static final String CREATE_WORD_TABLE =
            "CREATE TABLE " + WordEntry.TABLE_NAME + "(" +
                    WordEntry._ID + " INTEGER PRIMARY KEY" + "," +
                    WordEntry.COL_WORD + " TEXT" + "," +
                    WordEntry.COL_TYPE1 + " TEXT" + "," +
                    WordEntry.COL_DEF1 + "  TEXT" + "," +
                    WordEntry.COL_SYN1 + " TEXT" + "," +
                    WordEntry.COL_TYPE2 + " TEXT" + "," +
                    WordEntry.COL_DEF2 + "  TEXT" + "," +
                    WordEntry.COL_SYN2 + " TEXT" + "," +
                    WordEntry.COL_TIMESTAMP + " TEXT" +
                    ")";

    //constant String for dropping the milktea table
    public static final String DROP_FAVORITE_TABLE = "DROP TABLE IF EXISTS " + WordEntry.TABLE_NAME;

    //constant String for deleting a word
    public static final String DELETE_A_WORD = "DELETE FROM " + WordEntry.TABLE_NAME + " WHERE word='";

    // helper class
    public static class Helper extends SQLiteOpenHelper {
        private static Helper instance;

        public static final String DATABASE_NAME = "word.db";
        public static final int DATABASE_VERSION = 1;

        public Helper(Context context){
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        //singleton constructor!
        public static synchronized Helper getHelper(Context context){
            if(instance != null){
                instance = new Helper(context);
            }
            return instance;
        }

        @Override
        public void onCreate(SQLiteDatabase db){
            Log.v(TAG, "Creating a vocab word table");
            db.execSQL(CREATE_WORD_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
            db.execSQL(DROP_FAVORITE_TABLE);
            onCreate(db);
        }
    }

    // add a word to the database
    public static void addToDatabase(Context context, String word, String type1, String def1, String syn1,
                                     String type2, String def2, String syn2, String tStamp) {
        Log.v("AddToDatabase", "Adding...");
        Helper helper = new Helper(context);

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(WordEntry.COL_WORD, word);
        contentValues.put(WordEntry.COL_TYPE1, type1);
        contentValues.put(WordEntry.COL_DEF1, def1);
        contentValues.put(WordEntry.COL_SYN1, syn1);
        contentValues.put(WordEntry.COL_TYPE2, type2);
        contentValues.put(WordEntry.COL_DEF2, def2);
        contentValues.put(WordEntry.COL_SYN2, syn2);
        contentValues.put(WordEntry.COL_TIMESTAMP, tStamp);
        try {
            long newRowId = db.insert(WordEntry.TABLE_NAME, null, contentValues);
            Log.v("Database", "" + newRowId);
        } catch (SQLiteConstraintException e) {
            Log.v("Database", "Unexpected SQLite error occurred: " + e);
        }
    }

    public static void updateDatabase(Context context, String word, String type1, String def1, String syn1,
                                      String type2, String def2, String syn2, String tStamp, int id){
        Helper helper = new Helper(context);

        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(WordEntry.COL_WORD, word);
        contentValues.put(WordEntry.COL_TYPE1, type1);
        contentValues.put(WordEntry.COL_DEF1, def1);
        contentValues.put(WordEntry.COL_SYN1, syn1);
        contentValues.put(WordEntry.COL_TYPE2, type2);
        contentValues.put(WordEntry.COL_DEF2, def2);
        contentValues.put(WordEntry.COL_SYN2, syn2);
        contentValues.put(WordEntry.COL_TIMESTAMP, tStamp);
        deleteFromDatabase(context, word);
        try {
            long newRowId = db.insert(WordEntry.TABLE_NAME, null, contentValues);
        } catch (SQLiteConstraintException e){}
    }

    // delete the selected word from the database
    public static void deleteFromDatabase(Context context, String word){
        Log.v("DeleteFromDatabase", "Deleting...");
        Helper helper = new Helper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        db.execSQL(DELETE_A_WORD + word + "';");
    }

    // query the records from the database
    public static Cursor queryDatabase(Context context) {
        Log.v("QueryDatabase", "Query from database");
        Helper helper = new Helper(context);
        SQLiteDatabase db = helper.getWritableDatabase();

        String descOrder = WordEntry._ID + " DESC";

        Log.v("***QueryDatabase***", "" + WordEntry.COL_WORD);

        String[] cols = new String[]{
                WordEntry._ID,
                WordEntry.COL_WORD,
                WordEntry.COL_TYPE1,
                WordEntry.COL_DEF1,
                WordEntry.COL_SYN1,
                WordEntry.COL_TYPE2,
                WordEntry.COL_DEF2,
                WordEntry.COL_SYN2,
                WordEntry.COL_TIMESTAMP
        };
        Cursor results = db.query(WordEntry.TABLE_NAME, cols, null, null, null, null, descOrder, null);

        return results;
    }
}
