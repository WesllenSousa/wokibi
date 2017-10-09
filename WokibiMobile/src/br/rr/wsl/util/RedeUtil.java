package br.rr.wsl.util;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Enumeration;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Build;

public class RedeUtil {
	
	public static String obtemIpLocal() {
        String ip = "";
        try {
            InetAddress addr = InetAddress.getLocalHost();
            ip = addr.getHostAddress();
        } catch (UnknownHostException e) {
        }
        return ip;
    }
	
	public static String obtemIp(Context context) {
		String ip = null;
        if(Build.VERSION.SDK_INT >= 14) {
        	ip = RedeUtil.obtemIp_4_0(context);
        } else {
        	ip = RedeUtil.obtemIp_2_3();
        }
        return ip;
	}
	
	public static String obtemIp_2_3() {
	    try {
	        for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements();) {
	            NetworkInterface intf = en.nextElement();
	            for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements();) {
	                InetAddress inetAddress = enumIpAddr.nextElement();
	                if (!inetAddress.isLoopbackAddress()) {
	                    return inetAddress.getHostAddress().toString();
	                }
	            }
	        }
	    } catch (SocketException ex) {
	    }
	    return null;
	}
	
	public static String obtemIp_4_0(Context context) {
		WifiManager wifiManager = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
		WifiInfo wifiInfo = wifiManager.getConnectionInfo();
		int ip = wifiInfo.getIpAddress();
		String ipString = String.format(
				"%d.%d.%d.%d",
				(ip & 0xff),
				(ip >> 8 & 0xff),
				(ip >> 16 & 0xff),
				(ip >> 24 & 0xff));
		return ipString;
	}

	public static Boolean wifiEstiverConectado(Context context) {
	    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo networkInfo = null;
	    if (connectivityManager != null) {
	        networkInfo = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
	    }
	    return networkInfo == null ? false : networkInfo.isConnected();
	}
	
	public static Boolean verificarEnderecoPrivado(String ip) {
		String parte = "";
        for(int i = 0; i < ip.length(); i++) {
            char c = ip.charAt(i);
            if (c == '.') {
                if (parte.equals("10")) { //Classe A
                    return true;
                } else if(parte.equals("192.168")) { //Classe C
                    return true;
                } 
            }
            parte += c;
        }
        return false;
    }
	
}
