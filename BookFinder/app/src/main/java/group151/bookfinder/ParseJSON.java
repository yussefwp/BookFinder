package group151.bookfinder;

import org.json.JSONArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static group151.bookfinder.StringSimilarity.similarity;

public class ParseJSON {
    public static String[] Post_Id;
    public static String[] Seller_Id;
    public static String[] Title;
    public static String[] Bname;
    public static String[] Isbn;
    public static String[] Author;
    public static String[] Condition;
    public static String[] Price;

    public static final String KEY_Post_Id = "Post_Id";
    public static final String KEY_Seller_Id = "Seller_Id";
    public static final String KEY_Title = "Title";
    public static final String KEY_Bname = "Book_Name";
    public static final String KEY_Isbn = "ISBN";
    public static final String KEY_Author = "Author";
    public static final String KEY_Condition = "Condition";
    public static final String KEY_Price = "Price";

    private JSONArray users;
    private String json;

    public ParseJSON(String json) {
        this.json = json;
    }

    protected void parseJSON() {

        JSONObject jsonObject = null;

        try {

          //  jsonObject = new JSONArray(json).getJSONObject(0);
            // users = jsonObkect.getJSONArray(JSON_ARRAY);

            users = new JSONArray(json);

//           Post_Id = new String[users.length()];
//           Seller_Id = new String[users.length()];
//            Title = new String[users.length()];
//            Bname = new String[users.length()];
//            Isbn = new String[users.length()];
//            Author = new String[users.length()];
//            Condition = new String[users.length()];
//           Price = new String[users.length()];

            ArrayList<String> Title_AL = new ArrayList<String>();
            ArrayList<String> Post_Id_AL = new ArrayList<String>();
            ArrayList<String> Seller_Id_AL = new ArrayList<String>();
            ArrayList<String> Bname_AL = new ArrayList<String>();
            ArrayList<String> Isbn_AL = new ArrayList<String>();
            ArrayList<String> Author_AL = new ArrayList<String>();
            ArrayList<String> Condition_AL = new ArrayList<String>();
            ArrayList<String> Price_AL = new ArrayList<String>();


            int count = 0;
            for (int i = 0; i < users.length(); i++) {

                JSONObject jo = users.getJSONObject(i);

                if (StringSimilarity.similarity(jo.getString(KEY_Bname), Search.SearchString) >= 0.3) {
                    Title_AL.add(jo.getString(KEY_Title));
                    Post_Id_AL.add(jo.getString(KEY_Post_Id));
                    Seller_Id_AL.add(jo.getString(KEY_Seller_Id));
                    Bname_AL.add(jo.getString(KEY_Bname));
                    Isbn_AL.add(jo.getString(KEY_Isbn));
                    Author_AL.add(jo.getString(KEY_Author));
                    Condition_AL.add(jo.getString(KEY_Condition));
                    Price_AL.add(jo.getString(KEY_Price));
                    count++;
                }

            }
            Title = new String[Title_AL.size()];
            Title = Title_AL.toArray(Title);

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

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }


}