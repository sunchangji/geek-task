package com.suncj.geektask.week05.beans;

import lombok.Data;
import lombok.ToString;

/**
 * @Classname 学生
 * @Description TODO
 * @Date 2021/7/22 4:25 下午
 * @Created by sunchangji
 */
@Data
@ToString
public class Student {
    /**
     * 学生唯一标识id
     */
    private Long id;

    /**
     * 学生名称
     */
    private String name;


}
