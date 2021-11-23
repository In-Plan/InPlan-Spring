package com.inplan.inplan;

import com.inplan.inplan.dao.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InPlanService {
    void putUser(User user);
    List<User> getUserById(Long id);
    void deleteUserById(Long id);
    void updateUserById(Long id, User user);

}
