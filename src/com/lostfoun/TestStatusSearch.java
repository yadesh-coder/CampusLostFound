package com.lostfoun;

import java.util.Scanner;

import com.lostfound.dao.ItemDAO;

public class TestStatusSearch {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== LOST / FOUND SEARCH =====");

        System.out.print("Enter status (LOST / FOUND): ");
        String status = sc.nextLine();

        System.out.print("Enter keyword: ");
        String keyword = sc.nextLine();

        ItemDAO dao = new ItemDAO();

        dao.searchByStatus(status, keyword);

        sc.close();
    }
}