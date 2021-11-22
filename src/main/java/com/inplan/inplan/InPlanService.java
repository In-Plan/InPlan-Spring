package com.inplan.inplan;

import com.inplan.inplan.dao.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InPlanService {
    void putUser(User user);
    List<User> getUserById(int id);
    void deleteUserById(int id);
    void updateUserById(int id, User user);

}
