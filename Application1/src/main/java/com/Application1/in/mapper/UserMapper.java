package com.Application1.in.mapper;

import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import com.Application1.in.DTO.UserDTO;
import com.Application1.in.EntityVO.UserVO;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    UserVO userDTOToUser(UserDTO userDTO);
    
    
    UserDTO userToUserDTO(UserVO user);
    
    //@Mapping(target = "password", ignore = true)
    List<UserDTO> usersToUserDTOs(List<UserVO> users);
}
