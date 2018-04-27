package group151.bookfinder;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anh on 4/27/2017.
 */

public class MailingAddressRequest extends StringRequest {
    private static final String Mailing_REQUEST_URL = "http://proj-309-dk-3.cs.iastate.edu/mailing.php";
    private Map<String, String> params;

    public MailingAddressRequest(String username, String address, Response.Listener<String> listener) {
        super(Method.POST, Mailing_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("address", address);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}