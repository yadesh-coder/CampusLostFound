package com.lostfoun;

import com.lostfound.dao.UserDAO;
import com.lostfound.model.User;

public class TestUserUpdate {

    public static void main(String[] args) {

        User user = new User(
                104,
                "Yadesh Kumar",
                "yadesh2026@gmail.com",
                "9876543213",
                "IT",
                4,
                "STUDENT"
        );

        UserDAO dao = new UserDAO();

        boolean result = dao.updateUser(user);

        if (result) {
            System.out.println("User Updated Successfully!");
        } else {
            System.out.println("User Update Failed!");
        }
    }
}