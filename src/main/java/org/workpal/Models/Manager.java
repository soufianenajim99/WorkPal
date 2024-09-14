package org.workpal.Models;

public class Manager extends User{
    public Manager(int id, String username, String password, String email, String address) {
        super(id, username, password, email, address);
    }

    public Manager(String username, String password, String email, String address) {
        super(username, password, email, address);
    }

    public Manager() {
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
