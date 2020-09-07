package com.springtest.demo;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author xianghaochen
 * @date 2020/7/29 1:21
 */
@RestController
public class Hello {

    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public String hello() {

        return "Fuck SpringBoot";

    }

}
