package com.inplan.inplan;

import com.inplan.inplan.dao.User;
import com.inplan.inplan.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class UserTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    InPlanService inPlanService;

    public User getUser() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        User user = User.builder()
                .uid("user" + LocalDateTime.now())
                .password(passwordEncoder.encode("pass"))
                .name("test")
                .email("test@gmail.com")
                .roles(Collections.singletonList("ROLE_USER"))
                .build();
        return user;
    }

    @Test
    public void createUserTest() {
        User user = getUser();
        userRepository.save(user);

        assertThat(user.getMsrl()).isEqualTo(userRepository.findByUid(user.getUid()).get().getMsrl());
    }

    @Test
    public void updateUserTest() {
        User user = getUser();
        userRepository.save(user);

        assertThat(user.getMsrl()).isEqualTo(userRepository.findByUid(user.getUid()).get().getMsrl());

        String updateName = "test2";
        String updateEmail = "test2@test.com";

        user.setName(updateName);
        user.setEmail(updateEmail);

        userRepository.save(user);

        assertThat(userRepository.findByUid(user.getUid()).get().getName()).isEqualTo(updateName);
        assertThat(userRepository.findByUid(user.getUid()).get().getEmail()).isEqualTo(updateEmail);
    }

    @Test
    public void deleteUserTest() {
        User user = getUser();
        userRepository.save(user);

        assertThat(user.getMsrl()).isEqualTo(userRepository.findByUid(user.getUid()).get().getMsrl());

        int count = userRepository.deleteByUid(user.getUid());

        assertThat(count).isEqualTo(1);
    }
}
