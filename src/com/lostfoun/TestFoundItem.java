package com.lostfoun;

import java.sql.Date;

import com.lostfound.dao.FoundReportDAO;
import com.lostfound.model.Item;

public class TestFoundItem {

    public static void main(String[] args) {

        Item item = new Item(
                1005,
                "Blue College ID Card",
                "ID_CARD",
                "College identity card",
                "Blue",
                "College",
                "Name printed on the card",
                "FOUND"
        );

        FoundReportDAO dao = new FoundReportDAO();

        boolean result = dao.addFoundItem(
                item,
                6003,
                102,
                "Main Block",
                Date.valueOf("2026-08-08")
        );

        if (result) {
            System.out.println("Found Item Added Successfully!");
        } else {
            System.out.println("Found Item Addition Failed!");
        }
    }
}