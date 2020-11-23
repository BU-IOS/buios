package com.buios.buios;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class fooddbManager extends AppCompatActivity {
    private String TAG = "test";
    public SQLiteDatabase sqLiteDatabase;
    EditText edit_name,label_date,edit_memo, tvname, tvdate, tvmemo;//tv-는 아이템 선택시 출력되는 정보
    Button btn_add,btn_delete, btn_modify;
    String name, date, memo;
    //int num_category;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        try {//db생성


            sqLiteDatabase = openOrCreateDatabase("food_db", MODE_PRIVATE, null);


            sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS food_db" +
                    "(name VARCHAR ," +
                    "date VARCHAR ," +
                    "memo VARCHAR ," +
                    "category integer )");


        }catch (Exception e){
            Log.i(TAG, e.toString());
        }


    }
    public void addItem(){//아이템 추가
        btn_add.setOnClickListener(new View.OnClickListener() {//add item
            @Override
            public void onClick(View view) {
                String name = edit_name.getText().toString();
                String date = label_date.getText().toString();
                String memo = edit_memo.getText().toString();
                saveTask(name,date,memo);
            }
        });
    }
    public void saveTask(String name, String date, String memo) {//save item
        try {
            if (!name.isEmpty() && !date.isEmpty()) {
                sqLiteDatabase.execSQL("INSERT INTO food_db (name,date,memo) VALUES ('" + name + "' , '" + date + "','" + memo + "') ");//카테고리 넘버 추가
                Toast.makeText(this, "식자재 정보가 저장되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                Toast.makeText(this, "식자재 정보 저장이 실폐하였습니다.", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delItem(){//del item
        btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = tvname.getText().toString();
                String date = tvdate.getText().toString();
                String memo = tvmemo.getText().toString();
                removeTask(name,date,memo);
            }
        });

    }
    public void removeTask(String name, String date, String memo){//sql삭제
        try{
            sqLiteDatabase.execSQL("DELETE FROM food_db WHERE name ='" + name + "' AND date = '" + date +"'AND memo ='" + memo + "'");
            Toast.makeText(this, "식자재가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
            finish();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void modifyItem(){//아이템 수정
        btn_modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nametv= tvname.getText().toString();
                String datetv = tvdate.getText().toString();
                String memotv=tvmemo.getText().toString();
                updateTask(nametv,datetv,memotv);
            }
        });

    }
    public void updateTask(String nametv, String datetv, String memotv){//sql 수정
        try{
            if(!nametv.isEmpty() && !datetv.isEmpty()){
                sqLiteDatabase.execSQL("UPDATE food_db SET name = '" + nametv + "' , date = '" + datetv +"', memo = '" + memotv +"' WHERE name = '" + name + "' AND date = '" + date +"'AND memo = '" + memo +"'");
                Toast.makeText(this, "식자재 정보가 수정되었습니다.", Toast.LENGTH_SHORT).show();
                finish();
            }
            else{
                Toast.makeText(this, "식자재 정보 수정 실패", Toast.LENGTH_SHORT).show();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}
