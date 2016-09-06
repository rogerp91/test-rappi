package com.github.rappi.theme;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.github.rappi.R;
import com.github.rappi.activity.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThemeActivity extends BaseActivity {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        if (savedInstanceState == null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.main_content, ThemeFragment.newInstance())
                    .setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out).commit();

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

}