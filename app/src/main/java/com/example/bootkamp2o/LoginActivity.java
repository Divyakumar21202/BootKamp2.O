package com.example.bootkamp2o;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    Button login_btn;
    EditText id_txt,password_txt;
    String Retrieve_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        id_txt=findViewById(R.id.edt_txt_Log_id);
        password_txt=findViewById(R.id.edt_txt_Log_password);
        login_btn=findViewById(R.id.login_Log_btn);


        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String data;
                data=id_txt.getText().toString();
                if(data.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter ID",Toast.LENGTH_SHORT).show();
                    return;
                }

                data=password_txt.getText().toString();
                if(data.isEmpty())
                {
                    Toast.makeText(getApplicationContext(),"Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }

                FirebaseDatabase firebaseDatabase =FirebaseDatabase.getInstance();
                DatabaseReference myRef =firebaseDatabase.getReference("Student Info");
                myRef.child(id_txt.getText().toString()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {

                        if(task.isSuccessful()){

                            if(task.getResult().exists()){

                                DataSnapshot dataSnapshot =task.getResult();
                               Retrieve_password =String.valueOf(dataSnapshot.child("PASSWORD").getValue());
                               Toast.makeText(getApplicationContext(),"Successfully Read",Toast.LENGTH_SHORT).show();
                                try {
                                    int int1 = Integer.parseInt(password_txt.getText().toString());
                                    int int2 = Integer.parseInt(Retrieve_password);

                                    if (int1 == int2) {
                                        Intent intent=new Intent(getApplicationContext(),HomeFragmentActivity.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(),"Incorrect Password 222",Toast.LENGTH_SHORT).show();
                                        return;

                                    }
                                } catch (NumberFormatException e) {
                                    Toast.makeText(getApplicationContext(),"Incorrect Password",Toast.LENGTH_SHORT).show();

                                }

                            }
                            else {
                                Toast.makeText(getApplicationContext(),"User Doesn't Exist",Toast.LENGTH_SHORT).show();


                            }

                        }

                        else {
                            Toast.makeText(getApplicationContext(),"Failed To Read",Toast.LENGTH_SHORT).show();
                        }




                    }
                });


            }

        });



    }
}