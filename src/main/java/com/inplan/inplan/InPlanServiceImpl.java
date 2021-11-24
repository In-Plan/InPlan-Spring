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
        return userRepository.save(user).getId();
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
    public Long deleteUserById(Long id) {
        userRepository.deleteById(id);
        return id;
    }

    @Override
    public Long updateUserById(Long id, User user) {
        Optional<User> selectedUser = userRepository.findById(id);
        if (selectedUser.isPresent()){
            user.setId(selectedUser.get().getId());
            userRepository.save(user);
        }
        return id;
    }
}
