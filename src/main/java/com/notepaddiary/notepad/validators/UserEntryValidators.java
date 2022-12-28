package com.notepaddiary.notepad.validators;

public class UserEntryValidators {
    public static boolean isValidateEntryTitle(String title) {
        return title.length() <= 200;
    }

    public static boolean isValidateEntryBody(String body) {
        return body.length() <= 2000;
    }
}
