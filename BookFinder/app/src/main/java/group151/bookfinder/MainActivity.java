package group151.bookfinder;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    /* create Menu navigation bar (slide bar) on Main activity */
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mToggle;

    //public Button bSearchButton;
    public static final int IMAGE_GALLERY_REQUEST = 20;
    private ImageView selectedBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
//        mToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close);
//        /* implement action-bar */
//        mDrawerLayout.addDrawerListener(mToggle);
//        mToggle.syncState();
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //reference to selected book that user will see
        selectedBook = (ImageView) findViewById(R.id.selectedBook);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
            MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.navigation_menu, menu);
        return true;
    }
    /**
     * Any item of the Navigation bar will be displayed
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_account:
               this. startActivity(new Intent(this, Account.class));
                return true;
            case R.id.nav_credit:
                this. startActivity(new Intent(this, BalanceAccount.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void chooseBook(View view) {
        CharSequence colors[] = new CharSequence[]{"YES", "NO", "TAKE PHOTO"};
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Choose a photo from phone?");
        AlertDialog.Builder builder1 = builder.setItems(colors, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (which == 0) {
                    openImageGallery(getCurrentFocus());
                }
                if (which == 1) {
                    toSellActivity();
                }
                if (which == 2) {
                    toCamera();
                }
            }
        });
        builder1.show();
    }

    public void toCamera() {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    /**
     * Goes to Sell Book Activity.
     */
    public void toSellActivity() {
        Intent intent = new Intent(this, SellBookActivity.class);
        startActivity(intent);
    }

    /**
     * Goes to Buy Book Activity
     *
     * @param view
     */
    public void toBuyActivity(View view) {
        Intent intent = new Intent(this, Search.class);
        startActivity(intent);
    }

    /**
     * opens the gallery from the phone's SD card and asks the user to select a photo.
     *
     * @param view
     * @author Michael Gargano
     */
    public void openImageGallery(View view) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        String pictureDirectoryPath = pictureDirectory.getPath();
        Uri data = Uri.parse(pictureDirectoryPath);
        photoPickerIntent.setDataAndType(data, "image/*");
        startActivityForResult(photoPickerIntent, IMAGE_GALLERY_REQUEST);
    }

    /**
     * called automatically after openImageGallery runs. Sets the data from selected image.
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == IMAGE_GALLERY_REQUEST) {
                //address of image
                Uri imageUri = data.getData();
                Intent intent = new Intent(this, SellBookActivity.class);
                intent.putExtra("image uri", imageUri);
                startActivity(intent);
            }
        }
    }

    /**
     * convert from bitmap to byte array, needed to send to database
     *
     * @param bitmap
     * @return byte array
     */
    public static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    /**
     * convert from byte array to bitmap, used for pulling image from database
     *
     * @param image
     * @return bitmap
     */
    public static Bitmap getImage(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }


    /**
     * update the screen with the most recent activity. It can be used among activities, but exclusively accessed (1 thread at a time)
     */
    void updateScreen() {

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.searchbutton:
                startActivity(new Intent(MainActivity.this, Search.class));
                break;
        }
    }
}
