package com.buios.buios;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class FoodDBManager extends SQLiteOpenHelper {

  private final Context context;
  private final String name;
  private final CursorFactory factory;
  private final int version;
  //  private final String TAG = "buios";
  private final SQLiteDatabase db;
  static boolean dbCreated = false;

  public FoodDBManager(@Nullable Context context, @Nullable String name, CursorFactory factory,
      int version) {
    super(context, name, factory, version);
    this.context = context;
    this.name = name;
    this.factory = factory;
    this.version = version;
    db = context.openOrCreateDatabase("FOOD_DB", Context.MODE_PRIVATE, null);
    if (!dbCreated) {
      this.onCreate(db);
      dbCreated = true;
    }
  }

  @Override
  public void onCreate(SQLiteDatabase db) {
    String sql = "CREATE TABLE IF NOT EXISTS FOOD_DB(ID INTEGER PRIMARY KEY AUTOINCREMENT,"
        + " NAME VARCHAR NOT NULL, DUE DATE, MEMO VARCHAR, CATEGORY INT);";
    db.execSQL(sql);
  }

  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  }

  public void insertData(String name, String date, String memo, int category) {
    String sql = "INSERT INTO FOOD_DB(NAME, DUE, MEMO, CATEGORY) VALUES(?, ?, ?, ?);";
    db.execSQL(sql, new Object[]{name, date, memo, category});
  }

  public List<Food> selectAll(boolean isSortbyDate) {
    List<Food> result = new ArrayList<Food>();
    String sql;
    if (isSortbyDate) {
      sql = "SELECT * FROM FOOD_DB ORDER BY DUE";
    } else {
      sql = "SELECT * FROM FOOD_DB ORDER BY NAME";
    }

    Cursor cursor = db.rawQuery(sql, null);

    while (cursor.moveToNext()) {
      Food tmp = new Food();
      tmp.id = cursor.getInt(0);
      tmp.name = cursor.getString(1);
      tmp.date = cursor.getString(2);
      tmp.memo = cursor.getString(3);
      tmp.category = cursor.getInt(4);
      result.add(tmp);
    }
    return result;
  }

  public List<Food> selectAll(int category, boolean isSortbyDate) {
    List<Food> result = new ArrayList<Food>();
    String sql;
    if (isSortbyDate) {
      sql = "SELECT * FROM FOOD_DB WHERE  CATEGORY LIKE ?  ORDER BY DUE";
    } else {
      sql = "SELECT * FROM FOOD_DB WHERE  CATEGORY LIKE ?  ORDER BY NAME";
    }
    Cursor cursor = db.rawQuery(sql, new String[]{Integer.toString(category)});
    while (cursor.moveToNext()) {
      Food tmp = new Food();
      tmp.id = cursor.getInt(0);
      tmp.name = cursor.getString(1);
      tmp.date = cursor.getString(2);
      tmp.memo = cursor.getString(3);
      tmp.category = cursor.getInt(4);
      result.add(tmp);
    }
    return result;
  }

  public List<Food> searchItem(String keyword) {
    List<Food> result = new ArrayList<Food>();
    keyword = '%' + keyword + '%';
    String sql = "SELECT * FROM FOOD_DB WHERE ( NAME LIKE ? OR MEMO LIKE ?) ORDER BY DUE";
    Cursor cursor = db.rawQuery(sql, new String[]{keyword, keyword});
    while (cursor.moveToNext()) {
      Food tmp = new Food();
      tmp.id = cursor.getInt(0);
      tmp.name = cursor.getString(1);
      tmp.date = cursor.getString(2);
      tmp.memo = cursor.getString(3);
      tmp.category = cursor.getInt(4);
      result.add(tmp);
    }
    return result;
  }


  public void deleteItem(int id) {
    String sql = "DELETE FROM FOOD_DB WHERE ID = ?";
    db.execSQL(sql, new Object[]{id});
  }

  public void updateItem(int id, String name, String date, String memo, int category) {
    String sql = "UPDATE FOOD_DB SET NAME = ?, DUE = ?, MEMO = ?, CATEGORY = ? WHERE ID = ?;";
    db.execSQL(sql, new Object[]{name, date, memo, category, id});
  }

}
