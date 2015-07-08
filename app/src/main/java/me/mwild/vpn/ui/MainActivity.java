package me.mwild.vpn.ui;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import butterknife.ButterKnife;
import butterknife.InjectView;
import me.mwild.vpn.R;
import me.mwild.vpn.VpnApp;

public class MainActivity extends AppCompatActivity {



    @InjectView(R.id.toolbar) Toolbar toolbar;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);
        VpnApp.get(getApplicationContext()).inject(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}
