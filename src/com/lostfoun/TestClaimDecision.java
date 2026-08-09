package com.lostfoun;

import java.util.Scanner;

import com.lostfound.dao.ClaimDAO;

public class TestClaimDecision {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        ClaimDAO dao = new ClaimDAO();

        System.out.println("===== CLAIM VERIFICATION =====");

        System.out.print("Enter Claim ID: ");
        int claimId = sc.nextInt();

        System.out.print("Enter 1 to APPROVE or 2 to REJECT: ");
        int choice = sc.nextInt();

        boolean result;

        if (choice == 1) {

            result = dao.approveClaim(claimId);

            if (result) {
                System.out.println("Claim Approved Successfully!");
                System.out.println("Item Status: RETURNED");
                System.out.println("Report Status: CLOSED");
            } else {
                System.out.println("Claim Approval Failed!");
            }

        } else if (choice == 2) {

            result = dao.rejectClaim(claimId);

            if (result) {
                System.out.println("Claim Rejected Successfully!");
            } else {
                System.out.println("Claim Rejection Failed!");
            }

        } else {

            System.out.println("Invalid Choice!");
        }

        sc.close();
    }
}