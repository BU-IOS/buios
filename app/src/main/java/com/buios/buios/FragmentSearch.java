package com.buios.buios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragmentSearch extends Fragment {
  private ListView listview;
  private EditText search_keyword;
  private TextView youtube_text;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View rootView =  inflater.inflate(R.layout.fragment_search, container, false);
    // CODE AREA
    search_keyword = rootView.findViewById(R.id.fragment_search_keyword);
    youtube_text = rootView.findViewById(R.id.fragment_search_youtube_text);
    listview = rootView.findViewById(R.id.fragment_search_list);


    return rootView;
  }
}
