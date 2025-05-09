package com.scm.SCM20.services;

import java.util.List;
import java.util.Optional;

import com.scm.SCM20.Entity.User;

public interface UserService 
{
    User saveUser(User user);
    Optional<User> getUserById(String id );
    Optional<User> updateUser(User user);
    void deleteUsesr(String userrid);
    boolean isUserExist(String userid);
    boolean isUserExistByEmail(String email);
    List<User> getAllUser();   
    User getUserByMail(String email);

}
