package com.example.carbonjava;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;

import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;

public class ProfileCamera extends AppCompatActivity implements OnClickListener{

    private static final int CAMERA_REQUEST = 0;
    private static final int GALLERY_REQUEST = 1;

    private Button buttonCamera, buttonGallery ,buttonAddProfile;
    private ImageView imageViewProfile;
    private Bitmap picture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_camera);

        buttonCamera= findViewById(R.id.buttonCamera);
        buttonCamera.setOnClickListener(this);

        buttonAddProfile =findViewById(R.id.buttonAddProfile);
        buttonAddProfile.setOnClickListener(this);

        buttonGallery =findViewById(R.id.buttonGallery);
        buttonGallery.setOnClickListener(this);

        imageViewProfile= findViewById(R.id.imageViewProfile);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.buttonCamera){
            Intent i =new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(i,CAMERA_REQUEST);
        }else if(view.getId() == R.id.buttonGallery){
            Intent i =new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            startActivityForResult(i,GALLERY_REQUEST);
        }
        else{
            String base64Image = bitToString(picture);
            Intent returnIntent = new Intent();
            returnIntent.putExtra("picture", base64Image);
            setResult(Activity.RESULT_OK, returnIntent);
            finish();
        }

    }
    public String bitToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] arr=baos.toByteArray();
        return Base64.encodeToString(arr, Base64.DEFAULT);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == CAMERA_REQUEST){
            if(resultCode == RESULT_OK){
                picture =(Bitmap) data.getExtras().get("data");
                imageViewProfile.setImageBitmap(picture);
            }
        }else if(requestCode == GALLERY_REQUEST){
            if(resultCode == RESULT_OK){
                Uri targotUri =data.getData();
                try{
                    picture= BitmapFactory.decodeStream(getContentResolver().openInputStream(targotUri));
                    imageViewProfile.setImageBitmap(picture);

                } catch(FileNotFoundException e){
                    e.printStackTrace();
                }
            }

        }
    }
}