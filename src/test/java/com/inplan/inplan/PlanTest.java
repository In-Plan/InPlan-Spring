package com.inplan.inplan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inplan.inplan.dao.Plan;
import com.inplan.inplan.dao.PlanCategory;
import com.inplan.inplan.dao.User;
import com.inplan.inplan.repository.PlanRepository;
import com.inplan.inplan.repository.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import java.time.OffsetDateTime;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
                .uid("user" + System.currentTimeMillis())
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
    @DisplayName("PlanRepository를 통해 Plan 생성")
    void createPlanTest() {
        Plan plan = getPlan();
        plan = planRepository.save(plan);

        assertThat(plan.getId()).isEqualTo(planRepository.findById(plan.getId()).get().getId());
    }

    @Test
    @DisplayName("PlanRepository를 통해 Plan 업데이트")
    void updatePlan() {
        Plan plan = getPlan();
        plan = planRepository.save(plan);

        assertThat(plan.getId()).isEqualTo(planRepository.findById(plan.getId()).get().getId());

        String updateDescription = "update test";

        plan.setDescription(updateDescription);

        planRepository.save(plan);

        assertThat(planRepository.findById(plan.getId()).get().getDescription()).isEqualTo(updateDescription);
    }

    @Test
    @DisplayName("PlanRepository를 통해 Plan 삭제")
    void deletePlan() {
        Plan plan = getPlan();
        plan = planRepository.save(plan);

        assertThat(plan.getId()).isEqualTo(planRepository.findById(plan.getId()).get().getId());

        planRepository.deleteById(plan.getId());

        assertThat(planRepository.findById(plan.getId()).isPresent()).isFalse();
    }

    @Test
    @DisplayName("[GET /v1/plan] API를 통해 Plan 조회")
    void selectPlanByAPI() throws Exception {
        mockMvc.perform(get("/v1/plan"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.plans").isArray())
                .andDo(print());
    }

    @Test
    @DisplayName("[PUT /v1/plan] API를 통해 Plan 생성")
    void createPlanByAPI() throws Exception {
        Plan plan = getPlan();

        String content = objectMapper.writeValueAsString(plan);
        System.out.println("content = " + content);

        mockMvc.perform(put("/v1/plan")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg", "plan created").exists())
                .andDo(print());
    }

    @Test
    @DisplayName("[PATCH /v1/plan] API를 통해 Plan 수정")
    void updatePlanByAPI() throws Exception {
        Plan plan = getPlan();
        plan = planRepository.save(plan);

        assertThat(plan.getId()).isEqualTo(planRepository.findById(plan.getId()).get().getId());

        String updateDescription = "update test";

        plan.setDescription(updateDescription);
        String content = objectMapper.writeValueAsString(plan);

        mockMvc.perform(patch("/v1/plan")
                        .param("id", plan.getId().toString())
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg", "plan updated").exists())
                .andDo(print());

        assertThat(planRepository.findById(plan.getId()).get().getDescription()).isEqualTo(updateDescription);
    }

    @Test
    @DisplayName("[DELETE /v1/plan] API를 통해 Plan 삭제")
    void deletePlanByAPI() throws Exception {
        Plan plan = getPlan();
        plan = planRepository.save(plan);

        assertThat(plan.getId()).isEqualTo(planRepository.findById(plan.getId()).get().getId());

        mockMvc.perform(delete("/v1/plan")
                        .param("id", plan.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.msg", "plan deleted").exists())
                .andDo(print());
    }
}
