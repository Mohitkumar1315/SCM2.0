package com.scm.SCM20.Controllers;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController 
{
    //UserDashboard 
    @RequestMapping("/dashboard")
    public String userDashBoard()
     {
        System.out.println("This is testing for user dashboard..");
        return "/user/dashboard";
    }
    //User profile
    @RequestMapping("/profile")
    public String userProfile()
     {
        System.out.println("This is testing for user dashboard..");
        return "/user/profile";
     }
    //User add contact
    //user edit
    //user search
    //user delete 
}
