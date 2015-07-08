package me.mwild.vpn.ui;

import android.content.Intent;
import android.net.VpnService;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import me.mwild.vpn.R;
import me.mwild.vpn.VpnApp;
import me.mwild.vpn.data.SstpVpnService;

public class MainActivity extends AppCompatActivity {

    private static final int REQUEST_SSTP = 0;

    @InjectView(R.id.toolbar) Toolbar toolbar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.inject(this);
        VpnApp.get(getApplicationContext()).inject(this);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }


    @OnClick(R.id.button_connect)
    protected void connect_onClick(View v) {
        Intent intent = VpnService.prepare(VpnApp.get(this));

        if (intent != null) {
            startActivityForResult(intent, 0);
        } else {
            onActivityResult(REQUEST_SSTP, RESULT_OK, null);
        }
    }


    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SSTP && resultCode == RESULT_OK) {
            Intent intent = new Intent(this, SstpVpnService.class);
            // todo putExtra
            startService(intent);
        }
    }
}
