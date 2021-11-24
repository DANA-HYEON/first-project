package com.dana.book.firstproject.web;

import com.dana.book.firstproject.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

//Json을 반환하는 컨트롤러로 만들어준다
//예전에는 @ResponseBody를 각 메소드마다 선언했던 것을 한번에 사용할 수 있게 해주는 것
@RestController
public class HelloController {
    //HTTP Method인 Get요청을 받을 수 있는 API를 만들어준다.
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name")String name, @RequestParam("amount")int amount){
        return new HelloResponseDto(name, amount);
    }
}


