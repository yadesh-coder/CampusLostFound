package com.lostfoun;

import com.lostfound.dao.UserDAO;

public class TestUserDelete {

    public static void main(String[] args) {

        UserDAO dao = new UserDAO();

        int userId = 104;

        boolean result = dao.deleteUser(userId);

        if (result) {
            System.out.println("User Deleted Successfully!");
        } else {
            System.out.println("User Not Found!");
        }
    }
}