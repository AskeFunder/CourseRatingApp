package com.example.courseratingapp;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private static final String TAG = "RecyclerViewAdapter";
    private ArrayList<Course> courses = new ArrayList<>();
    private Context mContext;

    public RecyclerViewAdapter(ArrayList<Course> courses, Context mContext) {

        this.courses = courses;
        this.mContext = mContext;
    }


    //Det her er altid sådan
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_cell, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {
        Log.d(TAG, "onBindViewHolder: called.");

        Course course = courses.get(i);

        try {
            Picasso
                    .get()
                    .load(course.courseImgURL)
                    .fit()
                    .centerCrop()
                    .into(viewHolder.image);

        } catch (Exception e) {
            e.printStackTrace();
        }

        viewHolder.title.setText(course.courseName);
        viewHolder.teacherName.setText(course.teacherName);

        try {
            Picasso
                    .get()
                    .load(course.teacherURL)
                    .resize(100, 100)
                    .centerCrop()
                    .into(viewHolder.circleImageView);
        } catch(Exception e) {
            e.printStackTrace();
        }


        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: clicked on: " + courses.get(i).courseName);

                Intent intent = new Intent(mContext, DetailedViewActivity.class);
                intent.putExtra("course", courses.get(i));
                mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return courses.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView image;
        TextView title;
        CircleImageView circleImageView;
        TextView teacherName;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            title = itemView.findViewById(R.id.textView);
            circleImageView = itemView.findViewById(R.id.detailedCircleView);
            teacherName = itemView.findViewById(R.id.teacherName);
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
