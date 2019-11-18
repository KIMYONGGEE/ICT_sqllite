package com.ict.studentapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION=1;
    public DBHelper(Context context){
        super(context, "studentdb", null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //어플리케이션이 인스톨 된 후 ! 최초로 이용되는순간 딱 한번만 호출
        //테이블 생성!!
        String studentSql = "create table tb_student (" +
                "_id integer primary key autoincrement," +
                "name not null," +
                "email," +
                "phone," +
                "photo)";

        String scoreSql = "create table tb_grade (" +
                "_id integer primary key autoincrement," +
                "student_id not_null," +
                "subject,"+
                "date,"+
                "grade)";

        db.execSQL(studentSql);//구현하고자 하는 테이블!!
        db.execSQL(scoreSql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //생성자의 정보 버전이 변경될때마다 !! 사용!!
        //버전을 바꾸어 스키마 변경
        if(newVersion == DATABASE_VERSION){
            db.execSQL("drop table tb_student");
            db.execSQL("drop table tb_score");//드롭 후
            onCreate(db);//위의 db 생성

        }//버전과 상수가 같을 때만 변경
    }
}
