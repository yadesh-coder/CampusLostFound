package com.lostfoun;

import com.lostfound.dao.ClaimDAO;

public class TestPendingClaims {

    public static void main(String[] args) {

        ClaimDAO dao = new ClaimDAO();

        System.out.println("===== PENDING CLAIMS =====");

        dao.viewPendingClaims();
    }
}