package com.scm.SCM20.forms;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class UserForm 
{
    @NotBlank(message = "user name is requiered")
    @Size(min =3, message = "Min 3 charactes is requieird" )
    private String name;
    @NotBlank(message ="Email is required..")
    @Email(message = "invalid email address")
    private String email;
    @Size(min=6,message = "Minimum 6 characters is allowed")
    private String password;
    @NotBlank(message = "About is required")
    private String about;
    @Size(min=8,max = 12,message = "invalid phone number")
    private String phoneNumber;
}
