package android.course.prefererences;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements LoginFragment.OnLoginListener, TextWatcher {
    FloatingActionButton fab;
    Toolbar toolbar;
    EditText etNote;
    SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        etNote = (EditText) findViewById(R.id.etNote);
        etNote.addTextChangedListener(this);
        prefs = getSharedPreferences("notes", MODE_PRIVATE);
        setSupportActionBar(toolbar);

        load();
            }

    private void load() {
        String note = prefs.getString("Note", "");
        etNote.setText(note);
    }

    private void save(){
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("Note", etNote.getText().toString());
        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        switch (id) {
            case R.id.action_settings:
                return true;
            case R.id.action_login:
                new LoginFragment().show(getSupportFragmentManager(), "login");
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onLogin(String name, boolean isLoggedIn) {
        if (isLoggedIn) {
            Snackbar.make(fab, name + " Logged in", Snackbar.LENGTH_INDEFINITE)
                    .setAction("Ok!", new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
        }

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }


    @Override
    public void afterTextChanged(Editable s) {

    }
}
