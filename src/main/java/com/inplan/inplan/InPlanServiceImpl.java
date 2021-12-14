package com.inplan.inplan;

import com.inplan.inplan.dao.User;
import com.inplan.inplan.repository.UserRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Data
@Service
public class InPlanServiceImpl implements InPlanService{
    private final UserRepository userRepository;

    @Override
    public Long putUser(User user) {
        return userRepository.save(user).getMsrl();
    }

    @Override
    public List<User> getUserById(Long id) {
        List<User> userList = new ArrayList<>();

        if (id == null) {
            userList.addAll(userRepository.findAll());
        } else {
            Optional<User> user = userRepository.findById(id);
            if (user.isPresent()) {
                userList.add(user.get());
            }
        }
        return userList;
    }

    @Override
    public String deleteUserById(String uid) {
        userRepository.deleteByUid(uid);
        return uid;
    }

    @Override
    public String updateUserByUid(String uid, User user) {
        Optional<User> selectedUser = userRepository.findByUid(uid);
        if (selectedUser.isPresent()){
            user.setMsrl(selectedUser.get().getMsrl());
            userRepository.save(user);
        }
        return uid;
    }
}
