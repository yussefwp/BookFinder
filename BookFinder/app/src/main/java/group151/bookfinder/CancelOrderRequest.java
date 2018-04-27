package group151.bookfinder;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anh on 4/27/2017.
 */

public class CancelOrderRequest extends StringRequest {
    private static final String Purchase_request_URL =  "http://proj-309-dk-3.cs.iastate.edu/CancelOrderRequest.php";
    private Map<String, String> params;

    public CancelOrderRequest(String Post_ID, String seller_ID, String buyer_username, String bookname, String bookisbn, String Delivery_status, String Price, String Condition, String Author,  Response.Listener<String> listener) {
        super(Method.POST, Purchase_request_URL, listener, null);
        params = new HashMap<>();
        params.put("Post_ID", Post_ID);
        params.put("seller_ID", seller_ID);
        params.put("buyer_username", buyer_username);
        params.put("bookname", bookname);
        params.put("bookisbn", bookisbn);
        params.put("Delivery_status", Delivery_status);
        params.put("Price", Price);
        params.put("Condition", Condition);
        params.put("Author", Author);

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}

