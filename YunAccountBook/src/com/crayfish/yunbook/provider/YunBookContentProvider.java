package com.crayfish.yunbook.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.text.TextUtils;

import com.crayfish.yunbook.db.DBTools;

/**
 * 查询采用异步查询，具体步骤参看《Android高级编程》之“数据库和Content Provider”中的ToDo Activity即可。
 * 
 * @author huashuncai
 * 
 */
public class YunBookContentProvider extends ContentProvider {
	/**
	 * 添加一个私有的变量来保存一个DBOpenHelper类的引用，并在onCreate()方法中创建它。
	 */
	private DBTools.DBOpenHelper dbOpenHelper = null;

	/**
	 * 为该YunBookContentProvider发布一个URI，
	 * 其他应用程序组件通过ContentResolver使用这个URI来访问YunBookContentProvider
	 */
	public static final Uri CONTENT_ACCOUNTBOOK_URI = Uri
			.parse("content://com.crayfish.yunbook.accountbookprovider/accountbooks");

	/**
	 * 只针对accountbook表的全表查询
	 * 
	 */
	private static final int ALL_ROWS_ACCOUNTBOOK = 1;

	/**
	 * 只针对accountbook表的特定行查询
	 * 
	 */
	private static final int SINGLE_ROW_ACCOUNTBOOK = 2;

	/**
	 * 创建一个UriMatcher对象，使得ContentProvider能够区分是全表查询还是针对特定行的查询
	 */
	private static UriMatcher uriMatcher = null;

	static {

		// 实例化UriMatcher对象
		uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

		// 针对accountbooks表的全表查询
		uriMatcher.addURI("com.crayfish.yunbook.accountbookprovider",
				"accountbooks", ALL_ROWS_ACCOUNTBOOK);

		// 针对accountbooks表的特定行要查询
		uriMatcher.addURI("com.crayfish.yunbook.accountbookprovider",
				"accountbooks/#", SINGLE_ROW_ACCOUNTBOOK);

	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		String rowId = null;

		// 要执行操作的表
		String targetTable = null;

		switch (uriMatcher.match(uri)) {
		case SINGLE_ROW_ACCOUNTBOOK:
			targetTable = DBTools.DATABASE_TABLE1;
			rowId = uri.getPathSegments().get(1);
			selection = DBTools.KEY_ID1
					+ "="
					+ rowId
					+ (!TextUtils.isEmpty(selection) ? " and (" + selection
							+ ")" : "");
			break;
		default:
			break;
		}

		// 要想返回删除的项的数量，必须指定一和where子句。要删除所有的行并返回一个值，则传入"1"
		if (selection == null) {
			selection = "1";
		}

		// 执行删除。在此之前，应该先确定要对哪张表执行该操作
		int deleteCount = db.delete(targetTable, selection, selectionArgs);

		// 通知所有的观察者，数据集已经改变
		getContext().getContentResolver().notifyChange(uri, null);

		return deleteCount;
	}

	@Override
	public String getType(Uri uri) {

		// 为一个Content Provider URI 返回一个字符串，它标识了MIME类型
		switch (uriMatcher.match(uri)) {
		case ALL_ROWS_ACCOUNTBOOK:
			return "vnd.android.cursor.dir/vnd.crayfish.accountbooks";
		case SINGLE_ROW_ACCOUNTBOOK:
			return "vnd.android.cursor.item/vnd.crayfish.accountbooks";
		default:
			throw new IllegalArgumentException("Unsupported URI:" + uri);
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

		// 目标Content Uri
		Uri targetContentUri = null;

		// 要想通过传入一个空Content Value 对象的方式向数据库中添加一个空行，
		// 必须使用nullColumnHack参数来指定可以设置为null的列名。
		String nullColumnHack = null;

		// 向表中插入值。在执行此操作之前，要先确定，是对哪张表执行该操作
		String targetTable = null;
		switch (uriMatcher.match(uri)) {
		case ALL_ROWS_ACCOUNTBOOK:
			targetTable = DBTools.DATABASE_TABLE1;
			targetContentUri = CONTENT_ACCOUNTBOOK_URI;
			break;
		case SINGLE_ROW_ACCOUNTBOOK:
			targetTable = DBTools.DATABASE_TABLE1;
			targetContentUri = CONTENT_ACCOUNTBOOK_URI;
			break;
		default:
			throw new IllegalArgumentException("Unsupported URI:" + uri);
		}
		long id = db.insert(targetTable, nullColumnHack, values);

		// 构造并返回新插入行的uri对象
		if (id > -1) {

			// 在执行此操作之前要先确定，是对哪个Content Uri的操作。
			Uri insertedId = ContentUris.withAppendedId(targetContentUri, id);

			getContext().getContentResolver().notifyChange(insertedId, null);

			return insertedId;
		} else {
			return null;
		}
	}

	@Override
	public boolean onCreate() {
		// 实例化对DBOpenHelper的引用
//		this.dbOpenHelper = new DBOpenHelper(getContext(),
//				DBOpenHelper.DATABASE_NAME, null, DBOpenHelper.DATABASE_VERSION);
		DBTools dbTools = new DBTools(getContext());
		dbOpenHelper = dbTools.getDbHelper();
		return true;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		// 打开一个数据库
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();

		// 构造一个查询器
		SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();

		String rowId = null;
		
		// 设置要查询的表
					queryBuilder.setTables(DBTools.DATABASE_TABLE1);

		// 如果是行查询，用传入的行限制结果集。
		switch (uriMatcher.match(uri)) {
		case ALL_ROWS_ACCOUNTBOOK:
			break;
		case SINGLE_ROW_ACCOUNTBOOK:

			// 取得id
			rowId = uri.getPathSegments().get(1);

			// 添加where子句
			queryBuilder.appendWhere(DBTools.KEY_ID1 + "=" + rowId);

		default:
			break;
		}

		// 查询结果游标
		Cursor c = queryBuilder.query(db, projection, selection, selectionArgs,
				null, null, sortOrder);

		return c;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		String targetTable = null;
		String rowId = null;

		switch (uriMatcher.match(uri)) {
		case SINGLE_ROW_ACCOUNTBOOK:
			targetTable = DBTools.DATABASE_TABLE1;
			rowId = uri.getPathSegments().get(1);
			selection = DBTools.KEY_ID1
					+ "="
					+ rowId
					+ (!TextUtils.isEmpty(selection) ? " and (" + selection
							+ ")" : "");
			break;
		default:
			break;
		}

		// 执行更新
		int updateCount = db.update(targetTable, values, selection,
				selectionArgs);

		getContext().getContentResolver().notifyChange(uri, null);

		return updateCount;
	}

}
