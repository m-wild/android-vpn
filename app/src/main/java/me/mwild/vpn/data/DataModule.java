package me.mwild.vpn.data;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.mwild.vpn.R;

@Module(
        complete = false,
        library = true
)
public class DataModule {

    @Provides @Singleton
    SharedPreferences provideSharedPreferences(Application app) {
        return app.getSharedPreferences(app.getString(R.string.key_shared_prefs), Context.MODE_PRIVATE);
    }

}
