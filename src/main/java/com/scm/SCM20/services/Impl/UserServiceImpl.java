package com.scm.SCM20.services.Impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.scm.SCM20.Entity.User;
import com.scm.SCM20.Helper.AppConstants;
import com.scm.SCM20.Helper.ResourcesNotFoundException;
import com.scm.SCM20.repositories.UserRepositroy;
import com.scm.SCM20.services.UserService;
@Service
public class UserServiceImpl implements UserService 
{
   @Autowired
   PasswordEncoder passwordEncoder;
    @Autowired
    private UserRepositroy userRepositroy;//property injection 
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) 
    {
         //UserId have to genrated 
         String userid=UUID.randomUUID().toString();
         user.setUserId(userid);
         //password encode
         user.setPassword(passwordEncoder.encode(user.getPassword()));
         //User Role
         user.setRoleList(List.of(AppConstants.ROLE_USER));
        return userRepositroy.save(user);    
    }

    @Override
    public Optional<User> getUserById(String id) {
       return userRepositroy.findById(id);
    }

    @Override
    public Optional<User> updateUser(User user) {
     User user1= userRepositroy.findById(user.getUserId()).orElseThrow(()->new ResourcesNotFoundException("User not found"));  
     //update db user here 
     user1.setName(user.getName());
     user1.setEmail(user.getEmail());
     user1.setAbout(user.getAbout());
     user1.setPhoneNumber(user.getPhoneNumber());
     user1.setPassword(user.getPassword());
     user1.setProfilePic(user.getProfilePic());
     user1.setEnabled(user.isEnabled());
     user1.setPhoneVerified(user.isPhoneVerified());
     user1.setEmailVerified(user.isEmailVerified());
     user1.setProvider(user.getProvider());
     User u=userRepositroy.save(user1);
     return Optional.ofNullable(u); 
    }
    @Override
    public void deleteUsesr(String userrid) {
      User u= userRepositroy.findById(userrid).orElseThrow(()->new ResourcesNotFoundException("User not found for deletetion")); 
        userRepositroy.delete(u);
    }

    @Override
    public boolean isUserExist(String userid) {
       User user=userRepositroy.findById(userid).orElseThrow(()->new ResourcesNotFoundException("User not found"));
       return user !=null?true :false;
    }

    @Override
    public boolean isUserExistByEmail(String email) {
       User user=userRepositroy.findByEmail(email).orElseThrow(()->new ResourcesNotFoundException("User not found with this mail");
        return user!=null?true:false;
    }

    @Override
    public List<User> getAllUser() {
       return userRepositroy.findAll();
    }
    
}
