package com.buios.buios;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupMenu;
import android.widget.PopupMenu.OnMenuItemClickListener;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.ArrayList;


public class FragmentItemList extends Fragment {

  private ImageView logo_img;
  private TextView youtube_text;
  private Button[] category_btn;
  private Button sort_btn;
  private ListView itemlist;
  private FloatingActionButton floatingbtn;
  final static int[] cateogry_list = new int[]{R.id.fragment_itemlist_category1_btn,
      R.id.fragment_itemlist_category2_btn, R.id.fragment_itemlist_category3_btn,
      R.id.fragment_itemlist_category4_btn, R.id.fragment_itemlist_category5_btn,
      R.id.fragment_itemlist_category6_btn};
  static int filter = -1;
  private DialogItem dialogItem;
  boolean sortTooggle = true;

  private Food f;

  FragmentManager fm;

  public FragmentItemList() {
  }

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_item_list, container, false);

    fm = getFragmentManager();

    FoodListViewAdapter adapter = new FoodListViewAdapter(getContext());

    logo_img = rootView.findViewById(R.id.fragment_itemlist_logo_img);
    youtube_text = rootView.findViewById(R.id.main_youtube_text);
    category_btn = new Button[6];
    for (int i = 0; i < cateogry_list.length; i++) {
      category_btn[i] = rootView.findViewById(cateogry_list[i]);
      final int n = i;
      category_btn[i].setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          int number = n;
          if (filter == number) {
            filter = -1;
            adapter.updateList();
//            category_btn[number].setBackgroundColor(Color.WHITE);
          } else {
            filter = number;
            adapter.updateList(number);
//            category_btn[number].setBackgroundColor(container.getResources().getColor(R.color.green_2));
          }
        }
      });
    }
    sort_btn = rootView.findViewById(R.id.fragment_itemlist_sort_btn);
    itemlist = rootView.findViewById(R.id.fragment_itemlist_listview);
    floatingbtn = rootView.findViewById(R.id.fragment_itemlist_floating_btn);

    itemlist.setAdapter(adapter);

    // DB 불러오는 부분 함수로 따로 선언
    // oncReateview 바로 로드

    FoodDBManager db = new FoodDBManager(getContext(), "FOOD_DB", null, 1);
    ArrayList<Food> foodlist = db.selectAll(true);

    for (Food f : foodlist) {
      adapter.addItem(f);
    }

    /*

     // Dialog 종료 후에 데이터 변경 시 구현 예정
    adapter.registerDataSetObserver(new DataSetObserver() {
      @Override
      public void onChanged() {
        super.onChanged();
      }
    });

     */

    floatingbtn.setOnClickListener(new Button.OnClickListener() {

      @Override
      public void onClick(View v) {

        // 아이템을 추가하는 다이얼로그 작성 예정.
        // MARK : 현재 코드는 테스트용임

        Bundle bundle = new Bundle();
        bundle.putBoolean("isAdding", true);
        DialogItem dialog = DialogItem.getInstance();
        dialog.setArguments(bundle);
        dialog.show(fm, "itemdialog");

        fm.executePendingTransactions();

        dialog.getDialog().setOnDismissListener(new DialogInterface.OnDismissListener() {
          @Override
          public void onDismiss(DialogInterface dialog) {

            if (getArguments() != null) {
              int img = getArguments().getInt("itemimg", -1);
              String name = getArguments().getString("itemname", "");
              String date = getArguments().getString("itemdate", "");
              String memo = getArguments().getString("itemmemo", "");

              Log.d("item_dialog_test", Integer.toString(img));
              Log.d("item_dialog_test", name);
              Log.d("item_dialog_test", date);
              Log.d("item_dialog_test", memo);

            } else {
              Log.d("item_dialog_Test", "NULL");
            }

            adapter.updateList();
          }
        });
        //DB 추가

      }
    });

    sort_btn.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {
        if(sortTooggle){
          sortTooggle = false;
          sort_btn.setText("가나다 순");
          adapter.setSortByDate(false);
          adapter.updateList();
        } else {
          sortTooggle = true;
          sort_btn.setText("유통기한 순");
          adapter.setSortByDate(true);
          adapter.updateList();
        }

      }
    });
    // DB 불러오는 부분 함수로 따로 선언
    // oncReateview 바로 로드

    // CODE AREA

    return rootView;

  }
}


