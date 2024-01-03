package com.marpelt;

import com.sun.org.apache.xerces.internal.xs.StringList;
import com.sun.xml.internal.bind.v2.TODO;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Converter {

    private static List<String> results;
    private static List<String> converted;

    public static void convert(String from, String to, String input) {

        results = new ArrayList<>();
        converted = new ArrayList<>();
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
                    results.add(input);
                } else {
                    results.add("Error");
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
                    results.add(Integer.toBinaryString(Integer.parseInt(input)));
                } else {
                    results.add("Error");
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
                    convert("Dezimal", "Binär", String.valueOf(Integer.parseInt(input, 16)));
                } else {
                    results.add("Error");
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

                    results.add(String.valueOf(binary));

                } else {
                    results.add("Error: No Setting selected");
                }

            }

            if (to.contains("Binär")) {

                if (!results.get(results.size() - 1).contains("Error")) {
                    converted.add(results.get(results.size() - 1));
                    if (converted.size() == 2) {
                        converted.remove(0);
                    }
                }

            } else if (to.contains("Dezimal")) {

                if (!results.get(results.size() - 1).contains("Error")) {
                    converted.add(String.valueOf(Integer.parseInt(results.get(results.size() - 1), 2)));
                    if (converted.size() == 2) {
                        converted.remove(0);
                    }
                }

            } else if (to.contains("Hexadezimal")) {

                if (!results.get(results.size() - 1).contains("Error")) {
                    converted.add(new BigInteger(results.get(results.size() - 1), 2).toString(16));
                    if (converted.size() == 2) {
                        converted.remove(0);
                    }
                }

            } else if (to.contains("ASCII")) {

                if (Integer.parseInt(results.get(results.size() - 1), 2) >= 33 && Integer.parseInt(results.get(results.size() - 1), 2) <= 255) {
                    if (!results.get(results.size() - 1).contains("Error")) {
                        converted.add(String.valueOf((char) Integer.parseInt(results.get(results.size() - 1), 2)));
                    }
                } else {
                    converted.add("Error: ASCII < 33 && > 255");
                }

            } else {

                converted.add("Error: No Setting selected");

            }

        } catch (NumberFormatException e) {
            results.add(e.toString());
        }
    }

    public static List<String> response() {
        System.out.println("Nach Binär: " + results);
        System.out.println("Fertig: " + converted);
        return converted;
    }


    private static ArrayList<String> binaryContent;
    private static ArrayList<String> resultContent;
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
                if (string.isEmpty()) {
                    correctInput = false;
                    error = "Inputvalue is empty";
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
                if (string.isEmpty()) {
                    correctInput = false;
                    error = "Inputvalue is empty";
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
                    error = "Inputvalue is empty";
                }
            }

            if (correctInput) {
                for (String string : asciiContent) {
                    char[] chars = string.toCharArray();
                    StringBuilder hex = new StringBuilder();
                    for (char aChar : chars) {
                        hex.append(Integer.toHexString((int) aChar));
                    }
                    hexadecimalContent.append(hex).append(" ");
                }
                hexadecimalContent.deleteCharAt(hexadecimalContent.length() - 1);
                convertToBinary("Hexadecimal", hexadecimalContent.toString());
            } else {
                asciiContent.clear();
            }
        }

        if (from.contains("-")) {
            error = "Missing selection";
        }

    }

    public static String convertToResult(String to) {
        //TODO: Convert the binary numbers to the result.
        return "input";
    }

    public static String setErrorMessage() {
        return error;
    }
}