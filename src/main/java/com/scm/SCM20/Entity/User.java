package com.scm.SCM20.Entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity(name="user")
@Table(name="users")//  This is use to change the table name 
public class User 
{
    private String userId;
    private String name;
    private String password;
    private String about;
    private String profilePic;
    private String phoneNumber;
    
    private boolean enabled=false;
    private boolean emailVerified=false;
    private boolean phoneVerified=false;

    private Providers provider=Providers.SELF;
    private String providerUserId;
}
 