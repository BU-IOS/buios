package com.buios.buios;

import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

  private FragmentManager fragmentManager = getSupportFragmentManager();
  private FragmentItemList fragmentItemList = new FragmentItemList();
  private FragmentSearch fragmentSearch = new FragmentSearch();
  private FragmentSettings fragmentSettings = new FragmentSettings();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    FragmentTransaction transaction =  fragmentManager.beginTransaction();
    transaction.replace(R.id.main_layout, fragmentItemList).commitAllowingStateLoss();


    BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
    bottomNavigationView.setOnNavigationItemSelectedListener(new ItemSelectedListener());

  }
  class ItemSelectedListener implements BottomNavigationView.OnNavigationItemSelectedListener{
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
      FragmentTransaction transaction = fragmentManager.beginTransaction();

      switch(menuItem.getItemId())
      {
        case R.id.nav1:
          transaction.replace(R.id.main_layout, fragmentItemList).commitAllowingStateLoss();

          break;
        case R.id.nav2:
          transaction.replace(R.id.main_layout, fragmentSearch).commitAllowingStateLoss();
          break;
        case R.id.nav3:
          transaction.replace(R.id.main_layout, fragmentSettings).commitAllowingStateLoss();
          break;
      }
      return true;
    }
  }

}