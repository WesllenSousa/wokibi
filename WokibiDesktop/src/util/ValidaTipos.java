/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Wesllen Sousa Lima
 */
public class ValidaTipos {

    //Verifica se a String é vazia, tem apenas um espaço ou é nula;
    public static boolean stringVazia(String v) {
        if ((v == null) || (v.length() == 0) || (v.equals(" "))) {
            return true;
        }
        return false;
    }

    //Verifica se o número é negativo
    public static boolean negativaNumber(String v) {
        if (Double.parseDouble(v) >= 0) {
            return false;
        }
        return true;
    }

    //Verifica se o número é inteiro
    public static boolean isIntegerNumber(String v) {
        try {
            Integer.parseInt(v);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    //Verifica se o número é real do tipo Float
    public static boolean isFloatNumber(String v) {
        try {
            Float.parseFloat(v);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    //Verifica se o número é real do tipo Double
    public static boolean isDoubleNumber(String v) {
        try {
            Double.parseDouble(v);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    //Verifica se é um endereço ip válido
    public static boolean isIpAddress(String v) {
        if (v.startsWith(".") || v.endsWith(".")) { //NOI18N
            return false;
        }
        if (v.indexOf(" ") >= 0 || v.indexOf("\t") >= 0) {
            return false;
        }
        String[] parts = v.split("\\.");
        if (parts.length > 4) {
            return false;
        }
        for (int i = 0; i < parts.length; i++) {
            String part = parts[i];
            if (i == parts.length - 1 && part.indexOf(":") > 0) { //NOI18N
                String[] pts = part.split(":"); //NOI18N
                try {
                    int addr = Integer.parseInt(pts[0]);
                    if (addr < 0) {
                        return false;
                    }
                    if (addr > 256) {
                        return false;
                    }
                } catch (NumberFormatException e) {
                    return false;
                }
                if (pts.length == 2 && pts[1].length() == 0) {
                    return false;
                }
                if (pts.length > 1) {
                    try {
                        int port = Integer.parseInt(pts[1]);
                        if (port < 0) {
                            return false;
                        } else if (port >= 65536) {
                            return false;
                        }
                    } catch (NumberFormatException e) {
                        return false;
                    }
                }
            } else {
                try {
                    int addr = Integer.parseInt(part);
                    if (addr < 0) {
                        return false;
                    }
                    if (addr > 256) {
                        return false;
                    }
                } catch (NumberFormatException e) {
                    return false;
                }
            }
        }
        return true;
    }

    //Verifica se a string inicia com número
    public static boolean StartWithNumber(String v) {
        if (v.length() > 0) {
            char c = v.charAt(0);
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }

    //Verifica se é um email válido
    public static boolean isEmail(String email) {
        Pattern p = Pattern.compile("^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$");
        Matcher m = p.matcher(email);
        if (m.find()) {
            return true;
        } else {
            return false;
        }
    }

    //Verifica se é um cpf válido
    public static boolean isCPF(String cpf) {
        int d1, d2;
        int digito1, digito2, resto;
        int digitoCPF;
        String nDigResult;

        d1 = d2 = 0;
        digito1 = digito2 = resto = 0;

        for (int nCount = 1; nCount < cpf.length() - 1; nCount++) {
            digitoCPF = Integer.valueOf(cpf.substring(nCount - 1, nCount)).intValue();

            //multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
            d1 = d1 + (11 - nCount) * digitoCPF;

            //para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
            d2 = d2 + (12 - nCount) * digitoCPF;
        }

        //Primeiro resto da divisão por 11.
        resto = (d1 % 11);

        //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
        if (resto < 2) {
            digito1 = 0;
        } else {
            digito1 = 11 - resto;
        }

        d2 += 2 * digito1;

        //Segundo resto da divisão por 11.
        resto = (d2 % 11);

        //Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
        if (resto < 2) {
            digito2 = 0;
        } else {
            digito2 = 11 - resto;
        }

        //Digito verificador do CPF que está sendo validado.
        String nDigVerific = cpf.substring(cpf.length() - 2, cpf.length());

        //Concatenando o primeiro resto com o segundo.
        nDigResult = String.valueOf(digito1) + String.valueOf(digito2);

        //comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
        if (nDigVerific.equals(nDigResult)) {
            return true;
        } else {
            return false;
        }
    }

    //Verifica se é uma data válida;
    public static boolean isDate(String data) {
        DataUtil.formataData(data);
        return true;
    }

}
