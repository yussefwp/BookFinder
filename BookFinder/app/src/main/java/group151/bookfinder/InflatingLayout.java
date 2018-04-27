package group151.bookfinder;

import android.content.Intent;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


public class InflatingLayout extends ArrayAdapter<String> {
    public static String bookname;
    public static String condition;
    public static String author;
    public static String Post_ID;
    public static String Seller_ID;
    public static String title;
    public static String isbn;
    public static String price;
    private Button BuyThisBook;
    private static String[] Post_Id;
    private static String[] Seller_Id;
    private static String[] Title;
    private static String[] Bname;
    private static String[] Isbn;
    private static String[] Author;
    private static String[] Condition;
    private static String[] Price;
    private Activity context;

    public InflatingLayout(Activity context, String[] Post_Id, String[] Seller_Id, String[] Title, String[] Bname, String[] Isbn, String[] Author, String[] Condition, String[] Price) {
        super(context, R.layout.activity_inflating_layout, Post_Id);
        this.context = context;
        this.Post_Id = Post_Id;
        this.Seller_Id = Seller_Id;
        this.Title = Title;
        this.Bname = Bname;
        this.Isbn = Isbn;
        this.Author = Author;
        this.Condition = Condition;
        this.Price = Price;
    }

    @Override

    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_inflating_layout, null, true);
       // String positionNumber = getItem(position);

        TextView textViewCondition = (TextView) listViewItem.findViewById(R.id.textViewConditions);
        TextView textViewBname = (TextView) listViewItem.findViewById(R.id.textViewBname);
        TextView textViewAuthor = (TextView) listViewItem.findViewById(R.id.textViewAuthor);
        TextView textViewPrice = (TextView) listViewItem.findViewById(R.id.textViewPrice);
        BuyThisBook = (Button)listViewItem.findViewById(R.id.button_buyBook);

        //display Condition, Bkname, Author, and price to UI
        textViewCondition.setText(Condition[position]);
        textViewBname.setText(Bname[position]);
        textViewAuthor.setText(Author[position]);
        textViewPrice.setText(Price[position]);
        BuyThisBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent movetoTransaction = new Intent(getContext(), Transaction.class);
                bookname = Bname[position];
                condition= Condition[position];
                author= Author[position];
                price = Price[position];
                Post_ID = Post_Id[position];
                Seller_ID = Seller_Id[position];
                isbn = Isbn[position];
                author= Author[position];

                getContext().startActivity(movetoTransaction);
            }
        });



        return listViewItem;
    }




    }