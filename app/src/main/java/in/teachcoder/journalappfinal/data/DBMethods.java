package in.teachcoder.journalappfinal.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by Arnav on 19-Jun-16.
 */
public class DBMethods {

    private SQLiteDatabase db;
    private DBHelper dbHelper;

    public DBMethods(Context c){
        dbHelper = new DBHelper(c);
    }

    public void open(){
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        db.close();
    }

    public long insertEntry(String highlight, String mood, String content, String category, String date){
        ContentValues cv = new ContentValues();
        cv.put(Constants.DATE_NAME, date);
        cv.put(Constants.HIGHLIGHT_NAME, highlight);
        cv.put(Constants.CONTENT_NAME,content);
        cv.put(Constants.MOOD_NAME,mood);
        cv.put(Constants.CATEGORY_NAME,category);
        return db.insert(Constants.TABLE_NAME, null, cv);
    }
}
