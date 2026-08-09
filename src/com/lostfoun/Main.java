package com.lostfoun;

import java.sql.Date;
import java.util.Scanner;

import com.lostfound.dao.ClaimDAO;
import com.lostfound.dao.FoundReportDAO;
import com.lostfound.dao.ItemDAO;
import com.lostfound.dao.LoginDAO;
import com.lostfound.dao.LostReportDAO;
import com.lostfound.dao.MainUserSession;
import com.lostfound.dao.MatchDAO;
import com.lostfound.dao.UserDAO;
import com.lostfound.exception.InvalidInputException;
import com.lostfound.model.Claim;
import com.lostfound.model.Item;
import com.lostfound.model.User;
import com.lostfound.util.ValidationUtil;

public class Main {

    static Scanner sc = new Scanner(System.in);

    static UserDAO userDAO = new UserDAO();
    static ItemDAO itemDAO = new ItemDAO();
    static LostReportDAO lostDAO = new LostReportDAO();
    static FoundReportDAO foundDAO = new FoundReportDAO();
    static MatchDAO matchDAO = new MatchDAO();
    static ClaimDAO claimDAO = new ClaimDAO();
    static LoginDAO loginDAO = new LoginDAO();

    // ==========================================
    // MAIN
    // ==========================================

    public static void main(String[] args) {

        while (true) {

            if (!login()) {

                System.out.println();
                System.out.println(
                        "Login failed."
                );

                System.out.println(
                        "1. Try Again"
                );

                System.out.println(
                        "2. Exit"
                );

                System.out.print(
                        "Enter choice: "
                );

                String choice = sc.nextLine();

                if (!choice.equals("1")) {
                    break;
                }

                continue;
            }

            String role =
                    MainUserSession.getRole();

            if (role.equalsIgnoreCase("ADMIN")) {

                adminMenu();

            } else if (role.equalsIgnoreCase("USER")) {

                userMenu();

            } else {

                System.out.println(
                        "Invalid role!"
                );

                MainUserSession.logout();
            }

            if (!MainUserSession.isLoggedIn()) {

                System.out.println();
                System.out.println(
                        "1. Login Again"
                );

                System.out.println(
                        "2. Exit"
                );

                System.out.print(
                        "Enter choice: "
                );

                String choice =
                        sc.nextLine();

                if (!choice.equals("1")) {
                    break;
                }
            }
        }

        sc.close();

        System.out.println();
        System.out.println(
                "Thank you for using Campus Lost & Found."
        );
    }

    // ==========================================
    // LOGIN
    // ==========================================

    static boolean login() {

        System.out.println();
        System.out.println(
                "=========================================="
        );

        System.out.println(
                "       CAMPUS LOST & FOUND SYSTEM"
        );

        System.out.println(
                "                LOGIN"
        );

        System.out.println(
                "=========================================="
        );

        try {

            System.out.print(
                    "Username: "
            );

            String username =
                    sc.nextLine();

            ValidationUtil.validateUsername(
                    username
            );

            System.out.print(
                    "Password: "
            );

            String password =
                    sc.nextLine();

            ValidationUtil.validatePassword(
                    password
            );

            boolean result =
                    loginDAO.login(
                            username,
                            password
                    );

            if (result) {

                System.out.println();
                System.out.println(
                        "Login Successful!"
                );

                System.out.println(
                        "User ID  : " +
                        MainUserSession.getUserId()
                );

                System.out.println(
                        "Username : " +
                        MainUserSession.getUsername()
                );

                System.out.println(
                        "Role     : " +
                        MainUserSession.getRole()
                );

                return true;
            }

            System.out.println();
            System.out.println(
                    "Invalid Username or Password!"
            );

        } catch (InvalidInputException e) {

            System.out.println(
                    "Validation Error: " +
                    e.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                    "Login Error: " +
                    e.getMessage()
            );
        }

        return false;
    }

    // ==========================================
    // ADMIN MENU
    // ==========================================

