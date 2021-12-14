package com.inplan.inplan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inplan.inplan.dao.User;
import com.inplan.inplan.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserTest {

    @Autowired
    UserRepository userRepository;

    @Autowired
    InPlanService inPlanService;

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

    public User getRequestUser() {
        PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return User.builder()
                .uid("user")
                .password(passwordEncoder.encode("pass"))
                .name("test")
                .email("test@gmail.com")
                .build();
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

    @Test
    public void getUserByAPITest() throws Exception {
        mockMvc.perform(get("/v1/user"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void createUserByAPITest() throws Exception {
        String content = objectMapper.writeValueAsString(getRequestUser());
        System.out.println("content = " + content);
        mockMvc.perform(put("/v1/user")
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void updateUserByAPITest() throws Exception {
        User user = getUser();
        userRepository.save(user);

        assertThat(user.getMsrl()).isEqualTo(userRepository.findByUid(user.getUid()).get().getMsrl());

        String updateName = "test2";
        String updateEmail = "test2@test.com";


        User updateUser = User.builder()
                .uid(user.getUid())
                .name(updateName)
                .email(updateEmail)
                .build();

        String content = objectMapper.writeValueAsString(updateUser);

        System.out.println("content = " + content);

        mockMvc.perform(patch("/v1/user")
                        .param("uid", user.getUid())
                        .content(content)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        getUserByAPITest();
    }

    @Test
    public void deleteUserByAPITest() throws Exception {
        User user = getUser();
        userRepository.save(user);

        assertThat(user.getMsrl()).isEqualTo(userRepository.findByUid(user.getUid()).get().getMsrl());

        mockMvc.perform(delete("/v1/user")
                        .param("uid", user.getUid()))
                .andExpect(status().isOk())
                .andDo(print());

        getUserByAPITest();
    }

    @Test
    public void login() throws Exception {
        User user = getUser();
        userRepository.save(user);

        mockMvc.perform(get("http://user:pass@localhost:9000/oauth/authorize?response_type=code&client_id=foo&redirect_uri=http://localhost:8080/login/oauth2/code/local&scope=read"))
                .andDo(print());
    }
}
