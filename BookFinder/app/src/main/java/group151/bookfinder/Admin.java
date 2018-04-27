package group151.bookfinder;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

public class Admin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    /**
     * Return True if the issue between customers has been resolved, false otherwise.
     * @param TransactionID
     * @return
     */
    boolean isProblemResolved (String TransactionID){
        return true;
    }

    /**
     * Give a user a ban for a certain number of days where they cannot use the app.
     * @param username
     * @param days
     */
    void setBan (String username, int days){

    }

    /**
     * Deletes a user’s account and prevent his email for being registered again
     * @param username
     * @return
     */
    void deleteAccount(String username){

    }

    /**
     * force cancellation of a transaction and returns money to buyer
     * @param TransactionID
     */
    void forceCancelTransacion(String TransactionID){

    }

    /**
     * force  a a post to be deleted
     * @param PostID
     */
    void deletePost(String PostID ){

    }

}
