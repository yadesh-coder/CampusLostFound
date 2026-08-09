package com.lostfoun;

import java.sql.Connection;

import com.lostfound.util.DBUtil;

public class TestConnection {

    public static void main(String[] args) {

        try {
            Connection con = DBUtil.getConnection();

            System.out.println("Database Connected Successfully!");

            con.close();

        } catch (Exception e) {
            System.out.println("Database Connection Failed!");
            e.printStackTrace();
        }
    }
}