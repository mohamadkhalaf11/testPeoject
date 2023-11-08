package com.example.testpeoject;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final int PICK_IMAGE_REQUEST = 1;
    private ImageView ivPhoto;
    private Button btnOpen , btnCalculate;
    private EditText etA, etB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ivPhoto = findViewById(R.id.ivPhotoMain);
        btnOpen = findViewById(R.id.btnOpenMain);
        etB = findViewById(R.id.etBMain);
        etA = findViewById(R.id.etAMain);
        btnCalculate = findViewById(R.id.btnCalculateMain);

        btnOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String a = etA.getText().toString();
                String b = etB.getText().toString();
                if(a.isEmpty() || b.isEmpty())
                {
                    Toast.makeText(MainActivity.this, "something went wrong!", Toast.LENGTH_SHORT).show();
                    return;
                }
                double a1 , b1 , result1;
                a1 = Double.parseDouble(a);
                b1 = Double.parseDouble(b);
                result1 = Math.sqrt(((a1*a1)+(b1*b1)));
                Toast.makeText(MainActivity.this, "the result = "+result1, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();

            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), selectedImageUri);
                ivPhoto.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
