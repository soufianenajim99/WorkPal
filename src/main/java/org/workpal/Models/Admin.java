package org.workpal.Models;

public class Admin extends User{
    public Admin(String username, String password, String email, String address) {
        super(username, password, email, address);
    }

    public Admin() {
    }

    public Admin(int id, String username, String password, String email, String address) {
        super(id, username, password, email, address);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
