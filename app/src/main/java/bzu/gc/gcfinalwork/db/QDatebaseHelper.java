package bzu.gc.gcfinalwork.db;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class QDatebaseHelper extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "questbox.db";
    public static final String TABLE_NAME = "question";

    public QDatebaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    public QDatebaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuffer buffer = new StringBuffer();
        buffer.append("CREATE TABLE [" + TABLE_NAME + "](");
        buffer.append("[id] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,");
        buffer.append("[question] TEXT NOT NULL,");
        buffer.append("[answer] TEXT NOT NULL,");
        buffer.append("[item1] TEXT,");
        buffer.append("[item2] TEXT,");
        buffer.append("[item3] TEXT,");
        buffer.append("[item4] TEXT,");
        buffer.append("[explains] TEXT,");
        buffer.append("[url] TEXT,");
        buffer.append("[username] TEXT,");
        buffer.append("[collect] TEXT)");
        db.execSQL(buffer.toString());
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

}
