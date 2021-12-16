package com.inplan.inplan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inplan.inplan.dao.Plan;
import com.inplan.inplan.dao.PlanCategory;
import com.inplan.inplan.dao.User;
import com.inplan.inplan.repository.PlanRepository;
import com.inplan.inplan.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.OffsetDateTime;
import java.util.Collections;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PlanTest {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PlanRepository planRepository;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

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

    public Plan getPlan() {
        User user = getUser();
        userRepository.save(user);
        return new Plan(null, user, OffsetDateTime.now(), OffsetDateTime.now(), new PlanCategory(null, "운동"), "test");
    }

    @Test
    void createPlan() {
        planRepository.save(getPlan());
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

    @Test
    void selectPlanByAPI() throws Exception {
        mockMvc.perform(get("/v1/plan"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void createPlanByAPI() throws Exception {
        Plan plan = getPlan();

        String content = objectMapper.writeValueAsString(plan);
        System.out.println("content = " + content);

        mockMvc.perform(put("/v1/plan")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        selectPlanByAPI();
    }

    @Test
    void updatePlanByAPI() throws Exception {
        Plan plan = getPlan();
        Plan savedPlan = planRepository.save(plan);

        savedPlan.setDescription("update test");
        String content = objectMapper.writeValueAsString(savedPlan);

        mockMvc.perform(patch("/v1/plan")
                .param("id", savedPlan.getId().toString())
                .content(content)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        selectPlanByAPI();
    }
}
