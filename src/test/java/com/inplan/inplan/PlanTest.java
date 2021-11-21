package com.inplan.inplan;

import com.inplan.inplan.dao.Plan;
import com.inplan.inplan.dao.PlanCategory;
import com.inplan.inplan.dao.User;
import com.inplan.inplan.repository.PlanRepository;
import com.inplan.inplan.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.OffsetDateTime;
import java.util.Optional;

@SpringBootTest
public class PlanTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PlanRepository planRepository;

    @Test
    void createPlan() {
        User user = new User(0, "test", "test@test.com");
        userRepository.save(user);

        Plan plan = new Plan(0, user, OffsetDateTime.now(), OffsetDateTime.now(), new PlanCategory(0, "운동"), "test");
        planRepository.save(plan);
    }

    @Test
    void selectPlan() {
        User user = new User(0, "test", "test@test.com");
        userRepository.save(user);

        Plan plan = new Plan(0, user, OffsetDateTime.now(), OffsetDateTime.now(), new PlanCategory(0, "운동"), "test");
        planRepository.save(plan);

        Optional<Plan> selected = planRepository.findById(1);
        if (selected.isPresent()) {
            System.out.println("selected = " + selected);
        }
    }

    @Test
    void updatePlan() {
        User user = new User(0, "test", "test@test.com");
        userRepository.save(user);

        Plan plan = new Plan(0, user, OffsetDateTime.now(), OffsetDateTime.now(), new PlanCategory(0, "운동"), "test");
        planRepository.save(plan);

        plan.setDescription("update test");

        planRepository.save(plan);

        Optional<Plan> selected = planRepository.findById(1);
        if (selected.isPresent()) {
            System.out.println("selected = " + selected);
        }
    }

    @Test
    void deletePlan() {
        User user = new User(0, "test", "test@test.com");
        userRepository.save(user);

        Plan plan = new Plan(0, user, OffsetDateTime.now(), OffsetDateTime.now(), new PlanCategory(0, "운동"), "test");
        planRepository.save(plan);

        Optional<Plan> selected = planRepository.findById(1);
        if (selected.isPresent()) {
            System.out.println("selected = " + selected);
        }

        planRepository.delete(plan);

        selected = planRepository.findById(1);
        if (selected.isPresent()) {
            System.out.println("selected = " + selected);
        } else {
            System.out.println("deleted");
        }
    }
}
