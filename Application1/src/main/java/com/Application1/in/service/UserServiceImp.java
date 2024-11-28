package com.Application1.in.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Application1.in.BO.UserBO;
import com.Application1.in.DTO.UserDTO;
import com.Application1.in.Entity.User;

@Service
public class UserServiceImp implements UserService {

    @Autowired
    private UserBO userBO; 

    @Override
    public List<UserDTO> getAllUsers() {
     
        return userBO.getAllUsers(); 
    }

    @Override
    public UserDTO addUser(User user) {
        return userBO.addUser(user);
    }

}
