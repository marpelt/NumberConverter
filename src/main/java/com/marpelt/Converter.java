package com.marpelt;

import java.math.BigInteger;

public class Converter {

    private static String result;

    public static void convert(String from, String to, String input) {

        boolean correctInput = false;

        try {
            if (from.contains("Binär")) {

                for (int i = 0; i < input.length(); i++) {
                    char character = input.charAt(i);
                    if (character != '0' && character != '1') {
                        correctInput = false;
                        break;
                    }
                    correctInput = true;
                }

                if (correctInput) {
                    result = input;
                } else {
                    result = "Error";
                }

            } else if (from.contains("Dezimal")) {

                for (int i = 0; i < input.length(); i++) {
                    char character = input.charAt(i);
                    if (!Character.isDigit(character)) {
                        correctInput = false;
                        break;
                    }
                    correctInput = true;
                }

                if (correctInput) {
                    result = Integer.toBinaryString(Integer.parseInt(input));
                } else {
                    result = "Error";
                }

            } else if (from.contains("Hexadezimal")) {

                for (int i = 0; i < input.length(); i++) {
                    char character = input.charAt(i);
                    if (!(Character.isDigit(character) || (character >= 'A' && character <= 'F') || (character >= 'a' && character <= 'f'))) {
                        correctInput = false;
                        break;
                    }
                    correctInput = true;
                }

                if (correctInput) {
                    result = String.valueOf(Integer.parseInt(input, 16));
                    convert("Dezimal", "Binär", result);
                } else {
                    result = "Error";
                }

            } else if (from.contains("ASCII")) {

                for (int i = 0; i < input.length(); i++) {
                    char character = input.charAt(i);
                    if (!(character >= 0 && character <= 255)) {
                        correctInput = false;
                        break;
                    }
                    correctInput = true;
                }

                if (correctInput) {

                    byte[] bytes = input.getBytes();
                    StringBuilder binary = new StringBuilder();
                    for (byte b : bytes) {
                        int val = b;
                        for (int i = 0; i < 8; i++) {
                            binary.append((val & 128) == 0 ? 0 : 1);
                            val <<= 1;
                        }
                    }

                    result = String.valueOf(binary);

                } else {

                    result = "Error";

                }

            } else {

                result = "Error";

            }

            if (to.contains("Binär")) {

                if (!result.contains("Error")) {
                    result = result;
                }

            } else if (to.contains("Dezimal")) {

                if (!result.contains("Error")) {
                    result = String.valueOf(Integer.parseInt(result, 2));
                }

            } else if (to.contains("Hexadezimal")) {

                if (!result.contains("Error")) {
                    result = new BigInteger(result, 2).toString(16);
                }

            } else if (to.contains("ASCII")) {

                if (Integer.parseInt(result, 2) >= 33 && Integer.parseInt(result, 2) >= 255) {
                    if (!result.contains("Error")) {
                        result = String.valueOf((char) Integer.parseInt(result, 2));
                    }
                } else {
                    result = "Error";
                }

            } else {

                result = "Error";

            }

        } catch (NumberFormatException e) {
            result = e.toString();
        }

    }

    public static String response() {

        return result;

    }
}
