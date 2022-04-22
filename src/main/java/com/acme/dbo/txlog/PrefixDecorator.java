package com.acme.dbo.txlog;

public class PrefixDecorator {
    public static String decorateMessage(String prefix, String message) {
        return String.format("%s: %s", prefix, message);
    }
}
