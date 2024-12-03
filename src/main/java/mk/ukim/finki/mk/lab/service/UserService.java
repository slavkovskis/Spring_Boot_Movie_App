package mk.ukim.finki.mk.lab.service;

import mk.ukim.finki.mk.lab.model.Role;
import mk.ukim.finki.mk.lab.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User create(String username, String password, Role role);

    User findByUsername(String username);
}
