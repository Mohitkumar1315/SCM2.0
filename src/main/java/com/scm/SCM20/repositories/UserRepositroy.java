package com.scm.SCM20.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.scm.SCM20.Entity.User;
@Repository
public interface UserRepositroy extends JpaRepository<User,String>
{
    //Custome query methods for the data intrection 
     Optional<User>  findByEmail(String email);
     Optional<User> findByEmailAndPassword(String email,String passoword);
}
