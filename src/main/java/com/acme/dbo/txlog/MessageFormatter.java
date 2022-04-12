package com.acme.dbo.txlog;

public class MessageFormatter {
    static String getFormattedMessage(String prefix, String message) {
        String formattedMessage = String.format("%s: %s", prefix, message);
        return formattedMessage;
    }
}
