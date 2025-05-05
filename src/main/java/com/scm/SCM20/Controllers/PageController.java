package com.scm.SCM20.Controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.scm.SCM20.Entity.User;
import com.scm.SCM20.Helper.Message;
import com.scm.SCM20.Helper.MessageType;
import com.scm.SCM20.forms.UserForm;
import com.scm.SCM20.services.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PageController {
    @Autowired
    UserService userService;
    
    @GetMapping("/")
    public String index()
    {
        return "redirect:/home";
    }

    public String getMethodName(@RequestParam String param) {
        return new String();
    }
    
    @GetMapping("/home")
    public String homePage(Model model) {
        System.out.println("Hello, this is the first page for testing...");
        model.addAttribute("name", "Welcome to HOme pge ");
        model.addAttribute("gitrepo", "github.com/Mohitkumar1315/");
        return "home";
    }

    @GetMapping("/about")
    public String aboutPage(Model model) {
        model.addAttribute("isLogin", false);
        return "about";
    }

    @GetMapping("/service")
    public String servicePage() {
        return "Service";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        UserForm userform = new UserForm();
        // userform.setName("Test..");//Test case the redering data from the controller
        // to HTML view
        model.addAttribute("userform", userform);
        return "register";
    }

    @RequestMapping(value = "/do-register", method = { RequestMethod.GET, RequestMethod.POST })
    public String userRegistration(@Valid @ModelAttribute("userform") UserForm userForm,
                                   BindingResult result,
                                   HttpSession session,
                                   Model model) {
    
        if (result.hasErrors()) {
            // Log validation errors
            result.getFieldErrors().forEach(error ->
                System.out.println("Field: " + error.getField() + " - " + error.getDefaultMessage())
            );
    
            return "register"; //
        }   
        User user = new User();
        user.setName(userForm.getName());
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setAbout(userForm.getAbout());
        user.setPhoneNumber(userForm.getPhoneNumber());
    
        userService.saveUser(user);
    
        Message msg = Message.builder()
                .content("Registration successful 😉")
                .messagetype(MessageType.green)
                .build();
        session.setAttribute("message", msg);
    
        return "redirect:/register";
    }
    
}
