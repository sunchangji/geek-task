package com.suncj.geektask;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname TestWebController
 * @Description TODO
 * @Date 2021/7/22 4:38 下午
 * @Created by sunchangji
 */
@RestController
public class TestWebController {

    @GetMapping("")
    public String keepAvlive(){
        return "OK";
    }
}