    static void adminMenu() {

        while (
                MainUserSession.isLoggedIn()
                &&
                MainUserSession.getRole()
                        .equalsIgnoreCase("ADMIN")
        ) {

            System.out.println();
            System.out.println(
                    "=========================================="
            );

            System.out.println(
                    "             ADMIN DASHBOARD"
            );

            System.out.println(
                    "=========================================="
            );

            System.out.println(
                    "Logged in: " +
                    MainUserSession.getUsername()
            );

            System.out.println();

            System.out.println(
                    "1.  View All Users"
            );

            System.out.println(
                    "2.  Add User"
            );

            System.out.println(
                    "3.  Search User"
            );

            System.out.println(
                    "4.  Update User"
            );

            System.out.println(
                    "5.  Delete User"
            );

            System.out.println(
                    "6.  View Lost Items"
            );

            System.out.println(
                    "7.  View Found Items"
            );

            System.out.println(
                    "8.  Search Items"
            );

            System.out.println(
                    "9.  Find Possible Matches"
            );

            System.out.println(
                    "10. View Pending Claims"
            );

            System.out.println(
                    "11. Approve Claim"
            );

            System.out.println(
                    "12. Reject Claim"
            );

            System.out.println(
                    "13. Change Password"
            );

            System.out.println(
                    "14. Logout"
            );

            System.out.println(
                    "=========================================="
            );

            try {

                System.out.print(
                        "Enter choice: "
                );

                int choice =
                        Integer.parseInt(
                                sc.nextLine()
                        );

                switch (choice) {

                    case 1:
                        userDAO.viewAllUsers();
                        break;

                    case 2:
                        addUser();
                        break;

                    case 3:
                        searchUser();
                        break;

                    case 4:
                        updateUser();
                        break;

                    case 5:
                        deleteUser();
                        break;

                    case 6:
                        lostDAO.viewAllLostItems();
                        break;

                    case 7:
                        foundDAO.viewAllFoundItems();
                        break;

                    case 8:
                        searchItems();
                        break;

                    case 9:
                        findMatches();
                        break;

                    case 10:
                        claimDAO.viewPendingClaims();
                        break;

                    case 11:
                        approveClaim();
                        break;

                    case 12:
                        rejectClaim();
                        break;

                    case 13:
                        changePassword();
                        break;

                    case 14:

                        MainUserSession.logout();

                        System.out.println(
                                "Admin logged out successfully."
                        );

                        break;

                    default:

                        System.out.println(
                                "Invalid choice!"
                        );
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );

            } catch (Exception e) {

                System.out.println(
                        "Error: " +
                        e.getMessage()
                );
            }
        }
    }

    // ==========================================
    // USER MENU
    // ==========================================

    static void userMenu() {

        while (
                MainUserSession.isLoggedIn()
                &&
                MainUserSession.getRole()
                        .equalsIgnoreCase("USER")
        ) {

            System.out.println();
            System.out.println(
                    "=========================================="
            );

            System.out.println(
                    "              USER DASHBOARD"
            );

            System.out.println(
                    "=========================================="
            );

            System.out.println(
                    "Logged in: " +
                    MainUserSession.getUsername()
            );

            System.out.println();

            System.out.println(
                    "1.  Report Lost Item"
            );

            System.out.println(
                    "2.  Report Found Item"
            );

            System.out.println(
                    "3.  View Lost Items"
            );

            System.out.println(
                    "4.  View Found Items"
            );

            System.out.println(
                    "5.  Search Items"
            );

            System.out.println(
                    "6.  Search LOST Items"
            );

            System.out.println(
                    "7.  Search FOUND Items"
            );

            System.out.println(
                    "8.  Find Possible Matches"
            );

            System.out.println(
                    "9.  Claim Found Item"
            );

            System.out.println(
                    "10. Change Password"
            );

            System.out.println(
                    "11. Logout"
            );

            System.out.println(
                    "=========================================="
            );

            try {

                System.out.print(
                        "Enter choice: "
                );

                int choice =
                        Integer.parseInt(
                                sc.nextLine()
                        );

                switch (choice) {

                    case 1:
                        reportLostItem();
                        break;

                    case 2:
                        reportFoundItem();
                        break;

                    case 3:
                        lostDAO.viewAllLostItems();
                        break;

                    case 4:
                        foundDAO.viewAllFoundItems();
                        break;

                    case 5:
                        searchItems();
                        break;

                    case 6:
                        searchByStatus("LOST");
                        break;

                    case 7:
                        searchByStatus("FOUND");
                        break;

                    case 8:
                        findMatches();
                        break;

                    case 9:
                        createClaim();
                        break;

                    case 10:
                        changePassword();
                        break;

                    case 11:

                        MainUserSession.logout();

                        System.out.println(
                                "User logged out successfully."
                        );

                        break;

                    default:

                        System.out.println(
                                "Invalid choice!"
                        );
                }

            } catch (NumberFormatException e) {

                System.out.println(
                        "Please enter a valid number."
                );

            } catch (Exception e) {

                System.out.println(
                        "Error: " +
                        e.getMessage()
                );
            }
        }
    }

