package LicenseManagement;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

public class AdminClass {
    private String name;
    private String email;
    private String password;
    private ArrayList<UserApplication> applications;
    private ArrayList<UserApplication> approvedApplications;
    private HashMap<String, String> applicationStatus; // Stores Application ID -> Status

    public AdminClass(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.applications = new ArrayList<>();
        this.approvedApplications = new ArrayList<>();
        this.applicationStatus = new HashMap<>();
    }

    public boolean validateLogin(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }

    public void addApplication(UserApplication application) {
        applications.add(application);
        applicationStatus.put(application.getApplicationID(), "Pending"); // Store as Pending
    }

    public void showApplications() {
        System.out.println("\n=== Pending Applications ===");
        if (applications.isEmpty()) {
            System.out.println("No pending applications.");
            return;
        }
        for (int i = 0; i < applications.size(); i++) {
            System.out.println((i + 1) + ". " + applications.get(i));  // Displays Application Info
        }
    }

    public void approveApplication(int index) {
        if (index >= 0 && index < applications.size()) {
            UserApplication application = applications.remove(index);
            approvedApplications.add(application);
            applicationStatus.put(application.getApplicationID(), "Approved");

            // Calculate expiry date (5 years from today)
            LocalDate expiryDate = LocalDate.now().plusYears(5);
            application.setExpiryDate(expiryDate);

            // Format expiry date for display
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedExpiryDate = expiryDate.format(formatter);

            System.out.println("Application approved: " + application.getApplicantName() +
                    " - " + application.getLicenseType() +
                    " (Application ID: " + application.getApplicationID() + ")");
            System.out.println("License Expiry Date: " + formattedExpiryDate);
        } else {
            System.out.println("Invalid selection. Please try again.");
        }
    }

    public void rejectApplication(String applicationId) {
        applicationStatus.put(applicationId, "Rejected");
    }

    public void showApprovedApplications() {
        System.out.println("\n=== Approved Applications ===");
        if (approvedApplications.isEmpty()) {
            System.out.println("No approved applications yet.");
            return;
        }
        for (UserApplication app : approvedApplications) {
            System.out.println(app);  // Displays Application Info
        }
    }

    public String getApplicationStatus(String applicationId) {
        for (UserApplication app : approvedApplications) {
            if (app.getApplicationID().equals(applicationId)) {
                return "Approved \n" +
                        "Applicant Name: " + app.getApplicantName() + "\n" +
                        "License Type: " + app.getLicenseType() + "\n" +
                        "Expiry Date: " + app.getExpiryDate();
            }
        }

        if (applicationStatus.containsKey(applicationId)) {
            return "Application Status: " + applicationStatus.get(applicationId);  // Return "Pending" or "Rejected"
        }

        return "Not Found ";
    }

    public ArrayList<UserApplication> getPendingApplications() {
        return applications;
    }

    public String getUserReport(String applicationID) {
        for (UserApplication app : approvedApplications) {
            if (app.getApplicationID().equals(applicationID)) {
                return app.generateReport();
            }
        }
        return "No report found for Application ID: " + applicationID;
    }

}