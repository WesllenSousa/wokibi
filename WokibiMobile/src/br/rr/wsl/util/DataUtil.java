package br.rr.wsl.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class DataUtil {

    public static Date adicionaDias(Date data, Integer dias) {
        for (int i = 1; i <= dias; i++) {
            data.setTime(data.getTime() + 1 * 1000 * 60 * 60 * 24);
        }
        return data;
    }

    public static Calendar adicionaDias(Calendar data, Integer dias) {
        Date d = data.getTime();
        for (int i = 1; i <= dias; i++) {
            d.setTime(d.getTime() + 1 * 1000 * 60 * 60 * 24);
        }
        data.setTime(d);
        return data;
    }

    public static Calendar converteDateParaCalendar(Date data) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        return cal;
    }

    public static Calendar preparaDataInicio(Calendar data) {
        if (data != null) {
            data.set(Calendar.HOUR_OF_DAY, 00);
            data.set(Calendar.MINUTE, 00);
            data.set(Calendar.SECOND, 00);
            return data;
        }
        return null;
    }

    public static Calendar setaHoraCalendar(Calendar data, Integer h, Integer m, Integer s) {
        if (data != null) {
            data.set(Calendar.HOUR_OF_DAY, h);
            data.set(Calendar.MINUTE, m);
            data.set(Calendar.SECOND, s);
            return data;
        }
        return null;
    }

    public static Calendar preparaDataFinal(Calendar data) {
        if (data != null) {
            data.set(Calendar.HOUR_OF_DAY, 23);
            data.set(Calendar.MINUTE, 59);
            data.set(Calendar.SECOND, 59);
            return data;
        }
        return null;
    }

    public static Calendar alteraDiaData(Calendar data, Integer dia) {
        if (data != null) {
            data.set(Calendar.DAY_OF_MONTH, dia);
            return data;
        }
        return null;
    }

    public static String formataMes(Calendar data) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("MM");
        return dataFormat.format(data.getTime());
    }

    public static String formataDia(Calendar data) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd");
        return dataFormat.format(data.getTime());
    }

    public static String formataAno(Calendar data) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy");
        return dataFormat.format(data.getTime());
    }

    public static String formataDiaMes(Calendar data) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM");
        return dataFormat.format(data.getTime());
    }

    public static Date formataData(String data) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            return dataFormat.parse(data);
        } catch (ParseException ex) {
        }
        return null;
    }

    public static Date formataDataHora(String data) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
        try {
            return dataFormat.parse(data);
        } catch (ParseException ex) {
        }
        return null;
    }

    public static Calendar formataDataCalendar(String data) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(data));
            return cal;
        } catch (ParseException e) {
            Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }
    
    public static Calendar formataDataCalendarHHmmSS(String data) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
            Calendar cal = Calendar.getInstance();
            cal.setTime(sdf.parse(data));
            return cal;
        } catch (ParseException e) {
            Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, e);
        }
        return null;
    }

    public static Date formataHora(String data) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm");
        try {
            return dataFormat.parse(data);
        } catch (ParseException ex) {
        }
        return null;
    }

    public static String formataData(Date data) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dataFormat.format(data);
    }

    public static String formataHoraMinuto(Date data) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm");
        return dataFormat.format(data);
    }

    public static String formataData(Calendar data) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy");
        return dataFormat.format(data.getTime());
    }

    
    public static String formataDataHora(Calendar data) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
        return (dataFormat.format(data.getTime()));
    }

    public static String formataDataHHmmSS(Calendar data) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yyyy-HH:mm:ss");
        return (dataFormat.format(data.getTime()));
    }

    public static String formataSegundo(Calendar data) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("ss");
        return (dataFormat.format(data.getTime()));
    }

    public static String formataHoraMinuto(Calendar data) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm");
        return dataFormat.format(data.getTime());
    }

    public static String formataHora(Calendar data) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("HH");
        return dataFormat.format(data.getTime());
    }

    public static String formataHoraHHmmSS(Calendar data) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("HH:mm:ss");
        return dataFormat.format(data.getTime());
    }

    public static int quantidadeDias(Date inicial, Date finall) {
        int diffDays = (int) ((inicial.getTime() - finall.getTime()) / (24 * 60 * 60 * 1000));  // 7
        return diffDays;
    }

    public static String quantidadeHoraMinutoSegundo(Calendar inicial, Calendar finall) {
        int difMili = (int) ((finall.getTimeInMillis() - inicial.getTimeInMillis()) / 1000);
        int diffHor = (int) difMili / (int) 3600;
        int diffMin = (int) (difMili % 3600) / (int) 60;
        int diffSeg = (difMili % 3600) % 60;
        return diffHor + ":" + diffMin + ":" + diffSeg;
    }

    public static String getDataDia() {
        Date date = new Date();
        DateFormat formatData = DateFormat.getDateInstance(DateFormat.MEDIUM);
        return formatData.format(date);
    }

    public static String getHoraDia() {
        Date date = new Date();
        DateFormat formatData = DateFormat.getTimeInstance(DateFormat.MEDIUM);
        return formatData.format(date);
    }

    public static Boolean verificarDataMaiorDataAtual(Calendar data) {
        Date date = new Date();
        Calendar hoje = Calendar.getInstance();
        hoje.setTime(date);

        long dataHoje = hoje.getTimeInMillis();
        long dataTeste = data.getTimeInMillis();

        if (dataTeste > dataHoje) {
            return true;
        }

        return false;
    }

    public static Boolean verificarDataMaior(Calendar data1, Calendar data2) {
        long _data1 = data1.getTimeInMillis();
        long _data2 = data2.getTimeInMillis();
        if (_data1 > _data2) {
            return true;
        }
        return false;
    }

    public static String formataNanchoCalendarDataSimples(Object valor) {
        java.text.SimpleDateFormat formata = new java.text.SimpleDateFormat("dd/MM/yy");
        String data = formata.format(valor);

        return data;
    }
    

    public static Date StringParaData(String objeto) {

        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date(0);
        try {
            date = format.parse(objeto);
            return date;
        } catch (ParseException e) {
        }
        return null;
    }
    
    public static Date formataDataSimples(String data) {
        SimpleDateFormat dataFormat = new SimpleDateFormat("dd/MM/yy");
        try {
            return dataFormat.parse(data);
        } catch (ParseException ex) {
        }
        return null;
    }
}
