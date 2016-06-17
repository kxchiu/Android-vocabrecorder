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
        public static final String COL_TITLE = "title";
        public static final String COL_WORD = "word";
        public static final String COL_TYPE1 = "type1";
        public static final String COL_DEF1 = "definition1";
        public static final String COL_SYN1 = "synonym1";
        public static final String COL_TYPE2 = "type2";
        public static final String COL_DEF2 = "definition2";
        public static final String COL_SYN2 = "synonym2";
        public static final String COL_TIMESTAMP = "timestamp";
    }
}
