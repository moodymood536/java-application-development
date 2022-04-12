package com.acme.dbo.txlog;

import static com.acme.dbo.txlog.ConsolePrinter.*;
import static com.acme.dbo.txlog.MessageFormatter.*;

public class Facade {
    public static final String PRIMITIVE_PREFIX = "primitive";
    public static final String CHAR_PREFIX = "char";
    public static final String STRING_PREFIX = "string";
    public static final String REFERENCE_PREFIX = "reference";

    public static void log(int message) {
        String formattedMessage = getFormattedMessage(PRIMITIVE_PREFIX,
                Integer.toString(message));
        printMessage(formattedMessage);
    }

    public static void log(byte message) {
        String formattedMessage = getFormattedMessage(PRIMITIVE_PREFIX,
                Byte.toString(message));
        printMessage(formattedMessage);
    }

    public static void log(char message) {
        String formattedMessage = getFormattedMessage(CHAR_PREFIX,
                Character.toString(message));
        printMessage(formattedMessage);
    }

    public static void log(String message) {
        String formattedMessage = getFormattedMessage(STRING_PREFIX,
                message);
        printMessage(formattedMessage);
    }

    public static void log(Boolean message) {
        String formattedMessage = getFormattedMessage(PRIMITIVE_PREFIX,
                Boolean.toString(message));
        printMessage(formattedMessage);
    }

    public static void log(Object message) {
        String formattedMessage = getFormattedMessage(REFERENCE_PREFIX,
                message.toString());
        printMessage(formattedMessage);
    }

}
