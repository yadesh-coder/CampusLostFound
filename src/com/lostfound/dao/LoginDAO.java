package com.lostfound.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lostfound.util.DBUtil;

public class LoginDAO {

    public boolean login(
            String username,
            String password) {

        String sql =
                "SELECT USER_ID, USERNAME, ROLE " +
                "FROM USERS " +
                "WHERE USERNAME = ? " +
                "AND PASSWORD = ?";

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps =
                    con.prepareStatement(sql)
        ) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs =
                    ps.executeQuery()) {

                if (rs.next()) {

                    MainUserSession.createSession(
                            rs.getInt("USER_ID"),
                            rs.getString("USERNAME"),
                            rs.getString("ROLE")
                    );

                    return true;
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return false;
    }
}