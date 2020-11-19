package com.buios.buios;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageButton;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

public class DialogCategorySelect extends DialogFragment {

  final static int[] categorylist = new int[]{R.id.dialog_category_btn1, R.id.dialog_category_btn2,
      R.id.dialog_category_btn3,
      R.id.dialog_category_btn4, R.id.dialog_category_btn5, R.id.dialog_category_btn6};

  Bundle bundle;


  public DialogCategorySelect() {
  }

  public static DialogCategorySelect getInstance() {
    DialogCategorySelect e = new DialogCategorySelect();
    return e;
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View dialogView = inflater.inflate(R.layout.dialog_category_select, container, false);
    getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
    getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
    bundle = new Bundle();

    for (int i = 0; i < categorylist.length; i++) {
      final int num = i;
      ImageButton btn = dialogView.findViewById(categorylist[i]);
      btn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          bundle.putInt("click", num);
          Fragment frag = getFragmentManager().findFragmentByTag("itemdialog");
          frag.setArguments(bundle);
          getDialog().dismiss();
        }
      });
    }

    return dialogView;
  }
}
