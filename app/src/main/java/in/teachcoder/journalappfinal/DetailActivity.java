package in.teachcoder.journalappfinal;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import in.teachcoder.journalappfinal.data.Constants;

public class DetailActivity extends AppCompatActivity {
    TextView title, mood, content, date;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        title = (TextView) findViewById(R.id.entry_title);
        mood = (TextView) findViewById(R.id.entry_mood);
        content = (TextView) findViewById(R.id.entry_content);
        date = (TextView) findViewById(R.id.entry_date);

        position = getIntent().getIntExtra("clicked_item", 0);
        SimpleCursorAdapter adapter = (SimpleCursorAdapter) MainActivity.entriesList.getAdapter();
        Cursor myCursor = adapter.getCursor();

        myCursor.moveToPosition(position);

        title.setText(myCursor.getString(myCursor.getColumnIndexOrThrow(Constants.HIGHLIGHT_NAME)));
        mood.setText(myCursor.getString(myCursor.getColumnIndexOrThrow(Constants.MOOD_NAME)));
        content.setText(myCursor.getString(myCursor.getColumnIndexOrThrow(Constants.CONTENT_NAME)));
        date.setText(myCursor.getString(myCursor.getColumnIndexOrThrow(Constants.DATE_NAME)));
    }
}