    // ==========================================
    // ADD USER
    // ==========================================

    static void addUser() {

        try {

            System.out.println();
            System.out.println(
                    "========== ADD USER =========="
            );

            System.out.print(
                    "User ID: "
            );

            int id =
                    Integer.parseInt(
                            sc.nextLine()
                    );

            ValidationUtil.validateId(id);

            System.out.print(
                    "Name: "
            );

            String name =
                    sc.nextLine();

            ValidationUtil.validateName(name);

            System.out.print(
                    "Email: "
            );

            String email =
                    sc.nextLine();

            ValidationUtil.validateEmail(email);

            System.out.print(
                    "Phone: "
            );

            String phone =
                    sc.nextLine();

            ValidationUtil.validatePhone(phone);

            System.out.print(
                    "Department: "
            );

            String department =
                    sc.nextLine();

            if (department.trim().isEmpty()) {

                throw new InvalidInputException(
                        "Department cannot be empty."
                );
            }

            System.out.print(
                    "Year: "
            );

            int year =
                    Integer.parseInt(
                            sc.nextLine()
                    );

            ValidationUtil.validateYear(year);

            System.out.print(
                    "Role (ADMIN/USER): "
            );

            String role =
                    sc.nextLine();

            ValidationUtil.validateRole(role);

            System.out.print(
                    "Username: "
            );

            String username =
                    sc.nextLine();

            ValidationUtil.validateUsername(
                    username
            );

            System.out.print(
                    "Password: "
            );

            String password =
                    sc.nextLine();

            ValidationUtil.validatePassword(
                    password
            );

            User user =
                    new User(
                            id,
                            name,
                            email,
                            phone,
                            department,
                            year,
                            role.toUpperCase(),
                            username,
                            password
                    );

            if (userDAO.addUser(user)) {

                System.out.println();
                System.out.println(
                        "User Added Successfully!"
                );

            } else {

                System.out.println(
                        "User Addition Failed!"
                );
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a valid number."
            );

        } catch (InvalidInputException e) {

            System.out.println(
                    "Validation Error: " +
                    e.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                    "Error: " +
                    e.getMessage()
            );
        }
    }

    // ==========================================
    // SEARCH USER
    // ==========================================

