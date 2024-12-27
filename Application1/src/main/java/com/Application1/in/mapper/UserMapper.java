package com.Application1.in.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import com.Application1.in.DTO.UserDTO;
import com.Application1.in.EntityVO.UserVO;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    UserVO userDTOToUser(UserDTO userDTO);
    UserDTO userToUserDTO(UserVO user);
    List<UserDTO> usersToUserDTOs(List<UserVO> users);
}
