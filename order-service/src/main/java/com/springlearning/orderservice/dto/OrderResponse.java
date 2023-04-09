package com.springlearning.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderResponse {

    private String orderId;
    private String orderNumber;
    private List<OrderLineItemDto> orderLineItemsList;
}
