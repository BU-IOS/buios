package com.buios.buios;

import android.os.Bundle;
import androidx.preference.PreferenceFragmentCompat;

// 설정창 내부 구현
public class FragmentSettingsInner extends PreferenceFragmentCompat {

  public FragmentSettingsInner() {

  }

  public static FragmentSettingsInner getInstance() {
    FragmentSettingsInner e = new FragmentSettingsInner();
    return e;
  }

  @Override
  public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
    addPreferencesFromResource(R.xml.settings);
  }


}

