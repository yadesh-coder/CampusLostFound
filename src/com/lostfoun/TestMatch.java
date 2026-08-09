package com.lostfoun;

import java.util.Scanner;

import com.lostfound.dao.MatchDAO;

public class TestMatch {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== LOST ↔ FOUND MATCHING =====");

        System.out.print("Enter Lost Report ID: ");
        int lostId = sc.nextInt();

        MatchDAO dao = new MatchDAO();

        dao.findMatches(lostId);

        sc.close();
    }
}