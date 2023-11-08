package component;

import anno.Autowired;
import anno.Component;
import anno.Scope;
import processor.InitializingBean;

/**
 * Karl Rules!
 * 2023/11/4
 * now File Encoding is UTF-8
 */
@Component(value = "mService")
@Scope(value = "prototype")
public class MonsterService implements InitializingBean {
    @Autowired(value = "mDAO")
    private MonsterDAO monsterDAO;

    public void m1() {
        monsterDAO.hi();
    }

    //这个就是我们手动定义的init方法
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("MonsterService-afterPropertiesSet()");
    }
}

