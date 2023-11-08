package com.hspedu.spring.dao;

/**
 * @author 韩顺平
 * @version 1.0
 * DAO对象
 */
public class MemberDAOImpl {
    private String name;
    //构造器...
    public MemberDAOImpl() {
        System.out.println("MemberDAOImpl() 构造器被执行...");
    }
    public void setName(String name) {
        System.out.println("MemberDAOImpl()的setName()=" + name);
        this.name = name;
    }
    public void init(){
        System.out.println("MemberDAOImpl init()...");
    }

    //方法
    public void add() {

        System.out.println("MemberDAOImpl add()方法被执行");
    }
}
