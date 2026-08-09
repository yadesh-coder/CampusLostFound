package com.lostfound.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lostfound.util.DBUtil;

public class LostReportDAO {

    // ==========================================
    // ADD LOST REPORT
    // ==========================================

    public boolean addLostReport(
            int lostId,
            int itemId,
            int userId,
            String location,
            Date lostDate) {

        String sql =
                "INSERT INTO LOST_REPORT " +
                "(LOST_ID, ITEM_ID, USER_ID, LOCATION, LOST_DATE) " +
                "VALUES (?, ?, ?, ?, ?)";

        Connection con = null;

        try {

            con = DBUtil.getConnection();

            con.setAutoCommit(false);

            try (PreparedStatement ps =
                    con.prepareStatement(sql)) {

                ps.setInt(1, lostId);
                ps.setInt(2, itemId);
                ps.setInt(3, userId);
                ps.setString(4, location);
                ps.setDate(5, lostDate);

                int rows = ps.executeUpdate();

                if (rows > 0) {

                    con.commit();

                    return true;
                }

                con.rollback();

            }

        } catch (Exception e) {

            try {

                if (con != null) {
                    con.rollback();
                }

            } catch (Exception rollbackException) {

                rollbackException.printStackTrace();
            }

            e.printStackTrace();

        } finally {

            try {

                if (con != null) {

                    con.setAutoCommit(true);
                    con.close();
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

        return false;
    }

    // ==========================================
    // VIEW ALL LOST ITEMS
    // ==========================================

    public void viewAllLostItems() {

        String sql =
                "SELECT l.LOST_ID, " +
                "i.ITEM_ID, " +
                "i.ITEM_NAME, " +
                "i.CATEGORY, " +
                "i.COLOR, " +
                "i.BRAND, " +
                "l.LOCATION, " +
                "l.LOST_DATE, " +
                "u.NAME AS REPORTER " +
                "FROM LOST_REPORT l " +
                "JOIN ITEM i " +
                "ON l.ITEM_ID = i.ITEM_ID " +
                "JOIN USERS u " +
                "ON l.USER_ID = u.USER_ID " +
                "ORDER BY l.LOST_DATE DESC";

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps =
                    con.prepareStatement(sql);
            ResultSet rs =
                    ps.executeQuery()
        ) {

            System.out.println();
            System.out.println(
                    "=============================================================="
            );

            System.out.println(
                    "                 LOST ITEMS"
            );

            System.out.println(
                    "=============================================================="
            );

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println(
                        "Lost ID    : " +
                        rs.getInt("LOST_ID")
                );

                System.out.println(
                        "Item ID    : " +
                        rs.getInt("ITEM_ID")
                );

                System.out.println(
                        "Item Name  : " +
                        rs.getString("ITEM_NAME")
                );

                System.out.println(
                        "Category   : " +
                        rs.getString("CATEGORY")
                );

                System.out.println(
                        "Color      : " +
                        rs.getString("COLOR")
                );

                System.out.println(
                        "Brand      : " +
                        rs.getString("BRAND")
                );

                System.out.println(
                        "Location   : " +
                        rs.getString("LOCATION")
                );

                System.out.println(
                        "Lost Date  : " +
                        rs.getDate("LOST_DATE")
                );

                System.out.println(
                        "Reporter   : " +
                        rs.getString("REPORTER")
                );

                System.out.println(
                        "--------------------------------------------------------------"
                );
            }

            if (!found) {

                System.out.println(
                        "No lost items found."
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}