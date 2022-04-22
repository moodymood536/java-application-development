package com.acme.dbo.txlog;

import java.util.Objects;

import static com.acme.dbo.txlog.ConsoleSaver.save;
import static com.acme.dbo.txlog.PrefixDecorator.decorateMessage;

public class Facade {
    private static final String PRIMITIVE_PREFIX = "primitive";
    private static final String CHAR_PREFIX = "char";
    private static final String STRING_PREFIX = "string";
    private static final String REFERENCE_PREFIX = "reference";
    private static int intAccumulation;
    public static String stringAccumulation;
    public static int stringCounter;
    public static boolean isStringAccumulationPhase;
    public static boolean isIntAccumulationPhase;

    public static void log(int message) {
        if (intAccumulation == 0 || Integer.MAX_VALUE - intAccumulation < message) {
            flush();
            intAccumulation += message;
            isIntAccumulationPhase = true;
        } else {
            intAccumulation += message;
        }
    }

    public static void log(String message) {
        if (Objects.equals(stringAccumulation, null) ||
                stringAccumulation.isEmpty() || !stringAccumulation.equals(message)) {
            flush();
            stringAccumulation = message;
            stringCounter++;
            isStringAccumulationPhase = true;
        } else {
            stringCounter++;
        }
    }

    public static void log(byte message) {
        save(decorateMessage(PRIMITIVE_PREFIX, Byte.toString(message)));
    }

    public static void log(char message) {
        save(decorateMessage(CHAR_PREFIX, Character.toString(message)));
    }

    public static void log(Boolean message) {
        save(decorateMessage(PRIMITIVE_PREFIX, Boolean.toString(message)));
    }

    public static void log(Object message) {
        save(decorateMessage(REFERENCE_PREFIX, message.toString()));
    }

    public static void flush() {
        flushString();
        flushInteger();
    }

    private static void flushInteger() {
        if (isIntAccumulationPhase) {
            save(decorateMessage(PRIMITIVE_PREFIX, String.valueOf(intAccumulation)));
        }
        isIntAccumulationPhase = false;
        intAccumulation = 0;
    }

    private static void flushString() {
        if (isStringAccumulationPhase) {
            if (stringCounter == 1) {
                save(decorateMessage(STRING_PREFIX, stringAccumulation));
            } else {
                save(decorateMessage(STRING_PREFIX, String.format("%s (x%s)",
                        stringAccumulation, stringCounter)));
            }
        }
        isStringAccumulationPhase = false;
        stringAccumulation = "";
        stringCounter = 0;
    }
}
