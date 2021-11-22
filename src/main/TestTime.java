package main;

import javax.swing.text.Caret;
import javax.swing.text.DateFormatter;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;

public class TestTime {

    public static void main(String[] args) {
//        LocalDateTime myDateObj = LocalDateTime.now();
//        System.out.println("Before formatting: " + myDateObj);
//        DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("ss");
//
//        String formattedDate = myDateObj.format(myFormatObj);
//        System.out.println("After formatting: " + formattedDate);

//        for (int i = 0; i < 1000000; i++) {
//            System.out.println(timeNow());
//        }


        for (int i = 0; i < 1; i++) {
            System.out.println(timeNow2());
            System.out.println(timeNow());
        }
    }

    public static long timeNow2() {
        Calendar calendar = Calendar.getInstance();
        return calendar.getTimeInMillis() / 100;
    }

    public static int timeNow() {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("ss");

        String formattedDate = localDateTime.format(dateTimeFormatter);
        return Integer.parseInt(formattedDate);
    }
}
