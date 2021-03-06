package com.sda.auction.mapper;

import com.sda.auction.dto.UserDto;
import com.sda.auction.dto.UserHeaderDto;
import com.sda.auction.model.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public User map(UserDto userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());

        return user;
    }

    public UserHeaderDto map(User user) {
        UserHeaderDto userHeaderDto = new UserHeaderDto();
        userHeaderDto.setFirstName(user.getFirstName());
        return userHeaderDto;
    }
}
