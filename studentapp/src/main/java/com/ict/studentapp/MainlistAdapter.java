package com.ict.studentapp;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainlistAdapter extends ArrayAdapter<studentvo> {
    Context context;
    ArrayList<studentvo> datas;
    int resid;

    public MainlistAdapter(Context context, int resid, ArrayList<studentvo> datas){
        super (context, resid);
        this.context = context;
        this.datas = datas;
        this.resid = resid;
    }
    public int getCount(){
        return datas.size(); // 항목개수 리턴
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView==null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resid, null);

            Mainlistwrapper wrapper = new Mainlistwrapper(convertView);
            convertView.setTag(wrapper);
        }

        Mainlistwrapper wrapper=(Mainlistwrapper)convertView.getTag();

        ImageView studentImageView = wrapper.studentImageView;
        TextView nameView=wrapper.nameView;
        final ImageView contactView = wrapper.contactView;

        final studentvo vo = datas.get(position);
        nameView.setText(vo.name);

        contactView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(vo.phone != null && !vo.phone.equals("")){//퍼미션 부여
                    MyApplication myApplication=(MyApplication)context.getApplicationContext();
                    if(myApplication.callPermission){
                        Intent intent = new Intent();
                        intent.setAction(Intent.ACTION_CALL);
                        intent.setData(Uri.parse("tel : "+vo.phone));
                        context.startActivity(intent);
                    }else{//퍼미션 에러 즉, 퍼미션 부여 X
                        Toast t=Toast.makeText(context, R.string.permission_error, Toast.LENGTH_SHORT);
                        t.show();
                    }
                }else{
                    Toast t=Toast.makeText(context, R.string.main_list_phone_error, Toast.LENGTH_SHORT);
                    t.show();
                }
            }
        });

        return convertView;



    }
}
