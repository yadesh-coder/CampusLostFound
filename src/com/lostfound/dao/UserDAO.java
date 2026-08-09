package com.lostfound.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lostfound.model.User;
import com.lostfound.util.DBUtil;

public class UserDAO {

    // ==========================================
    // ADD USER
    // ==========================================

    public boolean addUser(User user) {

        String sql =
                "INSERT INTO USERS " +
                "(USER_ID, NAME, EMAIL, PHONE, DEPARTMENT, " +
                "YEAR, ROLE, USERNAME, PASSWORD) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, user.getUserId());
            ps.setString(2, user.getName());
            ps.setString(3, user.getEmail());
            ps.setString(4, user.getPhone());
            ps.setString(5, user.getDepartment());
            ps.setInt(6, user.getYear());
            ps.setString(7, user.getRole());
            ps.setString(8, user.getUsername());
            ps.setString(9, user.getPassword());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    // ==========================================
    // VIEW ALL USERS
    // ==========================================

    public void viewAllUsers() {

        String sql =
                "SELECT USER_ID, NAME, EMAIL, PHONE, " +
                "DEPARTMENT, YEAR, ROLE, USERNAME " +
                "FROM USERS " +
                "ORDER BY USER_ID";

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery()
        ) {

            System.out.println();
            System.out.println(
                    "=========================================================================="
            );

            System.out.println(
                    "ID | NAME | EMAIL | PHONE | DEPARTMENT | YEAR | ROLE | USERNAME"
            );

            System.out.println(
                    "=========================================================================="
            );

            while (rs.next()) {

                System.out.println(
                        rs.getInt("USER_ID") + " | " +
                        rs.getString("NAME") + " | " +
                        rs.getString("EMAIL") + " | " +
                        rs.getString("PHONE") + " | " +
                        rs.getString("DEPARTMENT") + " | " +
                        rs.getInt("YEAR") + " | " +
                        rs.getString("ROLE") + " | " +
                        rs.getString("USERNAME")
                );
            }

            System.out.println(
                    "=========================================================================="
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ==========================================
    // FIND USER BY ID
    // ==========================================

    public User findUserById(int userId) {

        String sql =
                "SELECT USER_ID, NAME, EMAIL, PHONE, " +
                "DEPARTMENT, YEAR, ROLE, USERNAME " +
                "FROM USERS " +
                "WHERE USER_ID = ?";

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, userId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    User user = new User();

                    user.setUserId(
                            rs.getInt("USER_ID")
                    );

                    user.setName(
                            rs.getString("NAME")
                    );

                    user.setEmail(
                            rs.getString("EMAIL")
                    );

                    user.setPhone(
                            rs.getString("PHONE")
                    );

                    user.setDepartment(
                            rs.getString("DEPARTMENT")
                    );

                    user.setYear(
                            rs.getInt("YEAR")
                    );

                    user.setRole(
                            rs.getString("ROLE")
                    );

                    user.setUsername(
                            rs.getString("USERNAME")
                    );

                    return user;
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }

    // ==========================================
    // UPDATE USER
    // ==========================================

    public boolean updateUser(User user) {

        String sql =
                "UPDATE USERS SET " +
                "NAME = ?, " +
                "EMAIL = ?, " +
                "PHONE = ?, " +
                "DEPARTMENT = ?, " +
                "YEAR = ?, " +
                "ROLE = ? " +
                "WHERE USER_ID = ?";

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getPhone());
            ps.setString(4, user.getDepartment());
            ps.setInt(5, user.getYear());
            ps.setString(6, user.getRole());
            ps.setInt(7, user.getUserId());

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    // ==========================================
    // DELETE USER
    // ==========================================

    public boolean deleteUser(int userId) {

        String sql =
                "DELETE FROM USERS " +
                "WHERE USER_ID = ?";

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, userId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }

    // ==========================================
    // CHANGE PASSWORD
    // ==========================================

    public boolean changePassword(
            int userId,
            String oldPassword,
            String newPassword) {

        String checkSql =
                "SELECT USER_ID " +
                "FROM USERS " +
                "WHERE USER_ID = ? " +
                "AND PASSWORD = ?";

        String updateSql =
                "UPDATE USERS " +
                "SET PASSWORD = ? " +
                "WHERE USER_ID = ?";

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement checkPs =
                    con.prepareStatement(checkSql)
        ) {

            checkPs.setInt(1, userId);
            checkPs.setString(2, oldPassword);

            try (ResultSet rs =
                    checkPs.executeQuery()) {

                if (!rs.next()) {

                    return false;
                }
            }

            try (
                PreparedStatement updatePs =
                        con.prepareStatement(updateSql)
            ) {

                updatePs.setString(
                        1,
                        newPassword
                );

                updatePs.setInt(
                        2,
                        userId
                );

                int rows =
                        updatePs.executeUpdate();

                return rows > 0;
            }

        } catch (Exception e) {

            e.printStackTrace();
            return false;
        }
    }
}