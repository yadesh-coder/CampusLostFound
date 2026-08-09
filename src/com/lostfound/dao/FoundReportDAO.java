package com.lostfound.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lostfound.model.Item;
import com.lostfound.util.DBUtil;

public class FoundReportDAO {

    // ==========================================
    // ADD FOUND ITEM + FOUND REPORT
    // ==========================================

    public boolean addFoundItem(
            Item item,
            int foundId,
            int userId,
            String location,
            Date foundDate) {

        Connection con = null;

        String itemSql =
                "INSERT INTO ITEM " +
                "(ITEM_ID, ITEM_NAME, CATEGORY, DESCRIPTION, " +
                "COLOR, BRAND, IDENTIFICATION_DETAILS, STATUS) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        String foundSql =
                "INSERT INTO FOUND_REPORT " +
                "(FOUND_ID, ITEM_ID, USER_ID, LOCATION, FOUND_DATE) " +
                "VALUES (?, ?, ?, ?, ?)";

        try {

            con = DBUtil.getConnection();

            con.setAutoCommit(false);

            try (
                PreparedStatement itemPs =
                        con.prepareStatement(itemSql);
                PreparedStatement foundPs =
                        con.prepareStatement(foundSql)
            ) {

                // Insert ITEM

                itemPs.setInt(
                        1,
                        item.getItemId()
                );

                itemPs.setString(
                        2,
                        item.getItemName()
                );

                itemPs.setString(
                        3,
                        item.getCategory()
                );

                itemPs.setString(
                        4,
                        item.getDescription()
                );

                itemPs.setString(
                        5,
                        item.getColor()
                );

                itemPs.setString(
                        6,
                        item.getBrand()
                );

                itemPs.setString(
                        7,
                        item.getIdentificationDetails()
                );

                itemPs.setString(
                        8,
                        "FOUND"
                );

                int itemRows =
                        itemPs.executeUpdate();

                if (itemRows <= 0) {

                    con.rollback();

                    return false;
                }

                // Insert FOUND_REPORT

                foundPs.setInt(
                        1,
                        foundId
                );

                foundPs.setInt(
                        2,
                        item.getItemId()
                );

                foundPs.setInt(
                        3,
                        userId
                );

                foundPs.setString(
                        4,
                        location
                );

                foundPs.setDate(
                        5,
                        foundDate
                );

                int foundRows =
                        foundPs.executeUpdate();

                if (foundRows <= 0) {

                    con.rollback();

                    return false;
                }

                // Both successful

                con.commit();

                return true;
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
    // VIEW ALL FOUND ITEMS
    // ==========================================

    public void viewAllFoundItems() {

        String sql =
                "SELECT f.FOUND_ID, " +
                "i.ITEM_ID, " +
                "i.ITEM_NAME, " +
                "i.CATEGORY, " +
                "i.COLOR, " +
                "i.BRAND, " +
                "i.STATUS, " +
                "f.LOCATION, " +
                "f.FOUND_DATE, " +
                "u.NAME AS REPORTER " +
                "FROM FOUND_REPORT f " +
                "JOIN ITEM i " +
                "ON f.ITEM_ID = i.ITEM_ID " +
                "JOIN USERS u " +
                "ON f.USER_ID = u.USER_ID " +
                "ORDER BY f.FOUND_DATE DESC";

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
                    "                 FOUND ITEMS"
            );

            System.out.println(
                    "=============================================================="
            );

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println(
                        "Found ID    : " +
                        rs.getInt("FOUND_ID")
                );

                System.out.println(
                        "Item ID     : " +
                        rs.getInt("ITEM_ID")
                );

                System.out.println(
                        "Item Name   : " +
                        rs.getString("ITEM_NAME")
                );

                System.out.println(
                        "Category    : " +
                        rs.getString("CATEGORY")
                );

                System.out.println(
                        "Color       : " +
                        rs.getString("COLOR")
                );

                System.out.println(
                        "Brand       : " +
                        rs.getString("BRAND")
                );

                System.out.println(
                        "Status      : " +
                        rs.getString("STATUS")
                );

                System.out.println(
                        "Location    : " +
                        rs.getString("LOCATION")
                );

                System.out.println(
                        "Found Date  : " +
                        rs.getDate("FOUND_DATE")
                );

                System.out.println(
                        "Reporter    : " +
                        rs.getString("REPORTER")
                );

                System.out.println(
                        "--------------------------------------------------------------"
                );
            }

            if (!found) {

                System.out.println(
                        "No found items available."
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}