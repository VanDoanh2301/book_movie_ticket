package com.test.nmt.service.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.nmt.model.user.UserDTO;
import com.test.nmt.repository.UserRepository;

@Service
public class UserServiceIpm implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO getByID(Long id) {
        return new UserDTO().loadFromEntity(userRepository.findById(id).orElse(null));
    }
}
