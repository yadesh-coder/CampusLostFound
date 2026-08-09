package com.lostfoun;

import com.lostfound.dao.FoundReportDAO;

public class TestFoundItems {

    public static void main(String[] args) {

        FoundReportDAO dao = new FoundReportDAO();

        System.out.println("===== ALL FOUND ITEMS =====");

        dao.viewAllFoundItems();
    }
}