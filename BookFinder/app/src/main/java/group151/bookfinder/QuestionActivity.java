package group151.bookfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity {

    Float actual = 0.0f;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);
        Intent intent = getIntent();
        actual = intent.getFloatExtra("rating", 0);
    }

    //if they answered YES
    public void lessThan3(View view){
        if (actual < 3.0){
            Toast.makeText(getBaseContext(), "Correct!", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getBaseContext(), "Try again!", Toast.LENGTH_SHORT).show();
    }
    //if they answered NO
    public void moreThan3(View view){
        if (actual >= 3.0){
            Toast.makeText(getBaseContext(), "Correct!", Toast.LENGTH_SHORT).show();
        }
        else Toast.makeText(getBaseContext(), "Try again!", Toast.LENGTH_SHORT).show();
    }
}

