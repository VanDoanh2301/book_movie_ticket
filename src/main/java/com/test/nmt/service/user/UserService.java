package com.test.nmt.service.user;

import org.springframework.stereotype.Service;

import com.test.nmt.model.user.UserDTO;

@Service
public interface UserService {
    public UserDTO getByID(Long id);
}
