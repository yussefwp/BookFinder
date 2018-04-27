package group151.bookfinder;
/**
 * Created by Anh on 3/15/2017.
 */

import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

import static group151.bookfinder.R.id.BalanceFill;

public class BalanceAccount extends AppCompatActivity {
    public TextView BalanceFillET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance_account);
        BalanceFillET = (TextView) findViewById(BalanceFill);
        sendRequest();

    }

    private void sendRequest() {
        final Response.Listener<String> responseListener = new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    int cred = jsonResponse.getInt("credit");
                    boolean success = jsonResponse.getBoolean("success");
                    if (success) {
                        BalanceFillET.setText("$ " + cred);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(BalanceAccount.this);
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
        CheckBalanceRequest cbr = new CheckBalanceRequest(LoginActivity.username_login, responseListener);
        RequestQueue requestQueue = Volley.newRequestQueue(BalanceAccount.this);
        requestQueue.add(cbr);

    }

    class CheckBalanceRequest extends StringRequest {
        private static final String LOGIN_REQUEST_URL = "http://proj-309-dk-3.cs.iastate.edu/checkBalanceRequest.php";
        private Map<String, String> params;

        public CheckBalanceRequest(String username, Response.Listener<String> listener) {
            super(Method.POST, LOGIN_REQUEST_URL, listener, null);
            params = new HashMap<>();
            params.put("username", username);
        }

        @Override
        public Map<String, String> getParams() {
            return params;
        }
    }

}
