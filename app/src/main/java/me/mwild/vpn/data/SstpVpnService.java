package me.mwild.vpn.data;


import android.content.Intent;
import android.net.VpnService;
import android.os.ParcelFileDescriptor;
import android.util.Log;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;

public class SstpVpnService extends VpnService {

    private Thread _thread;
    private ParcelFileDescriptor _interface;
    //a. configure a builder for the interface
    Builder builder = new Builder();

    // services interface
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        // start a new session by creating a new thread
        _thread = new Thread(new Runnable() {
            @Override public void run() {
                try {

                    //a. configure the TUN and get the interface
                    _interface = builder.setSession("SstpVpnService")
                            .addAddress("192.168.0.1", 24)
                            .addDnsServer("8.8.8.8")
                            .addRoute("0.0.0.0", 0)
                            .establish();

                    //b. packets to be sent are queued in this input stream
                    FileInputStream in = new FileInputStream(
                            _interface.getFileDescriptor());

                    //b. packets received need to be written to this output stream
                    FileOutputStream out = new FileOutputStream(
                            _interface.getFileDescriptor());

                    //c. the UDP channel can be used to pass/get ip package to/from the server
                    DatagramChannel tunnel = DatagramChannel.open();
                    // connect to the server
                    tunnel.connect(new InetSocketAddress("192.168.1.69", 8888));

                    //d. protect this socket, so packet sent by it will not feedback to the vpn itself
                    protect(tunnel.socket());

                    //e. use a loop to pass packets
                    for(;;) {
                        //get packet with in
                        //put packet to tunnel
                        //get packet form tunnel
                        //return packet with out
                        //sleep is a must
                        Thread.sleep(100);
                    }


                } catch (Exception ex) {
                    Log.e("SstpVpnService", ex.getMessage(), ex);

                } finally {
                    try {
                        if (_interface != null) {
                            _interface.close();
                            _interface = null;
                        }
                    } catch (Exception ex) {}
                }
            }
        }, "SstpVpnRunnable");

        // start the service
        _thread.start();
        return START_STICKY;
    }


    @Override public void onDestroy() {
        if (_thread != null) {
            _thread.interrupt();
        }
        super.onDestroy();
    }
}
