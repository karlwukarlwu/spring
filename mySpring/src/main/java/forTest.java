import component.MonsterService;
import component.SmartAnimalAspect;
import component.SmartAnimalable;
import ioc.CusSpringApplicationContext;
import ioc.CusSpringConfig;
import org.junit.jupiter.api.Test;

/**
 * Karl Rules!
 * 2023/11/5
 * now File Encoding is UTF-8
 */
public class forTest {
    @Test
    public void t1(){
        CusSpringApplicationContext  Ioc = new CusSpringApplicationContext(CusSpringConfig.class);
        System.out.println("Ioc = " + Ioc);
//        Object mDAO = Ioc.getBean("mDAO");
//        System.out.println("mDAO = " + mDAO);
        Object mService = Ioc.getBean("mService");
        Object mService2 = Ioc.getBean("mService");
//        System.out.println("mService = " + mService);
//        System.out.println("mService2 = " + mService2);
//        MonsterService mService1 = (MonsterService) mService;
//        mService1.m1();
        SmartAnimalable smartDog = (SmartAnimalable)Ioc.getBean("smartDog");
        smartDog.getSum(1,2);
        smartDog.getSub(1,2);
        System.out.println("smartDog = " + smartDog.getClass());
    }
}
