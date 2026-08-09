package com.lostfoun;

import java.util.Scanner;

import com.lostfound.dao.ItemDAO;

public class TestItemSearch {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("===== SEARCH ITEMS =====");

        System.out.print("Enter item name/category/color/brand: ");
        String keyword = sc.nextLine();

        ItemDAO dao = new ItemDAO();

        dao.searchItems(keyword);

        sc.close();
    }
}