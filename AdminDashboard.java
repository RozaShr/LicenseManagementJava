package LicenseManagement;

import java.util.Scanner;

public class AdminDashboard {
    private AdminClass admin;
    private ApprovalHandler approvalHandler;

    public AdminDashboard(AdminClass admin) {
        this.admin = admin;
        this.approvalHandler=new ApprovalHandler(admin);
    }

    public void showDashboard() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Admin Dashboard ===");
            System.out.println("1. View Applications");
            System.out.println("2. Approve Application");
            System.out.println("3. View Approved Applications");
            System.out.println("4. Generate Application Report");
            System.out.println("5. Logout");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    admin.showApplications();
                    break;
                case 2:
                    approvalHandler.processApplications(); // Let admin approve/reject applications
                    break;
                case 3:
                    admin.showApprovedApplications();
                    break;
                case 4:
                    viewApplicationReport(sc);
                case 5:
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
    private boolean confirmLogout(Scanner sc) {
        System.out.print("Are you sure you want to logout? (Yes/No): ");
        String response = sc.nextLine().trim().toLowerCase();
        return response.equals("yes");
    }
    private void viewApplicationReport(Scanner sc) {
        System.out.print("Enter Application ID to view the report: ");
        String applicationId = sc.nextLine();
        System.out.println("\n=== Application Report ===");
        System.out.println(admin.getUserReport(applicationId));
    }
}