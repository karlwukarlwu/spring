package work;

/**
 * Karl Rules!
 * 2023/11/3
 * now File Encoding is UTF-8
 */
public class A implements SmartAnimal{
    @Override
    public int getSum(int a, int b) {
        return a+b;
    }

    @Override
    public int getSub(int a, int b) {
        return a-b;
    }
}