    static void searchUser() {

        try {

            System.out.print(
                    "Enter User ID: "
            );

            int id =
                    Integer.parseInt(
                            sc.nextLine()
                    );

            ValidationUtil.validateId(id);

            User user =
                    userDAO.findUserById(id);

            if (user != null) {

                System.out.println();
                System.out.println(
                        "========== USER DETAILS =========="
                );

                System.out.println(
                        "User ID    : " +
                        user.getUserId()
                );

                System.out.println(
                        "Name       : " +
                        user.getName()
                );

                System.out.println(
                        "Email      : " +
                        user.getEmail()
                );

                System.out.println(
                        "Phone      : " +
                        user.getPhone()
                );

                System.out.println(
                        "Department : " +
                        user.getDepartment()
                );

                System.out.println(
                        "Year       : " +
                        user.getYear()
                );

                System.out.println(
                        "Role       : " +
                        user.getRole()
                );

                System.out.println(
                        "Username   : " +
                        user.getUsername()
                );

            } else {

                System.out.println(
                        "User Not Found!"
                );
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a valid User ID."
            );

        } catch (InvalidInputException e) {

            System.out.println(
                    "Validation Error: " +
                    e.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                    "Error: " +
                    e.getMessage()
            );
        }
    }

    // ==========================================
    // UPDATE USER
    // ==========================================

    static void updateUser() {

        try {

            System.out.print(
                    "Enter User ID: "
            );

            int id =
                    Integer.parseInt(
                            sc.nextLine()
                    );

            ValidationUtil.validateId(id);

            System.out.print(
                    "Name: "
            );

            String name =
                    sc.nextLine();

            ValidationUtil.validateName(name);

            System.out.print(
                    "Email: "
            );

            String email =
                    sc.nextLine();

            ValidationUtil.validateEmail(email);

            System.out.print(
                    "Phone: "
            );

            String phone =
                    sc.nextLine();

            ValidationUtil.validatePhone(phone);

            System.out.print(
                    "Department: "
            );

            String department =
                    sc.nextLine();

            if (department.trim().isEmpty()) {

                throw new InvalidInputException(
                        "Department cannot be empty."
                );
            }

            System.out.print(
                    "Year: "
            );

            int year =
                    Integer.parseInt(
                            sc.nextLine()
                    );

            ValidationUtil.validateYear(year);

            System.out.print(
                    "Role (ADMIN/USER): "
            );

            String role =
                    sc.nextLine();

            ValidationUtil.validateRole(role);

            User user =
                    new User(
                            id,
                            name,
                            email,
                            phone,
                            department,
                            year,
                            role.toUpperCase()
                    );

            if (userDAO.updateUser(user)) {

                System.out.println(
                        "User Updated Successfully!"
                );

            } else {

                System.out.println(
                        "User Update Failed!"
                );
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter valid numeric values."
            );

        } catch (InvalidInputException e) {

            System.out.println(
                    "Validation Error: " +
                    e.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                    "Error: " +
                    e.getMessage()
            );
        }
    }

    // ==========================================
    // DELETE USER
    // ==========================================

    static void deleteUser() {

        try {

            System.out.print(
                    "Enter User ID: "
            );

            int id =
                    Integer.parseInt(
                            sc.nextLine()
                    );

            ValidationUtil.validateId(id);

            System.out.print(
                    "Are you sure? (Y/N): "
            );

            String confirm =
                    sc.nextLine();

            if (!confirm.equalsIgnoreCase("Y")) {

                System.out.println(
                        "Delete cancelled."
                );

                return;
            }

            if (userDAO.deleteUser(id)) {

                System.out.println(
                        "User Deleted Successfully!"
                );

            } else {

                System.out.println(
                        "User Not Found!"
                );
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a valid User ID."
            );

        } catch (InvalidInputException e) {

            System.out.println(
                    "Validation Error: " +
                    e.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                    "Error: " +
                    e.getMessage()
            );
        }
    }

    // ==========================================
    // REPORT LOST ITEM
    // ==========================================

