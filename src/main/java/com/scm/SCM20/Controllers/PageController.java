package com.scm.SCM20.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/home")  
    public String homePage(Model model) {
        System.out.println("Hello, this is the first page for testing...");
        model.addAttribute("name", "Welcome to HOme pge ");
       model.addAttribute("gitrepo","github.com/Mohitkumar1315/");
        return "home"; 
    }
    @GetMapping("/about")
    public String aboutPage(Model model)
    {
        model.addAttribute("isLogin",false); 
        return "about";
    }
    @GetMapping("/service")
    public String servicePage()
    {
        return "Service";
    }
}
