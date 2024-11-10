package com.senjay.control;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
// 标识是一个请求处理类
public class HelloController {
    @RequestMapping("/luna") // 请求路径（浏览器请求后就会运行这个方法）
    public String say()
    {
        return "Hello LunaFreya";
    }

}
