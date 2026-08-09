package com.lostfoun;

import com.lostfound.dao.UserDAO;
import com.lostfound.model.User;

public class TestUser {

    public static void main(String[] args) {

        User user = new User(
                104,
                "Yadesh",
                "yadesh@gmail.com",
                "9876543213",
                "CSE",
                4,
                "STUDENT"
        );

        UserDAO dao = new UserDAO();

        boolean result = dao.addUser(user);

        if (result) {
            System.out.println("User Added Successfully!");
        } else {
            System.out.println("User Addition Failed!");
        }
    }
}