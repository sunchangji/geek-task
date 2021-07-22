package com.suncj.geektask.week05.mustwork.jdbc;

import com.google.common.collect.Lists;
import com.suncj.geektask.week05.beans.JdbcUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @Classname HikariCPJdbcOperate
 * @Description TODO
 * @Date 2021/7/22 8:11 下午
 * @Created by sunchangji
 */
@Service
public class HikariCPJdbcOperate {

    @Autowired
    private JdbcConnection jdbcConnection;

    /**
     * 新增
     *
     * @param jdbcUser
     * @return
     * @throws SQLException
     */
    public int insert(JdbcUser jdbcUser) throws SQLException {
        String sql = "insert into user(username,age) values(?,?)";
        Connection connection = jdbcConnection.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, jdbcUser.getUserName());
            ps.setInt(2, jdbcUser.getAge());
            return ps.executeUpdate();
        }
    }


    public int updateById(JdbcUser jdbcUser) throws SQLException {
        String sql = "update user set username = ?,age=? where id = ?";
        Connection connection = jdbcConnection.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setString(1, jdbcUser.getUserName());
            ps.setInt(2, jdbcUser.getAge());
            ps.setLong(3, jdbcUser.getId());
            return ps.executeUpdate();
        }
    }

    public int deleteById(Long id) throws SQLException {
        String sql = "delete from user where id = ?";
        Connection connection = jdbcConnection.getConnection();
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setLong(1, id);
            return ps.executeUpdate();
        }
    }

    /**
     * 查询全部
     * @return
     * @throws SQLException
     */
    public List<JdbcUser> findAll() throws SQLException {
        String sql = "select * from user";
        Connection connection = jdbcConnection.getConnection();
        ResultSet rs = null;
        try (PreparedStatement ps = connection.prepareStatement(sql)){
            rs = ps.executeQuery();
            List<JdbcUser> users = Lists.newArrayList();
            while (rs.next()) {
                JdbcUser user = new JdbcUser();
                user.setId(rs.getLong("id"));
                user.setUserName(rs.getString("username"));
                user.setAge(rs.getInt("age"));
                users.add(user);
            }
            return users;
        } finally {
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
        }
    }

    /**
     * 使用事务，PrepareStatement方式，批处理方式
     * @return
     */
    public void batchTrans() throws SQLException {
        String sql = "insert into user(username,age) values(?,?)";
        Connection conn = jdbcConnection.getConnection();
        try (PreparedStatement ps = conn.prepareStatement(sql);){
            conn.setAutoCommit(false);//将自动提交关闭
            for (int i = 0; i < 10000; i++) {
                ps.setString(1, "测试批量插入" + i);
                ps.setInt(2, i);
                ps.addBatch();
                //每100条执行一次
                if (i > 0 && i % 100 == 0) {
                    ps.executeBatch();
                    conn.commit();
                }
            }
            ps.executeBatch();//执行最后剩下不够100条的
            conn.commit();//执行完后，手动提交事务
            conn.setAutoCommit(true);
        }
    }
}
