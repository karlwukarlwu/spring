package aop;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Karl Rules!
 * 2023/11/4
 * now File Encoding is UTF-8
 */
@Component
@Scope("prototype")
public class Phone implements UsbInterface{

    public Phone() {
        System.out.println("phone constructor");
    }
    @Override
    public void work() {
        System.out.println("phone working");
    }
}
