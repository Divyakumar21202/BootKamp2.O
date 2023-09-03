package com.example.bootkamp2o;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.zip.Inflater;

public class StudentInfoRecyclerAdapter extends RecyclerView.Adapter<StudentInfoRecyclerAdapter.viewHolder> {

    Context context;
    ArrayList<StudentInfoModel> arrayList;
    StudentInfoRecyclerAdapter(Context context,ArrayList<StudentInfoModel> arrayList){
        this.arrayList=arrayList;
        this.context=context;
    }
    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(context).inflate(R.layout.student_data_view,parent,false);
        viewHolder viewHolder=new viewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        StudentInfoModel studentInfoModel=arrayList.get(position);
        holder.name.setText(studentInfoModel.getNAME());
        holder.id.setText(studentInfoModel.getID());
        holder.about.setText(studentInfoModel.getABOUT());
        Glide.with(holder.imageView.getContext())
                .load(studentInfoModel.getImages())
                .placeholder(R.drawable.img_1)
                .circleCrop()
                .error(R.drawable.img_2)
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView name,id,about;
        ImageView imageView;
        public viewHolder(View itemView){
            super(itemView);
            name=itemView.findViewById(R.id.txt_layout_student_name);
            id=itemView.findViewById(R.id.txt_layout_student_id);
            imageView=itemView.findViewById(R.id.imgview_layout_student_image);
            about=itemView.findViewById(R.id.txt_layout_student_about);
        }

    }
}
