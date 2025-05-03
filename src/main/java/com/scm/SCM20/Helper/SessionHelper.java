package com.scm.SCM20.Helper;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import jakarta.servlet.http.HttpSession;
@Component("sessionHelper")
public class SessionHelper 
{
    public String removeMessage() {
    System.out.println("Message removed successfully..");
    try {
        HttpSession session = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession();
        session.removeAttribute("message");
    } catch (Exception e) {
        e.printStackTrace();
    }
    return ""; // Return something to avoid Thymeleaf error
}

}
