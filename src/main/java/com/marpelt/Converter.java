package com.marpelt;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;

public class Converter {
    private static ArrayList<String> binaryContent;
    private static String error;
    public static void convertToBinary(String from, String input) {

        if (from.contains("Binary")) {
            String[] strings = input.split("\\s+");
            binaryContent = new ArrayList<>(Arrays.asList(strings));

            boolean correctInput = true;

            for (String string : binaryContent) {
                for (int i = 0; i < string.length(); i++) {
                    char character = string.charAt(i);
                    if (character != '0' && character != '1') {
                        correctInput = false;
                        break;
                    } else {
                        correctInput = true;
                    }
                }
            }
            if (!correctInput) {
                binaryContent.clear();
            }
        }

        if (from.contains("Decimal")) {

            ArrayList<String> decimalContent;

            String[] strings = input.split("\\s+");
            decimalContent = new ArrayList<>(Arrays.asList(strings));
            binaryContent = new ArrayList<>();

            boolean correctInput = true;

            for (String string : decimalContent) {
                for (int i = 0; i < string.length(); i++) {
                    char character = string.charAt(i);
                    if (Character.isDigit(character)) {
                        correctInput = true;
                    } else {
                        correctInput = false;
                        break;
                    }
                }
            }

            if (correctInput) {
                for (String string : decimalContent) {
                    binaryContent.add(Integer.toBinaryString(Integer.parseInt(string)));
                }
                decimalContent.clear();
            } else {
               decimalContent.clear();
               binaryContent.clear();
            }

        }

        if (from.contains("Hexadecimal")) {

            ArrayList<String> hexadecimalContent;

            String[] strings = input.split("\\s+");
            hexadecimalContent = new ArrayList<>(Arrays.asList(strings));
            StringBuilder decimalContent = new StringBuilder();

            boolean correctInput = true;

            for (String string : hexadecimalContent) {
                for (int i = 0; i < string.length(); i++) {
                    char character = string.charAt(i);
                    if ((Character.isDigit(character) || character >= 'A' && character <= 'F' || character >= 'a' && character <= 'f')) {
                        correctInput = true;
                    } else {
                        correctInput = false;
                        break;
                    }
                }
            }

            if (correctInput) {
                for (String string : hexadecimalContent) {
                    string = String.valueOf(Integer.parseInt(string, 16));
                    decimalContent.append(string).append(" ");
                }
                decimalContent.deleteCharAt(decimalContent.length() - 1);
                convertToBinary("Decimal", decimalContent.toString());
            } else {
                hexadecimalContent.clear();
            }
        }

        if (from.contains("Ascii")) {

            ArrayList<String> asciiContent;

            String[] strings = input.split("\\s+");
            asciiContent = new ArrayList<>(Arrays.asList(strings));
            StringBuilder hexadecimalContent = new StringBuilder();

            boolean correctInput = true;

            for (String string : asciiContent) {
                for (int i = 0; i < string.length(); i++) {
                    char character = string.charAt(i);
                    if (character <= 255) {
                        correctInput = true;
                    } else {
                        correctInput = false;
                        break;
                    }
                }
                if (string.isEmpty()) {
                    correctInput = false;
                    error = "Input value is empty";
                }
            }

            if (correctInput) {
                for (String string : asciiContent) {
                    char[] chars = string.toCharArray();
                    StringBuilder hex = new StringBuilder();
                    for (char aChar : chars) {
                        hex.append(Integer.toHexString(aChar));
                    }
                    hexadecimalContent.append(hex).append(" ");
                }
                hexadecimalContent.deleteCharAt(hexadecimalContent.length() - 1);
                convertToBinary("Hexadecimal", hexadecimalContent.toString());
            } else {
                asciiContent.clear();
            }
        }

    }

    public static String convertToResult(String to) {
        //TODO: Convert the binary numbers to the result.

        StringBuilder result = new StringBuilder();
        ArrayList<String> resultContent = new ArrayList<>();

        if (to.contains("Binary")) {
            resultContent = binaryContent;
        }

        if (to.contains("Decimal")) {
            for (String string : binaryContent) {
                resultContent.add(String.valueOf(Integer.parseInt(string, 2)));
            }
        }

        if (to.contains("Hexadecimal")) {
            for (String string : binaryContent) {
                resultContent.add(new BigInteger(string, 2).toString(16));
            }
        }

        if (to.contains("Ascii")) {
            for (String string : binaryContent) {
                resultContent.add(String.valueOf((char) Integer.parseInt(string, 2)));
            }
        }

        if (!(resultContent.isEmpty())) {

            for (String string : resultContent) {
                result.append(string).append(" ");
            }
            result.deleteCharAt(result.length() - 1);
            resultContent.clear();
        }

        return result.toString();
    }

    public static String setErrorMessage(String input) {
        //TODO: Error Message
        return error;
    }
}