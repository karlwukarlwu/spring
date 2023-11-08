package Injection;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author 韩顺平
 * @version 1.0
 *
 * 自定义泛型类
 */

public class BaseService<T> {
    @Autowired
    private BaseDao<T> baseDao;
//    还是得用类型来找 用name会报错

    public void save() {
        baseDao.save();
    }
}
