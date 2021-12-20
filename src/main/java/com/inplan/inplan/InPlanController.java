package com.inplan.inplan;

import com.inplan.inplan.dao.Plan;
import com.inplan.inplan.dao.User;
import com.inplan.inplan.dto.*;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1")
@Data
@Tag(name = "user")
public class InPlanController {

    private final InPlanService inPlanService;

    @Tag(name = "user")
    @PutMapping("/user")
    public ResponsePutUser putUser(@RequestBody User user) {
        return new ResponsePutUser(inPlanService.putUser(user), "user created");
    }

    @Tag(name = "user")
    @GetMapping("/user")
    public ResponseGetUser getUser(@RequestParam(required = false) Long id) {
        return ResponseGetUser.builder().users(inPlanService.getUserById(id)).build();
    }

    @Tag(name = "user")
    @PatchMapping("/user")
    public ResponsePatchUser patchUser(@RequestParam(required = false) String uid, @RequestBody User user) {
        return new ResponsePatchUser(inPlanService.updateUserByUid(uid, user), "user updated");
    }

    @Tag(name = "user")
    @DeleteMapping("/user")
    public ResponseDeleteUser deleteUser(@RequestParam(required = false) String uid) {
        return new ResponseDeleteUser(inPlanService.deleteUserById(uid), "user deleted");
    }

    @Tag(name = "plan")
    @GetMapping("/plan")
    public ResponseGetPlan getPlan(@RequestParam(required = false) Long id) {
        return ResponseGetPlan.builder().plans(inPlanService.getPlanById(id)).build();
    }

    @Tag(name = "plan")
    @PutMapping("/plan")
    public ResponsePutPlan putPlan(@RequestBody Plan plan) {
        return new ResponsePutPlan(inPlanService.putPlan(plan), "plan created");
    }

    @Tag(name = "plan")
    @PatchMapping("/plan")
    public ResponsePatchPlan patchPlan(@RequestParam Long id, @RequestBody Plan plan) {
        return new ResponsePatchPlan(inPlanService.updatePlanById(id, plan), "plan updated");
    }

    @Tag(name = "plan")
    @DeleteMapping("/plan")
    public ResponseDeletePlan deletePlan(@RequestParam Long id) {
        return new ResponseDeletePlan(inPlanService.deletePlanById(id), "plan deleted");
    }
}
