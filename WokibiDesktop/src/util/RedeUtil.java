package util;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class RedeUtil {

    public static String obtemIpLocal() {
        String ip = null;
        try {
            InetAddress addr = InetAddress.getLocalHost();
            ip = addr.getHostAddress();
        } catch (UnknownHostException e) {
        }
        if (ip.equals("127.0.0.1") || ip.equals("localhost")) {
            return null;
        }
        return ip;
    }

    public static Boolean verificarEnderecoPrivado(String ip) {
        String parte = "";
        for (int i = 0; i < ip.length(); i++) {
            char c = ip.charAt(i);
            if (c == '.') {
                if (parte.equals("10")) { //Classe A
                    return true;
                } else if (parte.equals("192.168")) { //Classe C
                    return true;
                }
            }
            parte += c;
        }
        return false;
    }
    
}
