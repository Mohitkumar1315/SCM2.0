package com.scm.SCM20.Controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.scm.SCM20.forms.UserForm;
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
    @GetMapping("/register")
    public String registerPage(Model model)
    {
        UserForm userform =new UserForm();
        model.addAttribute ("user",userform);
        return "register";
    }
    @PostMapping(value ="/do-register")
    public String UserRegisteration() 
    {
        System.out.println("Testing the register page ...");
        return "redirect:/register";
    }
    
}
