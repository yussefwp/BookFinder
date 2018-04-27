package group151.bookfinder;
/**
 * Created by Anh on 3/22/2017.
 */
import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;


public class RequestCancel_Refund extends AppCompatActivity {

    private Spinner spinner1;
    private Button btnSubmit;
    private String reason;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_refund);

        addListenerOnButton();
        addListenerOnSpinnerItemSelection();
    }

    // add items into spinner dynamically
    public void addItemsOnSpinner2() {

        List<String> list = new ArrayList<String>();
        list.add("list 1");
        list.add("list 2");
        list.add("list 3");
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }

    public void addListenerOnSpinnerItemSelection() {
        spinner1 = (Spinner) findViewById(R.id.spinner1);
        spinner1.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }

    // get the selected dropdown list value
    public void addListenerOnButton() {

        spinner1 = (Spinner) findViewById(R.id.spinner1);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
        reason = (String)spinner1.getSelectedItem();
                Response.Listener <String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean success = jsonResponse.getBoolean("success");
                            if (success) {

                            } else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(RequestCancel_Refund.this);
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
                CancelOrderRequest cor = new CancelOrderRequest(InflatingPurchase.Reason_Post_Id,InflatingPurchase.Reason_Seller_Id, LoginActivity.username_login,InflatingPurchase.Reason_Bname,InflatingPurchase.Reason_Isbn, InflatingPurchase.Reason_Delivery_status,InflatingPurchase.Reason_Price,InflatingPurchase.Reason_Condition, InflatingPurchase.Reason_Author,responseListener);
                RequestQueue queue = Volley.newRequestQueue(RequestCancel_Refund.this);
                queue.add(cor);
                Toast.makeText(RequestCancel_Refund.this,
                        "Order Cancelled!! Reason: " +reason, Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RequestCancel_Refund.this, purchaseHistory.class));


            }

        });
    }

    private class CustomOnItemSelectedListener implements android.widget.AdapterView.OnItemSelectedListener {
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            reason =  parent.getItemAtPosition(pos).toString();

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }
    }
}