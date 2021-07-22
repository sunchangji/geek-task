package com.suncj.geektask.week05.mustwork.jdbc;

import com.suncj.geektask.week05.beans.JdbcUser;
import org.apache.http.util.Asserts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

/**
 * @Classname HikariCPJdbcUserController
 * @Description TODO
 * @Date 2021/7/22 8:16 下午
 * @Created by sunchangji
 */
@RestController
@RequestMapping("/hikari/user")
public class HikariCPJdbcUserController {

    @Autowired
    private HikariCPJdbcOperate hikariCPJdbcOperate;

    @RequestMapping("add")
    public int addUser(Integer age,String username) throws SQLException {
        Asserts.notNull(age,"age不能为空");
        Asserts.notNull(username,"username不能为空");
        JdbcUser user = new JdbcUser();
        user.setUserName(username);
        user.setAge(age);
        return hikariCPJdbcOperate.insert(user);
    }


    @GetMapping("update")
    public int updateUser(Long id,String username,Integer age) throws SQLException {
        Asserts.notNull(id,"id不能为空");
        Asserts.notNull(username,"username不能为空");
        Asserts.notNull(username,"age不能为空");
        JdbcUser user = new JdbcUser();
        user.setId(id);
        user.setUserName(username);
        user.setAge(age);
        return hikariCPJdbcOperate.updateById(user);
    }

    @GetMapping("delete")
    public int delete(Long id) throws SQLException {
        Asserts.notNull(id,"id不能为空");
        return hikariCPJdbcOperate.deleteById(id);
    }

    @GetMapping("findAll")
    public List<JdbcUser> findAll() throws SQLException {
        return hikariCPJdbcOperate.findAll();
    }

    @GetMapping("/batchTrans")
    public String batchTrans() throws SQLException {
        hikariCPJdbcOperate.batchTrans();
        return "完成";
    }
}
