package me.mwild.vpn;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.mwild.vpn.VpnApp;
import me.mwild.vpn.data.DataModule;
import me.mwild.vpn.ui.UiModule;

@Module(
        includes = {
                DataModule.class,
                UiModule.class
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
