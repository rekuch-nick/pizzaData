package com.example.pizzaselector;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {

    private final static int DB_VERSION = 1;
    private final static String PIZZA_DATABASE = "PizzaData.db";

    private String table = "PizzaTable";
    private String column1 = "_id";
    private String column2 = "sauce";
    private String column3 = "top1";
    private String column4 = "top2";
    private String column5 = "top3";

    public DBHelper(Context context){
        super(context, PIZZA_DATABASE, null, DB_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String createQuery = "CREATE TABLE IF NOT EXISTS " + table + "(" +
                column1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                column2 + " TEXT, " +
                column3 + " TEXT, " +
                column4 + " TEXT, " +
                column5 + " TEXT);";
        db.execSQL(createQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table);
        onCreate(db);
    }

    public boolean addRecord(DBItem dbItem){
        ContentValues record = new ContentValues();
        record.put(column2, dbItem.getSauce());
        record.put(column3, dbItem.getTop1());
        record.put(column4, dbItem.getTop2());
        record.put(column5, dbItem.getTop3());
        SQLiteDatabase db = this.getWritableDatabase();
        Long result = db.insert(table, null, record);

        db.close();
        return (result >= 0);
    }

    public DBItem[] getAllRecords() {
        ArrayList<DBItem> list = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + table + " WHERE 1";
        Cursor c = db.rawQuery(query, null);
        while(c.moveToNext()){
            int tempId = (int) c.getLong(0);
            String tempName = c.getString(1);
            String tempTop1 = c.getString(2);
            String tempTop2 = c.getString(3);
            String tempTop3 = c.getString(4);

            DBItem temp = new DBItem(tempId, tempName, tempTop1, tempTop2, tempTop3);
            list.add(temp);

        }
        c.close();
        db.close();

        DBItem[] dbItems = new DBItem[list.size()];
        return list.toArray(dbItems);
    }
}
