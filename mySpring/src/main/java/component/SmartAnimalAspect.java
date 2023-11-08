package component;

/**
 * Karl Rules!
 * 2023/11/6
 * now File Encoding is UTF-8
 * 一开始直接当作切面类
 */
public class SmartAnimalAspect {
    public static void showBefore() {
        System.out.println("SmartAnimalAspect-before");
    }

    public  static void showAfter() {
        System.out.println("SmartAnimalAspect-after");
    }
}
