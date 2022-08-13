package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {

    private static SimpleDateFormat sdr = new SimpleDateFormat("dd/MM/yyyy");

    public static Date stringToDate(String dateStr) {
        try {
            return sdr.parse(dateStr);
        } catch (ParseException e) {
            System.out.println("Invalid date format");
        }
        return null;
    }

    public static String dateToString(Date date) {
        if(date ==null){
            return "";
        }
        return sdr.format(date);
    }
}
