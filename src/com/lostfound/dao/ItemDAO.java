package com.lostfound.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lostfound.model.Item;
import com.lostfound.util.DBUtil;

public class ItemDAO {

    // ==========================================
    // ADD ITEM
    // ==========================================

    public boolean addItem(Item item) {

        String sql =
                "INSERT INTO ITEM " +
                "(ITEM_ID, ITEM_NAME, CATEGORY, DESCRIPTION, " +
                "COLOR, BRAND, IDENTIFICATION_DETAILS, STATUS) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, item.getItemId());
            ps.setString(2, item.getItemName());
            ps.setString(3, item.getCategory());
            ps.setString(4, item.getDescription());
            ps.setString(5, item.getColor());
            ps.setString(6, item.getBrand());
            ps.setString(7, item.getIdentificationDetails());
            ps.setString(8, item.getStatus());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // ==========================================
    // DELETE ITEM
    // ==========================================

    public boolean deleteItem(int itemId) {

        String sql =
                "DELETE FROM ITEM WHERE ITEM_ID = ?";

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, itemId);

            int rows = ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // ==========================================
    // SEARCH ITEMS
    // ==========================================

    public void searchItems(String keyword) {

        String sql =
                "SELECT i.ITEM_ID, " +
                "i.ITEM_NAME, " +
                "i.CATEGORY, " +
                "i.DESCRIPTION, " +
                "i.COLOR, " +
                "i.BRAND, " +
                "i.IDENTIFICATION_DETAILS, " +
                "i.STATUS " +
                "FROM ITEM i " +
                "WHERE UPPER(i.ITEM_NAME) LIKE UPPER(?) " +
                "OR UPPER(i.CATEGORY) LIKE UPPER(?) " +
                "OR UPPER(i.COLOR) LIKE UPPER(?) " +
                "OR UPPER(i.BRAND) LIKE UPPER(?)";

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            String search =
                    "%" + keyword + "%";

            ps.setString(1, search);
            ps.setString(2, search);
            ps.setString(3, search);
            ps.setString(4, search);

            try (ResultSet rs = ps.executeQuery()) {

                boolean found = false;

                System.out.println();
                System.out.println(
                        "=================================================="
                );

                System.out.println(
                        "                 SEARCH RESULTS"
                );

                System.out.println(
                        "=================================================="
                );

                while (rs.next()) {

                    found = true;

                    System.out.println(
                            "Item ID       : " +
                            rs.getInt("ITEM_ID")
                    );

                    System.out.println(
                            "Item Name     : " +
                            rs.getString("ITEM_NAME")
                    );

                    System.out.println(
                            "Category      : " +
                            rs.getString("CATEGORY")
                    );

                    System.out.println(
                            "Description   : " +
                            rs.getString("DESCRIPTION")
                    );

                    System.out.println(
                            "Color         : " +
                            rs.getString("COLOR")
                    );

                    System.out.println(
                            "Brand         : " +
                            rs.getString("BRAND")
                    );

                    System.out.println(
                            "Identification: " +
                            rs.getString(
                                    "IDENTIFICATION_DETAILS"
                            )
                    );

                    System.out.println(
                            "Status        : " +
                            rs.getString("STATUS")
                    );

                    System.out.println(
                            "--------------------------------------------------"
                    );
                }

                if (!found) {

                    System.out.println(
                            "No items found."
                    );
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ==========================================
    // SEARCH BY STATUS
    // ==========================================

    public void searchByStatus(
            String status,
            String keyword) {

        String sql =
                "SELECT i.ITEM_ID, " +
                "i.ITEM_NAME, " +
                "i.CATEGORY, " +
                "i.DESCRIPTION, " +
                "i.COLOR, " +
                "i.BRAND, " +
                "i.STATUS, " +
                "u.NAME AS REPORTER, " +
                "r.LOCATION " +
                "FROM ITEM i " +
                "LEFT JOIN LOST_REPORT l " +
                "ON i.ITEM_ID = l.ITEM_ID " +
                "LEFT JOIN FOUND_REPORT r " +
                "ON i.ITEM_ID = r.ITEM_ID " +
                "LEFT JOIN USERS u " +
                "ON (l.USER_ID = u.USER_ID " +
                "OR r.USER_ID = u.USER_ID) " +
                "WHERE i.STATUS = ? " +
                "AND (" +
                "UPPER(i.ITEM_NAME) LIKE UPPER(?) " +
                "OR UPPER(i.CATEGORY) LIKE UPPER(?) " +
                "OR UPPER(i.COLOR) LIKE UPPER(?) " +
                "OR UPPER(i.BRAND) LIKE UPPER(?) " +
                "OR UPPER(NVL(l.LOCATION, r.LOCATION)) LIKE UPPER(?)" +
                ")";

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps =
                    con.prepareStatement(sql)
        ) {

            String search =
                    "%" + keyword + "%";

            ps.setString(1, status);
            ps.setString(2, search);
            ps.setString(3, search);
            ps.setString(4, search);
            ps.setString(5, search);
            ps.setString(6, search);

            try (ResultSet rs = ps.executeQuery()) {

                boolean found = false;

                System.out.println();
                System.out.println(
                        "=================================================="
                );

                System.out.println(
                        "             " + status + " ITEMS"
                );

                System.out.println(
                        "=================================================="
                );

                while (rs.next()) {

                    found = true;

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
                            "Description: " +
                            rs.getString("DESCRIPTION")
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
                            "Status     : " +
                            rs.getString("STATUS")
                    );

                    System.out.println(
                            "Reporter   : " +
                            rs.getString("REPORTER")
                    );

                    System.out.println(
                            "Location   : " +
                            rs.getString("LOCATION")
                    );

                    System.out.println(
                            "--------------------------------------------------"
                    );
                }

                if (!found) {

                    System.out.println(
                            "No " + status +
                            " items found."
                    );
                }
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}