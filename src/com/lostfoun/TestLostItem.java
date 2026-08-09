package com.lostfoun;

import java.sql.Date;

import com.lostfound.dao.ItemDAO;
import com.lostfound.dao.LostReportDAO;
import com.lostfound.model.Item;

public class TestLostItem {

    public static void main(String[] args) {

        Item item = new Item(
                1004,
                "Black Backpack",
                "BAG",
                "Black laptop backpack",
                "Black",
                "American Tourister",
                "Small blue keychain attached",
                "LOST"
        );

        ItemDAO itemDAO = new ItemDAO();

        boolean itemAdded = itemDAO.addItem(item);

        if (itemAdded) {

            System.out.println("Item Added Successfully!");

            LostReportDAO lostDAO = new LostReportDAO();

            boolean reportAdded = lostDAO.addLostReport(
                    5002,
                    1004,
                    101,
                    "Computer Lab",
                    Date.valueOf("2026-08-08")
            );

            if (reportAdded) {
                System.out.println("Lost Report Added Successfully!");
            } else {
                System.out.println("Lost Report Failed!");
            }

        } else {
            System.out.println("Item Addition Failed!");
        }
    }
}