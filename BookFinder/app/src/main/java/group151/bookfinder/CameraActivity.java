package group151.bookfinder;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import java.io.File;
/**
 * Created by kacao on 4/17/2017.
 */
public class CameraActivity extends AppCompatActivity {
    Button button;
    ImageView imageView;
    static final int CAM_REQUEST = 1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        button = (Button) findViewById(R.id.capture_pic_button);
        imageView = (ImageView) findViewById(R.id.image_view);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = getFile();
                camera_intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
                startActivityForResult(camera_intent, CAM_REQUEST);
            }
        });
    }
    private File getFile() {
        File folder = new File("sdcard/camera_app");        //change me: no SDcard
        if (!folder.exists())
            folder.mkdir();
        File image_file = new File(folder, "pic1.jpg");
        return image_file;
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        String path = "sdcard/camera_app/pic1.jpg";
        imageView.setImageDrawable(Drawable.createFromPath(path));
    }
}