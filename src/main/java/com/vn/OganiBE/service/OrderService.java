package com.vn.OganiBE.service;



import com.vn.OganiBE.entity.Order;
import com.vn.OganiBE.model.request.CreateOrderRequest;

import java.util.List;

public interface OrderService {

    // tạo đơn hàng
    void placeOrder(CreateOrderRequest request);

    // lấy danh sách đơn hàng
    List<Order> getList();

    // lấy đơn hàng theo username
    List<Order> getOrderByUser(String username);
}
