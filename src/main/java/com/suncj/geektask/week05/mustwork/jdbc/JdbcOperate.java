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
 * 原生jdbc增删查操作
 * @Classname JdbcOperate
 * @Description TODO
 * @Date 2021/7/22 6:13 下午
 * @Created by sunchangji
 */
@Service
public class JdbcOperate {
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
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = jdbcConnection.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, jdbcUser.getUserName());
            ps.setInt(2, jdbcUser.getAge());
            return ps.executeUpdate();
        } finally {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }


    public int updateById(JdbcUser jdbcUser) throws SQLException {
        String sql = "update user set username = ?,age=? where id = ?";
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = jdbcConnection.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setString(1, jdbcUser.getUserName());
            ps.setInt(2, jdbcUser.getAge());
            ps.setLong(3, jdbcUser.getId());
            return ps.executeUpdate();
        } finally {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    public int deleteById(Long id) throws SQLException {
        String sql = "delete from user where id = ?";
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            connection = jdbcConnection.getConnection();
            ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            return ps.executeUpdate();
        } finally {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    /**
     * 查询全部
     * @return
     * @throws SQLException
     */
    public List<JdbcUser> findAll() throws SQLException {
        String sql = "select * from user";
        Connection connection = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            connection = jdbcConnection.getConnection();
            ps = connection.prepareStatement(sql);
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
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
            if (rs != null && !rs.isClosed()) {
                rs.close();
            }
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        }
    }

    /**
     * 使用事务，PrepareStatement方式，批处理方式
     * @return
     */
    public void batchTrans() throws SQLException {
        String sql = "insert into user(username,age) values(?,?)";
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = jdbcConnection.getConnection();
            conn.setAutoCommit(false);//将自动提交关闭
            ps = conn.prepareStatement(sql);
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
        } finally {
            if (ps != null && !ps.isClosed()) {
                ps.close();
            }
            if (conn != null && !conn.isClosed()) {
                conn.close();
            }
        }
    }

}
