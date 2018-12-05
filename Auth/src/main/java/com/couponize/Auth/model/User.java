package com.couponize.Auth.model;

public class User extends BaseEntity {
    private int companySize;

    private String firstName;

    private String lastName;
    private String email;
    private String password;
    private String phone;
    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public int getCompanySize() {
        return companySize;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCompanySize(int companySize) {
        this.companySize = companySize;
    }


    @Override
    public String toString() {
        return "User{" +
                "companySize=" + companySize +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
