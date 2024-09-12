package com.core.payment.processor.service.core.transaction.card.schemes;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public final class CardUtils {


    public static boolean validateExpiryDate(String expiryDate) {
        try {
            String[] cardExpiry = expiryDate.split("/");
            if (cardExpiry.length != 2) {
                return false;
            }
            int cardMonth = Integer.parseInt(cardExpiry[0].trim());
            int cardYear = 2_000 + Integer.parseInt(cardExpiry[1].trim());
            if (cardMonth < 1 || cardMonth > 12 || cardYear < 2000 || cardYear > 9999) {
                return false;
            }
            LocalDate today = LocalDate.now();
            int currentMonth = today.getMonthValue();
            int currentYear = today.getYear();
            return cardYear >= currentYear && (cardYear != currentYear || cardMonth >= currentMonth);
        } catch (NumberFormatException | DateTimeParseException e) {
            return false;
        }
    }

    public static boolean validateCVV(String cvv) {
        if (cvv == null) {
            return false;
        }
        cvv = cvv.trim();
        if (cvv.length() == 3 || cvv.length() == 4) {
            return cvv.matches("\\d+");
        }
        return false;
    }

    public static boolean validateCardNumber(String cardNumber) {
        if (cardNumber == null) {
            return false;
        }
        cardNumber = cardNumber.trim().replaceAll("\\s+", "");
        if (cardNumber.length() < 13 || cardNumber.length() > 19) {
            return false;
        }
        if (!cardNumber.matches("\\d+")) {
            return false;
        }
        return isLuhnValid(cardNumber);
    }

    private static boolean isLuhnValid(String cardNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(cardNumber.charAt(i));
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            sum += digit;
            alternate = !alternate;
        }
        return (sum % 10 == 0);
    }
}
