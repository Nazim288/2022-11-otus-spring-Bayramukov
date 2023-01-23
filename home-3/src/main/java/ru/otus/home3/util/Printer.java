package ru.otus.home3.util;

public interface Printer {
     static void print(String string) {
        System.out.print(string);
    }
    static void printLn(String string) {
        System.out.println(string);
    }
}
