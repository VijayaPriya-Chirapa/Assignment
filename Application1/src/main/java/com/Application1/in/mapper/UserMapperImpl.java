package com.Application1.in.mapper;

import com.Application1.in.DTO.UserDTO;
import com.Application1.in.EntityVO.UserVO;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-29T21:28:46+0530",
    comments = "version: 1.5.3.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.11.1.jar, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserVO userDTOToUser(UserDTO userDTO) {
        if ( userDTO == null ) {
            return null;
        }

        UserVO user = new UserVO();

        user.setId( userDTO.getId() );
        user.setName( userDTO.getName() );
        user.setEmail( userDTO.getEmail() );
        user.setPassword( userDTO.getPassword() );

        return user;
    }

    @Override
    public UserDTO userToUserDTO(UserVO user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setId( user.getId() );
        userDTO.setName( user.getName() );
        userDTO.setEmail( user.getEmail() );
        user.setPassword( userDTO.getPassword() );
        return userDTO;
    }

    @Override
    public List<UserDTO> usersToUserDTOs(List<UserVO> users) {
        if ( users == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( users.size() );
        for ( UserVO user : users ) {
            list.add( userToUserDTO( user ) );
        }

        return list;
    }
}
