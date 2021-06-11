package it.maurosaladino.sdp.Model;

public class Utils {
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";

    public static void printRed(String msg) {
        System.err.println(ANSI_RED + msg + ANSI_RESET);
    }

    public static void printGreen(String msg) {
        System.out.print(ANSI_GREEN + msg + ANSI_RESET);
    }
}
