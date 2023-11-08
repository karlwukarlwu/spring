package com.hspedu.spring.service;

import com.hspedu.spring.dao.OrderDao;

/**
 * @author 韩顺平
 * @version 1.0
 * Service类
 */
public class OrderService {
    //OrderDao属性
    private OrderDao orderDao;
    private OrderDao orderDao2;

    //getter
    public OrderDao getOrderDao() {
        return orderDao;
    }
    public OrderDao getOrderDao2() {
        return orderDao2;
    }
    //setter
    public void setOrderDao(OrderDao orderDao) {
        this.orderDao = orderDao;
    }
    public void setOrderDao2(OrderDao orderDao2) {
        this.orderDao2 = orderDao2;
    }
}
