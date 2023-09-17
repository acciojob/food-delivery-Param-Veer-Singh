package com.driver.ui.controller;

import java.util.ArrayList;
import java.util.List;

import com.driver.model.request.OrderDetailsRequestModel;
import com.driver.model.response.*;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
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
@RequestMapping("/orders")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@GetMapping(path="/{id}")
	public OrderDetailsResponse getOrder(@PathVariable String id) throws Exception{

		OrderDto orderDto = orderService.getOrderById(id);
		OrderDetailsResponse orderDetailsResponse = new OrderDetailsResponse();
		orderDetailsResponse.setOrderId(orderDto.getOrderId());
		orderDetailsResponse.setCost(orderDto.getCost());
		orderDetailsResponse.setItems(orderDto.getItems());
		orderDetailsResponse.setUserId(orderDto.getUserId());
		orderDetailsResponse.setStatus(orderDto.isStatus());

		return orderDetailsResponse;
	}
	
	@PostMapping()
	public OrderDetailsResponse createOrder(@RequestBody OrderDetailsRequestModel order)  {

		OrderDto orderDto = new OrderDto();
		orderDto.setUserId(order.getUserId());
		orderDto.setCost(order.getCost());
		orderDto.setItems(order.getItems());


		try{

			OrderDto orderDto1 = orderService.getOrderById(order.getUserId());
			OrderDetailsResponse orderDetailsResponse = new OrderDetailsResponse();
			orderDetailsResponse.setOrderId(orderDto1.getOrderId());
			orderDetailsResponse.setCost(orderDto1.getCost());
			orderDetailsResponse.setItems(orderDto1.getItems());
			orderDetailsResponse.setUserId(orderDto1.getUserId());
			orderDetailsResponse.setStatus(orderDto1.isStatus());

			return orderDetailsResponse;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
	}
		
	@PutMapping(path="/{id}")
	public OrderDetailsResponse updateOrder(@PathVariable String id, @RequestBody OrderDetailsRequestModel order) throws Exception{

		OrderDto orderDto = new OrderDto();
		orderDto.setUserId(order.getUserId());
		orderDto.setCost(order.getCost());
		orderDto.setItems(order.getItems());


		try{

			OrderDto orderDto1 = orderService.updateOrderDetails(order.getUserId(),orderDto);
			OrderDetailsResponse orderDetailsResponse = new OrderDetailsResponse();
			orderDetailsResponse.setOrderId(orderDto1.getOrderId());
			orderDetailsResponse.setCost(orderDto1.getCost());
			orderDetailsResponse.setItems(orderDto1.getItems());
			orderDetailsResponse.setUserId(orderDto1.getUserId());
			orderDetailsResponse.setStatus(orderDto1.isStatus());

			return orderDetailsResponse;
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}

		return null;
	}
	
	@DeleteMapping(path = "/{id}")
	public OperationStatusModel deleteOrder(@PathVariable String id) throws Exception {
		orderService.deleteOrder(id);
		OperationStatusModel operationStatusModel = new OperationStatusModel();
		operationStatusModel.setOperationName(RequestOperationName.DELETE.toString());
		operationStatusModel.setOperationResult(RequestOperationStatus.SUCCESS.toString());
		return operationStatusModel;
	}
	
	@GetMapping()
	public List<OrderDetailsResponse> getOrders() {
		List<OrderDetailsResponse> orderDetailsResponses = new ArrayList<>();
		List<OrderDto> orderDtoList = orderService.getOrders();

		for (OrderDto orderDto : orderDtoList) {
			OrderDetailsResponse orderDetailsResponse = new OrderDetailsResponse();
			orderDetailsResponse.setOrderId(orderDto.getOrderId());
			orderDetailsResponse.setCost(orderDto.getCost());
			orderDetailsResponse.setItems(orderDto.getItems());
			orderDetailsResponse.setUserId(orderDto.getUserId());
			orderDetailsResponse.setStatus(orderDto.isStatus());

			orderDetailsResponses.add(orderDetailsResponse);
		}
		return orderDetailsResponses;
	}
}
