package com.lostfoun;

import com.lostfound.dao.UserDAO;
import com.lostfound.model.User;

public class TestUserSearch {

    public static void main(String[] args) {

        UserDAO dao = new UserDAO();

        System.out.println("===== ALL USERS =====");

        dao.viewAllUsers();

        System.out.println("\n===== SEARCH USER =====");

        User user = dao.findUserById(104);

        if (user != null) {

            System.out.println("User ID     : " + user.getUserId());
            System.out.println("Name        : " + user.getName());
            System.out.println("Email       : " + user.getEmail());
            System.out.println("Phone       : " + user.getPhone());
            System.out.println("Department  : " + user.getDepartment());
            System.out.println("Year        : " + user.getYear());
            System.out.println("Role        : " + user.getRole());

        } else {
            System.out.println("User Not Found!");
        }
    }
}