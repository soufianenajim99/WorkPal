package org.workpal.Models;

public class Member extends User{
    public Member(int id, String username, String password, String email, String address) {
        super(id, username, password, email, address);
    }

    public Member(String username, String password, String email, String address) {
        super(username, password, email, address);
    }

    public Member() {
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
