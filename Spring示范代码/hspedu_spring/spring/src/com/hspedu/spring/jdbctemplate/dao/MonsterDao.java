package com.hspedu.spring.jdbctemplate.dao;

import com.hspedu.spring.bean.Monster;
import org.aspectj.lang.JoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author 韩顺平
 * @version 1.0
 */
@Repository //将MonsterDao 注入到spring容器
public class MonsterDao {

//    为什么dao层要用jdbcTemplate
//    因为dao层的目标就是直接注入 不保存数据
//    这里需要jdbcTemplte对象来协助进行对数据库的注入  ·

    //注入一个属性
    @Resource
    private JdbcTemplate jdbcTemplate;

    //完成保存任务
    public void save(Monster monster) {
        //组织sql
        String sql = "INSERT INTO monster VALUES(?,?,?)";
        int affected = jdbcTemplate.update
                (sql, monster.getMonsterId(), monster.getName(), monster.getSkill());
        System.out.println("affected= " + affected);
    }
}
