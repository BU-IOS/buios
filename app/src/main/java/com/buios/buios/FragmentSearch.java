package com.buios.buios;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.util.ArrayList;

public class FragmentSearch extends Fragment {
  private ListView listview;
  private EditText search_keyword;
  private TextView youtube_text;
  private  FoodListViewAdapter adapter;
  private ListView listView;
  private EditText keywordEdit;
  private TextView youtubeLink;
  private FoodDBManager db;
  private ArrayList<Food> foodlist;


  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View rootView = inflater.inflate(R.layout.fragment_search, container, false);
    keywordEdit = rootView.findViewById(R.id.fragment_search_keyword);
    listView = rootView.findViewById(R.id.fragment_search_list);
    adapter = new FoodListViewAdapter(getContext());
    listView.setAdapter(adapter);
    youtubeLink = rootView.findViewById(R.id.fragment_search_youtube_text);
    listView.setTextFilterEnabled(true);

    keywordEdit.addTextChangedListener(new TextWatcher() {
      @Override
      public void beforeTextChanged(CharSequence s, int start, int count, int after) {

      }

      @Override
      public void onTextChanged(CharSequence s, int start, int before, int count) {
//        Log.d("SearchFragment", "onTextChanged, " + s.toString());
//        listView.setFilterText(s.toString());
      }

      @Override
      public void afterTextChanged(Editable s) {
        Log.d("SearchFragment", "afterTextChanged, " + s.toString());
        String filterText = s.toString();
        if (filterText.length() > 0) {
          listView.setFilterText(filterText);
        } else {
          listView.clearTextFilter();
        }
      }
    });

    return rootView;
  }

  @Override
  public void onResume() {
    super.onResume();
    db = new FoodDBManager(getContext(), "FOOD_DB", null, 1);
    foodlist = db.selectAll(true);
    adapter.updateItems(foodlist);
  }


}
