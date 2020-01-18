package com.exer.calendarfy.log;

public class Log {
    public static void d(String message) {
        System.out.println(message);
    }

    public static void e(String message, Exception e) {
        System.out.println(message + " " + e.toString());
    }
}