    static void reportLostItem() {

        try {

            System.out.println();
            System.out.println(
                    "========== REPORT LOST ITEM =========="
            );

            System.out.print(
                    "Item ID: "
            );

            int itemId =
                    Integer.parseInt(
                            sc.nextLine()
                    );

            ValidationUtil.validateId(itemId);

            System.out.print(
                    "Item Name: "
            );

            String name =
                    sc.nextLine();

            ValidationUtil.validateItemName(name);

            System.out.print(
                    "Category: "
            );

            String category =
                    sc.nextLine();

            ValidationUtil.validateCategory(
                    category
            );

            System.out.print(
                    "Description: "
            );

            String description =
                    sc.nextLine();

            ValidationUtil.validateDescription(
                    description
            );

            System.out.print(
                    "Color: "
            );

            String color =
                    sc.nextLine();

            if (color.trim().isEmpty()) {

                throw new InvalidInputException(
                        "Color cannot be empty."
                );
            }

            System.out.print(
                    "Brand: "
            );

            String brand =
                    sc.nextLine();

            if (brand.trim().isEmpty()) {

                throw new InvalidInputException(
                        "Brand cannot be empty."
                );
            }

            System.out.print(
                    "Identification Details: "
            );

            String identification =
                    sc.nextLine();

            if (identification.trim().isEmpty()) {

                throw new InvalidInputException(
                        "Identification details cannot be empty."
                );
            }

            System.out.print(
                    "Lost Location: "
            );

            String location =
                    sc.nextLine();

            ValidationUtil.validateLocation(
                    location
            );

            System.out.print(
                    "Lost Date (YYYY-MM-DD): "
            );

            Date date =
                    Date.valueOf(
                            sc.nextLine()
                    );

            System.out.print(
                    "Lost Report ID: "
            );

            int lostId =
                    Integer.parseInt(
                            sc.nextLine()
                    );

            ValidationUtil.validateId(lostId);

            Item item =
                    new Item(
                            itemId,
                            name,
                            category,
                            description,
                            color,
                            brand,
                            identification,
                            "LOST"
                    );

            /*
             * Insert ITEM first.
             * LostReportDAO inserts LOST_REPORT
             * using transaction management.
             */

            if (itemDAO.addItem(item)) {

                if (
                        lostDAO.addLostReport(
                                lostId,
                                itemId,
                                MainUserSession.getUserId(),
                                location,
                                date
                        )
                ) {

                    System.out.println();
                    System.out.println(
                            "Lost Item Reported Successfully!"
                    );

                } else {

                    itemDAO.deleteItem(itemId);

                    System.out.println(
                            "Lost Report Failed."
                    );

                    System.out.println(
                            "Item insertion has been rolled back."
                    );
                }

            } else {

                System.out.println(
                        "Item Addition Failed!"
                );
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter valid numeric values."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Invalid date format. Use YYYY-MM-DD."
            );

        } catch (InvalidInputException e) {

            System.out.println(
                    "Validation Error: " +
                    e.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                    "Error: " +
                    e.getMessage()
            );
        }
    }

    // ==========================================
    // REPORT FOUND ITEM
    // ==========================================

