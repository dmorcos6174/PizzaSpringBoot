package com.example.pizzaspringboot.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("helloworld")
public interface HelloWorldInterface {
    @GetMapping("/helloworld/hello/sayHello")
    public String sayHello();
}
