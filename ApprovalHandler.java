package LicenseManagement;

import java.util.Scanner;
import java.util.ArrayList;

public class ApprovalHandler {
    private AdminClass admin;

    public ApprovalHandler(AdminClass admin) {
        this.admin = admin;
    }

    public void processApplications() {
        Scanner sc = new Scanner(System.in);
        ArrayList<UserApplication> pendingApplications = admin.getPendingApplications();

        if (pendingApplications.isEmpty()) {
            System.out.println("No pending applications to approve.");
            return;
        }

        System.out.println("\n=== Approve Applications ===");
        for (int i = 0; i < pendingApplications.size(); i++) {
            System.out.println((i + 1) + ". " + pendingApplications.get(i));
        }

        System.out.print("Enter the number of the application to approve (or 0 to cancel): ");
        int choice = sc.nextInt();
        sc.nextLine(); // Consume newline

        if (choice > 0 && choice <= pendingApplications.size()) {
            //String application = pendingApplications.get(choice - 1);
            admin.approveApplication(choice - 1);
        } else if (choice == 0) {
            System.out.println("Approval process canceled.");
        } else {
            System.out.println("Invalid choice! Please select a valid application.");
        }
    }
}