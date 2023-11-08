package ioc;

/**
 * Karl Rules!
 * 2023/11/5
 * now File Encoding is UTF-8
 */
//BeanDefinition 用于封装记录Bean的信息
// [1. scope 2 Bean对应的Class对象, 反射可以生对应的对象]
public class BeanDefinition {
    //是否单例
    private String scope;
    //    类属性 用于反射
    private Class clazz;

    public BeanDefinition() {
    }

    public BeanDefinition(String scope, Class clazz) {
        this.scope = scope;
        this.clazz = clazz;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public String toString() {
        return "BeanDefinition{" +
                "scope='" + scope + '\'' +
                ", clazz=" + clazz +
                '}';
    }
}
