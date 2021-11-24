package com.inplan.inplan;

import com.inplan.inplan.dao.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InPlanService {
    Long putUser(User user);
    List<User> getUserById(Long id);
    Long deleteUserById(Long id);
    Long updateUserById(Long id, User user);

}
