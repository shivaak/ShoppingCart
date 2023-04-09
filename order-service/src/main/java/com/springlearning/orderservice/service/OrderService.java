package com.springlearning.orderservice.service;

import com.springlearning.orderservice.dto.OrderLineItemDto;
import com.springlearning.orderservice.dto.OrderRequest;
import com.springlearning.orderservice.dto.OrderResponse;
import com.springlearning.orderservice.model.Order;
import com.springlearning.orderservice.model.OrderLineItem;
import com.springlearning.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void createOrder(OrderRequest orderRequest){
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(mapToOrderLineItemList(orderRequest.getOrderLineItemsList())).build();
        orderRepository.save(order);
    }

    public List<OrderResponse> getAllOrders(){
       // return orderRepository.findAll();
        return null;
    }

    private List<OrderLineItemDto> mapToOrderLineItemDtoList(List<OrderLineItem> orderLineItemlist){
        return orderLineItemlist.stream()
                .map(orderLineItem -> mapToOrderLineItemDto(orderLineItem))
                .toList();

    }
    private OrderLineItemDto mapToOrderLineItemDto(OrderLineItem orderLineItem){
        OrderLineItemDto dto = OrderLineItemDto.builder()
                .price(orderLineItem.getPrice())
                .skuCode(orderLineItem.getSkuCode())
                .quantity(orderLineItem.getQuantity()).build();
        return dto;
    }

    private List<OrderLineItem> mapToOrderLineItemList(List<OrderLineItemDto> orderLineItemDtolist){
        return orderLineItemDtolist.stream()
                .map(orderLineItemDto -> mapToOrderLineItem(orderLineItemDto))
                .toList();

    }

    private OrderLineItem mapToOrderLineItem(OrderLineItemDto orderLineItemdto){
        OrderLineItem dto = OrderLineItem.builder()
                .price(orderLineItemdto.getPrice())
                .skuCode(orderLineItemdto.getSkuCode())
                .quantity(orderLineItemdto.getQuantity()).build();
        return dto;
    }

}
