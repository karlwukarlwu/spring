package com.hspedu.spring.bean;

/**
 * Karl Rules!
 * 2023/11/2
 * now File Encoding is UTF-8
 */
public class House2 {
    private String name;

    public House2() {
        System.out.println("House2() 构造器...");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("House2 setName()=" + name);
        this.name = name;
    }

    //老师解读
    //1. 这个方法是程序员来编写的.
    //2. 根据自己的业务逻辑来写.
    public void init() {
        System.out.println("House2 init()..");
    }

    //老师解读
    //1. 这个方法是程序员来编写的.
    //2. 根据自己的业务逻辑来写.
    //3. 名字也不是固定的
    public void destroy() {
        System.out.println("House2 destroy()..");
    }

    @Override
    public String toString() {
        return "House{" +
                "name='" + name + '\'' +
                '}';
    }
}
