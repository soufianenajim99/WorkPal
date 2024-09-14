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

        // Create a new Category and Manager for testing
        Category category = new Category( 4,"Fitness");
        Manager manager = new Manager( 20,"johndoe", "password123", "john.doe@example.com", "123 Elm Street");

        // Create a new Espace
        Espace newEspace = new Espace("Central Gym", "Downtown", category, manager);
//        managerRepo.save(manager);
//        categoryService.createCategory(category);
        // Save the new Espace
      espaceRepo.save(newEspace);
//        System.out.println("Saved Espace: " + savedEspace);
//
//        // Retrieve the Espace by ID
//        Espace retrievedEspace = espaceRepo.findById(savedEspace.getId()).orElse(null);
//        System.out.println("Retrieved Espace: " + retrievedEspace);
//
//        // Update the Espace
//        if (retrievedEspace != null) {
//            retrievedEspace.setName("Updated Gym Name");
//            espaceRepo.update(retrievedEspace);
//            System.out.println("Updated Espace: " + espaceRepo.findById(retrievedEspace.getId()).orElse(null));
//        }
//
//        // List all Espaces
//        System.out.println("All Espaces: " + espaceRepo.findAll());
//
//        // Delete the Espace
//        if (retrievedEspace != null) {
//            espaceRepo.delete(retrievedEspace.getId());
//            System.out.println("Deleted Espace. Remaining Espaces: " + espaceRepo.findAll());
//        }
    }
}