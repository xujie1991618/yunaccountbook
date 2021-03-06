/**
 * DBTools.java [V 1.0.0]
 * classes :　com.crayfish.yunbook.db.DBTools
 * crayfish create at 2015-5-18 上午10:39:59
 */
package com.crayfish.yunbook.db;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * com.crayfish.yunbook.db.DBTools
 * @name 数据库操作类
 * @author crayfish   <br/>
 * create at 2015-5-18 上午10:39:59
 */
public class DBTools {

	private static final String DATABASE_NAME = "yunaccountbook.db";
	private static final String DATABASE_TABLE1 = "accountdbooks";
	private static final String DATABASE_TABLE2 = "labelTypes";
	private static final int DATABASE_VERSION = 1;
	
	//TABLE1
	private static final String KEY_ID1 = "_id";
	private static final String KEY_TYPE = "type";
	private static final int TYPE_COLUMN = 1;
	private static final String KEY_MONEY = "money";
	private static final int MONEY_COLUMN = 2;
	private static final String KEY_CREATE_DATE = "create_date";
	private static final int CREATE_DATE_COLUMN = 3;
	private static final String KEY_REMARK = "remark";
	private static final int REMARK_COLUMN = 4;
	private static final String KEY_LABEL_TYPE = "label_type";
	private static final int LABEL_COLUMN = 5;
	
	//TABLE2
	private static final String KEY_ID2 = "_id";
	private static final String KEY_LABEL_TYPE_ID = "label_type_id";
	private static final int LABEL_TYPE_ID_COLUMN = 1;
	private static final String KEY_LABEL_TYPE_NAME = "label_type_name";
	private static final int LABEL_TYPE_NAME_COLUMN = 2;
	
	private DBOpenHelper dbHelper;
	private Context context = null;
	private SQLiteDatabase db = null;
	
	public DBTools(Context _context){
		this.context = _context;
		dbHelper = new DBOpenHelper(_context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	public void close(){
		db.close();
	}
	
	public void open() throws SQLException{
		
	}
	
	private static class DBOpenHelper extends SQLiteOpenHelper{

		/**
		 * @param context
		 * @param name
		 * @param factory
		 * @param version
		 */
		public DBOpenHelper(Context context, String name,
				CursorFactory factory, int version) {
			super(context, name, factory, version);
			// TODO Auto-generated constructor stub
		}
		
		private static final String DATABASE_CREATE1 = "create table "
				+ DATABASE_TABLE1 + "(" 
				+ KEY_ID1 + " integer primary key autoincrement, "
				+ KEY_TYPE + " varchar(10), "
				+ KEY_MONEY + " float, "
				+ KEY_CREATE_DATE + " varchar(50), "
				+ KEY_REMARK + " varchar(255), "
				+ KEY_LABEL_TYPE + " varchar(10));";
		
		private static final String DATABASE_CREATE2 = "create table "
				+ DATABASE_TABLE2 + "(" 
				+ KEY_ID2 + " integer primary key autoincrement, "
				+ KEY_LABEL_TYPE_ID + " varchar(10), "
				+ KEY_LABEL_TYPE_NAME + " varchar(50));";

		/* (non-Javadoc)
		 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
		 */
		@Override
		public void onCreate(SQLiteDatabase db) {
			// TODO Auto-generated method stub
			db.execSQL(DATABASE_CREATE1);
			db.execSQL(DATABASE_CREATE2);
		}

		/* (non-Javadoc)
		 * @see android.database.sqlite.SQLiteOpenHelper#onUpgrade(android.database.sqlite.SQLiteDatabase, int, int)
		 */
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			// TODO Auto-generated method stub
			Log.w("TaskDBAdapter", "Upgrading from version " +
					oldVersion + " to " + 
					newVersion + ",which will destroy all old data");

			//删除旧表
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE1);
			db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE2);
			//create a new one
			onCreate(db);
		}
		
	}
}
