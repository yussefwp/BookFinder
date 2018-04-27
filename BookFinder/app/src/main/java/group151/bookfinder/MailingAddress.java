package group151.bookfinder;

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static group151.bookfinder.R.id.bRegister;

public class MailingAddress extends AppCompatActivity {
    public static EditText address;
    public static String addressToDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mailing_address);
        Button submit = (Button) findViewById(R.id.submitAddress);
        address = (EditText) findViewById(R.id.MailingAddress);
        addressToDatabase = address.getText().toString();
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {
                                Intent intent = new Intent(MailingAddress.this, MainActivity.class);
                                MailingAddress.this.startActivity(intent);
                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(MailingAddress.this);
                                builder.setMessage("Register Failed")
                                        .setNegativeButton("Retry", null)
                                        .create()
                                        .show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                MailingAddressRequest mailingRequest = new MailingAddressRequest(LoginActivity.username_login, addressToDatabase, responseListener);
                RequestQueue queue = Volley.newRequestQueue(MailingAddress.this);
                queue.add(mailingRequest);

            }
        });
    }
}