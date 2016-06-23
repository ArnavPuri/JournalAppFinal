package in.teachcoder.journalappfinal;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.util.Calendar;

import in.teachcoder.journalappfinal.data.Constants;
import in.teachcoder.journalappfinal.data.DBMethods;

public class MainActivity extends AppCompatActivity {
    ListView entriesList;
    DBMethods myDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        entriesList = (ListView) findViewById(R.id.entries_list);
        myDB = new DBMethods(this);
        setReminder();
        displayListView();


    }

    public void displayListView() {
        myDB.open();
        Cursor cursor = myDB.getEntries();
        String[] from = new String[]{
                Constants.HIGHLIGHT_NAME,
                Constants.DATE_NAME,
                Constants.CONTENT_NAME
        };

        int[] to = new int[]{
                R.id.entry_title,
                R.id.entry_date,
                R.id.entry_content
        };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(MainActivity.this, R.layout.row_layout,
                cursor, from, to, 0);
        entriesList.setAdapter(adapter);
        myDB.close();
    }

    public void setReminder() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 12);
        calendar.set(Calendar.MINUTE, 42);
        calendar.set(Calendar.SECOND, 0);

        Intent notificationMessage = new Intent(this, NotificationHelper.class);

//This is alarm manager
        PendingIntent pi = PendingIntent.getBroadcast(this, 0,
                notificationMessage, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) getSystemService(ALARM_SERVICE);
        am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pi);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                AlarmManager.INTERVAL_DAY, pi);
    }
}
