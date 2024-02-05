package com.indatacore.backend.user.services;

import com.indatacore.backend.user.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface UserService {
    User saveUser(User user);
    Page<User> getAllUsers(Pageable pageable);
    User updateUser(User user);
    User getUserById(Long id);
    Boolean deleteUser(User user);
}
