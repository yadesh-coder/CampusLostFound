package com.lostfound.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lostfound.util.DBUtil;

public class MatchDAO {

    public void findMatches(int lostId) {

        String sql =
                "SELECT L.LOST_ID, " +
                "       F.FOUND_ID, " +
                "       LI.ITEM_NAME AS LOST_ITEM, " +
                "       FI.ITEM_NAME AS FOUND_ITEM, " +
                "       LI.CATEGORY, " +
                "       LI.COLOR AS LOST_COLOR, " +
                "       FI.COLOR AS FOUND_COLOR, " +
                "       LI.BRAND AS LOST_BRAND, " +
                "       FI.BRAND AS FOUND_BRAND, " +
                "       L.LOST_LOCATION, " +
                "       F.FOUND_LOCATION " +
                "FROM LOST_REPORTS L " +
                "JOIN ITEMS LI ON L.ITEM_ID = LI.ITEM_ID " +
                "JOIN FOUND_REPORTS F ON F.REPORT_STATUS = 'ACTIVE' " +
                "JOIN ITEMS FI ON F.ITEM_ID = FI.ITEM_ID " +
                "WHERE L.LOST_ID = ? " +
                "AND LI.CATEGORY = FI.CATEGORY " +
                "AND ( " +
                "       UPPER(LI.ITEM_NAME) = UPPER(FI.ITEM_NAME) " +
                "       OR UPPER(LI.COLOR) = UPPER(FI.COLOR) " +
                "       OR UPPER(LI.BRAND) = UPPER(FI.BRAND) " +
                "       OR UPPER(L.LOST_LOCATION) = UPPER(F.FOUND_LOCATION) " +
                "    ) " +
                "ORDER BY F.FOUND_ID";

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, lostId);

            try (ResultSet rs = ps.executeQuery()) {

                boolean found = false;

                while (rs.next()) {

                    found = true;

                    int score = 0;

                    if (rs.getString("LOST_ITEM")
                            .equalsIgnoreCase(rs.getString("FOUND_ITEM"))) {
                        score++;
                    }

                    if (rs.getString("LOST_COLOR")
                            .equalsIgnoreCase(rs.getString("FOUND_COLOR"))) {
                        score++;
                    }

                    if (rs.getString("LOST_BRAND")
                            .equalsIgnoreCase(rs.getString("FOUND_BRAND"))) {
                        score++;
                    }

                    if (rs.getString("LOST_LOCATION")
                            .equalsIgnoreCase(rs.getString("FOUND_LOCATION"))) {
                        score++;
                    }

                    System.out.println("--------------------------------");
                    System.out.println("Possible Match Found!");
                    System.out.println("Lost ID       : "
                            + rs.getInt("LOST_ID"));
                    System.out.println("Found ID      : "
                            + rs.getInt("FOUND_ID"));
                    System.out.println("Lost Item     : "
                            + rs.getString("LOST_ITEM"));
                    System.out.println("Found Item    : "
                            + rs.getString("FOUND_ITEM"));
                    System.out.println("Category      : "
                            + rs.getString("CATEGORY"));
                    System.out.println("Lost Color    : "
                            + rs.getString("LOST_COLOR"));
                    System.out.println("Found Color   : "
                            + rs.getString("FOUND_COLOR"));
                    System.out.println("Lost Brand    : "
                            + rs.getString("LOST_BRAND"));
                    System.out.println("Found Brand   : "
                            + rs.getString("FOUND_BRAND"));
                    System.out.println("Lost Location : "
                            + rs.getString("LOST_LOCATION"));
                    System.out.println("Found Location: "
                            + rs.getString("FOUND_LOCATION"));
                    System.out.println("Match Score   : "
                            + score + "/4");
                }

                if (!found) {
                    System.out.println("No Possible Matches Found!");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}