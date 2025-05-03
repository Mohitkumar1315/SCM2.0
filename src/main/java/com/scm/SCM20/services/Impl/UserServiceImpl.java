package com.scm.SCM20.services.Impl;

import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.scm.SCM20.Entity.User;
import com.scm.SCM20.Helper.ResourcesNotFoundException;
import com.scm.SCM20.repositories.UserRepositroy;
import com.scm.SCM20.services.UserService;
@Service
public class UserServiceImpl implements UserService 
{

    @Autowired
    private UserRepositroy userRepositroy;//property injection 
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public User saveUser(User user) 
    {
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
        User user=userRepository.findById(userid).orElseThrow(()->new ResourcesNotFoundException("User not found"));
        userRepository.deleteById(user.getUserId());
        
    }

    @Override
    public boolean isUserExist(String userid) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isUserExist'");
    }

    @Override
    public boolean isUserExistByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'isUserExistByEmail'");
    }

    @Override
    public List<User> getAllUser() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllUser'");
    }

}
