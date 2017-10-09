package controle.mensagens.modem;

/**
 *
 * @author Wesllen Sousa Lima
 */
import java.util.BitSet;

public class ComputeSmsData {

    private String telNum;
    private String telNumSMSC;
    private String telNumLen;
    private String telNumSMSCLen;
    private String pduTxt;
    private String pduTxtLen;
    private String rcvdPdu;
    private String rcvdSMSC;
    private String rcvdSenderNum;
    private String rcvdPduTxt;

    private void txtToSmsPdu(String testo) {
        // first of all set the msg lenght
        pduTxtLen = Integer.toHexString(testo.length());

        if (pduTxtLen.length() < 2) {
            pduTxtLen = "0" + pduTxtLen;
        }
        pduTxtLen = pduTxtLen.toUpperCase();

        byte[] ccc = testo.getBytes(); // get ASCII(?) bytes value

        BitSet bs = new BitSet(testo.length() * 8);
        int l = 0;

        for (int i = 0; i < testo.length(); i++) {
            for (int subI = 0; subI < 7; subI++) {
                l = i * 7 + subI;
                if ((ccc[i] & (1 << subI)) != 0) {
                    bs.set(l);
                }
            }
        }
        l++;

        int ll;

        // check if size fit a byte
        if (((l / 56) * 56) != l) { // 56 = 7*8 = M.C.M.
            ll = (l / 8) + 1;
        } else {
            ll = l / 8;
        }
        if (ll == 0) {
            ll++;
        }

        byte[] b = new byte[ll];
        for (int i = 0; i < ll; i++) {
            for (int subI = 0; subI < 8; subI++) {
                if ((l + 1) > (i * 8 + subI)) {  // should be less then last 1 in bs
                    if (bs.get(i * 8 + subI)) {
                        b[i] |= (byte) (1 << subI);
                    }
                }
            }
        }

        pduTxt = "";
        for (int i = 0; i < ll; i++) {
            String str1 = Integer.toHexString((int) b[i]);   // convert # in string
            if (str1.length() < 2) {
                str1 = '0' + str1;
            }
            str1 = (str1.substring(str1.length() - 2, str1.length()));
            pduTxt += str1.toUpperCase();
        }

        System.out.println("String convertita   <" + pduTxt + ">");
        System.out.println("String riconvertita <" + smsPduToTxt(pduTxt) + ">");
    }

    public void setAsciiTxt(String s) {
        txtToSmsPdu(s);
    }

    private String encodeTelNum(String str) {
        // remove trailing '+' if any
        if (str.charAt(0) == '+') {
            str = str.substring(1, str.length());
        }

        // check if num length is even or odd
        int l = str.length();
        if (((l / 2) * 2) != l) {
            str += 'F';
            l++;
        }

        String tmpStr = new String();
        // swap chars
        for (int cnt = 0; cnt < (l / 2); cnt++) {
            tmpStr += str.charAt(cnt * 2 + 1);
            tmpStr += str.charAt(cnt * 2);
        }

        return tmpStr;
    }

    private String encodeTelNumLen(String str) {
        String numLen = new String();

        // remove trailing '+' if any
        if (str.charAt(0) == '+') {
            str = str.substring(1, str.length());
        }

        // check if num length is even or odd
        int l = str.length();
        numLen = Integer.toHexString(l);
        if (numLen.length() < 2) {
            numLen = "0" + numLen.toUpperCase();
        }
        return numLen;
    }

    public void setTelNum(String s) {
        telNum = encodeTelNum(s);
        telNumLen = encodeTelNumLen(s);
    }

    public void setSMSCTelNum(String s) {
        telNumSMSC = encodeTelNum(s);
        telNumSMSCLen = encodeTelNumLen(s);
    }

    public String getCompletePduData() {
        String pduData = new String();
        pduData = "11";                 // first octet of SMS Submit
        pduData += "00";                // let telephone set msg reference
        pduData += telNumLen;           // tel # length
        pduData += "91";                // tel # is int'l format
        pduData += telNum;              // tel Num in GSM format
        pduData += "00";                // protocol identifier TP-PID
        pduData += "00";                // pdu data is encoded 7bit data
        pduData += "AA";                // TP validity period
        pduData += pduTxtLen;           // length of text data
        pduData += pduTxt;              // message encoded data

        return pduData;
    }

    public String getSMSCPduData() {
        String pduData = new String();
        pduData = telNumSMSCLen;           // tel # length
        pduData += "91";                // tel # is int'l format
        pduData += telNumSMSC;              // tel Num in GSM format

        return pduData;
    }

