package in.teachcoder.journalappfinal;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.detail_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.share_entry_menu){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT, "Highlight of my day was "+ title.getText());
            intent.setType("text/plain");
            startActivity(intent);
            return true;
        }else if(id == R.id.edit_entry_menu){
            Intent i = new Intent(DetailActivity.this, EntryActivity.class);
            i.putExtra("coming_from","DetailActivity");
            i.putExtra("clicked_item", position);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}
