package com.ict.studentapp;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Addstudentactivity extends AppCompatActivity implements View.OnClickListener{

    EditText nameView;
    EditText emailView;
    EditText phoneView;
    Button addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addstudentactivity);

        nameView = (EditText)findViewById(R.id.add_name);
        emailView = (EditText)findViewById(R.id.add_email);
        phoneView = (EditText)findViewById(R.id.add_phone);
        addBtn = (Button)findViewById(R.id.add_btn);

        addBtn.setOnClickListener(this);
    }
    public void onClick(View v){
        String name=nameView.getText().toString();
        String email=emailView.getText().toString();
        String phone=phoneView.getText().toString();

        if(name==null || name.equals("")) {
            //네임이 비어있거나 널이면
            //여기서 토스트는 검은화면에서 나오는 텍스트문자들
            Toast t = Toast.makeText(this, R.string.add_name_null, Toast.LENGTH_SHORT);
            t.show();
        }
        else {
            DBHelper helper = new DBHelper(this);//디비 사용
            SQLiteDatabase db=helper.getWritableDatabase();//디비 객체를 얻고자한다
            //문자열 배열 3개 지정하고 ?에 값이 입력된다!
            db.execSQL("insert into tb_student (name, email, phone) values(?,?,?)",
                    new String[]{name, email, phone});
            db.close();

            finish();// 현 엑티비티 종료 : 안드로이드 시스템에 의해 mainactivity가 보인다!
        }

    }
}
