package group151.bookfinder;
/**
 * Created by Anh on 3/12/2017.
 */
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class OrderUpdate extends AppCompatActivity {
private Button b_MailingAddress;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_update);

        final Button b_rateSeller = (Button) findViewById(R.id.b_rateSeller);
        final Button b_trackPkg = (Button) findViewById(R.id.b_trackPkg);
        b_MailingAddress = (Button) findViewById(R.id.b_Add_Update_Mailing_Address);

        b_rateSeller.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(OrderUpdate.this, RateSeller.class);
                OrderUpdate.this.startActivity(intent);
            }
        });
        b_trackPkg.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(OrderUpdate.this, TrackingActivity.class);
                OrderUpdate.this.startActivity(intent);
            }
        });
        b_MailingAddress.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(OrderUpdate.this, MailingAddress.class);
                OrderUpdate.this.startActivity(intent);
            }
        });


    }
}
