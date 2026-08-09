package com.lostfoun;

import java.util.Scanner;

import com.lostfound.dao.LoginDAO;
import com.lostfound.dao.MainUserSession;

public class TestLogin {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        LoginDAO loginDAO = new LoginDAO();

        System.out.println();
        System.out.println("======================================");
        System.out.println("       CAMPUS LOST & FOUND");
        System.out.println("            LOGIN TEST");
        System.out.println("======================================");

        System.out.print("Username: ");
        String username = sc.nextLine();

        System.out.print("Password: ");
        String password = sc.nextLine();

        boolean result =
                loginDAO.login(username, password);

        if (result) {

            System.out.println();
            System.out.println("LOGIN SUCCESSFUL");
            System.out.println("--------------------------------------");

            System.out.println(
                    "User ID  : " +
                    MainUserSession.getUserId()
            );

            System.out.println(
                    "Username : " +
                    MainUserSession.getUsername()
            );

            System.out.println(
                    "Role     : " +
                    MainUserSession.getRole()
            );

            System.out.println("--------------------------------------");

            if (MainUserSession
                    .getRole()
                    .equalsIgnoreCase("ADMIN")) {

                System.out.println(
                        "Access Granted: ADMIN"
                );

            } else if (MainUserSession
                    .getRole()
                    .equalsIgnoreCase("USER")) {

                System.out.println(
                        "Access Granted: USER"
                );

            } else {

                System.out.println(
                        "Unknown Role!"
                );
            }

            System.out.println();
            System.out.println(
                    "Session Active: " +
                    MainUserSession.isLoggedIn()
            );

            MainUserSession.logout();

            System.out.println(
                    "Logout Successful!"
            );

            System.out.println(
                    "Session Active After Logout: " +
                    MainUserSession.isLoggedIn()
            );

        } else {

            System.out.println();
            System.out.println(
                    "LOGIN FAILED"
            );

            System.out.println(
                    "Invalid Username or Password!"
            );
        }

        sc.close();
    }
}