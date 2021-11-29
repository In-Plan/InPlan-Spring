package com.inplan.inplan;

import com.inplan.inplan.dao.User;
import com.inplan.inplan.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class UserTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    InPlanService inPlanService;

    public User getUser() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        User user = User.builder()
                .uid("user")
                .password(passwordEncoder.encode("pass"))
                .name("test")
                .email("test@gmail.com")
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
        return user;
    }

    @Test
    void createUser() {
        User user = getUser();
        userRepository.save(user);
    }

    @Test
    void selectUser() {
        User user = getUser();
        userRepository.save(user);

        Optional<User> selectedUser = userRepository.findById(1L);
        if (selectedUser.isPresent()) {
            System.out.println("user.get() = " + selectedUser.get());
        }
    }

    @Test
    void updateUser() {
        User user = getUser();
        userRepository.save(user);

        Optional<User> selectedUser = userRepository.findById(1L);
        if (selectedUser.isPresent()) {
            System.out.println("user.get() = " + selectedUser.get());
        }

        user.setName("test_updated");
        userRepository.save(user);

        Optional<User> updatedUser = userRepository.findById(1L);
        if (updatedUser.isPresent()) {
            System.out.println("updatedUser.get() = " + updatedUser.get());
        }
    }

    @Test
    void deleteUser() {
        User user = getUser();
        userRepository.save(user);

        userRepository.deleteById(user.getMsrl());

        Optional<User> deletedUser = userRepository.findById(1L);
        if (deletedUser.isPresent()) {
            System.out.println("deletedUser.get() = " + deletedUser.get());
        } else {
            System.out.println("deleted");
        }
    }

    @Test
    void putUserByInPlanService() {
        User user = getUser();
        inPlanService.putUser(user);
    }

    @Test
    void getUserByInPlanService() {
        List<User> userList = inPlanService.getUserById(0L);
        System.out.println("userList = " + userList);
    }

    @Test
    void updateUserByInPlanService() {
        putUserByInPlanService();
        inPlanService.updateUserById(1L, getUser());
        getUserByInPlanService();
    }

    @Test
    void deleteUserByInPlanService() {
        putUserByInPlanService();
        inPlanService.deleteUserById(1L);
        getUserByInPlanService();
    }
}
