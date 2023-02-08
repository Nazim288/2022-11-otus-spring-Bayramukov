package ru.otus.home4.util;

public interface Printer {
     static void print(String string) {
        System.out.print(string);
    }
    static void printLn(String string) {
        System.out.println(string);
    }
}
