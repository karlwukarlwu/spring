package processor;

/**
 * Karl Rules!
 * 2023/11/5
 * now File Encoding is UTF-8
 */
public interface BeanPostProcessor {
    default Object postProcessBeforeInitialization(Object bean, String beanName) {
        return bean;
    }
    default Object postProcessAfterInitialization(Object bean, String beanName) {
        return bean;
    }

}
