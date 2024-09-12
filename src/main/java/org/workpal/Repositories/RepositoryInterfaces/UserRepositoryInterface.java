package org.workpal.Repositories.RepositoryInterfaces;

import org.workpal.Models.User;
import org.workpal.Repositories.UserRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryInterface<T>{
    T save(User user);
    Optional<T> findById(int id);
    List<T> findAll();
    void update(User user);
    void delete(int id);
}
