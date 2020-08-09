package com.example.nizal.creditmanager;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.nizal.creditmanager.adapter.ListUserAdapter;
import com.example.nizal.creditmanager.database.DatabaseHelper;
import com.example.nizal.creditmanager.model.User;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@SuppressWarnings("ALL")
public class HomeActivity extends AppCompatActivity {
    private ListView lvUser;
    private ListUserAdapter adapter;
    private List<User> mUserList;
    private DatabaseHelper mDBHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_home);
        lvUser=(ListView)findViewById(R.id.listUsers);
        mDBHelper=new DatabaseHelper(this);
        File Database=getApplicationContext().getDatabasePath(DatabaseHelper.DBNAME);
        if(!Database.exists()){
            mDBHelper.getReadableDatabase();
            if(copyDatabase(this)){
              //  Toast.makeText(this,"copy database success ",Toast.LENGTH_SHORT).show();
            }
            else
            {
               // Toast.makeText(this,"Error in copying database",Toast.LENGTH_SHORT).show();
                return;
            }
        }
        mUserList=mDBHelper.getListProduct();
        adapter=new ListUserAdapter(this,mUserList);
        lvUser.setAdapter(adapter);


        lvUser.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                Intent intent=new Intent(getApplicationContext(),view_singleUser.class);
                intent.putExtra("position", pos);
                startActivity(intent);
            }
        });
    }

    private boolean copyDatabase(Context context) {
        try{
            InputStream inputStream=context.getAssets().open(DatabaseHelper.DBNAME);
            String outFileName=DatabaseHelper.DBLOCATION + DatabaseHelper.DBNAME;
            OutputStream outputStream=new FileOutputStream(outFileName);
            byte[]buff=new byte[1024];
            int length=0;
            while((length = inputStream.read(buff)) >0 ){
                outputStream.write(buff,0,length);
            }
            outputStream.flush();
            outputStream.close();
           // Log.v("Main Activity","DB Copied");
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
}

