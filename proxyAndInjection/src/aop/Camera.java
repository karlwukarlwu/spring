package aop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Karl Rules!
 * 2023/11/4
 * now File Encoding is UTF-8
 */
@Component
public class Camera implements UsbInterface{
    @Value("1")
    public int a ;

    public int getA() {
        return a;
    }

    public void setA(int a) {
        System.out.println("camera setA");
        this.a = a;
    }

    public Camera() {
        System.out.println("camera constructor");
    }
    @Override
    public void work() {
        System.out.println("camera working");
    }
}
