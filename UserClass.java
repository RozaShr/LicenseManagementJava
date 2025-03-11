package LicenseManagement;

public class UserClass {
    private String name, address, email, phone,citizenship, role, password;
    private int age;

    public UserClass(String name, String address, String email, String phone, String citizenship, int age, String password, String role) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.phone = phone;
        this.citizenship = citizenship;
        this.age = age;
        this.password = password;
        this.role = role;
    }

    public boolean validateLogin(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }
    public String getName(){
        return name;
    }
    public int getAge() {
        return age;
    }
    public String getAddress(){
        return address;
    }
    public String getRole() {
        return role;
    }

    public String getEmail() {
        return email;
    }
    public boolean isGmail() {
        return email.toLowerCase().endsWith("@gmail.com");
    }

    public static boolean isValidEmail(String email) {
        return email != null && email.toLowerCase().endsWith("@gmail.com");
    }
}