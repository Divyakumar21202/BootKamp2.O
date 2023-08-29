package com.example.bootkamp2o;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.internal.Storage;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity {

    private final int GALLERY_REQUEST_CODE=1;
    Button gallery_btn,sign_in;
    EditText name,password,id,about;
    TextView login_txt;
    ImageView imageView;
    private Uri selectedImageUri;

    FirebaseStorage firebaseStorage =FirebaseStorage.getInstance();


    FirebaseDatabase database = FirebaseDatabase.getInstance();

    DatabaseReference databaseReference;

    StorageReference storageReference =firebaseStorage.getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        gallery_btn=findViewById(R.id.btn_img);
        sign_in=findViewById(R.id.btn_sign_in);
        name=findViewById(R.id.edt_txt_name);
        password=findViewById(R.id.edt_txt_password);
        id=findViewById(R.id.edt_txt_id);
        about=findViewById(R.id.edt_txt_about);
        login_txt=findViewById(R.id.txt_login);
        imageView=findViewById(R.id.img_view);


        login_txt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        gallery_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_PICK);
                intent.setData(MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,GALLERY_REQUEST_CODE);
            }
        });

        sign_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data=name.getText().toString();
                if(data.isEmpty() || data.length()<4)
                {
                    Toast.makeText(getApplicationContext(),"Enter Full Name",Toast.LENGTH_SHORT).show();
                    return;
                }

                data=id.getText().toString();
                if(data.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter ID",Toast.LENGTH_SHORT).show();
                    return;
                }
                data=about.getText().toString();
                if(data.isEmpty() || data.length()<20)
                {
                    Toast.makeText(getApplicationContext(),"Enter More Detail",Toast.LENGTH_SHORT).show();
                    return;
                }
                data=password.getText().toString();
                if(data.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }


                if(selectedImageUri ==null)
                {
                    Toast.makeText(getApplicationContext(),"Please Select Image",Toast.LENGTH_SHORT).show();
                    return;
                }

                StorageReference imgref =storageReference.child("Images/"+id.getText().toString());
                UploadTask uploadTask=imgref.putFile(selectedImageUri);

                uploadTask.addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                        Toast.makeText(getApplicationContext(),"Image Uploaded Successfully",Toast.LENGTH_SHORT).show();

                    }
                })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"Image Uploading Failed",Toast.LENGTH_SHORT).show();

                            }
                        });

                HashMap<String,Object> StudentData=new HashMap<>();
                StudentData.put("NAME",name.getText().toString());
                StudentData.put("ID",id.getText().toString());
                StudentData.put("PASSWORD",password.getText().toString());
                StudentData.put("ABOUT",about.getText().toString());

                databaseReference = database.getReference("Student Info");

                databaseReference.child(id.getText().toString()).setValue(StudentData)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(getApplicationContext(),"Data Uploaded",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(getApplicationContext(),"Data Uploaded Failed",Toast.LENGTH_SHORT).show();

                            }
                        });
                Intent LoginIntent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(LoginIntent);
                finish();
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode==RESULT_OK && requestCode==GALLERY_REQUEST_CODE)
        {
            assert data != null;
            selectedImageUri=data.getData();
            imageView.setImageURI(selectedImageUri);
        }
    }
}