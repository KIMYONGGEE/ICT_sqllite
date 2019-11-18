package com.ict.studentapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

        final ImageView studentImageView = wrapper.studentImageView;
        final TextView nameView=wrapper.nameView;
        final ImageView contactView = wrapper.contactView;

        final studentvo vo = datas.get(position);
        nameView.setText(vo.name);

        return convertView;



    }
}
