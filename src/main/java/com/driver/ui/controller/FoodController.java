package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.io.repository.FoodRepository;
import com.driver.model.request.FoodDetailsRequestModel;
import com.driver.model.response.FoodDetailsResponse;
import com.driver.model.response.OperationStatusModel;
import com.driver.model.response.RequestOperationName;
import com.driver.model.response.RequestOperationStatus;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
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
@RequestMapping("/foods")
public class FoodController {

	@Autowired
	private FoodService foodService;

	@GetMapping(path="/{id}")
	public FoodDetailsResponse getFood(@PathVariable String id) throws Exception{

		FoodDto foodDto = foodService.getFoodById(id);
		FoodDetailsResponse foodDetailsResponse = new FoodDetailsResponse();
		foodDetailsResponse.setFoodCategory(foodDto.getFoodCategory());
		foodDetailsResponse.setFoodName(foodDto.getFoodName());
		foodDetailsResponse.setFoodPrice(foodDto.getFoodPrice());
		foodDetailsResponse.setFoodId(foodDto.getFoodId());

		return foodDetailsResponse;
	}

	@PostMapping("/create")
	public FoodDetailsResponse createFood(@RequestBody FoodDetailsRequestModel foodDetails) {

		FoodDto foodDto = new FoodDto();
		foodDto.setFoodCategory(foodDetails.getFoodCategory());
		foodDto.setFoodName(foodDetails.getFoodName());
		foodDto.setFoodPrice(foodDetails.getFoodPrice());

		FoodDto foodDto1 = foodService.createFood(foodDto);

		FoodDetailsResponse foodDetailsResponse = new FoodDetailsResponse();
		foodDetailsResponse.setFoodCategory(foodDto1.getFoodCategory());
		foodDetailsResponse.setFoodName(foodDto1.getFoodName());
		foodDetailsResponse.setFoodPrice(foodDto1.getFoodPrice());
		foodDetailsResponse.setFoodId(foodDto1.getFoodId());

		return foodDetailsResponse;
	}

	@PutMapping(path="/{id}")
	public FoodDetailsResponse updateFood(@PathVariable String id, @RequestBody FoodDetailsRequestModel foodDetails) throws Exception{

		FoodDto foodDto = new FoodDto();
		foodDto.setFoodCategory(foodDetails.getFoodCategory());
		foodDto.setFoodName(foodDetails.getFoodName());
		foodDto.setFoodPrice(foodDetails.getFoodPrice());

		FoodDto foodDto1 = foodService.updateFoodDetails(id,foodDto);

		FoodDetailsResponse foodDetailsResponse = new FoodDetailsResponse();
		foodDetailsResponse.setFoodCategory(foodDto1.getFoodCategory());
		foodDetailsResponse.setFoodName(foodDto1.getFoodName());
		foodDetailsResponse.setFoodPrice(foodDto1.getFoodPrice());
		foodDetailsResponse.setFoodId(foodDto1.getFoodId());

		return foodDetailsResponse;
	}

	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteFood(@PathVariable String id) throws Exception{

		foodService.deleteFoodItem(id);
		OperationStatusModel operationStatusModel = new OperationStatusModel();
		operationStatusModel.setOperationName(RequestOperationName.DELETE.toString());
		operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.toString());

		return operationStatusModel;
	}
	
	@GetMapping()
	public List<FoodDetailsResponse> getFoods() {

		List<FoodDto>foodDtoList = foodService.getFoods();
		List<FoodDetailsResponse>foodDetailsResponseList = new ArrayList<>();

		for(FoodDto foodDto1 : foodDtoList){
			FoodDetailsResponse foodDetailsResponse = new FoodDetailsResponse();
			foodDetailsResponse.setFoodCategory(foodDto1.getFoodCategory());
			foodDetailsResponse.setFoodName(foodDto1.getFoodName());
			foodDetailsResponse.setFoodPrice(foodDto1.getFoodPrice());
			foodDetailsResponse.setFoodId(foodDto1.getFoodId());

			foodDetailsResponseList.add(foodDetailsResponse);
		}

		return foodDetailsResponseList;
	}
}
