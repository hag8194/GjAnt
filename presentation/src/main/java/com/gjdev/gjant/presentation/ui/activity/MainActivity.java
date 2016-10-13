package com.gjdev.gjant.presentation.ui.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;


import com.gjdev.gjant.presentation.ui.fragment.MangasFragment;
import com.gjdev.hugo.gjant.R;

import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    ButterKnife.bind(this);

    getSupportFragmentManager().beginTransaction()
        .add(R.id.main, new MangasFragment())
        .commit();

  }
}
