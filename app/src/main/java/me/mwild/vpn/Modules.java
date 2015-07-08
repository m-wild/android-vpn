package me.mwild.vpn;

final class Modules {
    static Object[] list(VpnApp app) {
        return new Object[] {
                new VpnModule(app)

                // we could also add a "debug" module here
                //,new DebugVpnModule(app)
        };
    }
}
