package group151.bookfinder;
/**
 * Created by Anh on 2/15/2017.
 */
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anh on 2/20/2017.
 */
        import com.android.volley.Response;
        import com.android.volley.toolbox.StringRequest;

        import java.util.HashMap;
        import java.util.Map;

import static android.R.attr.password;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL = "http://proj-309-dk-3.cs.iastate.edu/register.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String school,  String username, String password, Response.Listener<String> listener) {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("name", name);
        params.put("school", school);
        params.put("username", username);
        params.put("password", password);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}