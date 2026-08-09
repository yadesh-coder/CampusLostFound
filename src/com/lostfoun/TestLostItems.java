package com.lostfoun;

import com.lostfound.dao.LostReportDAO;

public class TestLostItems {

    public static void main(String[] args) {

        LostReportDAO dao = new LostReportDAO();

        System.out.println("===== ALL LOST ITEMS =====");

        dao.viewAllLostItems();
    }
}