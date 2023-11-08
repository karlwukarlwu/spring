package pG;

import org.junit.jupiter.api.Test;

/**
 * Karl Rules!
 * 2023/11/3
 * now File Encoding is UTF-8
 */

public class t1 {
    @Test
    public void test1(){
        Vehicle v = new Car();
        v.run();
        Vehicle v2 = new Ship();
        v2.run();
    }
    @Test
    public void test2(){
        Cat cat = new Cat();
//        Tom t =(Tom) cat.getAge();

    }
}

interface Vehicle {
    void run();
}
class Car implements Vehicle{
    @Override
    public void run() {
        System.out.println("car run");
    }
}
class Ship implements Vehicle{
    @Override
    public void run() {
        System.out.println("ship run");
    }
}


class Cat{
    int age;

    public int getAge() {
        return age;
    }
}
class Tom{

}