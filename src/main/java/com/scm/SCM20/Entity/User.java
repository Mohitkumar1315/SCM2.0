package com.scm.SCM20.Entity;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
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
public class User implements UserDetails
{
    @Id
    private String userId;
    @Column(name="user_name", nullable = false)
    private String name;
    @Column(name="email",unique = true,nullable=false)
    private String email;
    @Getter(value =AccessLevel.NONE)
    private String password;
    //@Lob  ///it use for change string to text vale (where we can hold the big data)
    @Column(length = 1000)
    private String about;
    //@Lob
    @Column(length = 1000)
    private String profilePic;
    private String phoneNumber;
    @Getter(value =AccessLevel.NONE)
    private boolean enabled=true;
    private boolean emailVerified=false;
    private boolean phoneVerified=false;
    @Enumerated(EnumType.STRING)
    private Providers provider=Providers.SELF;
    private String providerUserId;
    //Aditionl information
    @OneToMany(mappedBy ="user", cascade = CascadeType.ALL,fetch = FetchType.LAZY,orphanRemoval = true)    
    private List<Contacts> contacts=new ArrayList<>();
    
    @ElementCollection(fetch = FetchType.EAGER)
   private List<String> roleList=new ArrayList<String>();
   
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<SimpleGrantedAuthority> roles=roleList.stream().map(role->new SimpleGrantedAuthority(role)).toList();
        return roles; 
    }
    @Override
    public String getUsername() {
       return this.email;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public String getPassword() {
       return this.password;
    }   
}
 