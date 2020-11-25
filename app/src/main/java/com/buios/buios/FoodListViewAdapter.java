package com.buios.buios;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import java.util.ArrayList;

public class FoodListViewAdapter extends BaseAdapter {

  private final ArrayList<Food> listviewItemList = new ArrayList<Food>();
  static int[] imgList = new int[]{R.drawable.img_vegetable, R.drawable.img_fruit,
      R.drawable.img_seafood, R.drawable.img_milk, R.drawable.img_meat, R.drawable.img_meat};


  @Override
  public int getCount() {
    return listviewItemList.size();
  }

  @Override
  public Food getItem(int position) {
    return listviewItemList.get(position);
  }

  @Override
  public long getItemId(int position) {
    return position;
  }

  @Override
  public View getView(int position, View convertView, ViewGroup parent) {
//    final int pos = position;
    final Context context = parent.getContext();

    if (convertView == null) {
      LayoutInflater inflater = (LayoutInflater) context
          .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
      convertView = inflater.inflate(R.layout.item_list_layout, parent, false);
    }

    ImageView categoryView = convertView.findViewById(R.id.item_imgview);
    TextView nameView = convertView.findViewById(R.id.item_name_text);
    TextView dateView = convertView.findViewById(R.id.item_date_text);
    TextView memoView = convertView.findViewById(R.id.item_memo_text);

    Food f = listviewItemList.get(position);

    categoryView.setImageResource(imgList[f.category]);
    nameView.setText(f.name);
    dateView.setText(f.date);
    memoView.setText(f.memo);

    convertView.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View v) {

        DialogItem dialog = DialogItem.getInstance();
        Bundle bundle = new Bundle();
        bundle.putInt("itemimg", f.category);
        bundle.putString("itemname", f.name);
        bundle.putString("itemdate", f.date);
        bundle.putString("itemmemo", f.memo);
        dialog.setArguments(bundle);

        FragmentManager fm = ((AppCompatActivity) context).getSupportFragmentManager();
        dialog.show(fm, "itemdialog");

        fm.executePendingTransactions();

        // 데이터 변경시, DB 전송 및  갱신 구현

      }
    });

    return convertView;
  }


  public void addItem(Food f) {
    listviewItemList.add(f);
  }
}
