package org.workpal;

import org.workpal.Models.Admin;
import org.workpal.Models.Member;
import org.workpal.Models.User;
import org.workpal.Repositories.AdminRepository;
import org.workpal.Repositories.MemberRepository;
import org.workpal.Repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        AdminRepository<Admin> ur = new AdminRepository<>(Admin.class);
        MemberRepository<Member> mr = new MemberRepository<Member>(Member.class);

        Member member1 = new Member("soufiane","soufiane","ssss","soufiane");
        Admin user1 = new Admin(13,"soufiane","soufiane","soufiane","soufiane");

//      ur.update(user1);
      mr.save(member1);
    }
}