package com.lostfoun;

import java.util.Scanner;

import com.lostfound.dao.ClaimDAO;
import com.lostfound.model.Claim;

public class TestClaim {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== CLAIM FOUND ITEM =====");

        System.out.print("Enter Claim ID: ");
        int claimId = sc.nextInt();

        System.out.print("Enter Found ID: ");
        int foundId = sc.nextInt();

        System.out.print("Enter Claimant ID: ");
        int claimantId = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter Claim Description: ");
        String description = sc.nextLine();

        Claim claim = new Claim(
                claimId,
                foundId,
                claimantId,
                description,
                "PENDING"
        );

        ClaimDAO dao = new ClaimDAO();

        boolean result = dao.createClaim(claim);

        if (result) {
            System.out.println("Claim Submitted Successfully!");
            System.out.println("Status: PENDING");
        } else {
            System.out.println("Claim Submission Failed!");
        }

        sc.close();
    }
}