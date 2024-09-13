package org.workpal.Models;

public class Manger extends User{
    public Manger(int id, String username, String password, String email, String address) {
        super(id, username, password, email, address);
    }

    public Manger(String username, String password, String email, String address) {
        super(username, password, email, address);
    }

    public Manger() {
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
