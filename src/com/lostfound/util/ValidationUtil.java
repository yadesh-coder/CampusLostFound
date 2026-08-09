package com.lostfound.util;

import com.lostfound.exception.InvalidInputException;

public class ValidationUtil {

    // ==========================================
    // VALIDATE ID
    // ==========================================

    public static void validateId(int id)
            throws InvalidInputException {

        if (id <= 0) {
            throw new InvalidInputException(
                    "ID must be greater than 0."
            );
        }
    }

    // ==========================================
    // VALIDATE NAME
    // ==========================================

    public static void validateName(String name)
            throws InvalidInputException {

        if (name == null ||
                name.trim().isEmpty()) {

            throw new InvalidInputException(
                    "Name cannot be empty."
            );
        }

        if (name.length() < 2) {

            throw new InvalidInputException(
                    "Name must contain at least 2 characters."
            );
        }

        if (!name.matches("[a-zA-Z .]+")) {

            throw new InvalidInputException(
                    "Name can contain only letters, spaces and dots."
            );
        }
    }

    // ==========================================
    // VALIDATE EMAIL
    // ==========================================

    public static void validateEmail(String email)
            throws InvalidInputException {

        if (email == null ||
                email.trim().isEmpty()) {

            throw new InvalidInputException(
                    "Email cannot be empty."
            );
        }

        if (!email.matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {

            throw new InvalidInputException(
                    "Invalid email format."
            );
        }
    }

    // ==========================================
    // VALIDATE PHONE
    // ==========================================

    public static void validatePhone(String phone)
            throws InvalidInputException {

        if (phone == null ||
                phone.trim().isEmpty()) {

            throw new InvalidInputException(
                    "Phone number cannot be empty."
            );
        }

        if (!phone.matches("\\d{10}")) {

            throw new InvalidInputException(
                    "Phone number must contain exactly 10 digits."
            );
        }
    }

    // ==========================================
    // VALIDATE YEAR
    // ==========================================

    public static void validateYear(int year)
            throws InvalidInputException {

        if (year < 1 || year > 5) {

            throw new InvalidInputException(
                    "Year must be between 1 and 5."
            );
        }
    }

    // ==========================================
    // VALIDATE USERNAME
    // ==========================================

    public static void validateUsername(String username)
            throws InvalidInputException {

        if (username == null ||
                username.trim().isEmpty()) {

            throw new InvalidInputException(
                    "Username cannot be empty."
            );
        }

        if (username.length() < 4) {

            throw new InvalidInputException(
                    "Username must contain at least 4 characters."
            );
        }

        if (!username.matches("[A-Za-z0-9_]+")) {

            throw new InvalidInputException(
                    "Username can contain only letters, numbers and underscore."
            );
        }
    }

    // ==========================================
    // VALIDATE PASSWORD
    // ==========================================

    public static void validatePassword(String password)
            throws InvalidInputException {

        if (password == null ||
                password.trim().isEmpty()) {

            throw new InvalidInputException(
                    "Password cannot be empty."
            );
        }

        if (password.length() < 5) {

            throw new InvalidInputException(
                    "Password must contain at least 5 characters."
            );
        }
    }

    // ==========================================
    // VALIDATE ROLE
    // ==========================================

    public static void validateRole(String role)
            throws InvalidInputException {

        if (role == null ||
                role.trim().isEmpty()) {

            throw new InvalidInputException(
                    "Role cannot be empty."
            );
        }

        if (!role.equalsIgnoreCase("ADMIN") &&
                !role.equalsIgnoreCase("USER")) {

            throw new InvalidInputException(
                    "Role must be ADMIN or USER."
            );
        }
    }

    // ==========================================
    // VALIDATE STATUS
    // ==========================================

    public static void validateStatus(String status)
            throws InvalidInputException {

        if (status == null ||
                status.trim().isEmpty()) {

            throw new InvalidInputException(
                    "Status cannot be empty."
            );
        }

        if (!status.equalsIgnoreCase("LOST") &&
                !status.equalsIgnoreCase("FOUND")) {

            throw new InvalidInputException(
                    "Status must be LOST or FOUND."
            );
        }
    }

    // ==========================================
    // VALIDATE ITEM NAME
    // ==========================================

    public static void validateItemName(String name)
            throws InvalidInputException {

        if (name == null ||
                name.trim().isEmpty()) {

            throw new InvalidInputException(
                    "Item name cannot be empty."
            );
        }

        if (name.length() < 2) {

            throw new InvalidInputException(
                    "Item name must contain at least 2 characters."
            );
        }
    }

    // ==========================================
    // VALIDATE CATEGORY
    // ==========================================

    public static void validateCategory(String category)
            throws InvalidInputException {

        if (category == null ||
                category.trim().isEmpty()) {

            throw new InvalidInputException(
                    "Category cannot be empty."
            );
        }
    }

    // ==========================================
    // VALIDATE LOCATION
    // ==========================================

    public static void validateLocation(String location)
            throws InvalidInputException {

        if (location == null ||
                location.trim().isEmpty()) {

            throw new InvalidInputException(
                    "Location cannot be empty."
            );
        }
    }

    // ==========================================
    // VALIDATE DESCRIPTION
    // ==========================================

    public static void validateDescription(String description)
            throws InvalidInputException {

        if (description == null ||
                description.trim().isEmpty()) {

            throw new InvalidInputException(
                    "Description cannot be empty."
            );
        }

        if (description.length() < 5) {

            throw new InvalidInputException(
                    "Description must contain at least 5 characters."
            );
        }
    }
}