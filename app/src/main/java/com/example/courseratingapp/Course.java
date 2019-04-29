package com.example.courseratingapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Course implements Parcelable {

    String courseName;
    String courseImgURL;
    String courseDescription;

    String teacherName;
    String teacherURL;

    public Course(String courseName, String courseImgURL, String courseDescription, String teacherName, String teacherURL) {
        this.courseName = courseName;
        this.courseImgURL = courseImgURL;
        this.courseDescription = courseDescription;
        this.teacherName = teacherName;
        this.teacherURL = teacherURL;
    }

    protected Course(Parcel in) {
        courseName = in.readString();
        courseImgURL = in.readString();
        courseDescription = in.readString();
        teacherName = in.readString();
        teacherURL = in.readString();
    }

    public static final Creator<Course> CREATOR = new Creator<Course>() {
        @Override
        public Course createFromParcel(Parcel in) {
            return new Course(in);
        }

        @Override
        public Course[] newArray(int size) {
            return new Course[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(courseName);
        dest.writeString(courseImgURL);
        dest.writeString(courseDescription);
        dest.writeString(teacherName);
        dest.writeString(teacherURL);
    }
}
