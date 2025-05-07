package com.scm.SCM20.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.scm.SCM20.Helper.ResourcesNotFoundException;
import com.scm.SCM20.repositories.UserRepositroy;

@Service
public class SecurityCustomeDetailsService implements UserDetailsService
{

    @Autowired
    private UserRepositroy userRepositroy;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException 
    {
        System.out.println("user name using oauth:"+username);
        //Load our users here 
        return userRepositroy.findByEmail(username)
        .orElseThrow(()->new ResourcesNotFoundException("User not found"));
        
    }

}
