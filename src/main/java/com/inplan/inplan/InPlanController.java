package com.inplan.inplan;

import com.inplan.inplan.dao.User;
import lombok.Data;
import org.springframework.web.bind.annotation.*;

@RestController("v1")
@Data
public class InPlanController {

    private final InPlanService inPlanService;

    @PutMapping("/user")
    public void putUser(@RequestBody User user) {
        inPlanService.putUser(user);
    }

    @GetMapping("/user")
    public void getUser(@RequestParam int id) {
        inPlanService.getUserById(id);
    }

    @PatchMapping("/user")
    public void patchUser(@RequestParam int id, @RequestBody User user) {
        inPlanService.updateUserById(id, user);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam int id) {
        inPlanService.deleteUserById(id);
    }
}
