
package br.rr.wsl.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class Criptografia {

    public static String SHA(String texto) {
        MessageDigest algoritmoCriptografia = null;

        try {
            algoritmoCriptografia = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException ex) {
        }

        byte[] bytes = texto.getBytes();

        algoritmoCriptografia.reset();  //reseta.
        algoritmoCriptografia.update(bytes); //atualiza o algritmo com os bytes do arquivo

        byte[] hash = algoritmoCriptografia.digest(); //finaliza a implementacao dos bytes

        //transforma a mensagem transformadas nos algoritmos regeridos.
        String digest = "";
        for (int i = 0; i < hash.length; i++) {
            int v = hash[i] & 0xFF;
            if (v < 16) {
                digest += "0";//caso for menor que 16 completa com 0.
            }
            digest += Integer.toString(v, 16).toUpperCase() + "";
        }

        return digest;
    }
    
    public static String MD5(String texto) {
        String sign = texto;

        try {
            java.security.MessageDigest md =
                    java.security.MessageDigest.getInstance("MD5");
            md.update(sign.getBytes());
            byte[] hash = md.digest();
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < hash.length; i++) {
                if ((0xff & hash[i]) < 0x10) {
                    hexString.append(
                            "0" + Integer.toHexString((0xFF & hash[i])));
                } else {
                    hexString.append(Integer.toHexString(0xFF & hash[i]));
                }
            }
            sign = hexString.toString();
        } catch (Exception nsae) {
            System.out.println(nsae);
        }
        return sign;
    }
    
}
