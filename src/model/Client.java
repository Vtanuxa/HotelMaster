package model;

public class Client {

    private int id;
    private String fullName;
    private String phone;
    private String email;
    private String passport;

    public Client(String fullName, String phone, String email, String passport) {

        this.fullName = fullName;
        this.phone = phone;
        this.email = email;
        this.passport = passport;
    }

    public Client() {

    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getPassport() {
        return passport;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }
}
