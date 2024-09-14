package org.workpal.Services.ServicesInterfaces;

import org.workpal.Models.User;

import java.util.Optional;

public interface AuthenticationServiceInterface<T> {
    Optional<T> login(String email, String password);
    T register(User user);
}
