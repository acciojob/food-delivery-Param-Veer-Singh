package com.driver.service.impl;

import com.driver.io.entity.UserEntity;
import com.driver.io.repository.UserRepository;
import com.driver.service.UserService;
import com.driver.shared.dto.UserDto;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto user) throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(user.getEmail());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setUserId(user.getUserId());

        userRepository.save(userEntity);

        return user;

    }

    @Override
    public UserDto getUser(String email) throws Exception {
        UserEntity userEntity = userRepository.findByEmail(email);

        UserDto userDto = new UserDto();
        userDto.setEmail(userEntity.getEmail());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setUserId(userEntity.getUserId());
        userDto.setId(userDto.getId());

        return userDto;
    }

    @Override
    public UserDto getUserByUserId(String userId) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);

        UserDto userDto = new UserDto();
        userDto.setEmail(userEntity.getEmail());
        userDto.setFirstName(userEntity.getFirstName());
        userDto.setLastName(userEntity.getLastName());
        userDto.setUserId(userEntity.getUserId());
        userDto.setId(userDto.getId());

        return userDto;
    }

    @Override
    public UserDto updateUser(String userId, UserDto user) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);
        if(userEntity == null)throw new Exception("User not present");
        userEntity.setEmail(user.getEmail());
        userEntity.setFirstName(user.getFirstName());
        userEntity.setLastName(user.getLastName());
        userEntity.setUserId(user.getUserId());

        userRepository.save(userEntity);
        return user;
    }

    @Override
    public void deleteUser(String userId) throws Exception {
        UserEntity userEntity = userRepository.findByUserId(userId);
        userRepository.delete(userEntity);
    }

    @Override
    public List<UserDto> getUsers() {
        List<UserEntity> userEntityList = (List<UserEntity>) userRepository.findAll();
        List<UserDto> userDtoList = new ArrayList<>();

        for(UserEntity userEntity : userEntityList){
            UserDto userDto = new UserDto();
            userDto.setEmail(userEntity.getEmail());
            userDto.setFirstName(userEntity.getFirstName());
            userDto.setLastName(userEntity.getLastName());
            userDto.setUserId(userEntity.getUserId());
            userDto.setId(userDto.getId());

            userDtoList.add(userDto);
        }

        return userDtoList;
    }
}