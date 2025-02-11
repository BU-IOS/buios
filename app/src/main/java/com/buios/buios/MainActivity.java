package com.buios.buios;

import android.os.Bundle;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

  private final FragmentManager fragmentManager = getSupportFragmentManager();
  private final FragmentItemList fragmentItemList = new FragmentItemList();
  private final FragmentSearch fragmentSearch = new FragmentSearch();
  private final FragmentSettings fragmentSettings = new FragmentSettings();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
    bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

    FragmentTransaction transaction = fragmentManager.beginTransaction();
    transaction.replace(R.id.main_layout, fragmentItemList).commitAllowingStateLoss();


  }

  class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener {

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
      FragmentTransaction transaction = fragmentManager.beginTransaction();

      switch (menuItem.getItemId()) {
        case R.id.nav1:
          transaction.replace(R.id.main_layout, fragmentItemList).disallowAddToBackStack().commitAllowingStateLoss();

          break;
        case R.id.nav2:
          transaction.replace(R.id.main_layout, fragmentSearch).disallowAddToBackStack().commitAllowingStateLoss();
          break;
        case R.id.nav3:
          transaction.replace(R.id.main_layout, fragmentSettings).disallowAddToBackStack().commitAllowingStateLoss();
          break;
      }
      return true;
    }
  }

}