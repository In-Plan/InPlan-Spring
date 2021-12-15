package com.inplan.inplan;

import com.inplan.inplan.dao.User;
import com.inplan.inplan.dto.*;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@Data
public class InPlanController {

    private final InPlanService inPlanService;

    @PutMapping("/user")
    public ResponsePutUser putUser(@RequestBody User user) {
        return new ResponsePutUser(inPlanService.putUser(user), "user created");
    }

    @GetMapping("/user")
    public ResponseGetUser getUser(@RequestParam(required = false) Long id) {
        return ResponseGetUser.builder().users(inPlanService.getUserById(id)).build();
    }

    @PatchMapping("/user")
    public ResponsePatchUser patchUser(@RequestParam(required = false) String uid, @RequestBody User user) {
        return new ResponsePatchUser(inPlanService.updateUserByUid(uid, user), "user updated");
    }

    @DeleteMapping("/user")
    public ResponseDeleteUser deleteUser(@RequestParam(required = false) String uid) {
        return new ResponseDeleteUser(inPlanService.deleteUserById(uid), "user deleted");
    }

    @GetMapping("/plan")
    public ResponseGetPlan getPlan(@RequestParam(required = false) Long id) {
        return ResponseGetPlan.builder().plans(inPlanService.getPlanById(id)).build();
    }
}
