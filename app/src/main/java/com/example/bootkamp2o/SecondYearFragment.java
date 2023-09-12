package com.example.bootkamp2o;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


public class SecondYearFragment extends Fragment {

    public SecondYearFragment() {
        // Required empty public constructor
    }




    ArrayList<StudentInfoModel> StudentData=new ArrayList<>();
    StudentInfoModel studentInfoModel;
    DatabaseReference myref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_second_year, container, false);
            if(getContext()!=null){
                RecyclerView recyclerView = view.findViewById(R.id.recyclerview_secondYear_Fragment);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


                StudentInfoRecyclerAdapter recyclerAdapter = new StudentInfoRecyclerAdapter(getContext(), StudentData);
                recyclerView.setAdapter(recyclerAdapter);

                myref = FirebaseDatabase.getInstance().getReference("Student Info");
                myref.keepSynced(true);

                myref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            studentInfoModel = dataSnapshot.getValue(StudentInfoModel.class);
                            StudentData.add(studentInfoModel);
                            // Image Fetching
                            assert studentInfoModel != null;

                            String ImgPath = studentInfoModel.getID();
                            StorageReference storageReference = FirebaseStorage.getInstance().getReference(ImgPath);

                            storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                @Override
                                public void onSuccess(Uri uri) {
                                    studentInfoModel.setImages(uri.toString());
                                    StudentData.add(studentInfoModel);

                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {


                                }
                            });


                        }
                        recyclerAdapter.notifyDataSetChanged();

                    }


                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Toast.makeText(getContext(), "Error to Fetch data", Toast.LENGTH_SHORT).show();

                    }
                });

            }
            else{
                Log.d("Error","context Not Found");

            }




            return view;
        }

    }
