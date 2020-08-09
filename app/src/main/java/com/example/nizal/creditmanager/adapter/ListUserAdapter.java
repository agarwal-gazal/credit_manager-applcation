package com.example.nizal.creditmanager.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.nizal.creditmanager.R;
import com.example.nizal.creditmanager.model.User;

import java.util.List;

import static android.media.CamcorderProfile.get;

public class ListUserAdapter extends BaseAdapter {
    private Context mContext;
    private List<User> mUserList;

    public ListUserAdapter(Context mContext, List<User> mUserList) {
        this.mContext = mContext;
        this.mUserList = mUserList;
    }

    @Override
    public int getCount() {
        return mUserList.size();
    }

    @Override
    public Object getItem(int position) {
        return mUserList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mUserList.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v=View.inflate(mContext , R.layout.user_listview, null);
        TextView tvName=(TextView)v.findViewById(R.id.user_name);
        TextView tvEmail=(TextView)v.findViewById(R.id.user_email);
        TextView tvCredit=(TextView)v.findViewById(R.id.user_credit);
        tvName.setText(mUserList.get(position).getName());
        tvEmail.setText(mUserList.get(position).getEmail());
        tvCredit.setText(mUserList.get(position).getCredit());

        return v;
    }
}
