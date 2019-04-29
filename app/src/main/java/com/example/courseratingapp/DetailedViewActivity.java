package com.example.courseratingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailedViewActivity extends AppCompatActivity {

    private static final String TAG = "DetailedViewActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detailed_view_layout);
        Log.d(TAG, "onCreate: started");

        Button sendReview = (Button) findViewById(R.id.sendReview);
        sendReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "sendReview tapped");

                RatingBar ratingBar = (RatingBar) findViewById(R.id.ratingBar);
                EditText ratingText = (EditText) findViewById(R.id.ratingText);

                if(validateInput(ratingText.getText().toString(), ratingBar.getRating())) {
                    Log.d(TAG, "onClick: " + ratingText.getText() + ", " + ratingBar.getRating());
                }
            }
        });

        CircleImageView circleImageView = findViewById(R.id.detailedCircleView);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        getIncomingIntent();
    }

    private boolean validateInput(String comment, float starRating){

        Log.d(TAG, "validateInput " + comment.length() + " " + starRating);

        if (starRating == 0){
            Toast.makeText(this, "Can't rate 0 stars, sorry.", Toast.LENGTH_SHORT).show();
            return false;
        } else if (comment.length() < 50) {
            Toast.makeText(this, "Please write a comment of more than 50 chars", Toast.LENGTH_SHORT).show();
            return false;
        }

        Toast.makeText(this, "Sent mail to teacher with info", Toast.LENGTH_SHORT).show();
        return true;
    }

    private void getIncomingIntent() {
        Log.d(TAG, "getIncomingIntent: checking for incoming intents.");
        if(getIntent().hasExtra("course")) {
            Log.d(TAG, "getIncomingIntent: found intent extras.");

            Course course = getIntent().getParcelableExtra("course");


            ImageView imageView = findViewById(R.id.courseImage);
            CircleImageView circleImageView = findViewById(R.id.detailedCircleView);

            Picasso
                    .get()
                    .load(course.courseImgURL)
                    .fit()
                    .centerCrop()
                    .into(imageView);

            Picasso
                    .get()
                    .load(course.teacherURL)
                    .resize(150, 150)
                    .centerCrop()
                    .into(circleImageView);
        }
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "onRestoreInstanceState was called");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d(TAG, "onSaveInstanceState was called");
    }
}