    static void reportFoundItem() {

        try {

            System.out.println();
            System.out.println(
                    "========== REPORT FOUND ITEM =========="
            );

            System.out.print(
                    "Item ID: "
            );

            int itemId =
                    Integer.parseInt(
                            sc.nextLine()
                    );

            ValidationUtil.validateId(itemId);

            System.out.print(
                    "Item Name: "
            );

            String name =
                    sc.nextLine();

            ValidationUtil.validateItemName(name);

            System.out.print(
                    "Category: "
            );

            String category =
                    sc.nextLine();

            ValidationUtil.validateCategory(
                    category
            );

            System.out.print(
                    "Description: "
            );

            String description =
                    sc.nextLine();

            ValidationUtil.validateDescription(
                    description
            );

            System.out.print(
                    "Color: "
            );

            String color =
                    sc.nextLine();

            if (color.trim().isEmpty()) {

                throw new InvalidInputException(
                        "Color cannot be empty."
                );
            }

            System.out.print(
                    "Brand: "
            );

            String brand =
                    sc.nextLine();

            if (brand.trim().isEmpty()) {

                throw new InvalidInputException(
                        "Brand cannot be empty."
                );
            }

            System.out.print(
                    "Identification Details: "
            );

            String identification =
                    sc.nextLine();

            if (identification.trim().isEmpty()) {

                throw new InvalidInputException(
                        "Identification details cannot be empty."
                );
            }

            System.out.print(
                    "Found Location: "
            );

            String location =
                    sc.nextLine();

            ValidationUtil.validateLocation(
                    location
            );

            System.out.print(
                    "Found Date (YYYY-MM-DD): "
            );

            Date date =
                    Date.valueOf(
                            sc.nextLine()
                    );

            System.out.print(
                    "Found Report ID: "
            );

            int foundId =
                    Integer.parseInt(
                            sc.nextLine()
                    );

            ValidationUtil.validateId(foundId);

            Item item =
                    new Item(
                            itemId,
                            name,
                            category,
                            description,
                            color,
                            brand,
                            identification,
                            "FOUND"
                    );

            if (
                    foundDAO.addFoundItem(
                            item,
                            foundId,
                            MainUserSession.getUserId(),
                            location,
                            date
                    )
            ) {

                System.out.println();
                System.out.println(
                        "Found Item Reported Successfully!"
                );

            } else {

                System.out.println(
                        "Found Item Report Failed!"
                );
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter valid numeric values."
            );

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Invalid date format. Use YYYY-MM-DD."
            );

        } catch (InvalidInputException e) {

            System.out.println(
                    "Validation Error: " +
                    e.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                    "Error: " +
                    e.getMessage()
            );
        }
    }

    // ==========================================
    // SEARCH ITEMS
    // ==========================================

    static void searchItems() {

        try {

            System.out.print(
                    "Enter name/category/color/brand: "
            );

            String keyword =
                    sc.nextLine();

            if (keyword.trim().isEmpty()) {

                throw new InvalidInputException(
                        "Search keyword cannot be empty."
                );
            }

            itemDAO.searchItems(keyword);

        } catch (InvalidInputException e) {

            System.out.println(
                    "Validation Error: " +
                    e.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                    "Search Error: " +
                    e.getMessage()
            );
        }
    }

    // ==========================================
    // SEARCH BY STATUS
    // ==========================================

    static void searchByStatus(
            String status) {

        try {

            ValidationUtil.validateStatus(
                    status
            );

            System.out.print(
                    "Enter keyword: "
            );

            String keyword =
                    sc.nextLine();

            if (keyword.trim().isEmpty()) {

                throw new InvalidInputException(
                        "Search keyword cannot be empty."
                );
            }

            itemDAO.searchByStatus(
                    status,
                    keyword
            );

        } catch (InvalidInputException e) {

            System.out.println(
                    "Validation Error: " +
                    e.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                    "Search Error: " +
                    e.getMessage()
            );
        }
    }

    // ==========================================
    // FIND MATCHES
    // ==========================================

    static void findMatches() {

        try {

            System.out.print(
                    "Enter Lost Report ID: "
            );

            int lostId =
                    Integer.parseInt(
                            sc.nextLine()
                    );

            ValidationUtil.validateId(
                    lostId
            );

            matchDAO.findMatches(
                    lostId
            );

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a valid Lost Report ID."
            );

        } catch (InvalidInputException e) {

            System.out.println(
                    "Validation Error: " +
                e.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                    "Error: " +
                    e.getMessage()
            );
        }
    }

    // ==========================================
    // CREATE CLAIM
    // ==========================================

    static void createClaim() {

        try {

            System.out.println();
            System.out.println(
                    "========== CLAIM FOUND ITEM =========="
            );

            System.out.print(
                    "Claim ID: "
            );

            int claimId =
                    Integer.parseInt(
                            sc.nextLine()
                    );

            ValidationUtil.validateId(
                    claimId
            );

            System.out.print(
                    "Found ID: "
            );

            int foundId =
                    Integer.parseInt(
                            sc.nextLine()
                    );

            ValidationUtil.validateId(
                    foundId
            );

            System.out.print(
                    "Claim Description: "
            );

            String description =
                    sc.nextLine();

            ValidationUtil.validateDescription(
                    description
            );

            Claim claim =
                    new Claim(
                            claimId,
                            foundId,
                            MainUserSession.getUserId(),
                            description,
                            "PENDING"
                    );

            if (claimDAO.createClaim(claim)) {

                System.out.println();
                System.out.println(
                        "Claim Submitted Successfully!"
                );

                System.out.println(
                        "Status: PENDING"
                );

            } else {

                System.out.println(
                        "Claim Submission Failed!"
                );
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter valid numeric values."
            );

        } catch (InvalidInputException e) {

            System.out.println(
                    "Validation Error: " +
                    e.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                    "Error: " +
                    e.getMessage()
            );
        }
    }

    // ==========================================
    // APPROVE CLAIM
    // ==========================================

    static void approveClaim() {

        try {

            System.out.print(
                    "Enter Claim ID: "
            );

            int claimId =
                    Integer.parseInt(
                            sc.nextLine()
                    );

            ValidationUtil.validateId(
                    claimId
            );

            if (
                    claimDAO.approveClaim(
                            claimId
                    )
            ) {

                System.out.println();
                System.out.println(
                        "Claim Approved Successfully!"
                );

                System.out.println(
                        "Item Status: RETURNED"
                );

            } else {

                System.out.println(
                        "Claim Approval Failed!"
                );
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a valid Claim ID."
            );

        } catch (InvalidInputException e) {

            System.out.println(
                    "Validation Error: " +
                    e.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                    "Error: " +
                    e.getMessage()
            );
        }
    }

    // ==========================================
    // REJECT CLAIM
    // ==========================================

    static void rejectClaim() {

        try {

            System.out.print(
                    "Enter Claim ID: "
            );

            int claimId =
                    Integer.parseInt(
                            sc.nextLine()
                    );

            ValidationUtil.validateId(
                    claimId
            );

            if (
                    claimDAO.rejectClaim(
                            claimId
                    )
            ) {

                System.out.println();
                System.out.println(
                        "Claim Rejected Successfully!"
                );

            } else {

                System.out.println(
                        "Claim Rejection Failed!"
                );
            }

        } catch (NumberFormatException e) {

            System.out.println(
                    "Please enter a valid Claim ID."
            );

        } catch (InvalidInputException e) {

            System.out.println(
                    "Validation Error: " +
                    e.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                    "Error: " +
                    e.getMessage()
            );
        }
    }

    // ==========================================
    // CHANGE PASSWORD
    // ==========================================

    static void changePassword() {

        try {

            System.out.println();
            System.out.println(
                    "========== CHANGE PASSWORD =========="
            );

            System.out.print(
                    "Current Password: "
            );

            String oldPassword =
                    sc.nextLine();

            ValidationUtil.validatePassword(
                    oldPassword
            );

            System.out.print(
                    "New Password: "
            );

            String newPassword =
                    sc.nextLine();

            ValidationUtil.validatePassword(
                    newPassword
            );

            System.out.print(
                    "Confirm New Password: "
            );

            String confirmPassword =
                    sc.nextLine();

            ValidationUtil.validatePassword(
                    confirmPassword
            );

            if (
                    !newPassword.equals(
                            confirmPassword
                    )
            ) {

                throw new InvalidInputException(
                        "New passwords do not match."
                );
            }

            if (
                    oldPassword.equals(
                            newPassword
                    )
            ) {

                throw new InvalidInputException(
                        "New password must be different."
                );
            }

            if (
                    userDAO.changePassword(
                            MainUserSession.getUserId(),
                            oldPassword,
                            newPassword
                    )
            ) {

                System.out.println();
                System.out.println(
                        "Password Changed Successfully!"
                );

            } else {

                System.out.println();
                System.out.println(
                        "Current Password is Incorrect!"
                );
            }

        } catch (InvalidInputException e) {

            System.out.println(
                    "Validation Error: " +
                    e.getMessage()
            );

        } catch (Exception e) {

            System.out.println(
                    "Error: " +
                    e.getMessage()
            );
        }
    }
}