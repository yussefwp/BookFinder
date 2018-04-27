package group151.bookfinder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

import static android.R.attr.rating;

public class RateSeller extends AppCompatActivity {
private RatingBar rb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_seller);

        rb = (RatingBar)findViewById(R.id.ratingBar_seller);
        rb.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(getBaseContext(), "You gave the seller "+ rb.getRating(), Toast.LENGTH_SHORT).show();            }
        });

    }
}
