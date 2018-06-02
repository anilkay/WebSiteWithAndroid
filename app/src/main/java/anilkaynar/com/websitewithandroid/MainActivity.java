package anilkaynar.com.websitewithandroid;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            Runnable r=new Runnable() {
                @Override
                public void run() {


                    InetAddress address = null;
                    try {
                        address = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    String hostAddr = address.getHostAddress();
                    ((TextView)

                            findViewById(R.id.txtvie1)).

                            setText(address.getHostName());
                }
            };
            new Thread(new Runnable() {
                @Override
                public void run() {

                    InetAddress address = null;
                    try {
                        address = InetAddress.getLocalHost();

                    } catch (UnknownHostException e) {
                        e.printStackTrace();
                    }
                    String hostAddr = address.getHostAddress();
                    String a=new String(address.getAddress());
                    ((TextView)

                            findViewById(R.id.txtvie1)).
                            setText(a);
                    Log.e("Ip Ankara ","a: "+getIPAddress(true));
                }
            }).start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        JavaWebServer javaWebServer=new JavaWebServer();
        try {
            javaWebServer.ip444();
            javaWebServer.main2(null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        //boolean isIPv4 = InetAddressUtils.isIPv4Address(sAddr);
                        boolean isIPv4 = sAddr.indexOf(':')<0;

                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                return delim<0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception ignored) { } // for now eat exceptions
        return ""; //Get ip address ve socket kısmı rahat bir şekilde çalışıyor.
    }
}
