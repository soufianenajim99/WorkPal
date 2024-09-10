package org.workpal.Repositories.RepositoryInterfaces;

import org.workpal.Models.User;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryInterface {
    User save(User user);
    Optional<User> findById(Long id);
    List<User> findAll();
    void update(User user);
    void delete(Long id);
}
