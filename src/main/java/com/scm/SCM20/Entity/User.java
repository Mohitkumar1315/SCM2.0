package com.scm.SCM20.Entity;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Table(name="users")//  This is use to change the table name
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor 
public class User 
{
    @Id
    private String userId;
    @Column(name="user_name", nullable = false)
    private String name;
    @Column(name="email",unique = true,nullable=false)
    private String email;
    private String password;
    //@Lob  ///it use for change string to text vale (where we can hold the big data)
    @Column(length = 1000)
    private String about;
    //@Lob
    @Column(length = 1000)
    private String profilePic;
    private String phoneNumber;
    
    private boolean enabled=false;
    private boolean emailVerified=false;
    private boolean phoneVerified=false;
    @Enumerated(EnumType.STRING)
    private Providers provider=Providers.SELF;
    private String providerUserId;
    //Aditionl information
    @OneToMany(mappedBy ="user", cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)    
    private List<Contacts> contacts=new ArrayList<>();

    
   
}
 