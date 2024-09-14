package org.workpal.Services;

import org.workpal.Models.User;
import org.workpal.Repositories.RepositoryInterfaces.UserRepositoryInterface;
import org.workpal.Services.ServicesInterfaces.AuthenticationServiceInterface;

import java.util.Optional;

public class AuthenticationService<T> implements AuthenticationServiceInterface<T> {

    private final UserRepositoryInterface<T> userRepository;

    public AuthenticationService(UserRepositoryInterface<T> userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<T> login(String email, String password) {
        return userRepository.login(email, password);
    }

    @Override
    public T register(User user) {
        return userRepository.save(user);
    }
}
