package com.inplan.inplan;

import com.inplan.inplan.dao.Plan;
import com.inplan.inplan.dao.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface InPlanService {
    Long putUser(User user);

    List<User> getUserById(Long id);

    String deleteUserById(String uid);

    String updateUserByUid(String uid, User user);

    List<Plan> getPlanById(Long id);

    Long putPlan(Plan plan);

    Long updatePlanById(Long id, Plan plan);

    Long deletePlanById(Long id);
}
