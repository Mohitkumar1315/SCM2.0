package com.scm.SCM20.config;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.scm.SCM20.Entity.Providers;
import com.scm.SCM20.Entity.User;
import com.scm.SCM20.Helper.AppConstants;
import com.scm.SCM20.Helper.ResourcesNotFoundException;
import com.scm.SCM20.repositories.UserRepositroy;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class OAuthenicationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    UserRepositroy userRepositroy;
    Logger logger = LoggerFactory.getLogger(OAuthenicationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication) throws IOException, ServletException {
        logger.info("OAuthenticationSuccessHandler is working ");
        DefaultOAuth2User oauthauser = (DefaultOAuth2User) authentication.getPrincipal();
        logger.info(oauthauser.getName());
        oauthauser.getAttributes().forEach((key, value) -> {
            logger.info("{}=>{}", key, value);
        });
        logger.info(oauthauser.getAuthorities().toString());
        // user.getAuthorities().forEach(a -> logger.info("Authority => {}",
        // a.getAuthority()));
        // get inoformation using mail and save into database
        String email = oauthauser.getAttribute("email").toString();
        System.out.println("geted  usiing oauth" + email);
        String name = oauthauser.getAttribute("name").toString();
        String picture = oauthauser.getAttribute("picture").toString();
        // create user for saving to database
        Optional<User> optionalUser = userRepositroy.findByEmail(email);

        User saveuser;

        if (optionalUser.isPresent()) {
            saveuser = optionalUser.get();
            logger.info("User already exists: " + email);
        } else {
            saveuser = new User();
            saveuser.setEmail(email);
            saveuser.setName(name);
            saveuser.setProfilePic(picture);
            saveuser.setUserId(UUID.randomUUID().toString());
            saveuser.setProvider(Providers.GOOGLE);
            saveuser.setEnabled(true);
            saveuser.setEmailVerified(true);
            saveuser.setProviderUserId(oauthauser.getName());
            saveuser.setRoleList(List.of(AppConstants.ROLE_USER));
            saveuser.setAbout("This user is login using google");

            userRepositroy.save(saveuser);
            logger.info("New user saved: " + email);
        }

        new DefaultRedirectStrategy().sendRedirect(request, response, "/user/profile");
    }

}