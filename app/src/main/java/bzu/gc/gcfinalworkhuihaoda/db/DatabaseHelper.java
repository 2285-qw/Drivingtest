package bzu.gc.gcfinalworkhuihaoda.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "shuat.db";
    public static final String TABLE_NAME = "user";

    public DatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer buffer = new StringBuffer();

        buffer.append("CREATE TABLE [" + TABLE_NAME + "](");
        buffer.append("[id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,");
        buffer.append("[username] TEXT NOT NULL,");
        buffer.append("[password] TEXT NOT NULL,");
        buffer.append("[allnum] INTEGER,");
        buffer.append("[aimnum] INTEGER,");
        buffer.append("[errornum] INTEGER)");
        db.execSQL(buffer.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
