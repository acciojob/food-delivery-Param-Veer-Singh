package com.driver.service.impl;

import com.driver.io.entity.OrderEntity;
import com.driver.io.entity.UserEntity;
import com.driver.io.repository.OrderRepository;
import com.driver.service.OrderService;
import com.driver.shared.dto.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderDto createOrder(OrderDto order) {

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderId(order.getOrderId());
        orderEntity.setId(order.getId());
        orderEntity.setCost(order.getCost());
        orderEntity.setItems(order.getItems());
        orderEntity.setStatus(order.isStatus());
        orderEntity.setUserId(order.getUserId());

        orderRepository.save(orderEntity);

        return order;
    }

    @Override
    public OrderDto getOrderById(String orderId) throws Exception {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        if(orderEntity == null)throw new Exception("Order not present");
        OrderDto orderDto = new OrderDto();
        orderDto.setCost(orderEntity.getCost());
        orderDto.setId(orderEntity.getId());
        orderDto.setOrderId(orderEntity.getOrderId());
        orderDto.setItems(orderEntity.getItems());
        orderDto.setStatus(orderEntity.isStatus());
        orderDto.setUserId(orderEntity.getUserId());

        return orderDto;
    }

    @Override
    public OrderDto updateOrderDetails(String orderId, OrderDto order) throws Exception {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        if(orderEntity == null)throw new Exception("Order is not present");
        orderEntity.setCost(order.getCost());
        orderEntity.setId(order.getId());
        orderEntity.setOrderId(order.getOrderId());
        orderEntity.setItems(order.getItems());
        orderEntity.setStatus(order.isStatus());
        orderEntity.setUserId(order.getUserId());

        orderRepository.save(orderEntity);
        return order;
    }

    @Override
    public void deleteOrder(String orderId) throws Exception {
        OrderEntity orderEntity = orderRepository.findByOrderId(orderId);
        if(orderEntity == null)throw new Exception("Order is not present");
        orderRepository.delete(orderEntity);
    }

    @Override
    public List<OrderDto> getOrders() {
        List<OrderEntity> orderEntityList = (List<OrderEntity>) orderRepository.findAll();
        List<OrderDto> orderDtoList = new ArrayList<>();
        for (OrderEntity orderEntity : orderEntityList){
            OrderDto orderDto = new OrderDto();
            orderDto.setCost(orderEntity.getCost());
            orderDto.setId(orderEntity.getId());
            orderDto.setOrderId(orderEntity.getOrderId());
            orderDto.setItems(orderEntity.getItems());
            orderDto.setStatus(orderEntity.isStatus());
            orderDto.setUserId(orderEntity.getUserId());

            orderDtoList.add(orderDto);
        }
        return orderDtoList;
    }
}