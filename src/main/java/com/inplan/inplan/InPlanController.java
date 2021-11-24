package com.inplan.inplan;

import com.inplan.inplan.dao.User;
import com.inplan.inplan.dto.*;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("v1")
@Data
public class InPlanController {

    private final InPlanService inPlanService;

    @PutMapping("/user")
    public ResponsePutUser putUser(@RequestBody User user) {
        return new ResponsePutUser(inPlanService.putUser(user), "user created");
    }

    @GetMapping("/user")
    public ResponseGetUser getUser(@RequestParam(required = false) Long id) {
        return new ResponseGetUser(inPlanService.getUserById(id));
    }

    @PatchMapping("/user")
    public ResponsePatchUser patchUser(@RequestParam(required = false) Long id, @RequestBody User user) {
        return new ResponsePatchUser(inPlanService.updateUserById(id, user), "user updated");
    }

    @DeleteMapping("/user")
    public ResponseDeleteUser deleteUser(@RequestParam(required = false) Long id) {
        return new ResponseDeleteUser(inPlanService.deleteUserById(id), "user deleted");
    }
}
