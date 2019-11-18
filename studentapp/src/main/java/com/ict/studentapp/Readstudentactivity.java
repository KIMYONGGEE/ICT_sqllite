package com.ict.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class Readstudentactivity extends AppCompatActivity {

    ImageView studentImage;
    TextView nameView;
    TextView phoneView;
    TextView emailView;

    int studentId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_readstudentactivity);

        Intent intent=getIntent();
        studentId=intent.getIntExtra("id",1);

        studentImage=(ImageView)findViewById(R.id.read_student_image);
        nameView=(TextView)findViewById(R.id.read_name);
        phoneView=(TextView)findViewById(R.id.read_phone);
        emailView=(TextView)findViewById(R.id.read_email);

        DBHelper helper=new DBHelper(this);
        SQLiteDatabase db=helper.getWritableDatabase();

        Cursor cursor=db.rawQuery("Select * From tb_student Where _id="+studentId, null);

        String photo=null;

        while(cursor.moveToNext()){
            nameView.setText(cursor.getString(1));
            emailView.setText(cursor.getString(2));
            phoneView.setText(cursor.getString(3));
            photo=cursor.getString(4);
        }

        db.close();
    }
}
