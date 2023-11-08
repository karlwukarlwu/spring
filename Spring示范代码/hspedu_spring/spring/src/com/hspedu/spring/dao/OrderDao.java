package com.hspedu.spring.dao;

/**
 * @author 韩顺平
 * @version 1.0
 * DAO类
 */
public class OrderDao {
    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    //方法。。。
    public void saveOrder() {
        System.out.println("保存 一个订单...");
    }

    @Override
    public String toString() {
        return "OrderDao{" +
                "username='" + username + '\'' +
                '}';
    }
}
