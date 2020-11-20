package com.buios.buios;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class FragmentSettings extends Fragment {

  FragmentSettingsInner frag;

  FragmentTransaction transaction;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {

    View rootView = inflater.inflate(R.layout.fragment_settings, container, false);

    transaction = getFragmentManager().beginTransaction();

    frag = FragmentSettingsInner.getInstance();
    // CODE AREA
    transaction.replace(R.id.fragment_settings_framelayout, frag).addToBackStack(null).commit();

    return rootView;
  }

}



