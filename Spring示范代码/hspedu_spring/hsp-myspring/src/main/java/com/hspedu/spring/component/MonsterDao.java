package com.hspedu.spring.component;

import com.hspedu.spring.annotation.Component;
import com.hspedu.spring.annotation.Scope;
import com.hspedu.spring.processor.InitializingBean;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Component(value = "monsterDao")
//@Scope(value = "prototype")
public class MonsterDao implements InitializingBean {

    public void hi() {
        System.out.println("MonsterDao-hi()");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MonsterDao 初始化方法被调用...");
    }
}
