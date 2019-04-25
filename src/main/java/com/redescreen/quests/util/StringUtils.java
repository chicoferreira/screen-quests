package com.redescreen.quests.util;

import java.util.ArrayList;
import java.util.List;

public class StringUtils {

    public static List<String> divide(String input, int maxCharsPerLine) {
        List<String> result = new ArrayList<>();

        String[] splittedInput = input.split(" +");

        StringBuilder builder = new StringBuilder();

        int charCounter = 0;

        for (int i = 0; i != splittedInput.length; i++) {
            String splittedInputString = splittedInput[i];
            charCounter += splittedInputString.length();
            builder.append(splittedInputString).append(" ");
            if (charCounter >= maxCharsPerLine || i + 1 == splittedInput.length) {
                charCounter = 0;
                result.add(builder.toString());
                builder.setLength(0);
            }
        }
        return result;
    }

}
