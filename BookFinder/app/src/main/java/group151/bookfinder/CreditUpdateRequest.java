package group151.bookfinder;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anh on 3/23/2017.
 */

public class CreditUpdateRequest extends StringRequest {
        private static final String LOGIN_REQUEST_URL =  "http://proj-309-dk-3.cs.iastate.edu/CreditUpdateRequest.php";
        private Map<String, String> params;

    public CreditUpdateRequest(String username, int subtractMoney, Response.Listener<String> listener) {
        super(Method.POST, LOGIN_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("username", username);
        params.put("subtractValue", Integer.toString(subtractMoney));
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
