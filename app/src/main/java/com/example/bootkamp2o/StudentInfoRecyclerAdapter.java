package com.example.bootkamp2o;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
        holder.imageView.setImageResource(arrayList.get(position).imageview);
        holder.name.setText(arrayList.get(position).name);
        holder.id.setText(arrayList.get(position).id);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder{
        TextView name,id;
        ImageView imageView;
        public viewHolder(View itemView){
            super(itemView);
            name=itemView.findViewById(R.id.txt_layout_student_name);
            id=itemView.findViewById(R.id.txt_layout_student_id);
            imageView=itemView.findViewById(R.id.imgview_layout_student_image);
        }

    }
}