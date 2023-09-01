package com.example.bootkamp2o;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ArrayList<StudentInfoModel> StudentData=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView =findViewById(R.id.recycler_view__student);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        StudentData.add(new StudentInfoModel(R.drawable.img,"DivyaKumar Patel","79"));
        StudentInfoModel two=new StudentInfoModel(R.drawable.img_1,"Potnuru Sai Gopal","82");
        StudentData.add(two);

        StudentData.add(new StudentInfoModel(R.drawable.img,"DivyaKumar Patel","79"));
        StudentData.add(new StudentInfoModel(R.drawable.img,"DivyaKumar Patel","79"));
        StudentData.add(new StudentInfoModel(R.drawable.img_2,"DivyaKumar Patel","79"));
        StudentData.add(new StudentInfoModel(R.drawable.img_2,"DivyaKumar Patel","79"));
        StudentData.add(new StudentInfoModel(R.drawable.img_3,"DivyaKumar Patel","79"));
        StudentData.add(new StudentInfoModel(R.drawable.img_3,"DivyaKumar Patel","79"));
        StudentInfoModel three=new StudentInfoModel(R.drawable.img_1,"Potnuru Sai Gopal","82");
        StudentData.add(three);

        StudentInfoRecyclerAdapter recyclerAdapter=new StudentInfoRecyclerAdapter(getApplicationContext(),StudentData);
        recyclerView.setAdapter(recyclerAdapter);

    }
}