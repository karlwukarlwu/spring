package ContextReader;

import bean.Monster;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Karl Rules!
 * 2023/10/31
 * now File Encoding is UTF-8
 */
public class ApplicationContext {
    //实际上这里应该是两个map
    //一个是单例池，一个是配置信息的池子 对应beanDefinitionMap
    private ConcurrentHashMap<String, Object> singletonObjects = new ConcurrentHashMap<>();

    public ApplicationContext(String fileName) {
        //1.得到类加载路径
        String path = this.getClass().getResource("/").getPath();
        System.out.println("path=" + path);
        SAXReader saxReader = new SAXReader();
        //2.读取beans.xml
        try {
            Document document = saxReader.read(new File(path + fileName));
            Element rootElement = document.getRootElement();
            //简单获取第一个标签
            Element bean = (Element) rootElement.elements("bean").get(0);
//            System.out.println("bean=" + bean);
            //获取bean的属性
            String id = bean.attributeValue("id");
            String clazz = bean.attributeValue("class");
//            拿到属性
//            System.out.println("id=" + id);
            System.out.println("clazz=" + clazz);
            List<Element> propertyList = bean.elements("property");
            //拿到属性下面的节点
//            System.out.println("propertyList=" + propertyList);
            //拿到节点的值value
            Integer monsterId = Integer.parseInt(propertyList.get(0).attributeValue("value"));
            String name = propertyList.get(1).attributeValue("value");
            String skill = propertyList.get(2).attributeValue("value");
//            System.out.println(monsterId);
//            System.out.println(name);
//            System.out.println(skill);
            //反射这里用的是相对路径
            Class<?> aClass = Class.forName(clazz);
            //这里o对象就是Monster对象
            Monster o = (Monster) aClass.newInstance();
//            用反射给方法赋值
//              这里的方法就是setter方法
//              Method[] declaredMethods = aClass.getDeclaredMethods();
//              for (Method declaredMethod : declaredMethods) {
//                     declaredMethod.invoke();
//              }
            //这里直接赋值(实际上是反射 ，)
            o.setMonsterId(monsterId);
            o.setName(name);
            o.setSkill(skill);
            //8. 将创建好的对象放入到singletonObjects

            // 反射拿到类路径 配置文件里面拿到属性
            // 然后这里造了一个对象 然后把这个对象放到了singletonObjects里面
            // 用定义的id当key 用o当value
            singletonObjects.put(id, o);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public Object getBean(String id) {

        return singletonObjects.get(id);
    }
}
