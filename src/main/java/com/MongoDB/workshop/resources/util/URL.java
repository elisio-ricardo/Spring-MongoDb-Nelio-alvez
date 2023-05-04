package com.MongoDB.workshop.resources.util;


import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

//Classe para a decodificação da URL que no processo vem com caracteres especials
//tipo texto=bom%20dia espaço = %20
public class URL {
    public static String decodeParam(String text) {

        try {
            //o texto é a url codificada, e depois em qual padrao de decodificação
            return URLDecoder.decode(text, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            //Pode ser uma exception mas foi preferivel retornar vazio
            return "";
        }
    }


    public static Date convertDate(String textDate, Date defaultValue) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        try {
            return sdf.parse(textDate);
        } catch (ParseException e) {
            return defaultValue;
        }
    }

}
