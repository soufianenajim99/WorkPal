package org.workpal;

import org.workpal.Models.User;
import org.workpal.Repositories.UserRepository;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
   User user = new User("laylow","Password","lay@gmsaiil.com","address");
        UserRepository ur = new UserRepository();
        ur.save(user);

    }
}