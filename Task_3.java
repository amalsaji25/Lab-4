package org.example;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Task_3 {

    public static final ThreadLocal<SimpleDateFormat> threadSafeDateFormatter = ThreadLocal.withInitial(()-> new SimpleDateFormat("dd-MMM-yyyy"));

    public static void main(String[] args){

        Thread thread1 = new Thread(() -> {
            try {
                Date date1970 = threadSafeDateFormatter.get().parse("01-January-1970");
                String formattedDate = threadSafeDateFormatter.get().format(date1970);
                System.out.println("Thread 1: " + formattedDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });

        Thread thread2 = new Thread(() -> {
            try {
                Date date1970 = threadSafeDateFormatter.get().parse("01-January-1970");
                String formattedDate = threadSafeDateFormatter.get().format(date1970);
                System.out.println("Thread 2: " + formattedDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
        });

        thread1.start();
        thread2.start();
    }
}
