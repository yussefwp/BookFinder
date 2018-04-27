package group151.bookfinder;
/**
 * Created by Anh on 3/21/2017.
 */
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Account extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        final Button purchaseHistory = (Button) findViewById(R.id.b_purchaseHistory);
        final Button btn_WL = (Button) findViewById(R.id.b_WL);
        final Button btn_Balance = (Button) findViewById(R.id.b_Balance);
        final Button b_orderUpdate = (Button) findViewById(R.id.b_orderUpdate);
        TextView tv = (TextView)findViewById(R.id.myac);
        tv.setTextColor(Color.parseColor("#FFCC0000"));

        btn_WL.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Account.this, Wlist.class);
                Account.this.startActivity(intent);
            }
        });
        purchaseHistory.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Account.this, purchaseHistory.class);
                Account.this.startActivity(intent);
            }
        });
        btn_Balance.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Account.this, BalanceAccount.class);
                Account.this.startActivity(intent);
            }
        });
        b_orderUpdate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(Account.this, OrderUpdate.class);
                Account.this.startActivity(intent);
            }
        });

    }
}

