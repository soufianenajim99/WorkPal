package org.workpal.Models;

public class User {
private int Id;
private String Username;
private String Password;
private String Email;
private String Address;

    public User(int id, String username, String password, String email, String address) {
        Id = id;
        Username = username;
        Password = password;
        Email = email;
        Address = address;
    }

    static {
        System.out.println("User initialized");
    }
    public User(){
        System.out.println("constr");

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", Username='" + Username + '\'' +
                ", Password='" + Password + '\'' +
                ", Email='" + Email + '\'' +
                ", Address='" + Address + '\'' +
                '}';
    }
}
