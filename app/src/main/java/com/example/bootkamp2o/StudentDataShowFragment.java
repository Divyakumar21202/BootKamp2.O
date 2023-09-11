package com.example.bootkamp2o;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Objects;

public class StudentDataShowFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public StudentDataShowFragment() {

    }
    String name,id;
    String about;
    String image;

    public StudentDataShowFragment(String ID, String images, String NAME, String ABOUT) {
        this.id=ID;
        this.name=NAME;
        this.image=images;
        this.about=ABOUT;

    }
    public static StudentDataShowFragment newInstance(String param1, String param2) {
        StudentDataShowFragment fragment = new StudentDataShowFragment();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_student_data_show, container, false);
        TextView txtView_name=view.findViewById(R.id.txtView_name);
        TextView txtView_about=view.findViewById(R.id.txtView_about);
        TextView txtView_id=view.findViewById(R.id.txtView_id);
        ImageView imageView=view.findViewById(R.id.img_view);
        txtView_id.setText(id);
        txtView_about.setText(about);
        txtView_name.setText(name);
        Glide.with(getContext()).load(image)
                .placeholder(R.drawable.img_1)
                .error(R.drawable.img_2)
                .into(imageView);

        return view;
    }
    public void onBackPressed(){
        AppCompatActivity appCompatActivity=(AppCompatActivity)getContext();
        assert appCompatActivity != null;
        appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.viewPager,new SecondYearFragment()).addToBackStack(null).commit();

    }
}