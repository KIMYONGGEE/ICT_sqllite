package com.ict.studentapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {


    ImageButton addBtn;
    ListView listView;
    ArrayList<studentvo> datas;

    MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //testBtn=(Button)findViewById(R. id.main_test_bin);
        addBtn = (ImageButton) findViewById(R.id.fab);
        listView = (ListView) findViewById(R.id.main_list);

        //testBtn.setOnClickListener(this);
        addBtn.setOnClickListener(this);
        listView.setOnItemClickListener(this);

        myApplication=(MyApplication)getApplicationContext();//퍼미션 어플리케이션
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)== PackageManager.PERMISSION_GRANTED){
            myApplication.callPermission=true;
        }
        if(!myApplication.callPermission){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE},10);

        }


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode==1 && grantResults.length>0){
            if(grantResults[0]==PackageManager.PERMISSION_GRANTED){//퍼미션 부여
                myApplication.callPermission=true;
            }
        }
    }

    public void onClick(View v) {
        if (v == addBtn) {
            Intent intent = new Intent(this, Addstudentactivity.class);
            startActivity(intent);
        }

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = new Intent(this, Readstudentactivity.class);
        intent.putExtra("id", datas.get(position).id); // id는 primary 값

        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select*from tb_student order by name", null);

        datas = new ArrayList<>();

        while (cursor.moveToNext()) {
            studentvo vo = new studentvo();
            vo.id = cursor.getInt(0);
            vo.name = cursor.getString(1);
            vo.email = cursor.getString(2);
            vo.phone = cursor.getString(3);
            vo.photo = cursor.getString(4);
            datas.add(vo);

        }

        db.close();

        MainlistAdapter adapter = new MainlistAdapter(this, R.layout.main_list_item, datas);
        listView.setAdapter(adapter);
    }
}
