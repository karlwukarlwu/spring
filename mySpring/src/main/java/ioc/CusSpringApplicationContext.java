package ioc;

import anno.Autowired;
import anno.Component;
import anno.ComponentScan;
import anno.Scope;
import processor.BeanPostProcessor;
import processor.InitializingBean;

import java.io.File;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Karl Rules!
 * 2023/11/4
 * now File Encoding is UTF-8
 */
//第六阶段 aop
public class CusSpringApplicationContext {
    private Class config;
    //beanDefinitionMap 存放BeanDefinition
//    beanDefinition两个属性
//    private String scope;//单例多例
//    private Class clazz;//类属性 用于反射
    private ConcurrentHashMap<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    //SingletonObjects 存放单例对象
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();

    private List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();


    public CusSpringApplicationContext(Class config) {
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
                Object o = createObject(beanDefinition,key);
//                放入map中
                singletonObjects.put(key, o);
            }
//            如果是多例 不创建对象
        }
    }

    //这里是扫描
    public void beanDefinitionMapScan(Class config) {

        this.config = config;
        Annotation annotation = config.getDeclaredAnnotation(ComponentScan.class);
        String path = ((ComponentScan) annotation).value();
        path = path.replace(".", "/");

        URL resource = CusSpringApplicationContext.class.getClassLoader().getResource(path);
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
//                  阶段五
                        //进行一个后置处理器的判断（实际上开发中是放到DefinitionMap中的）
//                        逻辑是一样的  他单独弄一个列表是为了好获取
                        if (BeanPostProcessor.class.isAssignableFrom(clazz)) {
                            //                   isAssignableFrom 判断类对象是继承或实现一个父类或接口
//                    这里是判断clazz类对象是否有BeanPostProcessor这个接口
                            BeanPostProcessor o = (BeanPostProcessor) clazz.getDeclaredConstructor().newInstance();
                            beanPostProcessors.add(o);
                            continue;
                        }

                        String value = clazz.getDeclaredAnnotation(Component.class).value();

                        if (value.equals("")) {
                            value = s.substring(0, 1).toLowerCase() + s.substring(1);

                        }
                        BeanDefinition beanDefinition = new BeanDefinition();
                        beanDefinition.setClazz(clazz);
                        //获取Scope注解的值,有就设置多例,没有就默认单例
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
//    bean的生命周期在这里开始

    //    这里是创建
    private Object createObject(BeanDefinition beanDefinition,String beanName) {
        Class clazz = beanDefinition.getClazz();
        try {
//          生命周期第一步 这里是完成构造器的初始化
            Object instance = clazz.getDeclaredConstructor().newInstance();
//
            Field[] declaredFields = clazz.getDeclaredFields();
//          生命周期第二步
//          这里是完成你所有属性的set
            for (Field declaredField : declaredFields) {
                if (declaredField.isAnnotationPresent(Autowired.class)) {

                    String value = declaredField.getAnnotation(Autowired.class).value();
//
                    Object bean = getBean(value);

                    declaredField.setAccessible(true);
//
                    declaredField.set(instance, bean);

                }

            }
//            后置处理器的前置方法 插入
//            阶段五 当完成set方法以后 开始执行后置处理器的前置方法
            for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
//                这一步可以拿到set好的bean 开始进行前置处理 如果加了业务逻辑 其实反不返回没啥意义 本来就是引用对象
                Object current = beanPostProcessor.postProcessBeforeInitialization(instance, beanName);
//                这里属于优化 不想优化拉倒
                if (current != null) {
                    instance = current;
                }
            }

//                阶段五 生命周期的第三步 自定义的init方法
//                当这里完成以后可以认为是set方法完成 开始进行init和后置处理器
//                面向接口编程 如果我们配置的这个bean实现了这个接口 就执行初始化 init方法
//                    不然就算是没有定义init方法 忽略即可
            if (instance instanceof InitializingBean) {
//                    instanceof 检测的是运行类型 而不是编译类型 所以这里可以检测接口
//                    因为instance 的编译类型是Object类型 所以要转化
                InitializingBean initializingBean = (InitializingBean) instance;
                initializingBean.afterPropertiesSet();
            }
//            阶段五  后置处理器的后置方法
            for (BeanPostProcessor beanPostProcessor : beanPostProcessors) {
//                这一步可以拿到init好的bean 开始进行后置处理 如果加了业务逻辑  就把新的bean返回回来
//                这里还真需要beanName 因为要用beanName来判断要不要代理对象
                Object current = beanPostProcessor.postProcessAfterInitialization(instance,  beanName);
                //                这里属于优化 不想优化拉倒
                if (current != null) {
                    instance = current;
                }
              }
            return instance;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public Object getBean(String beanName) {
        System.out.println("beanName=" + beanName);
        if (!beanDefinitionMap.containsKey(beanName)) {
            throw new NullPointerException();
        }

        BeanDefinition beanDefinition = beanDefinitionMap.get(beanName);
        if (beanDefinition.getScope().equals("singleton")) {
            return singletonObjects.get(beanName);
        } else if (beanDefinition.getScope().equals("prototype")) {
            return createObject(beanDefinition,beanName);
        }

        return null;
    }
}
