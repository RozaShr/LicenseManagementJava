package LicenseManagement;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static ArrayList<UserClass> users = new ArrayList<>();
    private static AdminClass admin = new AdminClass("Admin", "admin@system.com", "admin@123");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== License Management System ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            if (choice == 1) {
                register(sc);
            } else if (choice == 2) {
                login(sc);
            } else {
                System.out.println("Exiting...");
                break;
            }
        }
        sc.close();
    }

    private static void register(Scanner sc) {
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Address: ");
        String address = sc.nextLine();

        System.out.print("Enter Email: ");
        String email = sc.nextLine();
        if (!UserClass.isValidEmail(email)) {
            System.out.println("Invalid email address. Please use a gmail.com email.");
            return;
        }

        System.out.print("Enter Phone Number (10 digits): ");
        String phone;
        while (true) {
            phone = sc.nextLine();
            if (phone.matches("\\d{10}")) {
                break;
            } else {
                System.out.println("Invalid phone number! It must be exactly 10 digits.");
            }
        }

        System.out.print("Enter Citizenship Number (10 digits): ");
        String citizenship;
        while (true) {
            citizenship = sc.nextLine();
            if (citizenship.matches("\\d{10}")) {
                break;
            } else {
                System.out.println("Invalid citizenship number! It must be exactly 10 digits.");
            }
        }

        System.out.print("Enter Age: ");
        int age = sc.nextInt();
        sc.nextLine(); // Consume newline

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        System.out.print("Register as (Admin/User): ");
        String role = sc.nextLine().trim();
        if (!role.equalsIgnoreCase("Admin") && !role.equalsIgnoreCase("User")) {
            System.out.println("Invalid role! Please enter 'Admin' or 'User'.");
            return;
        }

        if (role.equalsIgnoreCase("Admin")) {
            System.out.println("Admin registration is not allowed! Only one Admin exists.");
            return;
        }

        // Add user to the list
        users.add(new UserClass(name, address, email, phone, citizenship, age, password, role));
        System.out.println("Registration successful!");
    }

    private static void login(Scanner sc) {
        System.out.print("Enter Email: ");
        String email = sc.nextLine();

        System.out.print("Enter Password: ");
        String password = sc.nextLine();

        // Check if admin is logging in
        if (admin.validateLogin(email, password)) {
            System.out.println("Login successful! Welcome Admin.");
            new AdminDashboard(admin).showDashboard();
            return;
        }

        // Check if a user is logging in
        for (UserClass user : users) {
            if (user.validateLogin(email, password)) {
                System.out.println("Login successful! Welcome " + user.getName());
                new UserDashboard(user, admin).showDashboard();
                return;
            }
        }

        System.out.println("Invalid email or password.");
    }
}