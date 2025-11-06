package com.scm.SCM20.Controllers;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;
import com.scm.SCM20.Entity.User;
import com.scm.SCM20.Helper.Helper;
import com.scm.SCM20.services.UserService;

@ControllerAdvice
public class RootController {

    private Logger logger = org.slf4j.LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @ModelAttribute
    public void addLoggedInUserInformation(Model model, Authentication authentication) {
     if(authentication==null)
     {
        return;
     }System.out.println("Adding login user information to the model");
     String username=Helper.getEmailOfLoggedInUser(authentication);   
     User user=userService.getUserByMail(username);
     if(user==null)
     {
     model.addAttribute("loggedInUser", null);}
     else{
        model.addAttribute("loggedInUser", user);
     }

    }
}
