package com.github.jvanheesch;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ExampleController {
    @RequestMapping("/example")
    String example() {
        return "Hello, world!";
    }
}
