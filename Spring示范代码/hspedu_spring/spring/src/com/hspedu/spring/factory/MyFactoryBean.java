package com.hspedu.spring.factory;

import com.hspedu.spring.bean.Monster;
import org.springframework.beans.factory.FactoryBean;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 韩顺平
 * @version 1.0
 * 是一个FactoryBean
 */
//泛型指定要创建的对象类型
public class MyFactoryBean implements FactoryBean<Monster> {

    //这个就是你配置时候，指定要获取的对象对应key
    private String key;
    private Map<String, Monster> monster_map;

    {   //代码块，完成初始化
        monster_map = new HashMap<>();
        monster_map.put("monster03", new Monster(300, "牛魔王~", "芭蕉扇~"));
        monster_map.put("monster04", new Monster(400, "狐狸精~", "美人计~"));
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public Monster getObject()  {
        return monster_map.get(key);
    }

    @Override
    public Class<?> getObjectType() {
        //你要返回的类型
        return Monster.class;
    }

    //是否单例 true:单例  false:多例 他这里没细讲
//    涉及到八股文了 这边跳过
    @Override
    public boolean isSingleton() {//这里指定是否返是单例
        return true;
    }
}
