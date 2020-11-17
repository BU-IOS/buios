package com.buios.buios;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;

public class DialogItem extends Dialog {

  private Context context;

  private boolean isEditing;

  // View
  ImageView category_img;
  TextView name_text, date_text, memo_text;

  // Edit
  ImageButton category_imgbtn;
  EditText name_edit, date_edit, memo_edit;

  Button deletebtn;
  Button modifybtn;

  private String nametxt, datetxt, memotxt;

  // 생성자에서 넘겨주거나 dialog 값 넘기기
  public DialogItem(@NonNull Context context) {
    super(context);
    this.context = context;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.dialg_itemview);
    this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    isEditing = false;

    category_img = findViewById(R.id.dialog_itemview_img);
    name_text = findViewById(R.id.dialog_itemview_name_text);
    date_text = findViewById(R.id.dialog_itemview_date_text);
    memo_text = findViewById(R.id.dialog_itemview_memo_text);

    category_imgbtn = findViewById(R.id.dialog_itemview_imgbtn);
    name_edit = findViewById(R.id.dialog_itemview_name_edit);
    date_edit = findViewById(R.id.dialog_itemview_date_edit);
    memo_edit = findViewById(R.id.dialog_itemview_memo_edit);

    deletebtn = findViewById(R.id.dialog_itemview_deletebtn);
    modifybtn = findViewById(R.id.dialog_itemview_modifybtn);

    /*
    DB 작성후 작성
    category, name, date, memo load 후 setText, src 설정
     */

    setimg(4);
    setname("name");
    setdate("date");
    setmemo("memo");



    modifybtn.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View v) {
        if (!isEditing) {
          isEditing = true;

          category_img.setVisibility(View.GONE);
          category_imgbtn.setVisibility(View.VISIBLE);

          name_text.setVisibility(View.GONE);
          date_text.setVisibility(View.GONE);
          memo_text.setVisibility(View.GONE);

          name_edit.setVisibility(View.VISIBLE);
          date_edit.setVisibility(View.VISIBLE);
          memo_edit.setVisibility(View.VISIBLE);

          modifybtn.setText("수정 완료");
          deletebtn.setText("취소");

          /*
          액션 변경 예정
          modifybtn.setOnClickListener();
          deletebtn.setOnClickListener();
           */

        } else {
          isEditing = false;

          category_img.setVisibility(View.VISIBLE);
          category_imgbtn.setVisibility(View.GONE);

          name_text.setVisibility(View.VISIBLE);
          date_text.setVisibility(View.VISIBLE);
          memo_text.setVisibility(View.VISIBLE);

          name_edit.setVisibility(View.GONE);
          date_edit.setVisibility(View.GONE);
          memo_edit.setVisibility(View.GONE);

          modifybtn.setText("수정 완료");
          deletebtn.setText("취소");

         /*
          액션 변경 예정
          modifybtn.setOnClickListener();
          deletebtn.setOnClickListener();
           */

        }
      }
    });


  }

  protected void setimg(int number) {
    int[] imglist = new int[]{R.drawable.img_vegetable, R.drawable.img_fruit,
        R.drawable.img_seafood,
        R.drawable.img_milk, R.drawable.img_meat, R.drawable.img_food};

    category_imgbtn.setBackgroundResource(imglist[number]);
    category_img.setBackgroundResource(imglist[number]);
  }

  protected void setname(String txt) {
    name_text.setText(txt);
    name_edit.setText(txt);
  }

  protected void setdate(String txt) {
    date_text.setText(txt);
    date_edit.setText(txt);
  }

  protected void setmemo(String txt) {
    memo_text.setText(txt);
    memo_edit.setText(txt);
  }


}


