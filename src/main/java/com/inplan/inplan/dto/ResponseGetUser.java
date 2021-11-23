package com.inplan.inplan.dto;

import com.inplan.inplan.dao.User;
import lombok.Data;

import java.util.List;

@Data
public class ResponseGetUser {
    private final List<User> users;
}
