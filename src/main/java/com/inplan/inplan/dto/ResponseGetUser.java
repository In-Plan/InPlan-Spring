package com.inplan.inplan.dto;

import com.inplan.inplan.dao.User;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ResponseGetUser {
    private final List<User> users;
}
