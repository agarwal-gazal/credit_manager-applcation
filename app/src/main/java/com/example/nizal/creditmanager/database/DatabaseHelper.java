package com.example.nizal.creditmanager.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.nizal.creditmanager.model.User;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DBNAME = "CMdb";
    public static final String KEY_ROWID = "_id";
    public static final String DATABASE_TABLE = "Transfers";
    public static final String KEY_FROM = "from";
    public static final String KEY_TO = "to";
    public static final String KEY_FROM_INITIAL = "from_initial";
    public static final String KEY_FROM_FINAL = "from_final";
    public static final String KEY_TO_INITIAL = "to_initial";
    public static final String KEY_TO_FINAL= "to_final";
    public static String DBLOCATION=null;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public DatabaseHelper(Context context){
        super(context,DBNAME,null,1);
        this.mContext = context;
        this.DBLOCATION = "/data/data/" + context.getPackageName() + "/" + "databases/";
        Log.e("Path 1", DBLOCATION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void openDatabase(){
        String dbpath=mContext.getDatabasePath(DBNAME).getPath();
        if(mDatabase !=null && mDatabase.isOpen()){
            return;
        }
        mDatabase = SQLiteDatabase.openDatabase(dbpath, null,SQLiteDatabase.OPEN_READWRITE);
    }

    public void closeDatabase(){
        if(mDatabase !=null ){
            mDatabase.close();
        }
    }

    public List<User> getListProduct() {
        User user=null;
        List<User> userList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM Users", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
            userList.add(user);
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return userList;
    }

    public List<User> getProductList(int id) {
        User user=null;
        List<User> userList = new ArrayList<>();
        openDatabase();
        Cursor cursor = mDatabase.rawQuery("SELECT * FROM Users", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            if(cursor.getInt(0)!=id) {
                user = new User(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3));
                userList.add(user);
            }
            cursor.moveToNext();
        }
        cursor.close();
        closeDatabase();
        return userList;
    }

    public void updateDatabase(int id1,int c1,int id2,int c2,int c3,int c4) {
        openDatabase();
        ContentValues values = new ContentValues();
        values.put("CREDITS", c1);
        mDatabase.update("Users", values, "_id=" + id1, null);
        ContentValues values1 = new ContentValues();
        values1.put("CREDITS", c2);
        mDatabase.update("Users", values1, "_id=" + id2, null);
        try {

            mDatabase.execSQL("create table if not exists Transfers " +
                    "(_id integer primary key autoincrement, from_id integer, from_initial integer, from_final integer,  to_id integer, to_initial integer, to_final integer )");

        }catch (Exception e){
            e.printStackTrace();
        }
        ContentValues values2 = new ContentValues();
        values2.put("from_id",id1);
        values2.put("from_initial",c3);
        values2.put("from_final",c1);
        values2.put("to_id",id2);
        values2.put("to_initial",c4);
        values2.put("to_final",c2);
        mDatabase.insert("Transfers", null, values2);
        closeDatabase();
    }


}
