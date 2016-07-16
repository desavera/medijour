package com.crossover.mjreader;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;


import com.crossover.mjreader.model.Journals;

public class JournalsListingActivity extends AppCompatActivity implements JournalsListingActivityFragment.OnListFragmentInteractionListener {

    private String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_journals_listing);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

        //username = this.getIntent().getStringExtra(UsersService.USERNAME).toString();

        // I am the main
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_journals_listing, menu);



        return true;
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0)
        {
            this.moveTaskToBack(true);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                // User chose the "Settings" item, show the app settings UI...
                return true;

            case R.id.action_logout:

              //  UsersService.startActionLogout(this,username);
                return true;


            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    @Override
    public void onListFragmentInteraction(Journals journals) {

        /*Intent intent = new Intent(this, PaymentRequestService.class);
        intent.putExtra(UsersService.USERNAME,username);
        intent.putExtra(PaymentRequestService.PRODUCT_ID,journals.getId());
        startService(intent);*/
    }
}
