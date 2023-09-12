package com.example.bootkamp2o;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FirstYearFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FirstYearFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FirstYearFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FirstYearFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FirstYearFragment newInstance(String param1, String param2) {
        FirstYearFragment fragment = new FirstYearFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    ArrayList<StudentInfoModel> StudentData=new ArrayList<>();
    StudentInfoModel studentInfoModel;
    DatabaseReference myref;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_first_year, container, false);
        if(getContext()!=null){
            RecyclerView recyclerView = view.findViewById(R.id.recyclerView_FirstYear);
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