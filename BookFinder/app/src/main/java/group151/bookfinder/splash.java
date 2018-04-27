package group151.bookfinder;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;


public class splash extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

                /*
        Create a splash for 4s. This time to initialize
        the background process
         */
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            /*
            Every activity in side Run will execute after 4000ms
             */
            @Override
            public void run() {
                startActivity(new Intent(splash.this,LoginActivity.class));
                finish();
            }
        }, 4000);

    }
}
