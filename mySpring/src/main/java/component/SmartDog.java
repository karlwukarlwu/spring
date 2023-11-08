package component;

import anno.Component;

/**
 * Karl Rules!
 * 2023/11/6
 * now File Encoding is UTF-8
 */

@Component(value = "smartDog")
public class SmartDog implements SmartAnimalable {
    public float getSum(float i, float j) {
        float res = i + j;
        System.out.println("SmartDog-getSum-res=" + res);
        return res;
    }

    public float getSub(float i, float j) {
        float res = i - j;
        System.out.println("SmartDog-getSub-res=" + res);
        return res;
    }
}

