package anno;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Karl Rules!
 * 2023/11/3
 * now File Encoding is UTF-8
 * 类似spring原生容器
 */

//关于拿到文件路径的一些说明
//    为什么要这么费劲
//    以课堂代码为例
//    我们配置的注解是这样的@ComponentScan("com.hspedu.spring.component")
//    但是我们拿到的路径是这样的D:\hspedu_spring\spring\out\production\spring\com\hspedu\spring\component
//    我们反射的拿到的类路径是在out目录下 ，结构是这样的C:\Users\23584\Desktop\hsp_daima\Spring\hspedu_spring\spring\out\production\spring\com\hspedu\spring\component\AA.class
//    而我们需要的路径是这样的com.hspedu.spring.component.AA
//    进行字符串的各种拼接替换
//    主体逻辑是
//    1. 拿到注解的value值
//    2. 将.替换成/
//    3. 通过类加载器拿到根目录，在通过注解拿到的扫描路径进行拼接(一个url对象，其实字符串也可以)
//        URL resource = this.configClass.getClassLoader().getResource(path);
//    4. 进行遍历，拿到所有class文件（以.class结尾）
//    5. 通过以上四步 拿到所有的class文件的名字
//    6. 再通过和最开始的注解拿到的路径进行拼接，拿到src下面的类的路径
//    7. 反射，拿到类，判断类是否有我们要的注解
//    8. 有注解的话，就创建对象，放入map中
//和xml那个版本的区别
//    那个直接配置好了每个类的路径 不用这么麻烦，找个主要是只写了要扫描的文件夹的注解 我们需要遍历文件夹找到既是class结尾，又有我们要的注解的文件

public class CusSpringApplicationContext {
    private Class configClass;
    //存放根据反射创建的对象
    private final ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();

    //构造器
    public CusSpringApplicationContext(Class configClass) {
        this.configClass = configClass;
        //这一步拿到类 从而拿到注解
//        System.out.println("CusSpringApplicationContext 构造器..."+configClass);
        //1. 先得到CustomerConfig类上的@ComponentScan注解
        Annotation annotation = configClass.getDeclaredAnnotation(ComponentScan.class);
        ComponentScan componentScan = (ComponentScan) annotation;

        //2. 得到注解的value属性值 注解告诉我们要扫描什么文件夹 但是注解一般是com.spring.component格式
        String path = componentScan.value();
        System.out.println("path=" + path);
        //这个path 要替换 因为真实开发中 一般都是com.spring.component 但是我们的文件夹是com/spring/component
        path = path.replace(".", "/");

//        这里需要拿到路径 并拿到对应要扫描的文件夹
//        为什么这里要path
//        以课堂代码为例
//            没有path返回的是file:/C:/Users/23584/Desktop/hsp_daima/Spring/hspedu_spring/spring/out/production/spring/
//            有path 返回的是file:/C:/Users/23584/Desktop/hsp_daima/Spring/hspedu_spring/spring/out/production/spring/com/hspedu/spring/component
        URL resource = this.configClass.getClassLoader().getResource(path);
        System.out.println("resource=" + resource);

        //拿到这个文件夹下的所有文件
        System.out.println("resource.getFile()=" + resource.getFile());
        File file = new File(resource.getFile());
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (File f : files) {
                //这里拿到class 文件
                System.out.println("f.getAbsolutePath()=" + f.getAbsolutePath());
                //这里我们只处理.class文件
                if (f.getAbsolutePath().endsWith(".class")) {
                    //1. 获取到类名
                    String className = f.getAbsolutePath().substring(f.getAbsolutePath().lastIndexOf("\\") + 1, f.getAbsolutePath().indexOf(".class"));
                    //拿到这些名字AA MyComponent UserAction UserDao UserService
                    System.out.println("className=" + className);
                    //重新生成符合要求的类路径名,因为原来的路径名在上面的步骤中被替换成了/
                    String classPath = path.replace("/", ".") + "." + className;
                    System.out.println("classPath=" + classPath);
                    //通过反射创建对象
                    try {
//                        这一步拿到正确的类路径进行反射
                        Class<?> aClass = Class.forName(classPath);
                        //判断是否有注解
                        if (aClass.isAnnotationPresent(Component.class) ||
                                aClass.isAnnotationPresent(Controller.class) ||
                                aClass.isAnnotationPresent(Service.class) ||
                                aClass.isAnnotationPresent(Repository.class)) {
                            if(aClass.isAnnotationPresent(Component.class)) {
                                //获取到该注解
                                Component component = aClass.getDeclaredAnnotation(Component.class);
                                String id = component.value();
                                if(!"".endsWith(id)) {
                                    className = id;//替换
                                }
                            }

                            //创建对象
                            Object o = aClass.newInstance();
                            //放入map中
                            //用一个spring的工具来优化这一步
                            singletonObjects.put(StringUtils.uncapitalize(className), o);
                        }

                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
            }
        }

    }

    //编写方法返回对容器中对象
    public Object getBean(String name) {
        return singletonObjects.get(name);
    }

}
