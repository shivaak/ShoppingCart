package com.springlearning.orderservice.service;

import com.springlearning.orderservice.dto.InventoryResponse;
import com.springlearning.orderservice.dto.OrderLineItemDto;
import com.springlearning.orderservice.dto.OrderRequest;
import com.springlearning.orderservice.dto.OrderResponse;
import com.springlearning.orderservice.model.Order;
import com.springlearning.orderservice.model.OrderLineItem;
import com.springlearning.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;



@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;

    private final WebClient webClient;

    public void createOrder(OrderRequest orderRequest){
        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(mapToOrderLineItemList(orderRequest.getOrderLineItemsList())).build();

        //Call inventory service and place order if it is in stock
        List<String> skuCodeList = order.getOrderLineItemsList().stream().map(item -> item.getSkuCode()).toList();

        InventoryResponse[] inventoryResponses = webClient.get()
                .uri( "http://localhost:8082/api/inventory",
                        uriBuilder -> uriBuilder
                        .queryParam("skuCodes", skuCodeList)
                        .build())
                        .retrieve()
                        .bodyToMono(InventoryResponse[].class)
                        .block(); // make it synchronous

        boolean allProductsAvailable = inventoryResponses.length > 0 ?
                Arrays.stream(inventoryResponses).allMatch(inventoryResponse -> inventoryResponse.getInStock()) : false;


        if(allProductsAvailable){
            orderRepository.save(order);
        }else{
            throw new IllegalArgumentException("Product not in stock");
        }
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
