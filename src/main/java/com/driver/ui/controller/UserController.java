package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.io.entity.UserEntity;
import com.driver.model.request.UserDetailsRequestModel;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.model.response.UserResponse;
import com.driver.service.UserService;
import com.driver.service.impl.UserServiceImpl;
import com.driver.shared.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping(path = "/{id}")
	public UserResponse getUser(@PathVariable String id) throws Exception{

		try{

			UserDto userDto = userService.getUserByUserId(id);
			UserResponse userResponse = new UserResponse();
			userResponse.setUserId(userDto.getUserId());
			userResponse.setEmail(userDto.getEmail());
			userResponse.setFirstName(userDto.getFirstName());
			userResponse.setLastName(userDto.getLastName());

			return userResponse;
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}

		return null;

	}

	@PostMapping()
	public UserResponse createUser(@RequestBody UserDetailsRequestModel userDetails) throws Exception{
		UserDto userDto = new UserDto();
		userDto.setEmail(userDetails.getEmail());
		userDto.setFirstName(userDetails.getFirstName());
		userDto.setLastName(userDetails.getLastName());

		UserDto userDto1 = userService.createUser(userDto);

		UserResponse userResponse = new UserResponse();
		userResponse.setUserId(userDto1.getUserId());
		userResponse.setFirstName(userDto1.getFirstName());
		userResponse.setLastName(userDto1.getLastName());
		userResponse.setEmail(userDto1.getEmail());

		return userResponse;

	}

	@PutMapping(path = "/{id}")
	public UserResponse updateUser(@PathVariable String id, @RequestBody UserDetailsRequestModel userDetails) throws Exception{

		UserDto userDto = new UserDto();
		userDto.setFirstName(userDetails.getFirstName());
		userDto.setLastName(userDetails.getLastName());
		userDto.setEmail(userDetails.getEmail());

		UserDto userDto1 = userService.updateUser(id, userDto);

		UserResponse userResponse = new UserResponse();
		userResponse.setUserId(userDto1.getUserId());
		userResponse.setFirstName(userDto1.getFirstName());
		userResponse.setLastName(userDto1.getLastName());
		userResponse.setEmail(userDto1.getEmail());

		return userResponse;

	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteUser(@PathVariable String id) throws Exception{

		userService.deleteUser(id);
		OperationStatusModel operationStatusModel = new OperationStatusModel();
		operationStatusModel.setOperationName(RequestOperationName.DELETE.toString());
		operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.toString());

		return operationStatusModel;
	}
	
	@GetMapping()
	public List<UserResponse> getUsers(){

		List<UserResponse> userResponses = new ArrayList<>();
		List<UserDto> userDtoList = userService.getUsers();

		for (UserDto userDto1 : userDtoList){
			UserResponse userResponse = new UserResponse();
			userResponse.setUserId(userDto1.getUserId());
			userResponse.setFirstName(userDto1.getFirstName());
			userResponse.setLastName(userDto1.getLastName());
			userResponse.setEmail(userDto1.getEmail());

			userResponses.add(userResponse);
		}
		return userResponses;
	}
	
}