    public void setRcvdPdu(String s) {
        rcvdPdu = s;
        computeRcvdPdu(rcvdPdu);
    }

    public String getRcvdPduSMSC() {
        return rcvdSMSC;
    }

    public String getRcvdSenderNumber() {
        return rcvdSenderNum;
    }

    public String getRcvdPduTxt() {
        return rcvdPduTxt;
    }

    private void computeRcvdPdu(String s) {
        Integer lenOfSMSC = new Integer(Integer.parseInt(s.substring(0, 2), 16));

        // compute SMSC infos
        // ------------------
        rcvdSMSC = new String();
        rcvdSMSC = s.substring(2, 4);    // add type of address (future evaluation?)
        rcvdSMSC += '.';                // add a separator
        // decode SMSC number and add to string
        rcvdSMSC += decodeTelNum(s.substring(4, 4 + ((lenOfSMSC.intValue() - 1) * 2)));
//        System.out.println("dec'd SMSC info = " + rcvdSMSC);

        // remove part of message just evaluated                                                                                                 
        int lenOfRemovedTxt = 2 + (lenOfSMSC.intValue() * 2);
        s = s.substring(lenOfRemovedTxt, s.length());

        // remove first octet of SMS-DELIVER msg (future evaluation!)
        lenOfRemovedTxt = 2;
        s = s.substring(lenOfRemovedTxt, s.length());

        // evaluate sender address length
        Integer lenOfSenderNum = new Integer(Integer.parseInt(s.substring(0, 2), 16));

        rcvdSenderNum = "";

        int tmpLenOfSender = lenOfSenderNum.intValue();
        if ((tmpLenOfSender / 2 * 2) != tmpLenOfSender) {
            tmpLenOfSender++;
        }
        rcvdSenderNum += decodeTelNum(s.substring(4, 4 + tmpLenOfSender));

        // remove computed text        
        tmpLenOfSender += 4;
        s = s.substring(tmpLenOfSender, s.length());

        // remove TP-ID (2 octet) (future evaluation?)
        lenOfRemovedTxt = 2;
        s = s.substring(lenOfRemovedTxt, s.length());
        // remove TP-DCS (2 octet) (future evaluation?)        
        lenOfRemovedTxt = 2;
        s = s.substring(lenOfRemovedTxt, s.length());
        // remove Time stamp (14 octet) (future evaluation?)        
        lenOfRemovedTxt = 14;
        s = s.substring(lenOfRemovedTxt, s.length());
        // remove length of pdu data
        lenOfRemovedTxt = 2;
        s = s.substring(lenOfRemovedTxt, s.length());

        rcvdPduTxt = smsPduToTxt(s);
    }

    private String decodeTelNum(String s) {
        String decodedStr = new String();

        for (int i = 0; i < (s.length() / 2); i++) {
            decodedStr += s.charAt(i * 2 + 1);
            decodedStr += s.charAt(i * 2);
        }

        if (decodedStr.charAt(decodedStr.length() - 1) == 'F') {
            decodedStr = decodedStr.substring(0, decodedStr.length() - 1);
        }

        return decodedStr;
    }

    private String smsPduToTxt(String s) {
        byte[] bt = new byte[s.length() / 2];
        for (int i = 0; i < (s.length() / 2); i++) {
            bt[i] = (byte) (Integer.parseInt(s.substring(i * 2, i * 2 + 1), 16) * 16);
            bt[i] += (byte) Integer.parseInt(s.substring(i * 2 + 1, i * 2 + 2), 16);
        }

        BitSet bs = new BitSet(s.length() / 2 * 8);
        int l = 0;

        for (int i = 0; i < (s.length() / 2); i++) {
            for (int subI = 0; subI < 8; subI++) {
                l = i * 8 + subI;
                if ((bt[i] & (1 << subI)) != 0) {
                    bs.set(l);
                }
            }
        }
        l++;

        int ll;

        ll = (l / 7);
        if (ll == 0) {
            ll++;
        }

        byte[] b = new byte[ll];
        for (int i = 0; i < ll; i++) {
            for (int subI = 0; subI < 7; subI++) {
                if ((l + 1) > (i * 7 + subI)) {  // should be less then last 1 in bs
                    if (bs.get(i * 7 + subI)) {
                        b[i] |= (byte) (1 << subI);
                    }
                }
            }
        }

        if (b[ll - 1] == 0) {
            return (new String(b, 0, ll - 1));	// if last byte == 0 skip it
        } else {
            return (new String(b));
        }
    }
    
}
