package com.example.webregister.service.user;

import com.example.webregister.model.User;
import com.example.webregister.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public List<User> searchAll() {

        List<User> users;
        users = userRepository.findAll();
        if (users.isEmpty()) {
            System.out.println("No users found");
        }
        return users;

    }

    public User searchById(Long searchId) {

        User searchUser;
        searchUser = userRepository.findByUserId(searchId);
        if (searchUser == null) {
            System.out.println("No user found");
        }
        return searchUser;

    }

    public void updateById(User user) {

        User existingUser;
        existingUser = userRepository.findByUserId(user.getUserId());
        existingUser.setUserName(user.getUserName());
        existingUser.setMail(user.getMail());
        existingUser.setPassword(user.getPassword());
        existingUser.setRole(user.getRole());
        userRepository.save(existingUser);

    }

    public void deleteById(Long searchId) {

        userRepository.deleteById(searchId);

    }

}
