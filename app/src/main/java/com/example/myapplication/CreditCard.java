package com.example.myapplication;
        import java.util.Calendar;
        import java.util.Locale;

public class CreditCard {
    private String creditCardNumber;
    private String expirationDate;
    private String cvv;
    private String firstName;
    private String lastName;

    public CreditCard() {
        this.creditCardNumber = null;
        this.expirationDate = null;
        this.cvv = null;
        this.firstName = null;
        this.lastName = null;
    }

    public CreditCard(String creditCardNumber, String expirationDate, String cvv, String firstName, String lastName) {
        this.creditCardNumber = creditCardNumber;
        this.expirationDate = expirationDate;
        this.cvv = cvv;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static boolean isValidCardNumber(String cardNumber) {

        return luhnValidation(cardNumber) && (isValidAmex(cardNumber) ||
                isValidMasterCard(cardNumber) || isValidVisa(cardNumber));
    }

    private static boolean luhnValidation(String cardNumber) {
        int total = 0;
        for (int i = 1; i <= cardNumber.length(); i++) {
            int digit = Character.getNumericValue(cardNumber.charAt(cardNumber.length() - i));
            if (i % 2 == 0) {
                digit *= 2;
                if (digit > 9) {
                    digit -= 9;
                }
            }
            total += digit;
        }
        return total % 10 == 0;
    }

    private static boolean isValidAmex(String cardNumber) {
        return cardNumber.length() == 15 && cardNumber.charAt(0) == '3' && (cardNumber.charAt(1) == '4' || cardNumber.charAt(1) == '7');
    }

    private static boolean isValidMasterCard(String cardNumber) {
        return cardNumber.length() == 16 &&
                ((cardNumber.charAt(0) == '5' && cardNumber.charAt(1) >= '1' && cardNumber.charAt(1) <= '5') ||
                        (cardNumber.charAt(0) == '2' && cardNumber.charAt(1) >= '2' && cardNumber.charAt(1) <= '7'));
    }

    private static boolean isValidVisa(String cardNumber) {
        return cardNumber.length() >= 16 && cardNumber.length() <= 19 && cardNumber.charAt(0) == '4';
    }

    public static boolean isValidExpirationDate(String expirationDate) {
        String[] expDateParts = expirationDate.split("/");
        if (expDateParts.length != 2 || expDateParts[0].isEmpty() || expDateParts[1].isEmpty()) {
            return false;
        }
        int expMonth = Integer.parseInt(expDateParts[0]);
        int expYear = Integer.parseInt(expDateParts[1]);
        if (!(expMonth >= 1 && expMonth <= 12)) {
            return false;
        }
        Calendar today = Calendar.getInstance(Locale.getDefault());
        int todayMonth = today.get(Calendar.MONTH) + 1;
        int todayYear = today.get(Calendar.YEAR) % 100;
        return (expYear > todayYear) || (expYear == todayYear && expMonth >= todayMonth);
    }

    public static boolean isValidCvv(String cardNumber, String cvv) {
        return (isValidAmex(cardNumber) && cvv.length() == 4) || isValidMasterCard(cardNumber) || isValidVisa(cardNumber) && cvv.length() == 3;
    }

    public static String cleanName(String firstOrLastName) {
        return firstOrLastName.trim().replaceAll("\\s+", " ");
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}