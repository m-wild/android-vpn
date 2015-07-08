package me.mwild.vpn;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.mwild.vpn.VpnApp;
import me.mwild.vpn.data.DataModule;

@Module(
        includes = {
                DataModule.class
        },
        injects = {
                VpnApp.class
        }
)
public class VpnModule {

    private VpnApp app;

    public VpnModule(VpnApp app) { this.app = app; }

    @Provides @Singleton
    Application provideApplication() { return app; }

}
