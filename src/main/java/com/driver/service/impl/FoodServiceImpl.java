package com.driver.service.impl;

import com.driver.io.entity.FoodEntity;
import com.driver.io.entity.OrderEntity;
import com.driver.io.repository.FoodRepository;
import com.driver.service.FoodService;
import com.driver.shared.dto.FoodDto;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class FoodServiceImpl implements FoodService {

    @Autowired
    private FoodRepository foodRepository;

    @Override
    public FoodDto createFood(FoodDto food) {
        FoodEntity foodEntity = new FoodEntity();
        foodEntity.setId(food.getId());
        foodEntity.setFoodCategory(food.getFoodCategory());
        foodEntity.setFoodId(food.getFoodId());
        foodEntity.setFoodName(food.getFoodName());
        foodEntity.setFoodPrice(food.getFoodPrice());

        foodRepository.save(foodEntity);

        return food;
    }

    @Override
    public FoodDto getFoodById(String foodId) throws Exception {
        FoodEntity foodEntity = foodRepository.findByFoodId(foodId);
        if(foodEntity == null)throw new Exception("Food id not present");
        FoodDto foodDto = new FoodDto();
        foodDto.setId(foodEntity.getId());
        foodDto.setFoodCategory(foodEntity.getFoodCategory());
        foodDto.setFoodId(foodEntity.getFoodId());
        foodDto.setFoodName(foodEntity.getFoodName());
        foodDto.setFoodPrice(foodEntity.getFoodPrice());

        return foodDto;
    }

    @Override
    public FoodDto updateFoodDetails(String foodId, FoodDto foodDetails) throws Exception {
        FoodEntity foodEntity = foodRepository.findByFoodId(foodId);
        if(foodEntity == null)throw new Exception("Food id not present");
        foodEntity.setId(foodDetails.getId());
        foodEntity.setFoodCategory(foodDetails.getFoodCategory());
        foodEntity.setFoodId(foodDetails.getFoodId());
        foodEntity.setFoodName(foodDetails.getFoodName());
        foodEntity.setFoodPrice(foodDetails.getFoodPrice());

        foodRepository.save(foodEntity);
        return foodDetails;
    }

    @Override
    public void deleteFoodItem(String id) throws Exception {
        FoodEntity foodEntity = foodRepository.findByFoodId(id);
        if(foodEntity == null)throw new Exception("Food id not present");
        foodRepository.delete(foodEntity);
    }

    @Override
    public List<FoodDto> getFoods() {
        List<FoodEntity> foodEntityList = (List<FoodEntity>) foodRepository.findAll();
        List<FoodDto> foodDtoList = new ArrayList<>();
        for (FoodEntity foodEntity : foodEntityList){
            FoodDto foodDto = new FoodDto();
            foodDto.setId(foodEntity.getId());
            foodDto.setFoodCategory(foodEntity.getFoodCategory());
            foodDto.setFoodId(foodEntity.getFoodId());
            foodDto.setFoodName(foodEntity.getFoodName());
            foodDto.setFoodPrice(foodEntity.getFoodPrice());

            foodDtoList.add(foodDto);
        }
        return foodDtoList;
    }
}