package com.example.bootkamp2o;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<StudentInfoModel> StudentData=new ArrayList<>();
    StudentInfoModel studentInfoModel;
    DatabaseReference myref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView =findViewById(R.id.recycler_view__student);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        StudentInfoRecyclerAdapter recyclerAdapter=new StudentInfoRecyclerAdapter(getApplicationContext(),StudentData);
        recyclerView.setAdapter(recyclerAdapter);

        //Initialize DB
        myref= FirebaseDatabase.getInstance().getReference("Student Info");
        StorageReference storageReference=FirebaseStorage.getInstance().getReference("Images");

        myref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    studentInfoModel =dataSnapshot.getValue(StudentInfoModel.class);
                    StudentData.add(studentInfoModel);

                }
                recyclerAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"Error to Fetch data",Toast.LENGTH_SHORT).show();

            }
        });


    }
}