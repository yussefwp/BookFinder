package group151.bookfinder;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;


public class SellBookActivity extends AppCompatActivity {
    Button bSubmit;
    TextView textView_sell;
    EditText txPost_id, txSeller_id, txTitle, txBook_name, txAuthor, txISBN, txCondition, txPrice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell_book);

        Intent intent = getIntent();
        //byte[] imageByte = intent.getByteArrayExtra("image byte");
        //Bitmap imageBitmap = BitmapFactory.decodeByteArray(imageByte, 0, 0);
        Uri imageUri = intent.getParcelableExtra("image uri");
        //imagePath = imagePath.replace("content://", "");
        //File imgFile = new File(imagePath);
        //if (imgFile.exists()){
        ImageView selectedBook = (ImageView) findViewById(R.id.selectedBook);
        //Bitmap bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
        selectedBook.setImageURI(imageUri);
        //}
        txPost_id = (EditText) findViewById(R.id.sell_postId);
        txSeller_id = (EditText) findViewById(R.id.sell_sellerId);
        txTitle = (EditText) findViewById(R.id.sell_title);
        txBook_name = (EditText) findViewById(R.id.sell_book_name);
        txAuthor = (EditText) findViewById(R.id.sell_author);
        txISBN = (EditText) findViewById(R.id.sell_isbn);
        txCondition = (EditText) findViewById(R.id.sell_condition);
        txPrice = (EditText) findViewById(R.id.sell_price);
        bSubmit = (Button) findViewById(R.id.sell_Submit);
    }
    //Created by Kacao
    private void insertDataOnline() {
        final String post_id = txPost_id.getText().toString();
        final String seller_id = txSeller_id.getText().toString();
        final String title = txTitle.getText().toString();
        final String book_name = txBook_name.getText().toString();
        final String author = txAuthor.getText().toString();
        final String isbn = txISBN.getText().toString();
        final String condition = txCondition.getText().toString();
        final String price = txPrice.getText().toString();

        final String x_coor = "";           // CHANGE ME
        final String y_coor = "";           // CHANGE ME

        //final String imageString creation
        ImageView imageView = (ImageView) findViewById(R.id.selectedBook);
        imageView.buildDrawingCache();
        Bitmap bm = imageView.getDrawingCache();
        final String imageString = BitmapToString(bm);
        Log.d("D", "imageString is " + imageString);

        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response);
//                            if (!jsonResponse.getBoolean("success")) {
//
//                            } else {
//
//                            }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };
        sellRequest sellReq = new sellRequest(post_id, seller_id,
                title, book_name,
                author, isbn,
                condition, price,
                x_coor, y_coor,
                responseListener, null);
        RequestQueue queue = Volley.newRequestQueue(SellBookActivity.this);
        queue.add(sellReq);
    }

    public String BitmapToString(Bitmap bitmap){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.WEBP, (int)0.001, stream);
        byte [] byteArray = stream.toByteArray();
        String imageString = Base64.encodeToString(byteArray, 0);
        return imageString;
    }
    public Bitmap StringToBitmap(String string){
        try {
            byte[] eByte = Base64.decode(string, Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(eByte, 0, eByte.length);
            return bitmap;
        } catch(Exception e){
            e.getMessage();
            return null;
        }
    }

    /**
     * Sellrequest constructor to initialize an object for feeding the data into HashMap
     */
    private class sellRequest extends StringRequest{
        private static final String sell_book_url = "http://proj-309-dk-3.cs.iastate.edu/sell_insert.php";
        private Map<String, String> params;

        public sellRequest(String post_id, String seller_id,
                           String title, String Book_name,
                           String author, String isbn,
                           String condition, String price,
                           String x_coor, String y_coor,
                           Response.Listener<String> listener,
                           Response.ErrorListener errorListener) {


            super(Method.POST, sell_book_url, listener, null);
            params = new HashMap<>();
            params.put("post_id", post_id.toString());
            params.put("seller_id", seller_id);
            params.put("title", title);
            params.put("book_name", Book_name);
            params.put("author", author);
            params.put("isbn", isbn);
            params.put("condition", condition);
            params.put("price", price);
            params.put("x_coor", x_coor);
            params.put("y_coor", y_coor);
        }

        @Override
        public Map<String, String> getParams() {
            return params;
        }
    }

    /**
     * when Submit button is clicked,
     * gather all book info into a string array.
     * post string array to database.
     * return to main activity.
     */
    public void submitPost(View view){
        CheckBox location = (CheckBox) findViewById(R.id.location);
        if (location.isChecked()){
            Toast.makeText(getBaseContext(), "Your location is ", Toast.LENGTH_SHORT).show();
        }
        Intent intent = new Intent(this, MainActivity.class);
        insertDataOnline();
        Toast.makeText(getBaseContext(), "Your item is now published!", Toast.LENGTH_LONG).show();
        startActivity(intent);
    }


}