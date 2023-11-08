package processor;

/**
 * Karl Rules!
 * 2023/11/5
 * now File Encoding is UTF-8
 */
// * 为什么要定义这个接口呢？
//        因为以前我们是在xml文件中指定初始化方法和销毁方法的，但是现在我们是在注解中指定的，那么我们就需要一个接口来规定初始化方法和销毁方法
//        底层实际上是通过这个接口实现的
// * 1. 我们根据原生Spring 定义了一个InitializingBean
// * 2. 该InitializingBean接口有一个方法void afterPropertiesSet() throws Exception;
// * 3. afterPropertiesSet() 在Bean的 setter后执行,即就是我们原来的初始化方法
// * 4. 当一个Bean实现这个接口后，就实现afterPropertiesSet() , 这个方法就是初始化方法
public interface InitializingBean {
    void afterPropertiesSet() throws Exception ;
}
