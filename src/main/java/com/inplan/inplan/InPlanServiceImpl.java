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
    public void putUser(User user) {
        userRepository.save(user);
    }

    @Override
    public List<User> getUserById(int id) {
        List<User> userList = new ArrayList<>();

        if (id <= 0) {
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
    public void deleteUserById(int id) {
        userRepository.deleteById(id);
    }

    @Override
    public void updateUserById(int id, User user) {
        Optional<User> selectedUser = userRepository.findById(id);
        if (selectedUser.isPresent()){
            user.setId(selectedUser.get().getId());
            userRepository.save(user);
        }
    }
}
