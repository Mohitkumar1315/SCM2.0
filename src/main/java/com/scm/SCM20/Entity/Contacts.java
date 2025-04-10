package com.scm.SCM20.Entity;
import java.util.*;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
// import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Contacts 
{
    @Id
    private String id;
    private String  name;
    private String email;
    private String phoneNumbe;
    private String address;
    //@Lob
    @Column(length = 1000)
    private String description;
    private String picture;
    private boolean favorite;
    
    @OneToMany(mappedBy ="contact", cascade = CascadeType.ALL,fetch = FetchType.EAGER,orphanRemoval = true)    
    private List<SocialLink>socialLinks =new ArrayList();
    @ManyToOne
    private User user;
}
