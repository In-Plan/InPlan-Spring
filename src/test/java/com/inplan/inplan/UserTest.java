package com.inplan.inplan;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class UserTest {

    @Autowired
    UserRepository userRepository;

    @Test
    void createUser() {
        User user = new User(0,"test@test.com", "test");
        userRepository.save(user);
    }

    @Test
    void selectUser() {
        User user = new User(0,"test@test.com", "test");
        userRepository.save(user);

        Optional<User> selectedUser = userRepository.findById(1);
        if (selectedUser.isPresent()) {
            System.out.println("user.get() = " + selectedUser.get());
        }
    }

    @Test
    void updateUser() {
        User user = new User(0,"test@test.com", "test");
        userRepository.save(user);

        Optional<User> selectedUser = userRepository.findById(1);
        if (selectedUser.isPresent()) {
            System.out.println("user.get() = " + selectedUser.get());
        }

        user.setName("test_updated");
        userRepository.save(user);

        Optional<User> updatedUser = userRepository.findById(1);
        if (updatedUser.isPresent()) {
            System.out.println("updatedUser.get() = " + updatedUser.get());
        }
    }

    @Test
    void deleteUser() {
        User user = new User(0,"test@test.com", "test");
        userRepository.save(user);

        userRepository.deleteById(1);

        Optional<User> deletedUser = userRepository.findById(1);
        if (deletedUser.isPresent()) {
            System.out.println("deletedUser.get() = " + deletedUser.get());
        } else {
            System.out.println("deleted");
        }
    }
}
