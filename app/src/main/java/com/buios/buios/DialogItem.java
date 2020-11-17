package com.buios.buios;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;


public class DialogItem extends DialogFragment {

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


  FragmentManager fm;

  public DialogItem() {
  }

  public static DialogItem getInstance() {
    DialogItem e = new DialogItem();
    return e;
  }


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View dialogView = inflater.inflate(R.layout.dialg_itemview, container, false);
    getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

    fm = getFragmentManager();

    isEditing = false;

    category_img = dialogView.findViewById(R.id.dialog_itemview_img);
    name_text = dialogView.findViewById(R.id.dialog_itemview_name_text);
    date_text = dialogView.findViewById(R.id.dialog_itemview_date_text);
    memo_text = dialogView.findViewById(R.id.dialog_itemview_memo_text);

    category_imgbtn = dialogView.findViewById(R.id.dialog_itemview_imgbtn);
    name_edit = dialogView.findViewById(R.id.dialog_itemview_name_edit);
    date_edit = dialogView.findViewById(R.id.dialog_itemview_date_edit);
    memo_edit = dialogView.findViewById(R.id.dialog_itemview_memo_edit);

    deletebtn = dialogView.findViewById(R.id.dialog_itemview_deletebtn);
    modifybtn = dialogView.findViewById(R.id.dialog_itemview_modifybtn);



    /*
    DB 작성후 작성
    category, name, date, memo load 후 setText, src 설정
     */

    setimg(3);
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

    deletebtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        dismiss();
      }
    });

    category_imgbtn.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        //dialog fragment 수행 -> 카테고리 번호 받아옴
        DialogCategorySelect dialog = DialogCategorySelect.getInstance();
        dialog.show(fm, "categorydialog");

        fm.executePendingTransactions();

        dialog.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
          @Override
          public void onDismiss(DialogInterface dialog) {
            int i = getArguments().getInt("click", -1);
            if (i != -1) {
              setimg(i);
            }
          }
        });
      }
    });

    return dialogView;
  }


  @Override
  public void onResume() {
    super.onResume();

    // 화면 비율 조절
    Window window = getDialog().getWindow();
    Point size = new Point();

    Display display = window.getWindowManager().getDefaultDisplay();
    display.getSize(size);

    int width = size.x;
    int height = size.y;

    window.setLayout((int) (width * 0.75), (int) (height * 0.6));
    window.setGravity(Gravity.CENTER);


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


