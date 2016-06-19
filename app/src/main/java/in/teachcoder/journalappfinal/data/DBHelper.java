package in.teachcoder.journalappfinal.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Arnav on 19-Jun-16.
 */
public class DBHelper extends SQLiteOpenHelper {


    private static final String CREATE_TABLE = "create table " +
            Constants.TABLE_NAME + " (" +
            Constants.KEY_ID + " integer primary key autoincrement, " +
            Constants.HIGHLIGHT_NAME + " text not null, " +
            Constants.DATE_NAME + " text not null, " +
            Constants.CONTENT_NAME + " text not null, " +
            Constants.CATEGORY_NAME + " text not null, " +
            Constants.MOOD_NAME + " text not null) ";

    public DBHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + Constants.TABLE_NAME);
        onCreate(db);
    }
}
