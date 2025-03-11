package LicenseManagement;

import java.util.Scanner;

public class UserDashboard {
    private UserClass user;
    private AdminClass admin;

    public UserDashboard(UserClass user, AdminClass admin) {
        this.user = user;
        this.admin = admin;
    }

    public void showDashboard() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== User Dashboard ===");
            System.out.println("1. Apply for License");
            System.out.println("2. View Application Status");
            System.out.println("3. View Application Report");
            System.out.println("4. Logout");
            System.out.println("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.print("Enter vehicle type (Bike/Scooty/Car): ");
                    String vehicleType = sc.nextLine();
                    applyForLicense(vehicleType);
                    break;
                case 2:
                    viewApplicationStatus();
                    break;
                case 3:
                    viewApplicationReport(sc);
                    break;
                case 4:
                    if (confirmLogout(sc)) {
                        System.out.println("Logging out...");
                        return;
                    }
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    private void applyForLicense(String vehicleType) {
        if ((vehicleType.equalsIgnoreCase("Bike") || vehicleType.equalsIgnoreCase("Scooty")) && user.getAge() >= 18
                || (vehicleType.equalsIgnoreCase("Car") && user.getAge() >= 21)) {

            UserApplication application = new UserApplication(user.getName(), vehicleType); //Create UserApplication object
            admin.addApplication(application); // Pass UserApplication instead of String

            System.out.println("Application for " + vehicleType + " has been submitted successfully.");
            System.out.println("Your ApplicationID: " + application.getApplicationID());
            System.out.println("Please save this ID to check your application status.");
        } else {
            System.out.println("Application for " + vehicleType + " has been rejected due to age restrictions.");
        }
    }
    private void viewApplicationStatus() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your Application ID: ");
        String applicationId = sc.nextLine();

        String status = admin.getApplicationStatus(applicationId);
        System.out.println("\n=== Application Status ===");
        System.out.println(status);
    }
    private void viewApplicationReport(Scanner sc) {
        System.out.print("Enter your Application ID to view the report: ");
        String applicationId = sc.nextLine();

        System.out.println("\n=== Application Report ===");
        System.out.println(admin.getUserReport(applicationId));
    }
    private void viewApplicationStatus(Scanner sc) {
        System.out.print("Enter your Application ID to view the status: ");
        String applicationId = sc.nextLine();

        String status = admin.getApplicationStatus(applicationId);
        System.out.println("\n=== Application Status ===");
        System.out.println(status);
    }

    private boolean confirmLogout(Scanner sc) {
        System.out.print("Are you sure you want to logout? (Yes/No): ");
        String response = sc.nextLine().trim().toLowerCase();
        return response.equals("yes");
    }

}