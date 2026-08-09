package com.lostfound.dao;

public class MainUserSession {

    private static int userId;
    private static String username;
    private static String role;

    public static void createSession(
            int userId,
            String username,
            String role) {

        MainUserSession.userId = userId;
        MainUserSession.username = username;
        MainUserSession.role = role;
    }

    public static int getUserId() {
        return userId;
    }

    public static String getUsername() {
        return username;
    }

    public static String getRole() {
        return role;
    }

    public static boolean isLoggedIn() {
        return userId > 0 && role != null;
    }

    public static void logout() {

        userId = 0;
        username = null;
        role = null;
    }
}