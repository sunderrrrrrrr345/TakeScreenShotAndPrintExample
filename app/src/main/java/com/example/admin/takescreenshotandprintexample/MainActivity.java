package com.example.admin.takescreenshotandprintexample;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Environment;
import android.support.v4.print.PrintHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.ByteArrayOutputStream;
import java.io.File;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ImageView imageView,web;
    Bitmap mbitmap;
    Button captureScreenShot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        web = findViewById(R.id.web);
        textView.setText("Your ScreenShot Image:");
        captureScreenShot = findViewById(R.id.capture_screen_shot);
        imageView = findViewById(R.id.imageView);

    }

    public void screenShot(View view) {
        mbitmap = getBitmapOFRootView(textView);
        imageView.setImageBitmap(mbitmap);
        createImage(mbitmap);
    }

    public Bitmap getBitmapOFRootView(View v) {
        Bitmap image = Bitmap.createBitmap(v.getWidth(), v.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas b = new Canvas(image);
        textView.draw(b);
        return image;
    }

    public void createImage(Bitmap bmp) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        File file = new File(Environment.getExternalStorageDirectory() + "/capturedscreenandroid.jpg");
        PrintHelper photoPrinter = new PrintHelper(MainActivity.this);
        photoPrinter.setScaleMode(PrintHelper.SCALE_MODE_FIT);
        photoPrinter.printBitmap("capturedscreenandroid.jpg", bmp);
       // Log.i("Files", "" + file);
    }
}
