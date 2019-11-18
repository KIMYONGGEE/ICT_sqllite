package com.ict.studentapp;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Mainlistwrapper {
    public ImageView studentImageView;
    public TextView nameView;
    public ImageView contactView;

    public Mainlistwrapper(View root){
        studentImageView =(ImageView)root.findViewById(R.id.main_item_student_image);
        nameView =(TextView)root.findViewById(R.id.main_item_name);
        contactView =(ImageView)root.findViewById(R.id.main_itme_contact);
    }
}
