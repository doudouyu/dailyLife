package life.bean.com.beanlife.gesture;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class publicSQLiteOpenHelper extends SQLiteOpenHelper{
	 private static String name = "publicsqlite";
	 private static String GESTURE_PASSWORD = "gesture_password";
	 private static final int version = 1;
	 public SQLiteDatabase db;

	 public publicSQLiteOpenHelper(Context context) 
	    {
	        super(context, name, null, version);
	    }
	 @Override
	    public void onCreate(SQLiteDatabase db) 
	    {
	 
			String sqlpass = "create table " + "gesture_password"+ "("
					+ GesturePassword.WXID + " text not null,"
					+ GesturePassword.PASSWORD + " text not null,"
					 + GesturePassword.REMAINOPPORTUNITY + " text not null)";
			db.execSQL(sqlpass);
	    }
	    @Override
	    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	    {
	        db.execSQL("DROP TABLE IF EXISTS password");
	        onCreate(db); 
	    }
	    
		public class GesturePassword  
		{
			public static final String WXID = "wxid";
			public static final String PASSWORD = "password";
			public static final String REMAINOPPORTUNITY = "remainopportunity";
			
		}
	
		
		
		/**
		 * 插入手势密码表
		 */
		public void insertGestureInfo(SQLiteDatabase database ,String password,String times){
			ContentValues values = null;
			try {
				values = new ContentValues();
				values.put(GesturePassword.WXID, "001");
				values.put(GesturePassword.PASSWORD, password);
				values.put(GesturePassword.REMAINOPPORTUNITY, ""+times);
				database.insert(publicSQLiteOpenHelper.GESTURE_PASSWORD, null,
						values);
				
			} catch (Exception e) {
				e.printStackTrace();
				throw new SQLException(e.getMessage());
			}finally{
				if (values != null) {
					values.clear();
					values = null;
				}
			}
			
		}
		/**
		 * 修改手势密码表---初始化次数为8次
		 * 
		 * 
		 */
		
			public void updateGestureInfo(SQLiteDatabase database,String password,String times) {
				ContentValues values = null;
				
				try {
					
					if (password == null) {
						values = new ContentValues();
						values.put(GesturePassword.WXID, "001");
						values.put(GesturePassword.REMAINOPPORTUNITY, ""+times);
						database.update(publicSQLiteOpenHelper.GESTURE_PASSWORD,
								values, "WXID=?",
								new String[] { "001" });
						
					}else {
						
						values = new ContentValues();
						values.put(GesturePassword.WXID, "001");
						values.put(GesturePassword.PASSWORD, password);
						values.put(GesturePassword.REMAINOPPORTUNITY, ""+times);
						database.update(publicSQLiteOpenHelper.GESTURE_PASSWORD,
								values, "WXID=?",
								new String[] { "001" });
					}
				} catch (SQLException e) {
					
				} finally {
					if (values != null) {
						values.clear();
						values = null;
					}
				}
			}
			/**
			 * 查询手势密码表有无记录
			 * @return
			 */
			public boolean queryGestureTableCount(SQLiteDatabase database){
				Cursor cursor = null;
				
				try {
					cursor = database.query(publicSQLiteOpenHelper.GESTURE_PASSWORD, null, null, null, null, null, null);
					if (cursor != null && cursor.getCount()>0) {
						return true;
						
					}else {
						return false;
					}
					
				} catch (Exception e) {
					
				}
				finally{
					if (cursor != null) {
						cursor.close();
						cursor = null;
					}
				}
				return false;
			}
		
			/**
			 * 查询手势密码表--查看剩余次数
			 * 
			 */
			public String queryGestureTime(SQLiteDatabase database) {
				Cursor cursor = null;
				try {
					cursor = database.query(publicSQLiteOpenHelper.GESTURE_PASSWORD, null, null, null, null, null, null);

					if ((cursor != null) && (cursor.getCount() > 0)) {
						if (cursor.moveToFirst()) {
							int columnIndex = cursor
									.getColumnIndex(GesturePassword.REMAINOPPORTUNITY);
							return cursor.getString(columnIndex);
						}

					}
				} catch (Exception e) {
					
				} finally {
					if (cursor != null) {
						cursor.close();
						cursor = null;
					}
				}
				return "";
			}
			
			/**
			 * 查询手势密码表--查看密码
			 * 
			 */
			public String queryGesturePassword(SQLiteDatabase database) {
				Cursor cursor = null;
				try {
					cursor = database.query(publicSQLiteOpenHelper.GESTURE_PASSWORD, null, null, null, null, null, null);

					if ((cursor != null) && (cursor.getCount() > 0)) {
						if (cursor.moveToFirst()) {
							int columnIndex = cursor
									.getColumnIndex(GesturePassword.PASSWORD);
							return cursor.getString(columnIndex);
						}

					}
				} catch (Exception e) {
					
				} finally {
					if (cursor != null) {
						cursor.close();
						cursor = null;
					}
				}
				return "";
			}
}
