package com.ehacdev.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

public class ValidatorUtil {

    public static boolean validateString(String value, int minLength) {
        return value != null && !value.trim().isEmpty() && value.length() >= minLength;
    }

    public static boolean validatePhoneNumber(String phone) {
        String regex = "\\d{10}";
        return phone != null && Pattern.matches(regex, phone);
    }

    public static boolean validateEmail(String email) {
        String regex = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
        return email != null && Pattern.matches(regex, email);
    }

    public static boolean validateRequiredField(String value) {
        return value != null && !value.trim().isEmpty();
    }

    public static boolean validatePositiveNumber(int number) {
        return number > 0;
    }

    public static boolean validatePositiveDecimal(double number) {
        return number > 0;
    }

    public static boolean validateQRCode(String qrcode) {
        return validateString(qrcode, 6);
    }

    public static boolean validateDate(String date, String format) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);
        try {
            LocalDate.parse(date, dateFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean validatePassword(String password) {
        String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#])[A-Za-z\\d@$!%*?&#]{8,}$";
        return password != null && Pattern.matches(regex, password);
    }

    public static boolean validateNumericString(String value) {
        return value != null && value.matches("\\d+");
    }

    public static boolean validatePostalCode(String postalCode) {
        String regex = "\\d{5}";
        return postalCode != null && Pattern.matches(regex, postalCode);
    }

    public static boolean validateUUID(String uuid) {
        String regex = "^[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{4}-[0-9a-fA-F]{12}$";
        return uuid != null && Pattern.matches(regex, uuid);
    }

    public static boolean validateName(String name) {
        String regex = "^[a-zA-Z\\s]+$";
        return name != null && Pattern.matches(regex, name);
    }

    public static boolean validateAlphanumericString(String value) {
        String regex = "^[a-zA-Z0-9\\s]+$";
        return value != null && Pattern.matches(regex, value);
    }

    public static boolean validateCreditCardNumber(String cardNumber) {
        int nDigits = cardNumber.length();
        int sum = 0;
        boolean isSecond = false;
        for (int i = nDigits - 1; i >= 0; i--) {
            int d = cardNumber.charAt(i) - '0';
            if (isSecond) {
                d = d * 2;
            }
            sum += d / 10;
            sum += d % 10;
            isSecond = !isSecond;
        }
        return (sum % 10 == 0);
    }

    public static boolean validatePastDate(String date, String format) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);
        try {
            LocalDate localDate = LocalDate.parse(date, dateFormatter);
            return localDate.isBefore(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean validateFutureDate(String date, String format) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(format);
        try {
            LocalDate localDate = LocalDate.parse(date, dateFormatter);
            return localDate.isAfter(LocalDate.now());
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static boolean validateFileExtension(String fileName, String[] validExtensions) {
        if (fileName == null || fileName.trim().isEmpty()) {
            return false;
        }
        for (String extension : validExtensions) {
            if (fileName.endsWith(extension)) {
                return true;
            }
        }
        return false;
    }

    public static boolean validateUrl(String url) {
        String regex = "^(http|https)://[^\\s$.?#].[^\\s]*$";
        return url != null && Pattern.matches(regex, url);
    }

    public static boolean validateIPAddress(String ip) {
        String regex = "^((25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$";
        return ip != null && Pattern.matches(regex, ip);
    }

    public static boolean validateNumberInRange(int number, int min, int max) {
        return number >= min && number <= max;
    }

    public static boolean validateDecimalInRange(double number, double min, double max) {
        return number >= min && number <= max;
    }
}
