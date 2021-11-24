package com.inplan.inplan;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.inplan.inplan.dto.ResponsePutUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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

        MvcResult mvcResult = mockMvc.perform(put("/user")
                        .content(objectMapper.writeValueAsBytes(map))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        ResponsePutUser responsePutUser = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ResponsePutUser.class);
        System.out.println("responsePutUser = " + responsePutUser);

        getUser();
    }

    @Test
    public void updateUser() throws Exception {

        Map<String, String> map = new HashMap<>();
        map.put("name", "test");
        map.put("email", "test@test.com");

        MvcResult mvcResult = mockMvc.perform(put("/user")
                        .content(objectMapper.writeValueAsBytes(map))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        ResponsePutUser responsePutUser = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ResponsePutUser.class);
        System.out.println("responsePutUser = " + responsePutUser);

        getUser();

        map.put("name", "test2");
        map.put("email", "test@test.com2");

        mockMvc.perform(patch("/user")
                        .queryParam("id", responsePutUser.getId().toString())
                        .content(objectMapper.writeValueAsBytes(map))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(responsePutUser.getId()))
                .andExpect(jsonPath("$.msg").value("user updated"))
                .andDo(print());

        getUser();
    }

    @Test
    public void deleteUser() throws Exception {
        Map<String, String> map = new HashMap<>();
        map.put("name", "test");
        map.put("email", "test@test.com");

        MvcResult mvcResult = mockMvc.perform(put("/user")
                        .content(objectMapper.writeValueAsBytes(map))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andReturn();

        ResponsePutUser responsePutUser = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ResponsePutUser.class);
        System.out.println("responsePutUser = " + responsePutUser);

        getUser();

        mockMvc.perform(delete("/user")
                        .queryParam("id", responsePutUser.getId().toString())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(responsePutUser.getId()))
                .andExpect(jsonPath("$.msg").value("user deleted"))
                .andDo(print());

        getUser();
    }
}
