package ioc;

import anno.Autowired;
import anno.Component;
import anno.ComponentScan;
import anno.Scope;
import processor.InitializingBean;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.Enumeration;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Karl Rules!
 * 2023/11/4
 * now File Encoding is UTF-8
 */
//开始实现第四阶段 加上了属性注入
public class CusSpringApplicationContext02 {
    private Class config;
    //beanDefinitionMap 存放BeanDefinition
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    //SingletonObjects 存放单例对象
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();


    public CusSpringApplicationContext02(Class config) {
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


    public void beanDefinitionMapScan(Class config) {

        this.config = config;
        Annotation annotation = config.getDeclaredAnnotation(ComponentScan.class);
        String path = ((ComponentScan) annotation).value();
        path = path.replace(".", "/");

        URL resource = CusSpringApplicationContext02.class.getClassLoader().getResource(path);
//        System.out.println(resource);
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
//                        System.out.println(clazz.getName());
                        //放到DefinitionMap中
                        String value = clazz.getDeclaredAnnotation(Component.class).value();
//                        System.out.println("value = " + value);
//                      以防万一没有设置value
                        if (value.equals("")) {
                            value = s.substring(0, 1).toLowerCase() + s.substring(1);
//                            System.out.println("value = " + value);
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
//                        System.out.println(clazz.getName() + "没有注解");
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            } else {
                System.out.println(s + "不是class文件");
            }
        }


    }

//    第四步的逻辑 从拿到类以后 拿到类的属性 循环类的属性判断是否有需要的注解 拿到需要的注解 拿到注解的值 拿着这个值当作key从容器中取出对象
    private Object createObject(BeanDefinition beanDefinition) {
        Class clazz = beanDefinition.getClazz();
        try {

            Object instance = clazz.getDeclaredConstructor().newInstance();
//            第四步 这里实际上就是set方法
//                关于基本数据类型也可以用标签注入 省略去map里面找直接赋值即可
            //当我们拿到了对象后，开始判断是否属性有注解
            Field[] declaredFields = clazz.getDeclaredFields();
            //开始遍历注解
            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(Autowired.class)) {
                    //3.得到这个字段名字
                    String value = declaredField.getAnnotation(Autowired.class).value();
//          4.从容器中取出对象
//              什么意思 因为这里是对象有一个属性是对象
//              因此这里是从容器中取出另一个对象 付给这个对象的属性
                    Object bean = getBean(value);
                    //5.开始注入
//                    这里要爆破 因为属性是私有的
                    declaredField.setAccessible(true);
//                    这里是给对象的属性赋值
//                        第一个参数是对象 第二个参数是属性的值
                    declaredField.set(instance, bean);

                }
            }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Object getBean(String beanName) {
        if (!beanDefinitionMap.containsKey(beanName)) {
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
