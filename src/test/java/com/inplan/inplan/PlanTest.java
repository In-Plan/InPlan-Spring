package com.inplan.inplan;

import com.inplan.inplan.dao.Plan;
import com.inplan.inplan.dao.PlanCategory;
import com.inplan.inplan.dao.User;
import com.inplan.inplan.repository.PlanRepository;
import com.inplan.inplan.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.Optional;

@SpringBootTest
public class PlanTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PlanRepository planRepository;

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
    void createPlan() {
        User user = getUser();
        userRepository.save(user);
        Plan plan = new Plan(0L, user, OffsetDateTime.now(), OffsetDateTime.now(), new PlanCategory(0L, "운동"), "test");
        planRepository.save(plan);
    }

    @Test
    void selectPlan() {
        User user = getUser();
        userRepository.save(user);

        Plan plan = new Plan(0L, user, OffsetDateTime.now(), OffsetDateTime.now(), new PlanCategory(0L, "운동"), "test");
        planRepository.save(plan);

        Optional<Plan> selected = planRepository.findById(1L);
        if (selected.isPresent()) {
            System.out.println("selected = " + selected);
        }
    }

    @Test
    void updatePlan() {
        User user = getUser();
        userRepository.save(user);

        Plan plan = new Plan(0L, user, OffsetDateTime.now(), OffsetDateTime.now(), new PlanCategory(0L, "운동"), "test");
        planRepository.save(plan);

        plan.setDescription("update test");

        planRepository.save(plan);

        Optional<Plan> selected = planRepository.findById(1L);
        if (selected.isPresent()) {
            System.out.println("selected = " + selected);
        }
    }

    @Test
    void deletePlan() {
        User user = getUser();
        userRepository.save(user);

        Plan plan = new Plan(0L, user, OffsetDateTime.now(), OffsetDateTime.now(), new PlanCategory(0L, "운동"), "test");
        planRepository.save(plan);

        Optional<Plan> selected = planRepository.findById(1L);
        if (selected.isPresent()) {
            System.out.println("selected = " + selected);
        }

        planRepository.delete(plan);

        selected = planRepository.findById(1L);
        if (selected.isPresent()) {
            System.out.println("selected = " + selected);
        } else {
            System.out.println("deleted");
        }
    }
}
