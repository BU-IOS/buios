package com.buios.buios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class FragmentItemList extends Fragment {

  private ImageView logo_img;
  private TextView youtube_text;
  private Button[] category_btn;
  private Button sort_btn;
  private ListView itemlist;
  private FloatingActionButton floatingbtn;
  private int[] cateogry_list = new int[]{R.id.fragment_itemlist_category1_btn,
      R.id.fragment_itemlist_category2_btn, R.id.fragment_itemlist_category3_btn,
      R.id.fragment_itemlist_category4_btn, R.id.fragment_itemlist_category5_btn,
      R.id.fragment_itemlist_category6_btn};

  private DialogItem dialogItem;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_item_list, container, false);

    logo_img = rootView.findViewById(R.id.fragment_itemlist_logo_img);
    youtube_text = rootView.findViewById(R.id.main_youtube_text);
    category_btn = new Button[6];
    for (int i = 0; i < cateogry_list.length; i++) {
      category_btn[i] = (Button) rootView.findViewById(cateogry_list[i]);
    }
    sort_btn = rootView.findViewById(R.id.fragment_itemlist_sort_btn);
    itemlist = rootView.findViewById(R.id.fragment_itemlist_listview);
    floatingbtn = rootView.findViewById(R.id.fragment_itemlist_floating_btn);

    floatingbtn.setOnClickListener(new Button.OnClickListener() {
      @Override
      public void onClick(View v) {

        // 아이템을 추가하는 다이얼로그 작성 예정.
        // MARK : 현재 코드는 테스트용임
        FragmentManager fm = getFragmentManager();
        DialogItem dialog = DialogItem.getInstance();
        dialog.show(fm, "itemdialog");

      }
    });

    // DB 불러오는 부분 함수로 따로 선언
    // oncReateview 바로 로드

    // CODE AREA

    return rootView;

  }


}
