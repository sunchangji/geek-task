package com.suncj.geektask.week05.beans;

import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @Classname School
 * @Description TODO
 * @Date 2021/7/22 4:28 下午
 * @Created by sunchangji
 */
@Data
@ToString
public class School {
    /**
     * 学校包含的班级
     */
    private List<Klass> klasses;
}
