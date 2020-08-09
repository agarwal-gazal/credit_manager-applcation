package com.example.nizal.creditmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nizal.creditmanager.adapter.ListUserAdapter;
import com.example.nizal.creditmanager.database.DatabaseHelper;
import com.example.nizal.creditmanager.model.User;

import java.io.File;
import java.util.List;

public class DupHome extends AppCompatActivity {
    int pos1=0;
    private ListView lvUser;
    private ListUserAdapter adapter;
    private List<User> mUserList;
    private DatabaseHelper mDBHelper;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_dup_home2);
        Intent intent=getIntent();
        pos1=intent.getExtras().getInt("position");

        mDBHelper=new DatabaseHelper(this);
        File Database=getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        mDBHelper.getReadableDatabase();
        mUserList=mDBHelper.getListProduct();
        adapter=new ListUserAdapter(this,mUserList);

        final TextView tvName=(TextView)findViewById(R.id.user_name);
        final TextView tvCredit=(TextView)findViewById(R.id.user_credit);
        tvName.setText(mUserList.get(pos1).getName());
        final int credit,credit3;
        tvCredit.setText(mUserList.get(pos1).getCredit());
        lvUser=(ListView)findViewById(R.id.listUsers);
        mDBHelper=new DatabaseHelper(this);
        credit3=Integer.parseInt(mUserList.get(pos1).getCredit());
        credit = credit3-5;
        pos1+=1;
        mUserList=mDBHelper.getProductList((pos1));
        adapter=new ListUserAdapter(this,mUserList);
        lvUser.setAdapter(adapter);


        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                mDBHelper=new DatabaseHelper(getApplicationContext());
                mDBHelper.getReadableDatabase();
                mUserList=mDBHelper.getListProduct();
                adapter=new ListUserAdapter(getApplicationContext(),mUserList);
                int i=(int)id;
                int credit2= Integer.parseInt(mUserList.get((i-1)).getCredit());
                int credit1=credit2+5;
                mDBHelper.updateDatabase(pos1,credit,((int)id),credit1,credit3,credit2);

                Intent intent=new Intent(getApplicationContext(),HomeActivity.class);
                startActivity(intent);
            }
        });
    }

}