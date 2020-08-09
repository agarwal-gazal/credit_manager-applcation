package com.example.nizal.creditmanager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nizal.creditmanager.adapter.ListUserAdapter;
import com.example.nizal.creditmanager.database.DatabaseHelper;
import com.example.nizal.creditmanager.model.User;

import java.io.File;
import java.util.List;

@SuppressWarnings("UnusedAssignment")
public class view_singleUser extends Activity {
    int pos=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_view_single_user);

        Intent intent=getIntent();
        pos=intent.getExtras().getInt("position");

        final ListUserAdapter adapter;
        final List<User> mUserList;
        final DatabaseHelper mDBHelper;
        mDBHelper=new DatabaseHelper(this);
        File Database=getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        mDBHelper.getReadableDatabase();
        mUserList=mDBHelper.getListProduct();
        adapter=new ListUserAdapter(this,mUserList);

        TextView tvName=(TextView)findViewById(R.id.user_name);
        TextView tvEmail=(TextView)findViewById(R.id.user_email);
        TextView tvCredit=(TextView)findViewById(R.id.user_credit);
        Button transferCredit=(Button)findViewById(R.id.transfer_credit);
        tvName.setText(mUserList.get(pos).getName());
        tvEmail.setText(mUserList.get(pos).getEmail());
        tvCredit.setText(mUserList.get(pos).getCredit());


        transferCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(getApplicationContext(),DupHome.class);
               // Toast.makeText(getApplicationContext(),"passing pos as"+pos,Toast.LENGTH_SHORT).show();
                intent1.putExtra("position", pos);
                startActivity(intent1);
            }
        });
    }

}
