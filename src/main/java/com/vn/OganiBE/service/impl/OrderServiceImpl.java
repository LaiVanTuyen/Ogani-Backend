package com.vn.OganiBE.service.impl;


import com.vn.OganiBE.entity.Order;
import com.vn.OganiBE.entity.OrderDetail;
import com.vn.OganiBE.entity.User;
import com.vn.OganiBE.exception.NotFoundException;
import com.vn.OganiBE.model.request.CreateOrderDetailRequest;
import com.vn.OganiBE.model.request.CreateOrderRequest;
import com.vn.OganiBE.repository.OrderDetailRepository;
import com.vn.OganiBE.repository.OrderRepository;
import com.vn.OganiBE.repository.UserRepository;
import com.vn.OganiBE.service.OrderService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    
    private final OrderRepository orderRepository;

    private final OrderDetailRepository orderDetailRepository;

    private final UserRepository userRepository;

    public OrderServiceImpl(OrderRepository orderRepository, OrderDetailRepository orderDetailRepository, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.orderDetailRepository = orderDetailRepository;
        this.userRepository = userRepository;
    }

    // tạo đơn hàng
    @Override
    public void placeOrder(CreateOrderRequest request) {
        // TODO Auto-generated method stub
        Order order = new Order();
        User user = userRepository.findByUsername(request.getUsername()).orElseThrow(() -> new NotFoundException("Not Found User With Username:" + request.getUsername()));
        order.setFirstname(request.getFirstname());
        order.setLastname(request.getLastname());
        order.setCountry(request.getCountry());
        order.setAddress(request.getAddress());
        order.setTown(request.getTown());
        order.setState(request.getState());
        order.setPostCode(request.getPostCode());
        order.setEmail(request.getEmail());
        order.setPhone(request.getPhone());
        order.setNote(request.getNote());   
        orderRepository.save(order);
        long totalPrice = 0;
        for(CreateOrderDetailRequest rq: request.getOrderDetails()){
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setName(rq.getName());
            orderDetail.setPrice(rq.getPrice());
            orderDetail.setQuantity(rq.getQuantity());
            orderDetail.setSubTotal(rq.getPrice()* rq.getQuantity());
            orderDetail.setOrder(order);
            totalPrice += orderDetail.getSubTotal();
            orderDetailRepository.save(orderDetail);
            
        }
        order.setTotalPrice(totalPrice);
        order.setUser(user);
        orderRepository.save(order);
    }

    @Override
    public List<Order> getList() {
        return orderRepository.findAll(Sort.by("id").descending());
    }

    @Override
    public List<Order> getOrderByUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Not Found User With Username:" + username));

        List<Order> orders = orderRepository.getOrderByUser(user.getId());
        return orders;  
    }

}
