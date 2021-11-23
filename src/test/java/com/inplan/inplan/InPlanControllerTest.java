package com.inplan.inplan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inplan.inplan.dao.User;
import com.inplan.inplan.dto.ResponseGetUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class InPlanControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void getUser() throws Exception {
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("users").isArray())
                .andDo(print());
    }

    @Test
    public void putUser() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("name", "test");
        map.put("email", "test@test.com");

        mockMvc.perform(put("/user")
                        .content(objectMapper.writeValueAsBytes(map))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        getUser();
    }

    @Test
    public void updateUser() throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("name", "test");
        map.put("email", "test@test.com");

        mockMvc.perform(put("/user")
                        .content(objectMapper.writeValueAsBytes(map))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        getUser();

        map.put("name", "test2");
        map.put("email", "test@test.com2");

        mockMvc.perform(patch("/user")
                        .queryParam("id", "1")
                        .content(objectMapper.writeValueAsBytes(map))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());

        getUser();
    }
}
