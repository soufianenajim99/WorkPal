package org.workpal;

import org.workpal.Models.*;
import org.workpal.Repositories.*;
import org.workpal.Services.AuthenticationService;
import org.workpal.Services.CategoryService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        EspaceRepository espaceRepo = new EspaceRepository();
        ManagerRepository<Manager> managerRepo = new ManagerRepository<Manager>(Manager.class);
        CategoryRepository categoryRepo = new CategoryRepository();
        CategoryService categoryService = new CategoryService(categoryRepo);

        MemberRepository<Member> memberrepo = new MemberRepository<Member>(Member.class);
        Optional<Member> optionalMember = memberrepo.findById(9);
        Member member = optionalMember.get();
        System.out.println(member);

    }
}