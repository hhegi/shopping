package com.jiho.shopping.service;

import com.jiho.shopping.entity.User;
import com.jiho.shopping.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User findUser(Integer id){
        return userRepository.findById(id).get();
    }
}
