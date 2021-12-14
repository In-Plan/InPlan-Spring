package com.inplan.inplan;

import com.inplan.inplan.dao.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InPlanService {
    Long putUser(User user);

    List<User> getUserById(Long id);

    String deleteUserById(String uid);

    String updateUserByUid(String uid, User user);

}
