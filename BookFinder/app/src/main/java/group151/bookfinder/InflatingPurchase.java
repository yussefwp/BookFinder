package group151.bookfinder;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import static group151.bookfinder.R.id.textViewCondition;

public class InflatingPurchase   extends ArrayAdapter<String> {
    private static String[] Post_Id;
    private static String[] Seller_Id;
    private static String[] Bname;
    private static String[] Isbn;
    private static String[] Author;
    private static String[] Condition;
    private static String[] Price;
    private static String[] Delivery_status;
    private static String [] username;
    private static String[] dateTime;
    private Activity context;
    private Button ReturnBook;

    public static String Reason_Post_Id;
    public static String Reason_Seller_Id;
    public static String Reason_Bname;
    public static String Reason_Isbn;
    public static String Reason_Author;
    public static String Reason_Condition;
    public static String Reason_Price;
    public static String Reason_Delivery_status;
    public static String Reason_username;




    public InflatingPurchase(Activity context,String [] datetime, String[] Delivery_status, String[] Post_Id, String[] Seller_Id, String[] username, String[] Bname, String[] Isbn, String[] Author, String[] Condition, String[] Price) {
        super(context, R.layout.activity_inflating_purchase, Post_Id);
        this.context = context;
        this.Post_Id = Post_Id;
        this.Seller_Id = Seller_Id;
        this.Bname = Bname;
        this.Isbn = Isbn;
        this.Author = Author;
        this.Condition = Condition;
        this.Price = Price;
        this.Delivery_status = Delivery_status;
        this.username = username;
        this.dateTime= datetime;

    }

    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.activity_inflating_purchase, null, true);
      //  String positionNumber = getItem(position);
        ReturnBook = (Button)listViewItem.findViewById(R.id.button_returnRefund);
        ReturnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Reason_Post_Id = Post_Id[position];
                Reason_Seller_Id = Seller_Id[position];
                Reason_Bname = Bname[position];
                Reason_Isbn = Isbn[position];
                Reason_Author = Author[position];
                Reason_Condition = Condition[position];
                Reason_Price = Price[position];
                Reason_Delivery_status = Delivery_status[position];
                Reason_username = username[position];

                Intent moveToRefund = new Intent(getContext(), RequestCancel_Refund.class);
                getContext().startActivity(moveToRefund);
            }
        });


        TextView textViewCondition = (TextView) listViewItem.findViewById(R.id.textViewCondition_purchased);
        TextView textViewBname = (TextView) listViewItem.findViewById(R.id.textViewBname_purchased);
        TextView textViewAuthor = (TextView) listViewItem.findViewById(R.id.textViewAuthor_purchased);
        TextView textViewPrice = (TextView) listViewItem.findViewById(R.id.textViewPrice_purchased);
        TextView textViewISBN = (TextView) listViewItem.findViewById(R.id.textViewISBN_purchased);
        TextView textviewDeliveryStatus = (TextView) listViewItem.findViewById(R.id.textViewDelivery_status_purchased);
        TextView textviewDateTime = (TextView) listViewItem.findViewById(R.id.textViewDateTime);
        //display Condition, Bkname, Author, and price to UI
        textviewDateTime.setText("timePurchased: "+dateTime[position]);
        textviewDateTime.setTextColor(Color.parseColor("#FF9933CC"));

        textViewCondition.setText("Book Condition: "+Condition[position]);
        textViewCondition.setTextColor(Color.parseColor("#FF9933CC"));

        textViewBname.setText("BookName: "+Bname[position]);
        textViewBname.setTextColor(Color.parseColor("#FFCC0000"));

        textViewAuthor.setText("Author: "+Author[position]);
        textViewAuthor.setTextColor(Color.parseColor("#FFCC0000"));

        textViewPrice.setText(Price[position]);
        textViewPrice.setTextColor(Color.parseColor("#FFCC0000"));

        textViewISBN.setText("ISBN: "+ Isbn[position]);
        textViewISBN.setTextColor(Color.parseColor("#FFCC0000"));

        textviewDeliveryStatus.setText("Status: "+Delivery_status[position]);
        textviewDeliveryStatus.setTextColor(Color.parseColor("#FFCC0000"));

        return listViewItem;

    }
}