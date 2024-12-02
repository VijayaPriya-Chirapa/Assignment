package com.Application1.in.BO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Application1.in.DTO.UserDTO;
import com.Application1.in.Entity.UserVO;
import com.Application1.in.Repository.UserRepository;
import com.Application1.in.mapper.UserMapper;

import java.util.List;
import java.util.Optional;

@Service
public class UserBOImp implements UserBO
{
    private static final Logger logger = LoggerFactory.getLogger(UserBOImp.class); // Correct logger


	@Autowired
    private UserRepository userRepository;

    @Override
    public UserVO addUser(UserVO uservo) {
        try {
            
            UserVO savedUser = userRepository.save(uservo);
            logger.info("User with name '{}' successfully added with ID '{}'", savedUser.getName(), savedUser.getId());
            return savedUser;  // Returning UserDTO after saving

        } catch (Exception e) {
            logger.error("Error adding user with name '{}'", uservo.getName(), e);
            throw e;
        }
    }

    @Override
	public List<UserVO> getAllUsers() {
		List<UserVO> users = userRepository.findAll();
	    return users;
	}

	@Override
	public Optional<UserVO> getUserById(Long id) {
		
		return userRepository.findById(id);
	}


}
