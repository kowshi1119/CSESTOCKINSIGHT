package com.example.csestockinsight;

import android.util.Patterns;
import java.util.regex.Pattern;

// refer: GitHub/Gemini
public class AuthValidator {

    // Constants for validation rules
    private static final int MIN_USERNAME_LENGTH = 4;
    private static final int MIN_PASSWORD_LENGTH = 8;
    private static final Pattern PASSWORD_PATTERN = Pattern.compile(".*\\d.*"); // At least one digit

    /**
     * Validate if a string is not null and not empty.
     * @param input String to validate
     * @return true if input is not empty, false otherwise
     */
    public static boolean isNotEmpty(String input) {
        return input != null && !input.trim().isEmpty();
    }

    /**
     * Validate email format using Android's built-in pattern matcher.
     * @param email Email address to validate
     * @return true if email is valid, false otherwise
     */
    public static boolean isValidEmail(String email) {
        if (!isNotEmpty(email)) {
            return false;
        }
        return Patterns.EMAIL_ADDRESS.matcher(email.trim()).matches();
    }

    /**
     * Validate username length.
     * Username must be at least MIN_USERNAME_LENGTH characters.
     * @param username Username to validate
     * @return true if username meets length requirement, false otherwise
     */
    public static boolean isValidUsername(String username) {
        if (!isNotEmpty(username)) {
            return false;
        }
        return username.trim().length() >= MIN_USERNAME_LENGTH;
    }

    /**
     * Validate password meets minimum length requirement.
     * @param password Password to validate
     * @return true if password meets length requirement, false otherwise
     */
    public static boolean isPasswordLengthValid(String password) {
        if (!isNotEmpty(password)) {
            return false;
        }
        return password.length() >= MIN_PASSWORD_LENGTH;
    }

    /**
     * Validate password strength (contains at least one number).
     * In production, you might want more complex rules:
     * - Mix of uppercase and lowercase
     * - Special characters
     * - Not in common password list
     * @param password Password to validate
     * @return true if password contains at least one digit, false otherwise
     */
    public static boolean isPasswordStrong(String password) {
        if (!isNotEmpty(password)) {
            return false;
        }
        return PASSWORD_PATTERN.matcher(password).matches();
    }

    /**
     * Validate that two passwords match.
     * Used for password confirmation during registration.
     * @param password Original password
     * @param confirmPassword Confirmation password
     * @return true if passwords match, false otherwise
     */
    public static boolean doPasswordsMatch(String password, String confirmPassword) {
        if (!isNotEmpty(password) || !isNotEmpty(confirmPassword)) {
            return false;
        }
        return password.equals(confirmPassword);
    }

    /**
     * Get a descriptive error message for email validation failure.
     * @param email Email to check
     * @return Error message string, or null if valid
     */
    public static String getEmailError(String email) {
        if (!isNotEmpty(email)) {
            return "Email cannot be empty";
        }
        if (!isValidEmail(email)) {
            return "Please enter a valid email address";
        }
        return null;
    }

    /**
     * Get a descriptive error message for username validation failure.
     * @param username Username to check
     * @return Error message string, or null if valid
     */
    public static String getUsernameError(String username) {
        if (!isNotEmpty(username)) {
            return "Username cannot be empty";
        }
        if (!isValidUsername(username)) {
            return "Username must be at least " + MIN_USERNAME_LENGTH + " characters";
        }
        return null;
    }

    /**
     * Get a descriptive error message for password validation failure.
     * @param password Password to check
     * @return Error message string, or null if valid
     */
    public static String getPasswordError(String password) {
        if (!isNotEmpty(password)) {
            return "Password cannot be empty";
        }
        if (!isPasswordLengthValid(password)) {
            return "Password must be at least " + MIN_PASSWORD_LENGTH + " characters";
        }
        if (!isPasswordStrong(password)) {
            return "Password must contain at least one number";
        }
        return null;
    }
}
