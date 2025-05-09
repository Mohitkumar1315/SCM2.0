package com.scm.SCM20.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.scm.SCM20.services.UserService;

@Controller
@RequestMapping("/user")
public class UserController 
{
    
    @Autowired
    private UserService userService;
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
       
        return "/user/profile";
     }
    //User add contact
    //user edit
    //user search
    //user delete 
}
