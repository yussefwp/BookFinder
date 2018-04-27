package group151.bookfinder;
/**
 * Created by Anh on 4/18/2017.
 */

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import static group151.bookfinder.R.id.textViewAuthor;
import static group151.bookfinder.R.id.textViewBname;
import static group151.bookfinder.R.id.textViewCondition;
import static group151.bookfinder.R.id.textViewPrice;

public class Transaction extends AppCompatActivity {
    private Spinner spinnerXX;
    private Button btnSubmit;
    public String choicepicked;
    private TextView bookname;
    private TextView condition;
    private TextView author;
    private TextView price;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        bookname = (TextView) findViewById(textViewBname);
        bookname.setText(InflatingLayout.bookname);

        condition = (TextView) findViewById(textViewCondition);
        condition.setText(InflatingLayout.condition);

        author = (TextView) findViewById(textViewAuthor);
        author.setText(InflatingLayout.author);

        price = (TextView) findViewById(textViewPrice);
        price.setText(InflatingLayout.price);

        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
    }

    public void addListenerOnSpinnerItemSelection() {
        spinnerXX = (Spinner) findViewById(R.id.spinnerXX);
        spinnerXX.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    public void addListenerOnButton() {

        spinnerXX = (Spinner) findViewById(R.id.spinnerXX);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choicepicked = String.valueOf(spinnerXX.getSelectedItem());

                if (choicepicked.equalsIgnoreCase("Yes")) {
                    Response.Listener<String> responseListener = new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {

                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                               int cred = jsonResponse.getInt("credit");
                                if (success) {
                                    Toast.makeText(Transaction.this, "Credit Now is : " + cred, Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(Transaction.this, Account.class);
                                    Transaction.this.startActivity(intent);
                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Transaction.this);
                                    builder.setMessage("Error Alert")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };

                    Response.Listener<String> responseListener2 = new Response.Listener<String>() {
                        public void onResponse(String response) {
                            try {
                                JSONObject jsonResponse = new JSONObject(response);
                                boolean success = jsonResponse.getBoolean("success");
                                if (success) {

                                } else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(Transaction.this);
                                    builder.setMessage("Error Alert")
                                            .setNegativeButton("Retry", null)
                                            .create()
                                            .show();
                                }

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    };


                    CreditUpdateRequest cur = new CreditUpdateRequest(LoginActivity.username_login, Integer.parseInt(InflatingLayout.price.substring(1)), responseListener);
                    PurchaseUpdateRequest pur = new PurchaseUpdateRequest(InflatingLayout.Post_ID, InflatingLayout.Seller_ID, LoginActivity.username_login, InflatingLayout.bookname, InflatingLayout.isbn, "InProgress", InflatingLayout.price, InflatingLayout.condition, InflatingLayout.author, responseListener2);
                    RequestQueue queue = Volley.newRequestQueue(Transaction.this);
                    queue.add(pur);
                    queue.add(cur);

//                    RequestQueue queue2 = Volley.newRequestQueue(Transaction.this);


                } else {
                    Intent intent = new Intent(Transaction.this, Account.class);
                    Transaction.this.startActivity(intent);
                }

            }
        });
    }


    private class CustomOnItemSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            Toast.makeText(parent.getContext(),
                    "User chose : " + parent.getItemAtPosition(pos).toString(),
                    Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }
    }
}



