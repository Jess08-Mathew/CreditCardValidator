import java.util.Scanner;

public class CreditCardValidator {
    private String ccNumber; // Store credit card number as a string for easy manipulation
    private String remainingNumber; // To store the remaining number after removing the last digit
    private int lastDigit; // To store the last digit
    private String reversedNumber; // To store the reversed number
    private int sum; // To store the sum of digits after all steps

    // Constructor to initialize the credit card number
    public CreditCardValidator(String ccNumber) {
        this.ccNumber = ccNumber;
    }

    // Step a: Remove the last digit of the credit card number
    public void removeLastDigit() {
        if (ccNumber.length() < 8 || ccNumber.length() > 9) {
            System.out.println("Invalid credit card number. It must be 8 or 9 digits long.");
            return;
        }
        lastDigit = Character.getNumericValue(ccNumber.charAt(ccNumber.length() - 1));
        remainingNumber = ccNumber.substring(0, ccNumber.length() - 1);
        System.out.println("Step a: Last digit = " + lastDigit);
        System.out.println("Remaining number = " + remainingNumber);
    }

    // Step b: Reverse the remaining digits
    public void reverseRemainingDigits() {
        reversedNumber = new StringBuilder(remainingNumber).reverse().toString();
        System.out.println("Step b: Reversed number = " + reversedNumber);
    }

    // Step c: Double the digits in odd-numbered positions (1st, 3rd, 5th, etc.)
    public void doubleOddPositionedDigits() {
        StringBuilder doubledDigits = new StringBuilder();
        sum = 0; // Reset the sum for this step

        // Loop through the reversed number
        for (int i = 0; i < reversedNumber.length(); i++) {
            int digit = Character.getNumericValue(reversedNumber.charAt(i));

            // Double the digits in odd-numbered positions (0-based index: 0, 2, 4...)
            if (i % 2 == 0) {
                digit *= 2;
                // If the result is a double-digit number, sum the digits
                if (digit > 9) {
                    digit = (digit / 10) + (digit % 10); // Split the digits and sum them
                }
            }
            doubledDigits.append(digit);
            sum += digit; // Add to the sum for later steps
        }
        
        // Display the doubled digits and the sum
        System.out.println("Step c: After doubling odd-positioned digits = " + doubledDigits);
        System.out.println("Sum of digits so far = " + sum);
    }

    // Step d: Add up all the digits (already done in the loop in Step c)
    
    // Step e: Subtract the last digit obtained in Step a from 10
    public void subtractLastDigitFromTen() {
        int resultStepE = 10 - lastDigit; // Step e: Subtract last digit from 10
        System.out.println("Step e: 10 - last digit = " + resultStepE);
        
        // Step f: Compare the result of Step e with the last digit
        if (resultStepE == lastDigit) {
            System.out.println("Step f: The credit card number is valid.");
        } else {
            System.out.println("Step f: The credit card number is invalid.");
        }
    }

    public static void main(String[] args) {
        // Take input from the user
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the credit card number: ");
        String ccNumber = scanner.nextLine();

        // Create an instance of the validator and execute all steps
        CreditCardValidator validator = new CreditCardValidator(ccNumber);
        validator.removeLastDigit(); // Perform Step a
        validator.reverseRemainingDigits(); // Perform Step b
        validator.doubleOddPositionedDigits(); // Perform Step c
        validator.subtractLastDigitFromTen(); // Perform Steps d, e, f
    }
}
