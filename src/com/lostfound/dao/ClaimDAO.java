package com.lostfound.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.lostfound.model.Claim;
import com.lostfound.util.DBUtil;

public class ClaimDAO {

    // ==========================================
    // CREATE CLAIM
    // ==========================================

    public boolean createClaim(Claim claim) {

        String sql =
                "INSERT INTO CLAIM " +
                "(CLAIM_ID, FOUND_ID, USER_ID, " +
                "CLAIM_DESCRIPTION, STATUS) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps =
                    con.prepareStatement(sql)
        ) {

            ps.setInt(
                    1,
                    claim.getClaimId()
            );

            ps.setInt(
                    2,
                    claim.getFoundId()
            );

            ps.setInt(
                    3,
                    claim.getUserId()
            );

            ps.setString(
                    4,
                    claim.getClaimDescription()
            );

            ps.setString(
                    5,
                    "PENDING"
            );

            return ps.executeUpdate() > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }

    // ==========================================
    // VIEW PENDING CLAIMS
    // ==========================================

    public void viewPendingClaims() {

        String sql =
                "SELECT c.CLAIM_ID, " +
                "c.FOUND_ID, " +
                "c.CLAIM_DESCRIPTION, " +
                "c.STATUS, " +
                "u.USER_ID, " +
                "u.NAME AS CLAIMANT, " +
                "u.EMAIL, " +
                "i.ITEM_NAME, " +
                "i.CATEGORY, " +
                "i.COLOR, " +
                "i.BRAND, " +
                "f.LOCATION, " +
                "f.FOUND_DATE " +
                "FROM CLAIM c " +
                "JOIN USERS u " +
                "ON c.USER_ID = u.USER_ID " +
                "JOIN FOUND_REPORT f " +
                "ON c.FOUND_ID = f.FOUND_ID " +
                "JOIN ITEM i " +
                "ON f.ITEM_ID = i.ITEM_ID " +
                "WHERE c.STATUS = 'PENDING' " +
                "ORDER BY c.CLAIM_ID";

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
                    "                  PENDING CLAIMS"
            );

            System.out.println(
                    "=============================================================="
            );

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println(
                        "Claim ID       : " +
                        rs.getInt("CLAIM_ID")
                );

                System.out.println(
                        "Found ID       : " +
                        rs.getInt("FOUND_ID")
                );

                System.out.println(
                        "Claimant ID    : " +
                        rs.getInt("USER_ID")
                );

                System.out.println(
                        "Claimant       : " +
                        rs.getString("CLAIMANT")
                );

                System.out.println(
                        "Email          : " +
                        rs.getString("EMAIL")
                );

                System.out.println(
                        "Item Name      : " +
                        rs.getString("ITEM_NAME")
                );

                System.out.println(
                        "Category       : " +
                        rs.getString("CATEGORY")
                );

                System.out.println(
                        "Color          : " +
                        rs.getString("COLOR")
                );

                System.out.println(
                        "Brand          : " +
                        rs.getString("BRAND")
                );

                System.out.println(
                        "Location       : " +
                        rs.getString("LOCATION")
                );

                System.out.println(
                        "Found Date     : " +
                        rs.getDate("FOUND_DATE")
                );

                System.out.println(
                        "Description    : " +
                        rs.getString("CLAIM_DESCRIPTION")
                );

                System.out.println(
                        "Status         : " +
                        rs.getString("STATUS")
                );

                System.out.println(
                        "--------------------------------------------------------------"
                );
            }

            if (!found) {

                System.out.println(
                        "No pending claims."
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    // ==========================================
    // APPROVE CLAIM
    // ==========================================

    public boolean approveClaim(int claimId) {

        Connection con = null;

        String claimSql =
                "UPDATE CLAIM " +
                "SET STATUS = 'APPROVED' " +
                "WHERE CLAIM_ID = ? " +
                "AND STATUS = 'PENDING'";

        String foundSql =
                "UPDATE ITEM " +
                "SET STATUS = 'RETURNED' " +
                "WHERE ITEM_ID = " +
                "(SELECT ITEM_ID FROM FOUND_REPORT " +
                "WHERE FOUND_ID = " +
                "(SELECT FOUND_ID FROM CLAIM " +
                "WHERE CLAIM_ID = ?))";

        String reportSql =
                "UPDATE FOUND_REPORT " +
                "SET STATUS = 'CLOSED' " +
                "WHERE FOUND_ID = " +
                "(SELECT FOUND_ID FROM CLAIM " +
                "WHERE CLAIM_ID = ?)";

        try {

            con = DBUtil.getConnection();

            con.setAutoCommit(false);

            int claimRows;

            try (
                PreparedStatement ps =
                        con.prepareStatement(claimSql)
            ) {

                ps.setInt(1, claimId);

                claimRows =
                        ps.executeUpdate();
            }

            if (claimRows == 0) {

                con.rollback();

                return false;
            }

            try (
                PreparedStatement ps =
                        con.prepareStatement(foundSql)
            ) {

                ps.setInt(1, claimId);

                ps.executeUpdate();
            }

            try (
                PreparedStatement ps =
                        con.prepareStatement(reportSql)
            ) {

                ps.setInt(1, claimId);

                ps.executeUpdate();
            }

            con.commit();

            return true;

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
    // REJECT CLAIM
    // ==========================================

    public boolean rejectClaim(int claimId) {

        String sql =
                "UPDATE CLAIM " +
                "SET STATUS = 'REJECTED' " +
                "WHERE CLAIM_ID = ? " +
                "AND STATUS = 'PENDING'";

        try (
            Connection con = DBUtil.getConnection();
            PreparedStatement ps =
                    con.prepareStatement(sql)
        ) {

            ps.setInt(1, claimId);

            int rows =
                    ps.executeUpdate();

            return rows > 0;

        } catch (Exception e) {

            e.printStackTrace();

            return false;
        }
    }
}