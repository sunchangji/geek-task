package com.suncj.geektask.week05.beans;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Classname Klass
 * @Description TODO
 * @Date 2021/7/22 4:27 下午
 * @Created by sunchangji
 */
@Data
@ToString
public class Klass {
    /**
     * 班级里的学生
     */
    private List<Student> students;
}
