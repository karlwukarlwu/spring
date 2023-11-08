package ioc;

import anno.Component;
import anno.ComponentScan;
import anno.Scope;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Karl Rules!
 * 2023/11/4
 * now File Encoding is UTF-8
 */
//实现了123阶段

//我这个很多路径配的太简略了 实际上应该考虑更多的情况
//    涉及到这个的两类方法
//        CusSpringApplicationContext  Ioc = new CusSpringApplicationContext(CusSpringConfig.class);
//        Object bean = context.getBean("userService");
public class CusSpringApplicationContext01 {
    private Class config;
    //beanDefinitionMap 存放BeanDefinition
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    //SingletonObjects 存放单例对象
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();


    public CusSpringApplicationContext01(Class config) {
        this.config = config;
        //扫描
        beanDefinitionMapScan(config);
//        拿到所有的key
        Enumeration<String> keys = beanDefinitionMap.keys();
//        对key开始判断
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            BeanDefinition beanDefinition = beanDefinitionMap.get(key);
//            如果是单例 创建对象
            if (beanDefinition.getScope().equals("singleton")) {
                Object o = createObject(beanDefinition);
//                放入map中
                singletonObjects.put(key, o);
            }
//            如果是多例 不创建对象
        }
    }

    //    第一二步 扫描文件夹 以及把扫描到的类放到DefinitionMap中
//   把之前写在方法里面的代码提取出来
    public void beanDefinitionMapScan(Class config) {

        this.config = config;
        Annotation annotation = config.getDeclaredAnnotation(ComponentScan.class);
        String path = ((ComponentScan) annotation).value();
        path = path.replace(".", "/");

        URL resource = CusSpringApplicationContext01.class.getClassLoader().getResource(path);
        System.out.println(resource);
        File file = new File(resource.getFile());
        String[] list = file.list();
        path = path.replace("/", ".");
        for (String s : list) {
            if (s.endsWith(".class")) {
                s = s.replace(".class", "");
                try {
//                    System.out.println(path + "." + s);
                    Class<?> clazz = Class.forName(path + "." + s);
                    if (clazz.isAnnotationPresent(Component.class)) {
                        System.out.println(clazz.getName());
                        //放到DefinitionMap中
                        String value = clazz.getDeclaredAnnotation(Component.class).value();
                        System.out.println("value = " + value);
//                      以防万一没有设置value
                        if (value.equals("")) {
                            value = s.substring(0, 1).toLowerCase() + s.substring(1);
                            System.out.println("value = " + value);
                        }
                        BeanDefinition beanDefinition = new BeanDefinition();
                        beanDefinition.setClazz(clazz);
                        //获取Scope注解的值,有就设置,没有就默认
                        if (clazz.isAnnotationPresent(Scope.class)) {
                            Scope declaredAnnotation = clazz.getDeclaredAnnotation(Scope.class);
                            beanDefinition.setScope(declaredAnnotation.value());
                        } else {
                            beanDefinition.setScope("singleton");
                        }
                        beanDefinitionMap.put(value, beanDefinition);

                    } else {
                        System.out.println(clazz.getName() + "没有注解");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println(s + "不是class文件");
            }
        }


    }

    //    第三步 通过beanDefinitionMap中的信息创建对象 这一步仅仅是创建对象，放入map的逻辑在上面
    private Object createObject(BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getClazz();
        try {
//            这样写比较灵活 因为我们可以选择调用哪个构造器
            Object o = clazz.getDeclaredConstructor().newInstance();
            return o;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //第三步 上面是放入 现在开始取出
    public Object getBean(String beanName) {
        if(!beanDefinitionMap.containsKey(beanName)) {
            throw new NullPointerException();
        }

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition.getScope().equals("singleton")) {
            return singletonObjects.get(beanName);
        } else if (beanDefinition.getScope().equals("prototype")) {
            return createObject(beanDefinition);
        }

        return null;
    }
}
