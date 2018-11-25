package com.andreas.dummyProjektboerse.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HtmlController {

    @GetMapping(path="/")
    public String index() {
        return "index";
    }
}
