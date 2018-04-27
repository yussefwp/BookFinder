package group151.bookfinder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import com.android.volley.Response;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import static group151.bookfinder.R.id.buttonGet;


public class Search extends AppCompatActivity implements View.OnClickListener {

    public static final String JSON_URL = "http://proj-309-dk-3.cs.iastate.edu/ecpos.php";
   private Button buttonGet;
    private ListView listView;
    private EditText etTitleSearch;
    public static String SearchString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        etTitleSearch= (EditText)findViewById(R.id.etTitleSearch);
        buttonGet = (Button) findViewById(R.id.buttonGet);
        buttonGet.setOnClickListener(this);
        listView = (ListView) findViewById(R.id.listView);
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
                        Toast.makeText(Search.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

        );

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    private void showJSON(String json){
        ParseJSON pj = new ParseJSON(json);
        pj.parseJSON();
        //now, retrieving all info from database in JSON format and pass them to InflatingLayout Object
        InflatingLayout ma = new InflatingLayout(this, pj.Post_Id, pj.Seller_Id, pj.Title, pj.Bname, pj.Isbn, pj.Author, pj.Condition, pj.Price);



        listView.setAdapter(ma);

    }

    @Override

    public void onClick(View v) {
        String input = etTitleSearch.getText().toString();
                SearchString = input;
                if (!input.equals("")){
                    sendRequest();

                }
                else
                    Toast.makeText(getBaseContext(), "noInput", Toast.LENGTH_LONG).show();




    }

}
