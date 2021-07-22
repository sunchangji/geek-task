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
 * 测试jdbc方式操作数据库
 * @Classname TestJdbcController
 * @Description TODO
 * @Date 2021/7/22 6:50 下午
 * @Created by sunchangji
 */
@RestController
@RequestMapping("/jdbc/user")
public class TestJdbcUserController {

    @Autowired
    private JdbcOperate jdbcOperate;

    @RequestMapping("add")
    public int addUser(Integer age,String username) throws SQLException {
        Asserts.notNull(age,"age不能为空");
        Asserts.notNull(username,"username不能为空");
        JdbcUser user = new JdbcUser();
        user.setUserName(username);
        user.setAge(age);
        return jdbcOperate.insert(user);
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
        return jdbcOperate.updateById(user);
    }

    @GetMapping("delete")
    public int delete(Long id) throws SQLException {
        Asserts.notNull(id,"id不能为空");
        return jdbcOperate.deleteById(id);
    }

    @GetMapping("findAll")
    public List<JdbcUser> findAll() throws SQLException {
        return jdbcOperate.findAll();
    }

    @GetMapping("/batchTrans")
    public String batchTrans() throws SQLException {
        jdbcOperate.batchTrans();
        return "完成";
    }
}
