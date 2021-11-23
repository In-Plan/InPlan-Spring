package com.inplan.inplan;

import com.inplan.inplan.dao.User;
import com.inplan.inplan.dto.InPlanResponseEntity;
import com.inplan.inplan.dto.ResponseGetUser;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController("v1")
@Data
public class InPlanController {

    private final InPlanService inPlanService;

    @PutMapping("/user")
    public InPlanResponseEntity putUser(@RequestBody User user) {
        inPlanService.putUser(user);
        return new InPlanResponseEntity("user created");
    }

    @GetMapping("/user")
    public ResponseGetUser getUser(@RequestParam(required = false) Long id) {
        return new ResponseGetUser(inPlanService.getUserById(id));
    }

    @PatchMapping("/user")
    public InPlanResponseEntity patchUser(@RequestParam(required = false) Long id, @RequestBody User user) {
        inPlanService.updateUserById(id, user);
        return new InPlanResponseEntity("user updated");
    }

    @DeleteMapping("/user")
    public InPlanResponseEntity deleteUser(@RequestParam(required = false) Long id) {
        inPlanService.deleteUserById(id);
        return new InPlanResponseEntity("user deleted");
    }
}
