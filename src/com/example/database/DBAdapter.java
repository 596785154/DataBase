package com.example.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
public class DBAdapter {
	public static  int id;
	public static String name;
	public static String url;
	public static int type;
	private static String DATABASE_NAME="Led";
	private static String TableName="LedControl";
	private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_CREATE="create table LedControl(id integer primary " +
    		"key,name text ,url text ,type integer );";
    private final Context context;
    private static DatabaseHelper DBHelper;
    private SQLiteDatabase sdb;
    public DBAdapter(Context con){
    	this.context =con;
    	DBHelper=new DatabaseHelper(context);
    }
    private static class DatabaseHelper extends SQLiteOpenHelper{

		public DatabaseHelper(Context context
				) {
			super(context, name,null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {//创建表
			// TODO Auto-generated method stub
			db.execSQL(DATABASE_CREATE);
		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//更新数据库
			// TODO Auto-generated method stub
			db.execSQL("drop table if exits"+ TableName);
			onCreate(db);
		}	
    }
    public DBAdapter open() throws SQLException{//打开数据库
		sdb = DBHelper.getWritableDatabase();
		return this;}
	//---关闭数据库---
	public void close(){DBHelper.close();}
	
	public long insert(int id ,String name ,String url,int type){//插入一条数据
		ContentValues initialValues = new ContentValues();
		String sid=String.valueOf(id); 
		String stype=String.valueOf(type);
		initialValues.put(sid,sid);
		initialValues.put(name, name);
		initialValues.put(url, url);
		initialValues.put(stype, stype);
		return sdb.insert(TableName, null, initialValues);
	}
	public boolean delete(int rowid){//删除一条数据
		return sdb.delete(name,id+"="+rowid,null)>0;
	}
	public Cursor searchAll(){//查询表中的所有数据
		String sid=String.valueOf(id); 
		String stype=String.valueOf(type);
		return sdb.query(TableName, 
				new String[] {sid,name,url,stype},
				null,null,null,null,null);}
	public Cursor search(int rowId) throws SQLException{//查询一条数据
		String sid=String.valueOf(id); 
		String stype=String.valueOf(type);
		Cursor mCursor =sdb.query(true, TableName, 
				new String[] {sid,name,url,stype},
				id+ "=" + rowId,null,null,null,null,null);
		if (mCursor != null) 
		{
			mCursor.moveToFirst();
			}
		return mCursor;
		}
	public boolean update(int rowid, String name,String url, int type){//更新一条数据
		ContentValues args = new ContentValues();
	    String sid=String.valueOf(id); 
	    String stype=String.valueOf(type);
	    args.put(name,name);
	    args.put(url, url);
	    args.put(stype,stype);
	return sdb.update(TableName, args,id + "=" + rowid, null) > 0;
	}
}
