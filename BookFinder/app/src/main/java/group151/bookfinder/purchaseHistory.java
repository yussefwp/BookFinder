package group151.bookfinder;
/**
 * Created by Anh on 4/15/2017.
 */

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

import static group151.bookfinder.ParseJSON.KEY_Author;
import static group151.bookfinder.ParseJSON.KEY_Bname;
import static group151.bookfinder.ParseJSON.KEY_Condition;
import static group151.bookfinder.ParseJSON.KEY_Isbn;
import static group151.bookfinder.ParseJSON.KEY_Post_Id;
import static group151.bookfinder.ParseJSON.KEY_Price;
import static group151.bookfinder.ParseJSON.KEY_Seller_Id;
import static group151.bookfinder.ParseJSON.KEY_Title;
import static group151.bookfinder.ParseJSON.Title;

public class purchaseHistory extends AppCompatActivity {

    public static final String JSON_URL = "http://proj-309-dk-3.cs.iastate.edu/purchaseHistory.php";
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);
        listView = (ListView) findViewById(R.id.listView_purchased);
        sendRequest();
    }

    private void sendRequest() {
        StringRequest stringRequest = new StringRequest(JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        showJSON(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(purchaseHistory.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void showJSON(String json) {
        JSON_Processed jp = new JSON_Processed(json);
        jp.parseJSON();
        InflatingPurchase ip = new InflatingPurchase(this, jp.DateTime,jp.Delivery_status, jp.Post_Id, jp.Seller_Id, jp.username, jp.Bname, jp.Isbn, jp.Author, jp.Condition, jp.Price);
        listView.setAdapter(ip);

    }

    class JSON_Processed {
        private String json;
        private String [] DateTime;
        private String[] Post_Id;
        private String[] Seller_Id;
        private String[] Bname;
        private String[] Isbn;
        private String[] Author;
        private String[] Condition;
        private String[] Price;
        private String[] Delivery_status;
        private String[] username;
        private JSONArray users;

        public JSON_Processed(String json) {
            this.json = json;
        }

        protected void parseJSON() {
            try {
                users = new JSONArray(json);
                this.Post_Id = new String[users.length()];
                this.Seller_Id = new String[users.length()];
                this.Bname = new String[users.length()];
                this.Isbn = new String[users.length()];
                this.Author = new String[users.length()];
                this.Condition = new String[users.length()];
                this.Price = new String[users.length()];
                this.DateTime = new String [users.length()];
                this.Delivery_status = new String[users.length()];

                ArrayList<String> username_AL = new ArrayList<String>();
                ArrayList<String> Post_Id_AL = new ArrayList<String>();
                ArrayList<String> Seller_Id_AL = new ArrayList<String>();
                ArrayList<String> Bname_AL = new ArrayList<String>();
                ArrayList<String> Isbn_AL = new ArrayList<String>();
                ArrayList<String> Author_AL = new ArrayList<String>();
                ArrayList<String> Condition_AL = new ArrayList<String>();
                ArrayList<String> Price_AL = new ArrayList<String>();
                ArrayList<String> DeliveryStatus_AL = new ArrayList<String>();
                ArrayList<String> DateTime_AL = new ArrayList<String>();

                int count = 0;
                for (int i = 0; i < users.length(); i++) {


                    JSONObject jo = users.getJSONObject(i);
                    if (LoginActivity.username_login.equalsIgnoreCase(jo.getString("buyer_username"))) {
                        DateTime_AL.add(jo.getString("DateTime"));
                        username_AL.add(jo.getString("buyer_username"));
                        Post_Id_AL.add(jo.getString("Post_ID"));
                        Seller_Id_AL.add(jo.getString("seller_ID"));
                        Bname_AL.add(jo.getString("bookname"));
                        Isbn_AL.add(jo.getString("bookisbn"));
                        Author_AL.add(jo.getString("Author"));
                        Condition_AL.add(jo.getString("Condition"));
                        Price_AL.add(jo.getString("Price"));
                        DeliveryStatus_AL.add(jo.getString("Delivery_status"));
                        count++;
                    }
                }
                DateTime = new String[DateTime_AL.size()];
                DateTime = DateTime_AL.toArray(DateTime);

                Post_Id = new String[Post_Id_AL.size()];
                Post_Id = Post_Id_AL.toArray(Post_Id);

                Seller_Id = new String[Seller_Id_AL.size()];
                Seller_Id = Seller_Id_AL.toArray(Seller_Id);

                Bname = new String[Bname_AL.size()];
                Bname = Bname_AL.toArray(Bname);

                Isbn = new String[Isbn_AL.size()];
                Isbn = Isbn_AL.toArray(Isbn);

                Author = new String[Author_AL.size()];
                Author = Author_AL.toArray(Author);

                Condition = new String[Condition_AL.size()];
                Condition = Condition_AL.toArray(Condition);

                Price = new String[Price_AL.size()];
                Price = Price_AL.toArray(Price);

                Delivery_status = new String[DeliveryStatus_AL.size()];
                Delivery_status = DeliveryStatus_AL.toArray(Delivery_status);

                username = new String[username_AL.size()];
                username = username_AL.toArray(username);


            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

/*          REFUND/RETURN
        final Button b_refund = (Button) findViewById(R.id.b_refund);
        b_refund.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(purchaseHistory.this, RequestRefund.class);
                startActivity(intent);
            }
        });
*/


}

