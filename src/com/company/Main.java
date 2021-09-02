package com.company;

import jdk.jshell.spi.ExecutionControl;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Введите арифметическую операцию ");
        String str = in.nextLine();
        // Определение операндов и оператора
        var stringParts = str.split(" ");
        if (stringParts.length != 3) {
            throw new IllegalArgumentException("Входящая строка не соответсвует ожидаемому формату");
        }
        if (isArabic(stringParts[0]) && isArabic(stringParts[2])) {
            // Перевод строки в число
            var num1 = Integer.parseInt(stringParts[0]);
            var num2 = Integer.parseInt(stringParts[2]);
            if (num1 < 1 || num1 > 10) {
                throw new IllegalArgumentException("Числа не входят в заданный интервал");
            }
            if (num2 < 1 || num2 > 10) {
                throw new IllegalArgumentException("Числа не входят в заданный интервал");
            }
            switch (stringParts[1]) {
                case "+":
                    System.out.println(num1 + num2);
                    break;
                case "-":
                    System.out.println(num1 - num2);
                    break;
                case "*":
                    System.out.println(num1 * num2);
                    break;
                case "/":
                    System.out.println(num1 / num2);
                    break;
                default:
                    throw new IllegalArgumentException("Математическая операция не определена");
            }
        } else {
            var num1 = romanToArabic(stringParts[0]);
            var num2 = romanToArabic(stringParts[2]);
            if (num1 < 1 || num1 > 10) {
                throw new IllegalArgumentException("Числа не входят в заданный интервал");
            }
            if (num2 < 1 || num2 > 10) {
                throw new IllegalArgumentException("Числа не входят в заданный интервал");
            }
            switch (stringParts[1]) {
                case "+":
                    System.out.println(arabicToRoman(num1 + num2));
                    break;
                case "-":
                    System.out.println(arabicToRoman(num1 - num2));
                    break;
                case "*":
                    System.out.println(arabicToRoman(num1 * num2));
                    break;
                case "/":
                    System.out.println(arabicToRoman(num1 / num2));
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        }

    }

    private static boolean isArabic(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }

    }

    private static Integer romanToArabic(String roman) {
        String romanNumeral = roman.toUpperCase();
        int result = 0;

        List<RomanNumeral> sortedValues = RomanNumeral.getReverseSortedValues();

        int i = 0;

        while ((romanNumeral.length() > 0) && (i < sortedValues.size())) {
            RomanNumeral symbol = sortedValues.get(i);
            if (romanNumeral.startsWith(symbol.name())) {
                result += symbol.getValue();
                romanNumeral = romanNumeral.substring(symbol.name().length());
            } else {
                i++;
            }
        }

        if (romanNumeral.length() > 0) {
            throw new IllegalArgumentException(roman + " не может быть переведено");
        }

        return result;
    }

    private static String arabicToRoman(Integer arabic) {
        if (arabic <= 0) {
            throw new IllegalArgumentException(arabic + " не может быть меньше единицы");
        }

        List<RomanNumeral> romanNumerals = RomanNumeral.getReverseSortedValues();

        int i = 0;
        StringBuilder sb = new StringBuilder();

        while ((arabic > 0) && (i < romanNumerals.size())) {
            RomanNumeral currentSymbol = romanNumerals.get(i);
            if (currentSymbol.getValue() <= arabic) {
                sb.append(currentSymbol.name());
                arabic -= currentSymbol.getValue();
            } else {
                i++;
            }
        }

        return sb.toString();
    }
}

