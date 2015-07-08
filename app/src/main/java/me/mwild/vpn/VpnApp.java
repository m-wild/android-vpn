package me.mwild.vpn;

import android.app.Application;
import android.content.Context;

import dagger.ObjectGraph;

public class VpnApp extends Application {
    private ObjectGraph objectGraph;

    @Override public void onCreate() {
        super.onCreate();

        buildAndInject();
    }

    public void buildAndInject() {
        objectGraph = ObjectGraph.create(Modules.list(this));
        objectGraph.inject(this);
    }

    // allows you to get the app context from anywhere
    public static VpnApp get(Context context) {
        return (VpnApp) context.getApplicationContext();
    }

    // and inject things from the object graph
    public void inject(Object o) { objectGraph.inject(o); }

}
