package group151.bookfinder;
/**
 * Created by Anh on 3/20/2017.
 */
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

public class Wlist extends AppCompatActivity {
public ListView lview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wlist);

        lview = (ListView) findViewById(R.id.lView);
        lview.setAdapter(new CustomListAdapter(Wlist.this));
    }


}