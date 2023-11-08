package work;

import org.junit.jupiter.api.Test;
import proxy.Bus;
import proxy.Car;
import proxy.Vehicle;

/**
 * Karl Rules!
 * 2023/11/3
 * now File Encoding is UTF-8
 */
public class forTest {
    @Test
    public void test1(){
        SmartAnimal smartAnimal= new A();
        Factory<SmartAnimal> smartAnimalFactory = new Factory<>(smartAnimal);
        SmartAnimal instance = smartAnimalFactory.getInstance();
        int sub = instance.getSub(1, 2);
        System.out.println(sub);
        int sum = instance.getSum(1, 2);
        System.out.println(sum);
    }
}
