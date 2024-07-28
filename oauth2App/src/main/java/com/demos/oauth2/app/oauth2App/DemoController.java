package com.demos.oauth2.app.oauth2App;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DemoController {

    @GetMapping("/index")
    public String getIndexPage(){
        return "index.html";
    }
}
